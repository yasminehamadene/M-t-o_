package com.example.mto_.apis.weatherApi


import com.example.mto_.datacontracts.MeteoDto
import retrofit2.http.GET
import retrofit2.http.Path

interface MeteoApiService {

    // Exemple d'appel API pour récupérer la météo d'une ville
    @GET("cities/{cityName}/weather")
    suspend fun getWeather(@Path("cityName") cityName: String): MeteoDto
}
