package com.example.mto_.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.mto_.datacontracts.MeteoDto
import androidx.compose.runtime.mutableStateOf


/**
 * Composant pour afficher un élément de favori avec une icône de cœur.
 * @param modifier Modificateur à appliquer au composant.
 * @param meteoDto Objet contenant les informations sur la ville et la météo.
 * @param favoritBool État représentant si la ville est dans les favoris ou non.
 * @param onRemoveFromFavorites Fonction de suppression du favori.
 */
@Composable
fun favorit(
    modifier: Modifier,
    meteoDto: MeteoDto,
    favoritBool: MutableState<Boolean>,
    onRemoveFromFavorites: () -> Unit // Fonction de suppression à passer ici
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(meteoDto.cityName)

        // Icône pour ajouter/enlever des favoris
        IconButton(onClick = {
            if (favoritBool.value) {
                onRemoveFromFavorites() // Appel de la fonction de suppression
            }
            favoritBool.value = !favoritBool.value // Changement d'état du favori
        }) {
            Icon(
                imageVector = Icons.Filled.Favorite,
                contentDescription = "favorite",
                tint = if (favoritBool.value) Color.Red else Color.Gray // Icône rouge si favori
            )
        }
    }
}

@Composable
@Preview
fun favoritPreview() {
    val selecState = remember { mutableStateOf(true) }

    favorit(
        modifier = Modifier.fillMaxWidth(),
        meteoDto = MeteoDto(
            cityName = "Bastia",
            longitude = "",
            latitude = "",
            rainMeasureUnit = "",
            temperatureUnit = "",
            cloudMeasureUnit = "",
            windSpeedUnit = "",
            temperatureMeasures = mutableListOf()
        ),
        favoritBool = selecState,
        onRemoveFromFavorites = { /* Implémenter la suppression ici si nécessaire */ }
    )
}