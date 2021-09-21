package com.example.dundermifflinapp.create.customer

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.dundermifflinapp.data.create.customer.CreateCustomerRepository
import com.example.dundermifflinapp.ui.create.customer.CreateCustomerViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.koin.test.AutoCloseKoinTest
import org.mockito.Mockito.mock

@RunWith(AndroidJUnit4::class)
class CreateCustomerViewModelTest : AutoCloseKoinTest() {

    private lateinit var createCustomerViewModel: CreateCustomerViewModel
    private val dispatcher = TestCoroutineDispatcher()

    @Before
    fun before() {
        Dispatchers.setMain(dispatcher)
        createCustomerViewModel = CreateCustomerViewModel(
            mock(CreateCustomerRepository::class.java)
        )
    }

    @After
    fun after() {
        Dispatchers.resetMain()
    }



}