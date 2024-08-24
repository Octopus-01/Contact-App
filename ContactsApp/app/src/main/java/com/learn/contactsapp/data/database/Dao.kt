package com.learn.contactsapp.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
    @Upsert
    suspend fun UpsertContect(contact: Contact)

    @Delete
    suspend fun DeleteContect(contact: Contact)

    @Query("SELECT * FROM contect_table ORDER BY `user_name` ASC")
    fun getContectsSortByName(): Flow<List<Contact>>

    @Query("SELECT * FROM contect_table ORDER BY `user_name` DESC")
    fun getContectsSortByDate(): Flow<List<Contact>>
}