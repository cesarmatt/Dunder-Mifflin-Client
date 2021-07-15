package com.example.dundermifflinapp.data.db

import com.example.dundermifflinapp.data.models.Customer
import com.example.dundermifflinapp.data.models.OrderItem
import com.example.dundermifflinapp.data.models.Salesman
import com.example.dundermifflinapp.utils.doQuery

class DatabaseLocalDataSource(
    private val salesmanDao: SalesmanDao,
    private val orderItemDao: OrderItemDao,
    private val customerDao: CustomerDao
) {

    suspend fun findAllSalesman() = doQuery(
        query = { doFindAllSalesman() },
        errorMessage = "An error occurred while fetching all salesman"
    )

    suspend fun findAllItems() = doQuery(
        query = { doFindAllItems() },
        errorMessage = "An error occurred while fetching all salesman"
    )

    suspend fun findAllCustomers() = doQuery(
        query = { doFindAllCustomers() },
        errorMessage = "An error occurred while fetching all salesman"
    )

    private suspend fun doFindAllSalesman(): List<Salesman> {
        return salesmanDao.getAllSalesman()
    }

    private suspend fun doFindAllItems(): List<OrderItem> {
        return orderItemDao.getAllItems()
    }

    private suspend fun doFindAllCustomers(): List<Customer> {
        return customerDao.getAllCustomers()
    }

}