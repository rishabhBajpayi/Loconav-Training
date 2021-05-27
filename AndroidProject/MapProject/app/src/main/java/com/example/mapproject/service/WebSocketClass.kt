package com.example.mapproject.service

import android.content.Context
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

class WebSocketClass(private val ctx: Context) {

    private lateinit var webSocket: WebSocket
    lateinit var getDataInterfaceListener: GetDataListener
    private val SERVERPATH: String =
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
            data2?.payload?.let { getDataInterfaceListener.onNewValuesReceived(it) }
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

    interface GetDataListener {
        fun onNewValuesReceived(value: Payload)
    }

    fun initiateSocketConnection() {
        initializeListener()
        val client = OkHttpClient()
        val request = Request.Builder().url(SERVERPATH).build()
        webSocket = client.newWebSocket(request,obj)
    }

    private fun initializeListener() {
        if (ctx is GetDataListener)
            getDataInterfaceListener = ctx
        else
            throw RuntimeException(ctx.toString() + "must implement BelowFragmentLListener ")
    }

    fun closeWebSocket() {
        webSocket.close(1001, "Work Completed")
    }

}