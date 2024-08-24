package com.learn.contactsapp.data.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.learn.contactsapp.data.database.ContactDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object HiltModule {

    @Provides
    fun provideDatabase(application: Application): ContactDatabase {

        return Room.databaseBuilder(
            application.baseContext,
            ContactDatabase::class.java,
            "contact_db"
        ).setJournalMode(RoomDatabase.JournalMode.TRUNCATE).fallbackToDestructiveMigration().build()


    }
}