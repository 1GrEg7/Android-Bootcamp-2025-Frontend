package ru.sicampus.bootcamp2025.ui.screens

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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
import androidx.compose.foundation.layout.*
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import kotlin.math.sqrt
import ru.sicampus.bootcamp2025.R
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.Polyline

@Composable
fun MapScreen(
    toMainScreen: () -> Unit
) {

    val context = LocalContext.current
    var currentLocation by remember { mutableStateOf<LatLng?>(null) }
    var nearestCenter by remember { mutableStateOf<LatLng?>(null) }
    val cameraPositionState = rememberCameraPositionState()
    var selectedcenter by remember {mutableStateOf<LatLng?>(null)}

    val centers = listOf(
        LatLng(56.84609092607202, 60.65044826858674) , // Добрые руки
        LatLng(55.82357395654006, 37.3166217820105), // Центр помощи детям
        LatLng(59.93479819990618, 30.319239711127295), // Благотворительный фонд 'Надежда'
        LatLng(55.75309602852054, 49.21457228385668), // Волонтерский центр 'Помощь'
        LatLng(55.04297762282368, 82.91324048195861) // Помощь без границ
    )

    val center = listOf(
        LatLng(56.84609092607202, 60.65044826858674) to "Добрые руки",
        LatLng(55.82357395654006, 37.3166217820105) to "Центр помощи детям",
        LatLng(59.93479819990618, 30.319239711127295
        ) to "Благотворительный фонд 'Надежда'",
        LatLng(55.75309602852054, 49.21457228385668) to "Волонтерский центр 'Помощь'",
        LatLng(55.04297762282368, 82.91324048195861) to  "Помощь без границ"
    )
    val cent = listOf(
        Center("Центр 1", LatLng(55.7580, 37.6175)),
        Center("Центр 2", LatLng(55.7520, 37.6150)),
        Center("Центр 3", LatLng(55.7600, 37.6200))
    )

    val locationPermissionRequest = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        if (permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true) {
            getLastLocation(context) { location ->
                currentLocation = location
                nearestCenter = findNearestCenter(location, centers)
                cameraPositionState.position = CameraPosition.fromLatLngZoom(location, 15f)
            }
        }
    }

    LaunchedEffect(Unit) {
        locationPermissionRequest.launch(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION))
    }

    Column(modifier = Modifier.fillMaxSize()) {

        SearchWithDropdown { selectedLocation ->
            currentLocation = selectedLocation
            nearestCenter = findNearestCenter(selectedLocation, centers)
            cameraPositionState.position = CameraPosition.fromLatLngZoom(selectedLocation, 15f)
        }

        Box(modifier = Modifier.fillMaxSize()) {
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState,
                properties = MapProperties(isMyLocationEnabled = true)
            ) {
                currentLocation?.let {
                    Marker(
                        state = MarkerState(position = it),
                        title = "Ваше местоположение"
                    )
                }

                center.forEach { (latlng, name) ->
                    Marker(
                        onClick = {
                            selectedcenter = latlng
                                  true},
                        state = MarkerState(position = latlng),
                        title = name
                    )
                    if (currentLocation!= null && selectedcenter!=null){
                        Polyline(
                            points = listOf(currentLocation!!, selectedcenter!!),
                            color = Color.Green
                        )
                    }
                }
                if (currentLocation != null && nearestCenter != null) {
                    Polyline(
                        points = listOf(currentLocation!!, nearestCenter!!),
                        color = Color.Blue
                    )
                }

                Box(
                    modifier = Modifier.padding(16.dp)
                ) {
                    currentLocation?.let {
                        val centersWithDistances = findAllCentersWithDistances(it, cent)
                        centersWithDistances.forEach { distance ->
                            Text(text = distance)
                        }
                    }
                }



            }
            // Кнопка "Назад"
            IconButton(
                onClick = { toMainScreen() },
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.BottomStart)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.arrow),
                    contentDescription = "Назад"
                )
            }
        }
    }
}

@Composable
fun SearchWithDropdown(
    onLocationSelected: (LatLng) -> Unit
) {
    var textState by remember { mutableStateOf(TextFieldValue("")) }
    var expanded by remember { mutableStateOf(false) }

    val centers = listOf(
        LatLng(56.84609092607202, 60.65044826858674) to "Добрые руки",
        LatLng(55.82357395654006, 37.3166217820105) to "Центр помощи детям",
        LatLng(59.93479819990618, 30.319239711127295) to "Благотворительный фонд 'Надежда'",
        LatLng(55.75309602852054, 49.21457228385668) to "Волонтерский центр 'Помощь'",
        LatLng(55.04297762282368, 82.91324048195861) to "Помощь без границ"
    )

    val filteredCenters = centers.filter {
        it.second.contains(textState.text, ignoreCase = true)
    }

    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        OutlinedTextField(
            value = textState,
            onValueChange = { newText ->
                textState = newText
                expanded = newText.text.isNotEmpty()
            },
            placeholder = { Text("Найти волонтерский центр") },
            modifier = Modifier.fillMaxWidth()
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            filteredCenters.forEach { (point, name) ->
                DropdownMenuItem(
                    text = { Text(name) },
                    onClick = {
                        textState = TextFieldValue(name)
                        expanded = false
                        onLocationSelected(point)
                    }
                )
            }
        }
    }
}

@SuppressLint("MissingPermission")
private fun getLastLocation(
    context: Context,
    onSuccess: (LatLng) -> Unit
) {
    val fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    fusedLocationClient.lastLocation
        .addOnSuccessListener { location ->
            location?.let {
                onSuccess(LatLng(it.latitude, it.longitude))
            }
        }
        .addOnFailureListener { exception ->
            Log.e("Location", "Ошибка получения местоположения", exception)
        }
}

data class Center(val name: String, val location: LatLng)


@SuppressLint("DefaultLocale")
private fun findAllCentersWithDistances(currentLocation: LatLng, centers: List<Center>): List<String> {
    return centers.map { center ->
        val distance = calculateDistance(currentLocation, center.location)
        "${center.name} - ${String.format("%.2f", distance)} км"
    }
}

private fun findNearestCenter(currentLocation: LatLng, centers: List<LatLng>): LatLng? {
    return centers.minByOrNull { calculateDistance(currentLocation, it) }
}

private fun calculateDistance(point1: LatLng, point2: LatLng): Double {
    val dx = point1.latitude - point2.latitude
    val dy = point1.longitude - point2.longitude
    return sqrt(dx * dx + dy * dy)
}
