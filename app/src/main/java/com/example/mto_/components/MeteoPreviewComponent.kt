package com.example.mto_.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier

import com.example.mto_.datacontracts.MeteoDto

@Composable
fun MeteoPreviewComponent(modifier : Modifier, meteoDto: MeteoDto, onClicked : ()-> Unit){
    Row (modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        ){
        Text(meteoDto.cityName)
        IconButton(onClick = onClicked) {
            Icon(imageVector = Icons.Filled.Info, contentDescription = "info")
        }
    }

}
 @Composable
@Preview
 fun MeteoPreviewComponentPreview(){
     MeteoPreviewComponent(
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
         onClicked = {}
     )
 }