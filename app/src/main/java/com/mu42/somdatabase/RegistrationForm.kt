package com.mu42.somdatabase

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class RegistrationForm : AppCompatActivity() {

    // database helper class
    lateinit var db_helper:MySQLiteOpenHelper

    lateinit var  Reg_btn:Button
    lateinit var Reg_Name:EditText
    lateinit var Reg_Password:EditText
    lateinit var Reg_Phone:EditText
    lateinit var tv:ListView

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registration_form)

        // dbHelper class initilization
        db_helper= MySQLiteOpenHelper(applicationContext)



        // initilization
        Reg_btn=findViewById(R.id.Reg_btn)
        Reg_Name=findViewById(R.id.Reg_name)
        Reg_Phone=findViewById(R.id.Reg_Phone)
        Reg_Password=findViewById(R.id.Reg_Password)
        tv=findViewById(R.id.tvData)

        Reg_btn.setOnClickListener{

          val a=  db_helper.RegisterHelper(

                Reg_Name.text.toString(),
                Reg_Phone.text.toString(),
                Reg_Password.text.toString()

                )
            if(a) {


                Toast.makeText(this, " User Registered ", Toast.LENGTH_LONG).show()

                Reg_Name.text.clear()
                Reg_Phone.text.clear()
                Reg_Password.text.clear()
            }
            else {
                Toast.makeText(this,"error ",Toast.LENGTH_LONG).show()
            }

//            val cursor=db_helper.ReadCursor()

           startActivity(Intent(this@RegistrationForm,Login::class.java))


           val list= db_helper.ReadData()

            var usr=ArrayList<String>()

            for ( u in list )
            {
                usr.add(u.name)

            }

            val adapter=ArrayAdapter<String>(this,R.layout.listitem,usr)
//            val adapter=ArrayAdapter<user>(this,R.layout.listitem,list)
            tv.adapter=adapter


//            list.forEach { Log.d("User", it.toString()) }
        }



    }





}