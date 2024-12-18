package yahvya.meteo_app.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mto_.datacontracts.Hourly
import com.example.mto_.datacontracts.MeteoDto
import java.time.LocalDateTime


/**
 * @brief Meteo details page
 * @param modifier modifier
 * @param weatherDto weather data
 * @param onAddInFavorites event when add in favorites is clicked
 */
@Composable
fun MeteoDetailsView(
    modifier: Modifier,
    weatherDto: MeteoDto,
    onAddInFavorites: () -> Unit
) {
    var expanded = remember { mutableStateOf(false) }
    var optionsMap = mutableMapOf<String, Hourly>()

    // Remplir la map avec les données horaires
    weatherDto.temperatureMeasures.forEach {
        optionsMap[it.date] = it
    }
    var defaultItemToShow:Hourly? = null

    // show the last hour
    val greaterKey = optionsMap.keys.maxWithOrNull(compareBy{ LocalDateTime.parse(it)})

    if(greaterKey !== null)
        defaultItemToShow = optionsMap[greaterKey]

    val hourlyNow = remember {mutableStateOf<Hourly?>(defaultItemToShow)}

    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {

        // Affichage de la ville et bouton pour ajouter aux favoris
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = weatherDto.cityName,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF00796B)
            )
            Button(
                onClick = onAddInFavorites,
                modifier = Modifier.background(Color(0xFF00796B))
            ) {
                Text("Ajouter aux favoris", color = Color.White)
            }
        }

        // Sélection de l'heure
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            ClickableText(
                text = AnnotatedString("Choisir l'heure"),
                onClick = { expanded.value = !expanded.value },
                style = TextStyle(fontSize = 20.sp, fontStyle = FontStyle.Italic, color = Color(0xFF00796B))
            )
            DropdownMenu(
                expanded = expanded.value,
                onDismissRequest = { expanded.value = false },
                modifier = Modifier.background(Color.White)
            ) {
                optionsMap.forEach {
                    DropdownMenuItem(
                        text = { Text(it.key) },
                        onClick = {
                            expanded.value = false
                            hourlyNow.value = it.value
                        },
                        modifier = Modifier.background(Color(0xFF00796B))
                    )
                }
            }
        }

        // Affichage des données météo détaillées de l'heure choisie
        hourlyNow.value?.let {
            Spacer(modifier = Modifier.height(16.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Température: ${it.temperature} ${weatherDto.temperatureUnit}", fontSize = 22.sp)
                Text("Température Max: ${it.temperatureMax} ${weatherDto.temperatureUnit}", fontSize = 20.sp)
                Text("Température Min: ${it.temperatureMin} ${weatherDto.temperatureUnit}", fontSize = 20.sp)
                Text("Pluie: ${it.rainMeasure} ${weatherDto.rainMeasureUnit}", fontSize = 20.sp)
                Text("Nuage: ${it.cloudHighMeasure} ${weatherDto.cloudMeasureUnit}", fontSize = 20.sp)
                Text("Nuage bas: ${it.cloudLowMeasure} ${weatherDto.cloudMeasureUnit}", fontSize = 20.sp)

            }
        }
    }
}

@Composable
@Preview
fun MeteoDetailsViewPreview() {
    MeteoDetailsView(
        modifier = Modifier,
        weatherDto = MeteoDto(
            cityName = "Corte",
            longitude = "-122.083922",
            latitude = "37.4220936",
            cloudMeasureUnit = "%",
            windSpeedUnit = "Km/h",
            rainMeasureUnit = "mm",
            temperatureUnit = "°C",
            temperatureMeasures = mutableListOf(
                Hourly(
                    date = "2024-12-17T07:00",
                    temperatureMax = "100",
                    temperatureMin = "0",
                    temperature = "30",
                    rainMeasure = "30",
                    cloudHighMeasure = "30",
                    cloudLowMeasure = "20",
                )
            )
        ),
        onAddInFavorites = {}
    )
}
