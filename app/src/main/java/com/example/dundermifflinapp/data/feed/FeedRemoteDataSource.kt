package com.example.dundermifflinapp.data.feed

import com.example.dundermifflinapp.data.models.Customer
import com.example.dundermifflinapp.data.models.Order
import com.example.dundermifflinapp.data.models.OrderItem
import com.example.dundermifflinapp.data.models.Salesman
import com.example.dundermifflinapp.utils.Result
import com.example.dundermifflinapp.utils.safeApiCall
import java.io.IOException

class FeedRemoteDataSource(private val service: FeedService) {
    suspend fun getOrders(): Result<List<Order>> = safeApiCall(
        call = { doGetOrders() },
        errorMessage = "An error occurred while loading the order"
    )

    suspend fun getSalesman(): Result<List<Salesman>> = safeApiCall(
        call = { doGetSalesman() },
        errorMessage = "An error occurred while loading the salesman staff"
    )

    suspend fun getItems(): Result<List<OrderItem>> = safeApiCall(
        call = { doGetItems() },
        errorMessage = "An error occurred while loading your items portfolio"
    )

    suspend fun getCustomer(): Result<List<Customer>> = safeApiCall(
        call = { doGetCustomers() },
        errorMessage = "An error occurred while loading your customers"
    )

    private suspend fun doGetOrders(): Result<List<Order>> {
        val response = service.getOrders()
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                return Result.Success(body)
            }
        }

        return Result.Error(
            IOException("Error retrieving your orders")
        )
    }

    private suspend fun doGetSalesman(): Result<List<Salesman>> {
        val response = service.getSalesman()
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                return Result.Success(body)
            }
        }

        return Result.Error(
            IOException("Error retrieving your salesman staff")
        )
    }

    private suspend fun doGetItems(): Result<List<OrderItem>> {
        val response = service.getItems()
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                return Result.Success(body)
            }
        }

        return Result.Error(
            IOException("Error retrieving your items portfolio")
        )
    }

    private suspend fun doGetCustomers(): Result<List<Customer>> {
        val response = service.getCustomers()
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                return Result.Success(body)
            }
        }

        return Result.Error(
            IOException("Error retrieving your customers")
        )
    }
}