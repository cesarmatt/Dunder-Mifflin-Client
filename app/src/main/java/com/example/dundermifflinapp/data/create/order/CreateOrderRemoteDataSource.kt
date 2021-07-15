package com.example.dundermifflinapp.data.create.order

import com.example.dundermifflinapp.data.models.Order
import com.example.dundermifflinapp.utils.safeApiCall
import com.example.dundermifflinapp.utils.Result
import java.io.IOException

class CreateOrderRemoteDataSource(private val service: CreateOrderService) {

    suspend fun saveOrder(order: Order) = safeApiCall(
        call = { doSaveOrder(order) },
        errorMessage = "An error occurred while saving your order"
    )

    private suspend fun doSaveOrder(order: Order): Result<Boolean> {
        val response = service.saveOrder(order)

        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                return Result.Success(true)
            }
        }

        return Result.Error(
            IOException("Error saving your order")
        )
    }
}