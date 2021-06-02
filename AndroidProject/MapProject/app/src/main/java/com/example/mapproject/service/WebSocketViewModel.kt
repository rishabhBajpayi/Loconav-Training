package com.example.mapproject.service

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mapproject.model.Message
import com.example.mapproject.model.Payload
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.*
import org.json.JSONException
import org.json.JSONObject

class WebSocketViewModel : ViewModel()  {


    private var _payload = MutableLiveData<Payload>()
    val payload : LiveData<Payload>
        get() = _payload

    private lateinit var webSocket: WebSocket
    private val serverPath: String =
        "wss://stgtessaractws.loconav.com/cable?token=Re_RxxzPi-5ZK_HmuUhG"

    var obj = object : WebSocketListener(){
        override fun onOpen(webSocket: WebSocket, response: Response) {
            super.onOpen(webSocket, response)
            println("websocketlistener open")
            webSocket.send(sendMessage)
        }

        override fun onMessage(webSocket: WebSocket, text: String) {
            super.onMessage(webSocket, text)
            println(text)
            val obj = JSONObject(text)
            CoroutineScope(Dispatchers.Main).launch {
                try {
                    if (obj.get("message") as? JSONObject != null)
                        getResult(obj.get("message").toString())
                } catch (e: JSONException) {
                    println("error")
                }
            }
        }

        override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
            super.onFailure(webSocket, t, response)
            println("error failure")
        }

        override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
            super.onClosed(webSocket, code, reason)
            println("webSocket closed")
        }

        private fun getResult(text: String) {
            val moshi2 = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
            val jsonAdapter2: JsonAdapter<Message> = moshi2.adapter(Message::class.java)
            val data2 = jsonAdapter2.fromJson(text)
            println(data2?.payload)
            _payload.value = data2?.payload
        }

        private val sendMessage: String
            get() {
                val cus = JSONObject()
                cus.put("channel", "VehicleUpdatesChannel")
                cus.put("vehicle_id", 13)
                val obj = JSONObject()
                return try {
                    obj.put("command", "subscribe")
                    obj.put("identifier", cus.toString())
                    obj.toString()
                } catch (e: JSONException) {
                    e.printStackTrace()
                    ""
                }
            }
    }

    fun initiateSocketConnection() {
        val client = OkHttpClient()
        val request = Request.Builder().url(serverPath).build()
        webSocket = client.newWebSocket(request,obj)
    }

    fun closeWebSocket() {
        webSocket.close(1001, "Work Completed")
    }

}