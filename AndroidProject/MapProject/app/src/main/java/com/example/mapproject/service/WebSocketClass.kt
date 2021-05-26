package com.example.mapproject.service

import android.content.Context
import com.example.mapproject.model.Message
import com.example.mapproject.model.Payload
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.*
import org.json.JSONException
import org.json.JSONObject

class WebSocketClass(val ctx: Context) {

    lateinit var webSocket: WebSocket
    private val SERVER_PATH: String =
        "wss://stgtessaractws.loconav.com/cable?token=Re_RxxzPi-5ZK_HmuUhG"

    interface WebSocketListenerInterface{
        fun onNewValuesReceived(value: Payload)
    }

    lateinit var webSocketInterfaceListener: WebSocketListenerInterface

    fun initiateSocketConnection() {

        if(ctx is WebSocketListenerInterface){
            webSocketInterfaceListener = ctx
        }
        else{
            throw RuntimeException(ctx.toString()+"must implement BelowFragmentLListener ")
        }

        var client = OkHttpClient()
        var request = Request.Builder().url(SERVER_PATH).build()
        webSocket = client.newWebSocket(request, SocketListener())
    }

    fun closeWebSocket() {
        webSocket.close(1001, "Work Completed")
    }

    inner class SocketListener() : WebSocketListener() {
        override fun onOpen(webSocket: WebSocket, response: Response) {
            super.onOpen(webSocket, response)
            println("websocketlistener open")
            webSocket.send(sendMessage())
        }

        override fun onMessage(webSocket: WebSocket, text: String) {
            super.onMessage(webSocket, text)
            println(text)
            var obj = JSONObject(text)
            CoroutineScope(Dispatchers.Main).launch {
                try {
                    if (obj.get("message") as? JSONObject != null )
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

        fun getResult(text: String) {
            val moshi2 = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
            val jsonAdapter2: JsonAdapter<Message> = moshi2.adapter(Message::class.java)
            val data2 = jsonAdapter2.fromJson(text)
            println(data2?.payload)
            data2?.payload?.let { webSocketInterfaceListener.onNewValuesReceived(it) }
        }

        override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
            super.onClosed(webSocket, code, reason)
            println("webSocket closed")
        }

        fun sendMessage(): String {
            var cus = JSONObject()
            cus.put("channel", "VehicleUpdatesChannel")
            cus.put("vehicle_id", 13)
            var obj = JSONObject()
            try {
                obj.put("command", "subscribe")
                obj.put("identifier", cus.toString())
                return obj.toString()
            } catch (e: JSONException) {
                e.printStackTrace()
                return ""
            }
        }

    }
}