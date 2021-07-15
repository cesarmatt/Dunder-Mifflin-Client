package com.example.dundermifflinapp.data.feed

import com.example.dundermifflinapp.data.db.CustomerDao
import com.example.dundermifflinapp.data.db.DatabaseLocalDataSource
import com.example.dundermifflinapp.data.db.OrderItemDao
import com.example.dundermifflinapp.data.db.SalesmanDao
import com.example.dundermifflinapp.data.models.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.example.dundermifflinapp.utils.Result

class FeedRepository(
    private val feedRemoteDataSource: FeedRemoteDataSource,
    private val databaseLocalDataSource: DatabaseLocalDataSource,
    private val salesmanDao: SalesmanDao,
    private val orderItemDao: OrderItemDao,
    private val customerDao: CustomerDao
) {

    suspend fun getOrders() = withContext(Dispatchers.IO) {
        feedRemoteDataSource.getOrders()
    }

    suspend fun getSalesman() = withContext(Dispatchers.IO) {
        val response = feedRemoteDataSource.getSalesman()

        if (response is Result.Success) {
            for (salesman in response.data) {
                salesmanDao.insert(salesman)
            }
        }
    }

    suspend fun getItems() = withContext(Dispatchers.IO) {
        val response = feedRemoteDataSource.getItems()

        if (response is Result.Success) {
            for (item in response.data) {
                orderItemDao.insert(item)
            }
        }
    }

    suspend fun getCustomers() = withContext(Dispatchers.IO) {
        val response = feedRemoteDataSource.getCustomer()

        if (response is Result.Success) {
            for (customer in response.data) {
                customerDao.insert(customer)
            }
        }
    }

    suspend fun getOrderView(salesmanId: String?, customerId: String?, itemsId: List<String>?, order: Order) = withContext(Dispatchers.IO) {
        val salesman = orderSalesman(databaseLocalDataSource.findAllSalesman(), salesmanId)
        val items = orderItems(databaseLocalDataSource.findAllItems(), itemsId)
        val customer = orderCustomer(databaseLocalDataSource.findAllCustomers(), customerId)

        OrderView(
            orderId = order.orderId,
            customerName = customer?.name,
            salesmanName = salesman?.name,
            items = items,
            value = order.value,
            deliveryDate = order.deliveryDate,
            purchaseDate = order.purchaseDate
        )
    }

    private fun orderSalesman(salesmanResult: Result<List<Salesman>>, salesmanId: String?): Salesman? {
        if (salesmanResult is Result.Success) {
            for (salesman in salesmanResult.data) {
                if (salesman.id == salesmanId) {
                    return salesman
                }
            }
        }

        return null
    }

    private fun orderItems(itemsResult: Result<List<OrderItem>>, itemsId: List<String>?): List<OrderItem> {
        var items = listOf<OrderItem>()
        val orderItems = mutableListOf<OrderItem>()
        if (itemsResult is Result.Success) {
            items = itemsResult.data
        }

        items.forEach { orderItem ->
            if (itemsId != null) {
                for (id in itemsId) {
                    if (id == orderItem.orderItemId) {
                        orderItems.add(orderItem)
                    }
                }
            }
        }

        return orderItems
    }

    private fun orderCustomer(customerResult: Result<List<Customer>>, customerId: String?): Customer? {
        if (customerResult is Result.Success) {
            for (customer in customerResult.data) {
                if (customer.customerId == customerId) {
                    return customer
                }
            }
        }

        return null
    }
}