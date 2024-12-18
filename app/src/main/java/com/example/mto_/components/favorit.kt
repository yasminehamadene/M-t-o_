package com.example.mto_.components

import android.util.MutableBoolean
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.mto_.datacontracts.MeteoDto

@Composable
fun favorit(modifier : Modifier, meteoDto: MeteoDto,favoritBool: MutableState<Boolean>){
    Row (modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ){
        Text(meteoDto.cityName)
        IconButton(onClick ={ favoritBool.value =!favoritBool.value}) {
            if (favoritBool.value)
            Icon(imageVector = Icons.Filled.Favorite,
                contentDescription = "favorite",
                tint = Color.Red

                )
            else
            Icon(imageVector = Icons.Filled.Favorite,
                contentDescription = "favorite",
            )

        }
    }

}

@Composable
@Preview
fun favoritPreview(){
    val selecState = remember { mutableStateOf(true) }
    favorit(
        modifier = Modifier.fillMaxWidth(),
        meteoDto = MeteoDto(
            cityName = "bastia",
            longitude = "",
            latitude = "",
            rainMeasureUnit = "",
            temperatureUnit = "",
            cloudMeasureUnit = "",
            windSpeedUnit = "",
            temperatureMeasures = mutableListOf()

        ),
        favoritBool = selecState
    )
}