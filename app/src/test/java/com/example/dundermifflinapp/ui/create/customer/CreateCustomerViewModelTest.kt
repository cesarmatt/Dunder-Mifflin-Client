package com.example.dundermifflinapp.ui.create.customer

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.dundermifflinapp.data.create.customer.CreateCustomerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.hamcrest.CoreMatchers.equalTo
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.AutoCloseKoinTest
import org.mockito.Mockito.mock
import org.hamcrest.MatcherAssert.assertThat

@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
class CreateCustomerViewModelTest : AutoCloseKoinTest() {

    private lateinit var createCustomerViewModel: CreateCustomerViewModel
    private val dispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        createCustomerViewModel = CreateCustomerViewModel(
            mock(CreateCustomerRepository::class.java)
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when a new name value is inputted form state name should update`() {
        createCustomerViewModel.onNameChanged("Naruto Uzumaki")

        val value = createCustomerViewModel.formState.name.value
        assertThat(value, equalTo("Naruto Uzumaki"))
    }

    @Test
    fun `when a new address value is inputted form state name should update`() {
        createCustomerViewModel.onAddressChanged("Konoha Avenue")

        val value = createCustomerViewModel.formState.address.value
        assertThat(value, equalTo("Konoha Avenue"))
    }

    @Test
    fun `when a new email value is inputted form state name should update`() {
        createCustomerViewModel.onEmailChanged("naruto@konoha.jp")

        val value = createCustomerViewModel.formState.email.value
        assertThat(value, equalTo("naruto@konoha.jp"))
    }
}