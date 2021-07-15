package com.example.dundermifflinapp.data.create.customer

import com.example.dundermifflinapp.data.models.Customer
import com.example.dundermifflinapp.utils.Result
import com.example.dundermifflinapp.utils.safeApiCall
import java.io.IOException

class CreateCustomerRemoteDataSource(private val service: CreateCustomerService) {

    suspend fun saveCustomer(customer: Customer) = safeApiCall(
        call = { doPostCustomer(customer) },
        errorMessage = "An error occurred while saving your customer"
    )

    private suspend fun doPostCustomer(customer: Customer): Result<Boolean> {
        val response = service.saveCustomer(customer)

        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                return Result.Success(true)
            }
        }

        return Result.Error(
            IOException("Error saving your customer")
        )
    }
}