package ru.sicampus.bootcamp2025.data

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import ru.sicampus.bootcamp2025.domain.userInfo.UserRepo
import java.io.IOException
import kotlin.concurrent.thread

class UserRepoImpl: UserRepo {
    override suspend fun fetchUser(id:Int): UserDTO {
        return withContext(Dispatchers.IO) {
            val userDeferred = async {
                delay(1000L)
                val response = getUser(id)

                Gson().fromJson(response, UserDTO::class.java)
            }
            userDeferred.await()
        }
    }

    override suspend fun fetchAllUsers(): List<UserDTO> {
        var userList: List<UserDTO> = listOf()
        withContext(Dispatchers.IO) {
            launch {
                delay(1000L)
                val response = getAllUsers()


                 val listType = object : TypeToken<List<UserDTO>>() {}.type

                 userList = Gson().fromJson(response, listType)


            }.join()


        }
        return userList
    }


}

fun getAllUsers():String?{
        val client = OkHttpClient()
        var result: String? = ""
        // Формируем запрос по адресу API
        val request = Request.Builder()
            .url("http://192.168.207.239:8080/api/users")
            .build()

        try {
            // Выполняем запрос синхронно
            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) throw IOException("Неожиданный код ответа: $response")

                // Получаем содержимое ответа в виде строки
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

fun getUser(id:Int):String?{
    val client = OkHttpClient()
    var result: String? = ""
    // Формируем запрос по адресу API
    val request = Request.Builder()
        .url("http://192.168.207.239:8080/api/users/$id")

        .build()

    try {
        // Выполняем запрос синхронно
        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw IOException("Неожиданный код ответа: $response")

            // Получаем содержимое ответа в виде строки
            val responseData = response.body?.string()

            result = responseData
        }
    } catch (e: IOException) {
        Log.d("111111111111111111","Ошибка при выполнении запроса: ${e.message}")
        result = null
    }

    return result
}



