package com.example.dundermifflinapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dundermifflinapp.data.models.Customer

@Dao
interface CustomerDao {

    @Query("SELECT * FROM customer")
    suspend fun getAllCustomers(): List<Customer>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(customer: Customer)
}