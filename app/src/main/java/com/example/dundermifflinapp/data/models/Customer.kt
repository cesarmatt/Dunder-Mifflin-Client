package com.example.dundermifflinapp.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "customer")
data class Customer(
    @PrimaryKey
    val customerId: String,
    val name: String? = null,
    val address: String? = null,
    val email: String? = null
)