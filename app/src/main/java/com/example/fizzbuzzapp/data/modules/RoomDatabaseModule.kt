package com.example.fizzbuzzapp.data.modules

import android.content.Context
import androidx.room.Room
import com.example.fizzbuzzapp.data.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RoomDatabaseModule {
    private const val LOCAL_DB_NAME = "fizz_buzz.sqlite"
    private var appDatabase: AppDatabase? = null

    @Provides
    fun provideMonBicDatabase(
        @ApplicationContext context: Context,
    ): AppDatabase =
        appDatabase ?: Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            LOCAL_DB_NAME
        ).build()

    @Provides
    fun provideFormDao(db: AppDatabase) = db.formDao()
}