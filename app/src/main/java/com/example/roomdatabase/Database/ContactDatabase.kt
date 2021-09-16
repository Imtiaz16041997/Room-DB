package com.example.roomdatabase.Database

import android.content.Context
import androidx.room.*
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.roomdatabase.Convertors.Convertors
import com.example.roomdatabase.DAO.ContactDAO
import com.example.roomdatabase.Entity.Contact

@Database(entities =
            [Contact :: class],
            version = 2
            )

@TypeConverters (Convertors :: class)

abstract class  ContactDatabase : RoomDatabase() {

    abstract fun contactDao(): ContactDAO

    companion object{

        val migration_1_2 = object : Migration(1,2){
            override fun migrate(database: SupportSQLiteDatabase) {
//                TODO("Not yet implemented")
                database.execSQL("ALTER TABLE contact ADD COLUMN isActive INTEGER NOT NULL DEFAULT (1)")
            }

        }

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
                        "contactDB")
                        .addMigrations(migration_1_2)
                        .build()
                }

            }
            return INSTANCE!!
        }

    }



}