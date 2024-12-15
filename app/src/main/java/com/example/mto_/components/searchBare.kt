package com.example.mto_.components

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview

/**
 *
 */
@Composable
fun searchBare(
    modifier: Modifier,
    textFieldValue: MutableState<String>, // a chaque fois ça rafraichi la variable de base
    searchBarePlaceholder: String,
    description: String,
    onValueChange:(it:String) -> Unit,
    onButton: () -> Unit){
    Row (
        modifier = modifier,
        horizontalArrangement = Arrangement.Center
    ){
        OutlinedTextField(

            value = textFieldValue.value,
            onValueChange = onValueChange,
            placeholder = { Text(searchBarePlaceholder) }
        )

        IconButton(onClick = onButton) {
            Icon(Icons.Filled.Search, contentDescription = description)
        }

    }
}
@Composable
@Preview
fun searchBarePreview(){
    val citySearch= remember { mutableStateOf("") }

    searchBare(modifier= Modifier.fillMaxSize(), textFieldValue = citySearch,searchBarePlaceholder = "Faites une recherche de météo",description = "Recherche de météo", onButton = {
        Log.d("ex","Result written : ${citySearch.value}")
    },
        onValueChange = {
           newVal-> citySearch.value= newVal
        }
        )

}