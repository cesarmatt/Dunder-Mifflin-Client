package com.example.dundermifflinapp.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "salesman")
data class Salesman(
    @PrimaryKey
    val id: String,
    val name: String? = null
)
