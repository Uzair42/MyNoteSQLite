package com.mu42.somdatabase

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Home : AppCompatActivity() {

    lateinit var myRecyclerView:RecyclerView
    lateinit var myNoteAdapter: noteAdapter
    lateinit var helper: MySQLiteOpenHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)

//        myNoteAdapter=noteAdapter(this,dbList)

        myRecyclerView=findViewById(R.id.recyclerContainer)
        myRecyclerView.layoutManager=LinearLayoutManager(this)




        helper=MySQLiteOpenHelper(this)
        val data=helper.ReadNote()
        myNoteAdapter= noteAdapter(this,data)
        myRecyclerView.adapter=myNoteAdapter


        val logoutBtn=findViewById<ImageView>(R.id.logout).setOnClickListener{

            val shrpre=getSharedPreferences("login", MODE_PRIVATE)
            val editor=shrpre.edit()
            editor.putBoolean("flag",false)
            editor.apply()
           startActivity(Intent(this@Home,Login::class.java))
            finish()




        }
    }

    fun newNoteFunc(view: View) {

  startActivity(Intent(this@Home,add_note::class.java))

    }

    fun newFunReloadNotes(view: View) {

    }


}