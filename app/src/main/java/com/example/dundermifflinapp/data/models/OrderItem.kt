package com.example.dundermifflinapp.data.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "orderitem")
data class OrderItem(
    @PrimaryKey
    @ColumnInfo(name = "order_item_id")
    val orderItemId: String,
    val name: String? = null,
    val value: Float? = null
): Parcelable
