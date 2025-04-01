package com.mu42.somdatabase

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class add_note : AppCompatActivity() {

    lateinit var title:EditText
    lateinit var discription:EditText
    lateinit var saveBTN:Button

    lateinit var db_helperNote:MySQLiteOpenHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.add_note)


        saveBTN=findViewById<Button>(R.id.addSaveBTN)
        title=findViewById<EditText>(R.id.titleId)
        discription=findViewById<EditText>(R.id.DiscID)

        db_helperNote= MySQLiteOpenHelper(this)


        saveBTN.setOnClickListener{


           if(!(title.text.isEmpty() || discription.text.isEmpty()))
               {

                  if( db_helperNote.SaveNote(title.text.toString(),discription.text.toString()) ) {

                      Toast.makeText(this@add_note,"Note Saved...",Toast.LENGTH_LONG).show()

                      startActivity(Intent(this@add_note, Home::class.java))
                  }else
                  {
                      Toast.makeText(this@add_note,"Note Does Not Save...",Toast.LENGTH_LONG).show()
                  }

               }
            else
            {
                Toast.makeText(this@add_note," Give Title And discription ",Toast.LENGTH_LONG).show()
            }
        }

    }
}