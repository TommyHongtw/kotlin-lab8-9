package com.example.lab8

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    public lateinit var button: Button //宣告button並繼承Button屬性，延遲初始化
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState) //初始化Activity
        setContentView(R.layout.activity_main) //連結到xml畫面
        button=findViewById(R.id.button) //使用findviewid類別來連接到xml的元件
        button.setOnClickListener {
            val intent = Intent(this@MainActivity, MyService::class.java)
            //宣告常數intent並繼承Intent類別，丟入參數此頁面與要傳到的頁面
            startService(intent) //連結
            Toast.makeText(this@MainActivity, "啟動SERVICE", Toast.LENGTH_LONG).show()
            //跳出訊息Toast
            finish()//結束連結
        }
    }
}