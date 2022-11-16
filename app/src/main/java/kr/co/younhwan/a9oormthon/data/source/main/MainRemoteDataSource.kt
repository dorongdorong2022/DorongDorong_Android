package kr.co.younhwan.a9oormthon.data.source.main

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import org.json.JSONObject
import java.io.IOException

object MaybeRemoteDataSource : MainSource {
    private val client = OkHttpClient()
    private const val serverInfo = "http://"
    private val jsonMediaType = "application/json; charset=utf-8".toMediaTypeOrNull()

    override fun read(readCallback: MainSource.ReadCallback?) {
        runBlocking {
            launch {
                val site = "$serverInfo/"

                val request = Request.Builder().url(site).get().build()

                client.newCall(request).enqueue(object : Callback {
                    override fun onFailure(call: Call, e: IOException) {

                    }

                    override fun onResponse(call: Call, response: Response) {
                        if (response.isSuccessful) {
                            val resultText = response.body?.string()!!.trim()
                            val json = JSONObject(resultText)
                        }
                    }
                })
            }
        }
    }
}