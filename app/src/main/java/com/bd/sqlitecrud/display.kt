package com.bd.sqlitecrud

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class display : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)

        val passInfo = intent.getSerializableExtra("listData") as Model
        Log.d("Name", passInfo.name)
        Log.d("Email", passInfo.email)

    }
}