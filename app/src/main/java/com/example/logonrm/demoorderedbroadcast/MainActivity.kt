package com.example.logonrm.demoorderedbroadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var receiver: BroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var filter = IntentFilter()
        filter.addAction(Intent.ACTION_POWER_CONNECTED)
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED)

        receiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {

                when (intent?.action){
                    Intent.ACTION_POWER_CONNECTED -> tvUSBStatus.text = "Cabo conectado"
                    Intent.ACTION_POWER_DISCONNECTED -> {tvUSBStatus.text = "Cabo desconectado"}
                }

            }
        }

        registerReceiver(receiver, filter)

        btEnviar.setOnClickListener {
            sendBroadcast(Intent("com.example.logonrm.demoorderedbroadcast.MeuOrderedBroadcast"))
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }
}
