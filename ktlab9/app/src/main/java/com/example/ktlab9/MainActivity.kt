package com.example.ktlab9
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
class MainActivity : AppCompatActivity() {
    lateinit var tv_clock: TextView
    lateinit var btn_start: Button
    var flag: Boolean = false
    private val receiver: BroadcastReceiver = object : BroadcastReceiver()
    {
        override fun onReceive(context: Context?, intent: Intent) {
            val b = intent.extras
            tv_clock.text = String.format("%02d:%02d:%02d",b!!.getInt("H"), b.getInt("M"), b.getInt("S"))
        }}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv_clock = findViewById(R.id.tv_clock)
        btn_start = findViewById(R.id.btn_start)
        registerReceiver(receiver, IntentFilter("MyMessage")) //註冊Receiver來接收有『MyMessage』識別字串的廣播
        flag=MyService.flag//取得Service狀態
        if(flag)
            btn_start.setText("stop");
        else
            btn_start.setText("start");
        btn_start.setOnClickListener {
            flag = !flag
            if (flag) {
                btn_start.text = "stop"
                Toast.makeText(this@MainActivity,"計時開始", Toast.LENGTH_SHORT).show()
            } else {
                btn_start.text = "start"
                Toast.makeText(this@MainActivity,"計時暫停",Toast.LENGTH_SHORT).show()}
            startService(Intent(this@MainActivity,MyService::class.java).putExtra("flag", flag))
        } }
    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)} }//註銷Receiver