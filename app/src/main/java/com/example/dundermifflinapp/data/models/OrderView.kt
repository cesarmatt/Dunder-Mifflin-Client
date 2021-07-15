package com.example.dundermifflinapp.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class OrderView(
    val orderId: String = "",
    val customerName: String? = null,
    val salesmanName: String? = null,
    val items: List<OrderItem>? = null,
    val value: Float? = null,
    val deliveryDate: String? = null,
    val purchaseDate: String? = null
): Parcelable
