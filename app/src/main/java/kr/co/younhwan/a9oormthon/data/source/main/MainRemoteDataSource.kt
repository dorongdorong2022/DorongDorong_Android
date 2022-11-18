package kr.co.younhwan.a9oormthon.data.source.main

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kr.co.younhwan.a9oormthon.data.soundItem
import kr.co.younhwan.a9oormthon.data.taleItem
import kr.co.younhwan.a9oormthon.data.voiceItem
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

object MainRemoteDataSource : MainSource {
    private val client = OkHttpClient()
    private const val serverInfo =
        "http://ec2-43-201-63-73.ap-northeast-2.compute.amazonaws.com:8080"
    private val jsonMediaType = "application/json; charset=utf-8".toMediaTypeOrNull()

    override fun readKey(uuid: String, readKeyCallback: MainSource.ReadKeyCallback?) {
        runBlocking {
            launch {
                val site = "${serverInfo}/login/device"

                val jsonData = JSONObject()
                jsonData.put("fcmToken", uuid) // 주문 정보

                val requestBody = jsonData.toString().toRequestBody(jsonMediaType)
                val request = Request.Builder().url(site).post(requestBody).build()

                client.newCall(request).enqueue(object : Callback {
                    override fun onFailure(call: Call, e: IOException) {
                        CoroutineScope(Dispatchers.Main).launch {
                            readKeyCallback?.onReadKey("")
                        }
                    }

                    override fun onResponse(call: Call, response: Response) {
                        if (response.isSuccessful) {
                            val resultText = response.body?.string()!!.trim()
                            val json = JSONObject(resultText)
                            val token = json.getString("accessToken") ?: ""

                            CoroutineScope(Dispatchers.Main).launch {
                                readKeyCallback?.onReadKey(token)
                            }
                        }
                    }
                })
            }
        }
    }

    override fun readVoice(token: String, readVoiceCallback: MainSource.ReadVoiceCallback?) {
        runBlocking {
            launch {
                val site = "$serverInfo/"

            }
        }

    }

    override fun readSound(token: String, readSoundCallback: MainSource.ReadSoundCallback?) {
        val list = ArrayList<soundItem>()

        runBlocking {
            launch {
                val site = "$serverInfo/jejusound/select/list/10"
                val request = Request.Builder().url(site).header("token", token).get().build()

                client.newCall(request).enqueue(object : Callback {
                    override fun onFailure(call: Call, e: IOException) {
                        CoroutineScope(Dispatchers.Main).launch {
                            readSoundCallback?.onReadSound(list)
                        }
                    }

                    override fun onResponse(call: Call, response: Response) {
                        if (response.isSuccessful) {
                            val resultText = response.body?.string()!!.trim()
                            val json = JSONObject(resultText)

                            val data = JSONArray(json["jejuSoundList"].toString())

                            for (i in 0 until data.length()) {
                                val obj = data.getJSONObject(i)

                                list.add(
                                    soundItem(
                                        obj.getString("jejuSoundImgUrl") ?: "",
                                        obj.getString("jejuSoundTxt") ?: "",
                                        obj.getString("jejuSoundNo") ?: "",
                                        obj.getString("jejuSoundThumbnailImgUrl") ?: "",
                                        obj.getString("jejuSoundUrl") ?: "",
                                    )
                                )
                            }
                        }

                        CoroutineScope(Dispatchers.Main).launch {
                            readSoundCallback?.onReadSound(list)
                        }
                    }
                })
            }
        }
    }

    override fun readTale(token: String, readTaleCallback: MainSource.ReadTaleCallback?) {
        val list = ArrayList<soundItem>()

        runBlocking {
            launch {
                val site = "$serverInfo/jejustory/select/list/10"
                val request = Request.Builder().url(site).header("token", token).get().build()

                client.newCall(request).enqueue(object : Callback {
                    override fun onFailure(call: Call, e: IOException) {
                        CoroutineScope(Dispatchers.Main).launch {
                            readTaleCallback?.onReadTale(list)
                        }
                    }

                    override fun onResponse(call: Call, response: Response) {
                        if (response.isSuccessful) {
                            val resultText = response.body?.string()!!.trim()
                            val json = JSONObject(resultText)
                            val data = JSONArray(json["jejuStoryList"].toString())

                            for (i in 0 until data.length()) {
                                val obj = data.getJSONObject(i)

                                list.add(
                                    soundItem(
                                        jejuSoundImgUrl = obj.getString("jejuStoryImgUrl") ?: "",
                                        jejuSoundThumbnailImgUrl = obj.getString("jejuStoryThumbnailImgUrl") ?: "",
                                        jejuSoundMsg = obj.getString("jejuStoryTitle") ?: "",
                                        jejuSoundNo = obj.getString("jejuStoryNo") ?: "",
                                        jejuSoundUrl = ""
                                    )
                                )
                            }
                        }

                        CoroutineScope(Dispatchers.Main).launch {
                            readTaleCallback?.onReadTale(list)
                        }
                    }
                })

            }
        }

    }

    override fun read(token:String, readCallback: MainSource.ReadCallback?) {
        val list = ArrayList<voiceItem>()

        runBlocking {
            launch {
                val site = "$serverInfo/jejustoryvoice/select/list/all"
                val request = Request.Builder().url(site).header("token", token).get().build()

                client.newCall(request).enqueue(object : Callback {
                    override fun onFailure(call: Call, e: IOException) {
                        CoroutineScope(Dispatchers.Main).launch {
                            readCallback?.onRead(list)
                        }
                    }

                    override fun onResponse(call: Call, response: Response) {
                        if (response.isSuccessful) {
                            val resultText = response.body?.string()!!.trim()
                            val json = JSONObject(resultText)

                            val data = JSONArray(json["jejuStoryVoiceList"].toString())

                            Log.d("temp", data.toString())

                            for (i in 0 until data.length()) {
                                val obj = data.getJSONObject(i)

                                list.add(
                                    voiceItem(
                                        id = obj.getInt("jejuStoryVoiceSeqNo"),
                                        name = obj.getString("jejuStoryVoiceNm"),
                                        selected = obj.getBoolean("checkYn"),
                                        audioFile = obj.getString("jejuStoryVoiceUrl"),
                                        type = 1
                                    )
                                )
                            }

                            CoroutineScope(Dispatchers.Main).launch {
                                readCallback?.onRead(list)
                            }
                        }
                    }
                })
            }
        }
    }

}