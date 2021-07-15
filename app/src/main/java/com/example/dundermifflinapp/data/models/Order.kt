package com.example.dundermifflinapp.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Order(
    val orderId: String = "",
    val customerId: String? = null,
    val salesmanId: String? = null,
    val items: List<String>? = null,
    val value: Float? = null,
    val deliveryDate: String? = null,
    val purchaseDate: String? = null
): Parcelable
