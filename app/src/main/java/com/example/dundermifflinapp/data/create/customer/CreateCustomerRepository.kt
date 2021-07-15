package com.example.dundermifflinapp.data.create.customer

import com.example.dundermifflinapp.data.models.Customer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CreateCustomerRepository(private val createCustomerRemoteDataSource: CreateCustomerRemoteDataSource) {

    suspend fun saveCustomer(customer: Customer) = withContext(Dispatchers.IO) {
        createCustomerRemoteDataSource.saveCustomer(customer)
    }

}