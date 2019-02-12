package com.example.blablacartest.data

import android.content.Context
import com.example.blablacartest.data.api.BlablaApi
import com.example.blablacartest.data.model.Client
import com.example.blablacartest.data.model.ClientOutput
import com.example.blablacartest.data.model.DataModel
import com.example.blablacartest.domain.Repository
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

//TODO Changer les URL et les Headers
class BlablacarRepository(context: Context) : Repository {
    val sharedPreferencesPersistence = SharedPreferencesPersistence(context)

    companion object {
        const val TOKEN_KEY = "tokenKey"
        const val RESPONSE_FORMAT = "json"
        const val RESPONSE_LOCALE = "fr_FR"
        const val RESPONSE_MONEY_LOCALE = "EUR"
    }

    override fun getClientToken(client: Client): Call<ClientOutput> {
        val retrofit = Retrofit.Builder()
            .baseUrl(BlablaApi.BLABLA_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create<BlablaApi>()

        return service.getClientToken(client)
    }

    override fun saveToken(token: String) {
        sharedPreferencesPersistence.putString(TOKEN_KEY, token)
    }

    override fun search(departure: String, arrival: String): Call<DataModel> {
        val headers = buildHeaders()
        val retrofit = Retrofit.Builder()
            .baseUrl(BlablaApi.BLABLA_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create<BlablaApi>()

        return service.getTrips(headers, RESPONSE_FORMAT, RESPONSE_LOCALE, RESPONSE_MONEY_LOCALE, departure, arrival)
    }

    fun buildHeaders(): Map<String, String> {
        val token = sharedPreferencesPersistence.getString(TOKEN_KEY)
        val headers = LinkedHashMap<String, String>()
        headers.put("Accept", "application/json")
        headers.put("Accept-Language", "fr")
        headers.put("Application-Client", "Android")
        headers.put("Application-Version", "5.20.0-debug-33fbb08d3")
        headers.put("Authorization", "Bearer " + token)
        headers.put("Connection", "Keep-Alive")
        headers.put("X-Client", "ANDROID|5.25.0")
        headers.put("X-Currency", "EUR")
        headers.put("X-Locale", "fr_FR")
        return headers
    }
}
