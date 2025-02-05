package ru.sicampus.bootcamp2025.ui.screens

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.maps.android.compose.MapProperties
import ru.sicampus.bootcamp2025.R

//@Preview(showBackground = true)
//@Composable
//fun PreviewScreen() {
//    MapScreen()
//}

@Composable
fun MapScreen(
    toMainScreen:()->Unit
) {
    var zoomLevel by remember { mutableFloatStateOf(10f) }//начальный зум
    val context = LocalContext.current
    var currentLocation by remember { mutableStateOf<LatLng?>(null) }
    val cameraPositionState = rememberCameraPositionState()

    val locationPermissionRequest = rememberLauncherForActivityResult( //здесь получаем разрешение на
                                                                      //использование геолокации
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        when {
            permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                getLastLocation(context) { location ->
                    currentLocation = location
                    location.let {
                        cameraPositionState.position = CameraPosition.fromLatLngZoom(it, 15f)
                    }
                }
            }
            permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                getLastLocation(context) { location ->
                    currentLocation = location
                    location.let {
                        cameraPositionState.position = CameraPosition.fromLatLngZoom(it, 12f)
                    }
                }
            }
        }
    }

    LaunchedEffect(Unit) { //здесь происходит проверка на имеющиееся разрешения
        val permissionsToRequest = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
        if (permissionsToRequest.all {
                ContextCompat.checkSelfPermission(context, it) == android.content.pm.PackageManager.PERMISSION_GRANTED
            }) {
            getLastLocation(context) { location ->
                currentLocation = location
                location.let {
                    cameraPositionState.position = CameraPosition.fromLatLngZoom(it, 15f)
                }
            }
        } else {
            locationPermissionRequest.launch(permissionsToRequest)
        }
    }
    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        properties = MapProperties(isMyLocationEnabled = true)
    ) {
        currentLocation?.let { location ->
            Marker(
                state = MarkerState(position = location),
                title = "Ваше местоположение"
            )
        }
        val center = LatLng(56.84609092607202, 60.65044826858674)
        Marker(
            state = MarkerState(position = center),
            title = "Добрые руки"
        )
        val center1 = LatLng(55.82357395654006, 37.3166217820105)
        Marker(
            state = MarkerState(position = center1),
            title = "Центр помощи детям"
        )
        val center2 = LatLng(59.93479819990618, 30.319239711127295)
        Marker(
            state = MarkerState(position = center2),
            title = "Благотворительный фонд 'Надежда' "
        )
        val center3 = LatLng(55.75309602852054, 49.21457228385668)
        Marker(
            state = MarkerState(position = center3),
            title = "Волонтерский центр 'Помощь' "
        )
        val center4 = LatLng(55.04297762282368, 82.91324048195861)
        Marker(
            state = MarkerState(position = center4),
            title = "Помощь без границ "
        )
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomStart
    ) {
        IconButton(
            onClick = { toMainScreen() },
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.BottomStart)
        ) {
            Icon(
                painter = painterResource(R.drawable.arrow),
                contentDescription = "Значок назад",
            )
        }
    }
    Column(
        modifier = Modifier
            //.align(Alignment.TopStart)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Button(onClick = {
            zoomLevel += 1f
            cameraPositionState.move(CameraUpdateFactory.zoomIn())
        }) {
            Text("Увеличить масштаб")
        }

        Button(onClick = {
            zoomLevel -= 1f
            cameraPositionState.move(CameraUpdateFactory.zoomOut())
        }) {
            Text("Уменьшить масштаб")
        }
    }
}

@SuppressLint("MissingPermission")
private fun getLastLocation(  //тут происходит получение последнего местоположения устройства
    context: Context,
    onSuccess: (LatLng) -> Unit
) {
    val fusedLocationClient: FusedLocationProviderClient = //переменная,в которую мы cохраняем объект,позволяющий работать с местоположением
        LocationServices.getFusedLocationProviderClient(context)

    fusedLocationClient.lastLocation
        .addOnSuccessListener { location ->
            location?.let {
                onSuccess(LatLng(it.latitude, it.longitude))//выполняется только после получения координат
            }
        }
        .addOnFailureListener { exception ->
            Log.e("Location", "Ошибка получения местоположения", exception)
        }
}

// Для работы с координатами
data class LatLng(
    val latitude: Double,
    val longitude: Double
)