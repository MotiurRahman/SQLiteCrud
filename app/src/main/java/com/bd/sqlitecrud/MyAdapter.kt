package com.bd.sqlitecrud

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import java.security.AccessController.getContext

class MyAdapter(var context: Context, var data: MutableList<Model>) :
    RecyclerView.Adapter<MyAdapter.TextItemViewHolder>() {

    class TextItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var txtNameValue: TextView

        init {
            txtNameValue = itemView.findViewById<TextView>(R.id.txtNameValue)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)

        return TextItemViewHolder(view)

    }

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onBindViewHolder(holder: TextItemViewHolder, position: Int) {
        holder.txtNameValue.text = data[position].name
        var mydb = DBhandler(holder.txtNameValue.context)
        Log.d("onBInd","onBindView")

        holder.itemView.setOnLongClickListener(View.OnLongClickListener {

            val builder = AlertDialog.Builder(holder.txtNameValue.context)
            builder.setTitle("Delete Record")
            builder.setMessage("Are you sure to delete this item")
            builder.setIcon(android.R.drawable.ic_dialog_alert)
            builder.setPositiveButton("Yes", object : DialogInterface.OnClickListener {

                override fun onClick(dialog: DialogInterface?, which: Int) {
                    mydb.deleteData(data[position].id);
                    data.removeAt(position);
                    notifyItemRemoved(position)
                    notifyDataSetChanged()
                    Toast.makeText(
                        holder.txtNameValue.context, "Deleted Successfully",
                        Toast.LENGTH_SHORT
                    ).show();
                }

            })

            builder.setNegativeButton("No", object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, which: Int) {

                }

            })
            builder.show()

            true
        })

        // single click for display value

        holder.itemView.setOnClickListener(View.OnClickListener {
            var model = Model(
                data[position].id,
                data[position].name,
                data[position].email,
                data[position].password,
                data[position].account,
                data[position].pin,
                data[position].url
            )

            val intent = Intent(context, display::class.java)

            val b = Bundle()
            b.putSerializable("listData", model)
            intent.putExtras(b)
            context.startActivity(intent)

        })

    }

    override fun getItemCount(): Int {
        return data.size
    }


}


