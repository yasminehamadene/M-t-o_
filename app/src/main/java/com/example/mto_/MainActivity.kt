package com.example.mto_

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.background
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.mto_.navigation.navigate
import com.example.mto_.navigation.Routes
import com.example.mto_.ui.theme.Météo_Theme
import com.example.mto_.ui.theme.Purple // Importer la couleur Purple
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info // Icône Info


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Météo_Theme {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                Scaffold(
                    containerColor = Purple, // Applique la couleur violette pour le fond global
                    bottomBar = {
                        NavigationBar {
                            // Accueil
                            NavigationBarItem(
                                selected = currentRoute == Routes.HOME_PAGE,
                                onClick = {
                                    navController.navigate(Routes.HOME_PAGE) {
                                        popUpTo(Routes.HOME_PAGE) { inclusive = true }
                                    }
                                },
                                icon = {
                                    Icon(Icons.Filled.Home, contentDescription = "Accueil")
                                },
                                label = { Text("Accueil") }
                            )

                            // Favoris
                            NavigationBarItem(
                                selected = currentRoute == Routes.FAVORITES,
                                onClick = {
                                    navController.navigate(Routes.FAVORITES) {
                                        popUpTo(Routes.FAVORITES) { inclusive = true }
                                    }
                                },
                                icon = {
                                    Icon(Icons.Filled.Favorite, contentDescription = "Favoris")
                                },
                                label = { Text("Favoris") }
                            )

                            // Détails Météo
                            NavigationBarItem(
                                selected = currentRoute == Routes.METEO_DETAILS,
                                onClick = {
                                    navController.navigate(Routes.METEO_DETAILS) {
                                        popUpTo(Routes.METEO_DETAILS) { inclusive = true }
                                    }
                                },
                                icon = {
                                    Icon(Icons.Filled.Info, contentDescription = "Détails Météo")
                                },
                                label = { Text("Détails Meteo") }
                            )
                        }
                    }
                ) { paddingValues ->
                    navigate(
                        navController = navController,
                        modifier = Modifier
                            .padding(paddingValues)  // Ajoute la gestion des paddings
                            .background(Purple)      // Applique le fond violet à toute la page
                    )
                }
            }
        }
    }
}
