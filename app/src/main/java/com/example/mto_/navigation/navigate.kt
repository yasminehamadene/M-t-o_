package com.example.mto_.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mto_.views.Accueil
import com.example.mto_.views.FavoritesView
import com.example.mto_.datacontracts.MeteoDto
import com.example.mto_.datacontracts.Hourly
import com.example.meteo_app.views.MeteoDetailsView

data object Routes {
    const val HOME_PAGE = "home"
    const val FAVORITES = "favoritView"  // Vérifier que ce nom correspond bien partout
    const val METEO_DETAILS = "details"

}

@Composable
fun navigate(navController: NavHostController, modifier: Modifier = Modifier) {

    NavHost(
        navController = navController,
        startDestination = Routes.HOME_PAGE,
        modifier = modifier
    ) {
        composable(route = Routes.HOME_PAGE) {
            Accueil()
        }

        composable(route = Routes.FAVORITES) {
            FavoritesView()
        }

        composable(route = Routes.METEO_DETAILS) {
            MeteoDetailsView(
                modifier = Modifier,
                weatherDto = MeteoDto(
                    cityName = "Paris",
                    longitude = "2.3522",
                    latitude = "48.8566",
                    temperatureUnit = "°C",
                    windSpeedUnit = "km/h",
                    cloudMeasureUnit = "%",
                    rainMeasureUnit = "mm",
                    temperatureMeasures = mutableListOf(
                        Hourly(
                            date = "2024-12-17T06:00",
                            temperature = "8",
                            temperatureMin = "2",
                            temperatureMax = "13",
                            rainMeasure = "0",
                            cloudLowMeasure = "10%",
                            cloudHighMeasure = "20%"
                        )
                    )
                ),
                onAddInFavorites = { /* Action à définir */ }
            )
        }
    }
}
