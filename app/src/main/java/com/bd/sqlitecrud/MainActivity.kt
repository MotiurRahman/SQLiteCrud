package com.bd.sqlitecrud

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {

    var layoutManager: RecyclerView.LayoutManager? = null
    var recView: RecyclerView? = null
    var adapter: RecyclerView.Adapter<*>? = null



    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recView = findViewById<RecyclerView>(R.id.recView)
        layoutManager = LinearLayoutManager(this)
        recView?.layoutManager = layoutManager
        recView?.itemAnimator = DefaultItemAnimator()
        recView?.setHasFixedSize(true)
        setuplistOfDataIntoRecyclearveiw()

    }

    @RequiresApi(Build.VERSION_CODES.P)
    fun setuplistOfDataIntoRecyclearveiw() {
        var dBhandler = DBhandler(this)
        adapter = MyAdapter(this, dBhandler.fetchAllData())
        recView?.adapter = adapter

    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==1){
            recreate()
        }
    }

    fun refreshAdapter(recadapter: MyAdapter){
        recView?.adapter=null
        recView?.adapter = recadapter

        Log.d("message", "refreshAdaptered")
    }
/*
    override fun onResume() {
        adapter?.notifyDataSetChanged()
        super.onResume()
    }

 */


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.add -> {
                val intent = Intent(applicationContext, add_nfo::class.java)
                startActivity(intent)

                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

}