package com.bd.sqlitecrud

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.annotation.RequiresApi

class add_nfo : AppCompatActivity() {
    lateinit var txtName: EditText
    lateinit var txtEmail: EditText
    lateinit var txtPassword: EditText
    lateinit var txtAccount: EditText
    lateinit var txtPin: EditText
    lateinit var txtUrl: EditText
    lateinit var txtBtnSave: Button
    lateinit var context: Context
    lateinit var dbhandeler: DBhandler


    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_nfo)

        txtBtnSave = findViewById(R.id.txtBtnSave)
        txtName = findViewById(R.id.txtName)
        txtEmail = findViewById(R.id.txtEmail)
        txtPassword = findViewById(R.id.txtPassword)
        txtAccount = findViewById(R.id.txtAccount)
        txtPin = findViewById(R.id.txtPin)
        txtUrl = findViewById(R.id.txtUrl)


        txtBtnSave.setOnClickListener(View.OnClickListener {
            SaveData()
            this.finish()
            //val intent: Intent = Intent(this, MainActivity::class.java)
            //startActivity(intent)
            //          var mainActivity = MainActivity()
//            mainActivity.recreate()

        })
    }


    @RequiresApi(Build.VERSION_CODES.P)
    private fun SaveData() {
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
            dbhandeler.insertPasswordInfo(passInfo)
            var recadapter = MyAdapter(this, dbhandeler.fetchAllData())
            recadapter.notifyDataSetChanged();
            recadapter.notifyItemInserted(0)
            Log.d("DataLength", dbhandeler.fetchAllData().size.toString())
            Log.d("adapter", recadapter.toString())
            var mainActivity = MainActivity()
            mainActivity.refreshAdapter(recadapter)


            Toast.makeText(this, "Saved Data", Toast.LENGTH_LONG).show()


        }
    }


}