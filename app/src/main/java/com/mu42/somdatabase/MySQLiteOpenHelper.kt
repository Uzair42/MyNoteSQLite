package com.mu42.somdatabase

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.ContactsContract.CommonDataKinds.Note

class MySQLiteOpenHelper(val context: Context):SQLiteOpenHelper(context,Name,null,version) {

    companion object{
        val Name="demo_db"
        val version=3

    }

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(p0: SQLiteDatabase?) {

        val cmd ="CREATE TABLE ${UserEntry.TABLE_NAME}(${UserEntry.COLUMN_ID} INTEGER PRIMARY KEY AUTOINCREMENT, ${UserEntry.COLUMN_NAME} TEXT , ${UserEntry.COLUMN_PASSWORD} TEXT, ${UserEntry.COLUMN_PHONE} TEXT)"
        val notecmd ="CREATE TABLE ${NoteData.tableName}(${NoteData.colId} INTEGER PRIMARY KEY AUTOINCREMENT, ${NoteData.colTitle} TEXT , ${NoteData.colDisc} TEXT)"



                p0?.execSQL(cmd)
        p0?.execSQL(notecmd)



    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {


        p0?.execSQL("DROP TABLE IF EXISTS ${UserEntry.TABLE_NAME}")
        p0?.execSQL("DROP TABLE IF EXISTS ${NoteData.tableName}")
        onCreate(p0)

    }

    fun RegisterHelper( Name:String,Phone:String,Password:String):Boolean
    {

        val db : SQLiteDatabase= this.writableDatabase

        val myContentValues:ContentValues=ContentValues()
        myContentValues.put(UserEntry.COLUMN_NAME,Name)
        myContentValues.put(UserEntry.COLUMN_PHONE,Phone)
        myContentValues.put(UserEntry.COLUMN_PASSWORD,Password)

       val a= db.insert(UserEntry.TABLE_NAME,null,myContentValues)

        if(a>0)
        {
            return true

        }
        else{
            return false
        }

    }




    fun ReadData():ArrayList<user>{

        val usr=ArrayList<user>()

         val db = writableDatabase

       val cursor= db.query(
            "register",
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            )

        while(cursor.moveToNext())
        {
            val name1=cursor.getString(cursor.getColumnIndexOrThrow("name"))
            val phone1=cursor.getString(cursor.getColumnIndexOrThrow("phone"))
            val password1=cursor.getString(cursor.getColumnIndexOrThrow("password"))
            usr.add(user(name1,phone1,password1))
        }
         cursor.close()


        return usr

    }

    fun LoginCheck(name:String , Password: String):Boolean
    {
         val bd:SQLiteDatabase=this.readableDatabase
       val cursor= bd.rawQuery("select ${UserEntry.COLUMN_NAME}, ${UserEntry.COLUMN_PASSWORD}" +
                " from " +
                "${UserEntry.TABLE_NAME} Where ${UserEntry.COLUMN_NAME}= ? AND ${UserEntry.COLUMN_PASSWORD}=? ",
           arrayOf(name,Password))
        return cursor.use { it.count>0 }
    }


    fun SaveNote(pTitle:String,pDisc:String):Boolean
    {
       val db=this.writableDatabase

        val mycontentValues:ContentValues=ContentValues()
        mycontentValues.put(NoteData.colTitle,pTitle)
        mycontentValues.put(NoteData.colDisc,pDisc)

      if(db.insert(NoteData.tableName,null,mycontentValues)>0)
      {return true}
        else
      {return false}

    }


    fun ReadNote():ArrayList<modelNote>{

        val note=ArrayList<modelNote>()

        val db=this.readableDatabase

        val cmd="select * from ${NoteData.tableName} "
        val cursor= db.query(
            NoteData.tableName,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
        )

        while(cursor.moveToNext())
        {
            val title=cursor.getString(cursor.getColumnIndexOrThrow(NoteData.colTitle))
            val disc=cursor.getString(cursor.getColumnIndexOrThrow(NoteData.colDisc))
            note.add(modelNote(title,disc))
        }
        cursor.close()


        return note


    }

}


data class user(val name:String,val phone:String,val Password: String)




    object UserEntry {
        const val TABLE_NAME = "register"
        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
        const val COLUMN_PHONE = "phone"
        const val COLUMN_PASSWORD = "password"
    }

 object  NoteData{
     const val tableName="NoteTable"
     const val colId="id"
     const val colTitle="title"
     const val colDisc="disc"

 }
