
package com.example.mto_.components
import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

/**
 * Composable fonction pour afficher un bouton permettant de récupérer la localisation de l'utilisateur.
 * @param modifier Un modificateur pour personnaliser la mise en page.
 * @param context Le contexte actuel (nécessaire pour accéder aux services de localisation).
 * @param onLocationGet Callback déclenché lorsque la localisation est récupérée avec succès.
 * @param onDeny Callback déclenché lorsque l'utilisateur refuse la permission.
 */
@Composable
fun locationUser(
    modifier: Modifier,
    context: Context,
    onLocationGet:(Location?) -> Unit,
    onDeny: () -> Unit
){
    val fusedLocationClient = remember { LocationServices.getFusedLocationProviderClient(context) }
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted)
            getLastKnownLocation(fusedLocationClient) { location -> onLocationGet(location)}
        else
            onDeny()
    }

    fun requestLocationPermission() {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
            getLastKnownLocation(fusedLocationClient) {location -> onLocationGet(location)}
        else
            permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }

    Button(modifier = modifier, onClick = {requestLocationPermission()}) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Me positioner")
            Icon(imageVector = Icons.Filled.LocationOn, contentDescription = "Position")
        }
    }
}

@SuppressLint("MissingPermission")
private fun getLastKnownLocation(
    fusedLocationClient: FusedLocationProviderClient,
    onLocationReceived: (Location?) -> Unit
) {
    fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
        onLocationReceived(location)
    }.addOnFailureListener {
        Log.e("locationUser", "error to find location: ${it.message}")
        onLocationReceived(null)
    }
}

@Composable
@Preview
fun locationUserPreview(){
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
}
