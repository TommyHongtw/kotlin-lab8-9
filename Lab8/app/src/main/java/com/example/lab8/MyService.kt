package com.example.lab8

import android.app.Service
import android.content.Intent
import android.os.IBinder

class MyService : Service() {
    //使用Thread來執行這個function,因為這邊比較耗時,會佔到其他任務的執行時間
    override fun onCreate() { //覆寫的function
        super.onCreate()
        Thread(Runnable { //開啟一個thread,叫做runnable
            try {
                Thread.sleep(5000) //delay 5 seconds
                //開啟一個intent連結到Main2Activity
                val intent = Intent(this@MyService, Main2Activity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK //建立新的任務
                this@MyService.startActivity(intent) //起動Main2Activity
            } catch (e: InterruptedException) {
                e.printStackTrace() //如果有錯就回報
            }
        }).start()//啟動thread
    }
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        return START_STICKY //service停止後重啟並清空Intent
    }
    //結束服務
    override fun onDestroy() {
        super.onDestroy()
    }
    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }
}
