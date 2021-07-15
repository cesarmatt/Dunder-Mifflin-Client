package com.example.dundermifflinapp.data.feed

import com.example.dundermifflinapp.data.models.Customer
import com.example.dundermifflinapp.data.models.Order
import com.example.dundermifflinapp.data.models.OrderItem
import com.example.dundermifflinapp.data.models.Salesman
import retrofit2.Response
import retrofit2.http.GET

interface FeedService {

    @GET("order")
    suspend fun getOrders(): Response<List<Order>>

    @GET("salesman")
    suspend fun getSalesman(): Response<List<Salesman>>

    @GET("item")
    suspend fun getItems(): Response<List<OrderItem>>

    @GET("customer")
    suspend fun getCustomers(): Response<List<Customer>>
}