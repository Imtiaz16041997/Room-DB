package com.example.roomdatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.room.Room
import com.example.roomdatabase.Database.ContactDatabase
import com.example.roomdatabase.Entity.Contact
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var database: ContactDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        database = Room.databaseBuilder(applicationContext,

            ContactDatabase::class.java,
            "contactDB").build()

        GlobalScope.launch{

            database.contactDao().insertContact(Contact(0,"Jhon","11111"))

        }


    }

    fun getData(view: android.view.View) {
        database.contactDao().getContact().observe(this, Observer{
            Log.d("codeDe",it.toString())
        })

    }
}