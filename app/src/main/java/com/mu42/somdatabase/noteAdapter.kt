package com.mu42.somdatabase

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

data class modelNote(val mtitle:String,val mDisc:String) {

}

class noteAdapter(val context:Context, val list : ArrayList<modelNote>):RecyclerView.Adapter<noteAdapter.myNoteViewHolder>() {

    inner  class myNoteViewHolder(view : View):RecyclerView.ViewHolder(view)
    {
        var title=view.findViewById<TextView>(R.id.titleItemId)
        var discp=view.findViewById<TextView>(R.id.itemDiscID)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myNoteViewHolder {
        val view=LayoutInflater.from(context).inflate(R.layout.item_notes,parent, false)
        return myNoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: myNoteViewHolder, position: Int) {

        val title1=list[position].mtitle.toString()
        val disc1=list[position].mDisc.toString()

        holder.title.text=title1
        holder.discp.text=disc1


    }

    override fun getItemCount(): Int {
        return  list.size
    }
}