package com.example.dundermifflinapp.data.create.customer

import com.example.dundermifflinapp.data.models.Customer
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface CreateCustomerService {

    @POST("customer")
    suspend fun saveCustomer(@Body customer: Customer): Response<Void>

}