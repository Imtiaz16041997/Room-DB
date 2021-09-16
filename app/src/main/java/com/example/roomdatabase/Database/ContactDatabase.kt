package com.example.roomdatabase.Database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.roomdatabase.DAO.ContactDAO
import com.example.roomdatabase.Entity.Contact

@Database(entities =
            [Contact :: class],
            version = 1
            )
abstract class  ContactDatabase : RoomDatabase() {

    abstract fun contactDao(): ContactDAO
}