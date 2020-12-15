package com.example.ktlab9
import android.app.Service
import android.content.Intent
import android.os.Bundle
import android.os.IBinder
class MyService : Service() {
    companion object {
        var flag: Boolean = false//計數器狀態
        }
    //計數器數值（時、分、秒）
    var hour = 0
    var minute = 0
    var second = 0
    override fun onBind(intent: Intent): IBinder {
        throw UnsupportedOperationException("Not yet implemented")}
    override fun onStartCommand(intent: Intent, flags: Int, startID: Int): Int {
        flag = intent.getBooleanExtra("flag", false)
        //秒數增加要開新的Thread來算
        object : Thread() {
            override fun run() {
            while (flag) {
                try {
                    sleep(1000)//延遲1s
                } catch (e: InterruptedException) {
                    e.printStackTrace() }
                second++//計數器加一
                if (second >= 60) { //秒數大於60進位
                    second = 0
                    minute++
                    if (minute >= 60) { //分鐘數大於60進位
                        minute = 0
                        hour++
                    } }
                val intent = Intent("MyMessage")//發送帶有『MyMessage』識別字串的廣播
                val bundle = Bundle()//將時間放入Bundle
                bundle.putInt("H", hour)
                bundle.putInt("M", minute)
                bundle.putInt("S", second)
                intent.putExtras(bundle)
                sendBroadcast(intent)//發送廣播
                }}
        }.start()
        return START_STICKY //自動重啟，但不會保留Intent
    } }