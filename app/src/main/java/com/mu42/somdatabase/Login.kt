package com.mu42.somdatabase

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.collection.emptyLongSet
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Login : AppCompatActivity() {

    lateinit var  logincheckHelper:MySQLiteOpenHelper



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        logincheckHelper=MySQLiteOpenHelper(this)

        val btn =findViewById<Button>(R.id.Login_button)
         var LoginName:EditText=findViewById(R.id.LoginName)
         var LoginPassword:EditText=findViewById(R.id.loginPassword)


        btn.setOnClickListener{

            if(!(LoginName.text.isEmpty()||LoginPassword.text.isEmpty())) {
                val isVailed = logincheckHelper.LoginCheck(
                    LoginName.text.toString(),
                    LoginPassword.text.toString()
                )

                if (isVailed) {
                    // create shared Preference Object
                    val sharePref_obj: SharedPreferences =
                        getSharedPreferences("login", MODE_PRIVATE)
                    val editor = sharePref_obj.edit()
                    editor.putBoolean("flag", true)
                    editor.apply()
                    val inta = Intent(this@Login, Home::class.java)
                    startActivity(inta)
                } else {
                    Toast.makeText(this@Login, " Wrong Login Details", Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(this@Login, " Enter Your Details", Toast.LENGTH_LONG).show()

            }

        }


    }

    fun createNewAccount(view: View) {

        val intet=Intent(this@Login,RegistrationForm::class.java)
        startActivity(intet)
    }
}