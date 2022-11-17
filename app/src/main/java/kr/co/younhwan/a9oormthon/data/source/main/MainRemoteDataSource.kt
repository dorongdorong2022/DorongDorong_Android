package kr.co.younhwan.a9oormthon.data.source.main

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
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


//    override fun create(
//        kakaoAccountId: Long,
//        orderItem: OrderItem,
//        createCallback: OrderSource.CreateCallback?
//    ) {
//        runBlocking {
//            var orderId = -1
//
//            launch {
//                // API 서버 주소
//                val site = serverInfo
//
//                // 새로운 데이터 생성을 위한 POST Request 생성
//                val jsonData = JSONObject()
//
//                jsonData.put("user_id", kakaoAccountId) // 주문 정보
//                jsonData.put("name", orderItem.name)
//                jsonData.put("status", orderItem.status)
//
//                jsonData.put("receiver", orderItem.receiver) // 배달 정보
//                jsonData.put("phone", orderItem.phone)
//                jsonData.put("address", orderItem.address)
//
//                jsonData.put("requirement", orderItem.requirement) /// 배달 요청사항
//                jsonData.put("point", orderItem.point)
//                jsonData.put("detective_handling_method", orderItem.detectiveHandlingMethod)
//
//                jsonData.put("payment", orderItem.payment) // 결제수단
//
//                jsonData.put("original_price", orderItem.originalPrice) // 주문 상품 확인
//                jsonData.put("event_price", orderItem.eventPrice)
//                jsonData.put("be_paid_price", orderItem.bePaidPrice)
//
//                val products = JSONArray()
//                for (index in 0 until orderItem.products.size) {
//                    val product = JSONObject()
//                    product.put("product_id", orderItem.products[index].productId)
//                    product.put("count", orderItem.products[index].countInBasket)
//                    if (orderItem.products[index].onSale) {
//                        product.put("price", orderItem.products[index].eventPrice)
//                    } else {
//                        product.put("price", orderItem.products[index].productPrice)
//                    }
//                    products.put(product)
//                }
//                jsonData.put("products", products)
//
//                val requestBody = jsonData.toString().toRequestBody(jsonMediaType)
//                val request = Request.Builder().url(site).post(requestBody).build()
//
//                // 응답
//                client.newCall(request).enqueue(object : Callback {
//                    override fun onFailure(call: Call, e: IOException) {
//                        CoroutineScope(Dispatchers.Main).launch {
//                            createCallback?.onCreate(orderId)
//                        }
//                    }
//
//                    override fun onResponse(call: Call, response: Response) {
//                        val resultText = response.body?.string()!!.trim()
//                        val json = JSONObject(resultText)
//                        val success = json.getBoolean("success")
//
//                        if (success) {
//                            orderId = json.getInt("order_id")
//                        }
//
//                        CoroutineScope(Dispatchers.Main).launch {
//                            createCallback?.onCreate(orderId)
//                        }
//                    }
//                })
//            }
//        }
}