package com.example.mto_.views

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mto_.components.MeteoPreviewComponent
import com.example.mto_.components.favorit
import com.example.mto_.components.searchBare
import com.example.mto_.datacontracts.MeteoDto
import com.example.mto_.components.locationUser

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Accueil() {
    val research = remember { mutableStateOf("") }
    val proposition = remember { mutableStateListOf<MeteoDto>() }
    val favorites = remember { mutableStateListOf<MeteoDto>() }

    // Exemple de données pour les propositions et les favoris
    proposition.add(
        MeteoDto(
            cityName = "Corte",
            longitude = "122.083922",
            latitude = "37.4220922",
            rainMeasureUnit = "mm",
            temperatureUnit = "C",
            cloudMeasureUnit = "%",
            windSpeedUnit = "Km/h",
            temperatureMeasures = mutableListOf()
        )
    )
    favorites.add(
        MeteoDto(
            cityName = "Ajaccio",
            longitude = "122.083922",
            latitude = "37.4220922",
            rainMeasureUnit = "mm",
            temperatureUnit = "C",
            cloudMeasureUnit = "%",
            windSpeedUnit = "Km/h",
            temperatureMeasures = mutableListOf()
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5)) // Couleur douce pour l'arrière-plan
            .padding(16.dp)
    ) {
        // Barre de recherche
        searchBare(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            textFieldValue = research,
            searchBarePlaceholder = "Saisir le nom de la ville",
            description = "Recherche météo",
            onButton = {},
            onValueChange = { newVal -> research.value = newVal }
        )
        locationUser(
            modifier = Modifier,
            context = LocalContext.current,
            onLocationGet = {location ->
                if(location != null){
                    Log.d("info","Longitude : ${location.longitude} - Latitude : ${location.latitude}")
                }
            },
            onDeny = { Log.d("info","Permission denied")
            }
        )


        // Résultats de recherche
        if (proposition.isNotEmpty()) {
            Text(
                text = "🌍 Résultats pour : ${research.value}",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF00796B), // Couleur verte
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                textAlign = TextAlign.Start
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                items(items = proposition) { item ->
                    MeteoPreviewComponent(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        meteoDto = item,
                        onClicked = { /* Action à définir */ }
                    )
                }
            }
        }

        // Favoris
        if (favorites.isNotEmpty()) {
            Text(
                text = "⭐ Vos villes favorites",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFFFA726), // Couleur orange
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                textAlign = TextAlign.Start
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                items(items = favorites) { item ->
                    val fav = remember { mutableStateOf(true) }
                    favorit(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        meteoDto = item,
                        favoritBool = fav
                    )
                }
            }
        } else {
            // Message si aucun favori
            Text(
                text = "Vous n'avez pas encore ajouté de favoris.",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF78909C), // Couleur grise
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
@Preview
fun AccueilPreview() {
    Accueil()
}
