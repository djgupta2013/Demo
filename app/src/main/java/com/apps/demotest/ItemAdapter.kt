package com.apps.demotest

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter(private val list: ArrayList<String>): RecyclerView.Adapter<ItemAdapter.MyItemHolder>() {

   inner class MyItemHolder(view: View): RecyclerView.ViewHolder(view) {
       var tvItem : TextView = view.findViewById(R.id.tvItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyItemHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list,parent,false)
        return MyItemHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyItemHolder, position: Int) {
        holder.tvItem.text = list[position]
    }

    fun addItem(value : String){
        list.add(value)
        Log.e("dataatt",value)
        notifyItemChanged(list.size)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}


