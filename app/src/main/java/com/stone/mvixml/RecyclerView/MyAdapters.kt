package com.stone.mvixml.RecyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.stone.mvixml.R
import com.stone.mvixml.model.Posts

class MyAdapters(): RecyclerView.Adapter<MyAdapters.MyViewHolder>() {

var list= listOf<Posts>()
    fun setdata(list: List<Posts>){
        this.list=list
notifyDataSetChanged()
    }
class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var textViewid: TextView = itemView.findViewById(R.id.textViewid)
    var textViewuserid: TextView = itemView.findViewById(R.id.textViewuserid)
    var textViewtitle: TextView = itemView.findViewById(R.id.textViewtitle)
    var textViewbody: TextView = itemView.findViewById(R.id.textViewbody)
}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.itemsforrecycler,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentuser = list[position]
holder.textViewid.text=currentuser.id.toString()
        holder.textViewuserid.text=currentuser.userID.toString()
        holder.textViewtitle.text=currentuser.title.toString()
        holder.textViewbody.text=currentuser.body.toString()

    }

    override fun getItemCount(): Int {
return list.size   }
}