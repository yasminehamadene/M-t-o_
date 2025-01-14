package com.example.mto_.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mto_.datacontracts.MeteoDto
import com.example.mto_.components.favorit

/**
 * Composable fonction représentant l'écran des favoris avec une liste de villes favorites.
 */
@Composable
fun FavoritesView() {
    // Liste des villes favorites, stockée dans un état mutable
    val favoriteCities = remember {
        mutableStateListOf(
            MeteoDto(
                cityName = "Paris",
                longitude = "2.3522",
                latitude = "48.8566",
                rainMeasureUnit = "mm",
                temperatureUnit = "°C",
                cloudMeasureUnit = "%",
                windSpeedUnit = "Km/h",
                temperatureMeasures = mutableListOf()
            ),
            MeteoDto(
                cityName = "Corte",
                longitude = "122.083922",
                latitude = "37.4220922",
                rainMeasureUnit = "mm",
                temperatureUnit = "°C",
                cloudMeasureUnit = "%",
                windSpeedUnit = "Km/h",
                temperatureMeasures = mutableListOf()
            ),
            MeteoDto(
                cityName = "Ajaccio",
                longitude = "8.7430",
                latitude = "41.9190",
                rainMeasureUnit = "mm",
                temperatureUnit = "°C",
                cloudMeasureUnit = "%",
                windSpeedUnit = "Km/h",
                temperatureMeasures = mutableListOf()
            )
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "⭐ Vos favoris",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn {
            items(items = favoriteCities) { city ->
                CityItem(city, onRemove = {
                    favoriteCities.remove(city) // Supprimer la ville des favoris
                })
            }
        }
    }
}

@Composable
fun CityItem(city: MeteoDto, onRemove: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE0F7FA))
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = city.cityName,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Latitude: ${city.latitude}, Longitude: ${city.longitude}",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            // Bouton pour supprimer la ville des favoris
            Button(
                onClick = onRemove,
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
            ) {
                Text("Retirer des favoris", color = Color.White)
            }
        }
    }
}

@Composable
@Preview
fun FavoritesViewPreview() {
    FavoritesView()
}
