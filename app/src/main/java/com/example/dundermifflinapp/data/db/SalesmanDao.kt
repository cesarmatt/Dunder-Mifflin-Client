package com.example.dundermifflinapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dundermifflinapp.data.models.Salesman

@Dao
interface SalesmanDao {

    @Query("SELECT * FROM salesman")
    suspend fun getAllSalesman(): List<Salesman>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(salesman: Salesman)
}