package com.example.dundermifflinapp.data.create.order

import com.example.dundermifflinapp.data.models.Order
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface CreateOrderService {

    @POST("order")
    suspend fun saveOrder(@Body order: Order): Response<Void>
}