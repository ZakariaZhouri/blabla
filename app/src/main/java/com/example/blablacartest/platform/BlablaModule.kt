package com.example.blablacartest.platform

import android.content.Context
import android.os.Handler
import android.os.Looper
import com.example.blablacartest.data.BlablacarRepository
import com.example.blablacartest.domain.OutPutPort
import com.example.blablacartest.domain.BlablacarInteractor
import com.example.blablacartest.domain.Repository
import com.example.blablacartest.presentation.BlablacarPresenter
import com.example.blablacartest.presentation.TripsViewModel
import com.example.blablacartest.presentation.View
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class BlablaModule(context: Context, view: View) {
    var interactor: BlablacarInteractor
    val controller: Controller
    val repository: Repository
    val outPutPort: OutPutPort

    class UiThreadExecutor : Executor {
        private val handler = Handler(Looper.getMainLooper())

        override fun execute(runnable: Runnable) {
            handler.post(runnable)
        }
    }

    inner class BlablacarControllerDecorator(
        val executor: Executor,
        val controller: Controller
    ) : Controller {
        override fun getToken() {
            executor.execute { controller.getToken() }
        }

        override fun getTrips(departure: String, arrival: String) {
            executor.execute { controller.getTrips(departure, arrival) }

        }
    }

    inner class BlablacarViewDecorator(
        val executor: Executor,
        val view: View
    ) : View {
        override fun showError(errorMessage: String) {
            executor.execute {
                view.showError(errorMessage)
            }
        }

        override fun showTrips(viewModels: List<TripsViewModel>) {
            executor.execute {
                view.showTrips(viewModels)
            }
        }

    }

    private fun provideAboutController(controller: Controller): Controller =
        BlablacarControllerDecorator(Executors.newSingleThreadExecutor(), controller)

    private fun provideView(view: View): View =
        BlablacarViewDecorator(UiThreadExecutor(), view)

    init {
        outPutPort = BlablacarPresenter(context.resources, provideView(view))
        repository = BlablacarRepository(context)
        interactor = BlablacarInteractor(repository, outPutPort)
        controller = provideAboutController(BlablaCarController(interactor))
    }
}