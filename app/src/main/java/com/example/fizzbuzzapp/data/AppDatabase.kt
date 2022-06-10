package com.example.fizzbuzzapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.fizzbuzzapp.data.AppDatabase.Companion.BDD_VERSION
import com.example.fizzbuzzapp.data.daos.FormDao
import com.example.fizzbuzzapp.data.entities.FormEntity

@Database(
    entities = [FormEntity::class],
    version = BDD_VERSION
)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        const val BDD_VERSION = 1
    }

    abstract fun formDao(): FormDao
}