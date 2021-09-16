package com.example.roomdatabase.Database

import android.content.Context
import androidx.room.*
import com.example.roomdatabase.Convertors.Convertors
import com.example.roomdatabase.DAO.ContactDAO
import com.example.roomdatabase.Entity.Contact

@Database(entities =
            [Contact :: class],
            version = 1
            )

@TypeConverters (Convertors :: class)

abstract class  ContactDatabase : RoomDatabase() {

    abstract fun contactDao(): ContactDAO

    companion object{

        @Volatile
        private var INSTANCE : ContactDatabase? = null  //make a private field to hold the db instance


        // to access this private field instacne we need to make a public method
        fun getDatabase(context: Context): ContactDatabase {
            if(INSTANCE == null){
                // it might be multiple thread so that we use Synchronized locking
                synchronized(this)
                {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,

                        ContactDatabase::class.java,
                        "contactDB").build()
                }

            }
            return INSTANCE!!
        }

    }



}