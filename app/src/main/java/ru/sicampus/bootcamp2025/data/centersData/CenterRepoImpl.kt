package ru.sicampus.bootcamp2025.data.centersData

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import ru.sicampus.bootcamp2025.data.userData.UserDTO
import ru.sicampus.bootcamp2025.data.userData.getAllUsers
import ru.sicampus.bootcamp2025.domain.centersInfo.CenterRepo
import java.io.IOException

class CenterRepoImpl:CenterRepo {
    override suspend fun fetchAllCenters(): List<CenterDTO> {
        var centersList: List<CenterDTO> = listOf()
        withContext(Dispatchers.IO) {
            launch {

                val response = getAllCenters()


                val listType = object : TypeToken<List<CenterDTO>>() {}.type

                centersList = Gson().fromJson(response, listType)


            }.join()
        }
        return centersList
    }

}


fun getAllCenters():String?{
    val client = OkHttpClient()
    var result: String? = ""
    // Формируем запрос по адресу API
    val request = Request.Builder()
        .url("http://192.168.0.16:8080/api/volunteer-centers")
        .build()

    try {
        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw IOException("Неожиданный код ответа: $response")


            val responseData = response.body?.string()

            result = responseData
        }
    } catch (e: IOException) {
        Log.d("111111111111111111","Ошибка при выполнении запроса: ${e.message}")
        result = null
    }
    Log.d("UUUUUUU",result.toString())
    return result
}