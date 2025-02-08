package ru.sicampus.bootcamp2025.ui.screens
import android.content.Context
import android.Manifest
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import coil.compose.rememberAsyncImagePainter
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import ru.sicampus.bootcamp2025.R
import ru.sicampus.bootcamp2025.ui.viewModels.EditProfileViewModel
import android.net.Uri
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.MutableState
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.FileProvider
import java.io.File

@Preview(showBackground = true)
@Composable
fun PreviewEditScreen(){
   EditProfileScreen(
       onSave={},
       toProfileScreen = {},
       viewModel = EditProfileViewModel()
   )
}

@Composable
fun EditProfileScreen(
    onSave: () -> Unit,
    toProfileScreen: () -> Unit,
    viewModel: EditProfileViewModel
) {
    var isImage = remember { mutableStateOf(false) }
    var imageUri = remember { mutableStateOf<Uri?>(null) }
    val context = LocalContext.current

    val cameraPermissionRequest = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val cameraPermissionGranted = permissions[Manifest.permission.CAMERA] == true
        val writeStoragePermissionGranted = permissions[Manifest.permission.WRITE_EXTERNAL_STORAGE] == true
        if (cameraPermissionGranted && writeStoragePermissionGranted) {
            isImage.value = true
        } else {
            Toast.makeText(context, "Необходимы разрешения на использование камеры", Toast.LENGTH_SHORT).show()
        }
    }
    fun onAddPhotoClick() {
        cameraPermissionRequest.launch(arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE))
    }

    if (isImage.value) {
        CameraScreen(onPhotoTaken = { uri ->
            imageUri.value = uri
            isImage.value = false
        })
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize().weight(2f).background(Color(0xFFC37B5C))) {
            Row(modifier = Modifier.fillMaxSize().weight(2f).padding(top = 30.dp)) {
                Icon(
                    painter = painterResource(R.drawable.arrow),
                    contentDescription = "",
                    modifier = Modifier.weight(1f).wrapContentSize().clickable { toProfileScreen() }
                )
                Box(
                    modifier = Modifier.weight(3f).fillMaxSize().padding(end = 5.dp),
                    contentAlignment = Alignment.TopCenter
                ) {
                    Text(text = "Профиль", fontSize = 30.sp)
                }
                Text("", modifier = Modifier.weight(0.9f).fillMaxSize())
            }

            Box(modifier = Modifier.fillMaxSize().weight(3f)) {
                Image(
                    modifier = Modifier.fillMaxSize().zIndex(0f),
                    painter = painterResource(R.drawable.avatar),
                    contentDescription = ""
                )
                Box(modifier = Modifier.fillMaxSize().zIndex(1f), contentAlignment = Alignment.BottomCenter) {
                    Icon(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .size(30.dp)
                            .clickable { onAddPhotoClick() }, // Открытие камеры при нажатии
                        painter = painterResource(R.drawable.add_plus),
                        contentDescription = ""
                    )
                }
            }
        }

        Column(
            modifier = Modifier.fillMaxSize().weight(5f).background(Color.White)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextAndField(
                modifier = Modifier.padding(top = 30.dp),
                label = "Имя",
                value = viewModel.firstName.value,
                onValueChange = { viewModel.firstName.value = it }
            )
            TextAndField(
                modifier = Modifier.padding(top = 30.dp),
                label = "Фамилия",
                value = viewModel.lastName.value,
                onValueChange = { viewModel.lastName.value = it }
            )
            TextAndField(
                modifier = Modifier.padding(top = 30.dp),
                label = "Возраст",
                value = viewModel.age.value,
                onValueChange = { viewModel.age.value = it }
            )
            TextAndField(
                modifier = Modifier.padding(top = 30.dp),
                label = "Стаж",
                value = viewModel.experience.value,
                onValueChange = { viewModel.experience.value = it }
            )
            TextAndField(
                modifier = Modifier.padding(top = 30.dp),
                label = "О себе",
                value = viewModel.aboutMe.value,
                onValueChange = { viewModel.aboutMe.value = it }
            )
            Spacer(Modifier.size(40.dp))
            Row(Modifier.fillMaxSize(), horizontalArrangement = Arrangement.SpaceAround) {
                Button(
                    onClick = {
                        viewModel.saveProfile()
                        onSave()
                    },
                    modifier = Modifier.width(140.dp)
                        .height(40.dp)
                        .clickable {},
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = Color(0xFF47A76A),
                        contentColor = Color.White,
                        disabledContainerColor = Color.LightGray,
                        disabledContentColor = Color.Gray
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("Сохранить")
                }
            }
            Spacer(Modifier.size(40.dp))
        }
    }
}

@Composable
fun CameraScreen(onPhotoTaken: (Uri) -> Unit) {
    val context = LocalContext.current
    var imageUri = remember { mutableStateOf<Uri?>(null) }

    val cameraLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { success ->
        if (success) {
            imageUri.value?.let { onPhotoTaken(it) }
        }
    }

    fun takePhoto() {
        val file = File(context.cacheDir, "photo.jpg")
        val uri = FileProvider.getUriForFile(context, "${context.packageName}.provider", file)
        cameraLauncher.launch(uri)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        imageUri.value?.let {
            Image(
                painter = rememberAsyncImagePainter(it),
                contentDescription = "Сделанное фото",
                modifier = Modifier
                    .size(200.dp)
                    .clip(CircleShape)
            )
        }
        Button(onClick = { takePhoto() }) {
            Text("Сделать фото")
        }
    }
}

@Composable
fun TextAndField(modifier: Modifier = Modifier, label: String, value: String, onValueChange: (String) -> Unit) {
    Column(modifier = modifier) {
        Text(
            text = label,
            fontSize = 18.sp
        )
        OutlinedTextField(
            modifier = Modifier.padding(top = 10.dp),
            value = value,
            onValueChange = onValueChange,
            shape = RoundedCornerShape(8.dp),
        )
    }
}

