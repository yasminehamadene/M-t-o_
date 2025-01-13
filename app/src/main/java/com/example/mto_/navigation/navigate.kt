package com.example.mto_.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mto_.components.favorit
import com.example.mto_.datacontracts.MeteoDto
import com.example.mto_.views.Accueil
import com.example.mto_.views.FavoritesView
import yahvya.meteo_app.views.MeteoDetailsView
import yahvya.meteo_app.views.MeteoDetailsViewPreview


data object Routes{
    const val HOME_PAGE= "home"
    const val FAVORITES= "favoritView"
    const val METEO_DETAILS= "details"

}


@Composable
fun navigate(navController: NavHostController, modifier: Modifier = Modifier) {
    lateinit var meteoDto: MeteoDto
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


    }
}