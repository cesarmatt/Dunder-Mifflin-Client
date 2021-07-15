package com.example.dundermifflinapp.data.create.order

import com.example.dundermifflinapp.data.db.DatabaseLocalDataSource
import com.example.dundermifflinapp.data.models.Order
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CreateOrderRepository(
    private val createOrderRemoteDataSource: CreateOrderRemoteDataSource,
    private val databaseLocalDataSource: DatabaseLocalDataSource
) {

    suspend fun saveOrder(order: Order) = withContext(Dispatchers.IO) {
        createOrderRemoteDataSource.saveOrder(order)
    }

    suspend fun getAllCustomers() = withContext(Dispatchers.IO) {
        databaseLocalDataSource.findAllCustomers()
    }

    suspend fun getAllSalesman() = withContext(Dispatchers.IO) {
        databaseLocalDataSource.findAllSalesman()
    }

    suspend fun getAllItems() = withContext(Dispatchers.IO) {
        databaseLocalDataSource.findAllItems()
    }
}