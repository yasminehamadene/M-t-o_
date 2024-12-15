package com.example.mto_.datacontracts

/**
 * @brief weather data for a specific hour
 */
data class Hourly(
    /**
     * @brief associated date
     */
    val date: String,
    /**
     * @brief associated temperature value
     */
    val temperature: String,
    /**
     * @brief associated minimum temperature value
     */
    val temperatureMin: String,
    val temperatureMax: String,

    val rainMeasure: String,

    val cloudLowMeasure: String,

    val cloudHighMeasure: String,
)

data class MeteoDto(
    val cityName:String,

    val longitude:String,

    val latitude:String,

    val temperatureUnit: String,

    val windSpeedUnit: String,

    val cloudMeasureUnit: String,

    val rainMeasureUnit: String,

    val temperatureMeasures: MutableList<Hourly>
)