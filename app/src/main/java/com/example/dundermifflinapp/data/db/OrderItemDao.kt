package com.example.dundermifflinapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dundermifflinapp.data.models.OrderItem
import com.example.dundermifflinapp.data.models.Salesman

@Dao
interface OrderItemDao {

    @Query("SELECT * FROM orderitem")
    suspend fun getAllItems(): List<OrderItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(orderItem: OrderItem)
}