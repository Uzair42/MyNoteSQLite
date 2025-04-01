package com.mu42.somdatabase

import android.app.PendingIntent.OnFinished
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash)

         Handler(Looper.getMainLooper()).postDelayed(object : Runnable {
             override fun run() {
           val pref=getSharedPreferences("login", MODE_PRIVATE)
                val check= pref.getBoolean("flag",false)




                 if(check==true){
                   val  next=Intent(this@splash,Home::class.java)
                     startActivity(next)


                 }
                 else{
                     val  Inext=Intent(this@splash,Login::class.java)
                     startActivity(Inext)
                 }


             }
         },1500)








    }

    override fun onStop() {
        super.onStop()
        finish()
    }
}