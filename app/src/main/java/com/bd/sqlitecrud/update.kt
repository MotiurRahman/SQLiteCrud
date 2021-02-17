package com.bd.sqlitecrud

import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi

class update : AppCompatActivity() {
    lateinit var txtName: EditText
    lateinit var txtEmail: EditText
    lateinit var txtPassword: EditText
    lateinit var txtAccount: EditText
    lateinit var txtPin: EditText
    lateinit var txtUrl: EditText
    lateinit var btnUpdate: Button
    lateinit var context: Context
    lateinit var dbhandeler: DBhandler

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        btnUpdate = findViewById(R.id.btnUpdate)
        txtName = findViewById(R.id.txtName)
        txtEmail = findViewById(R.id.txtEmail)
        txtPassword = findViewById(R.id.txtPassword)
        txtAccount = findViewById(R.id.txtAccount)
        txtPin = findViewById(R.id.txtPin)
        txtUrl = findViewById(R.id.txtUrl)


        btnUpdate.setOnClickListener(View.OnClickListener {
            updateData()

            this.finish()

        })
    }


    @RequiresApi(Build.VERSION_CODES.P)
    private fun updateData() {
        var name = txtName.text.toString()
        var email = txtEmail.text.toString()
        var password = txtPassword.text.toString()
        var account = txtAccount.text.toString()
        var pin = txtPin.text.toString()
        var url = txtUrl.text.toString()
        if (name.isEmpty()) {
            txtName.error = "Enter your Name"

        } else if (email.isEmpty()) {
            txtEmail.error = "Enter your Email"
        } else {

            var passInfo = Model(0, name, email, password, account, pin, url)

            dbhandeler = DBhandler(this)
            dbhandeler.updateData(passInfo)
            // var mainActivity = MainActivity()

            // MainActivity.adapter?.notifyDataSetChanged()


            Toast.makeText(this, "Saved Data", Toast.LENGTH_LONG).show()


        }
    }

}