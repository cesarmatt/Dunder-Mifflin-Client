package com.example.dundermifflinapp.di

import com.example.dundermifflinapp.data.create.customer.CreateCustomerRemoteDataSource
import com.example.dundermifflinapp.data.create.customer.CreateCustomerRepository
import com.example.dundermifflinapp.data.create.customer.CreateCustomerService
import com.example.dundermifflinapp.data.create.order.CreateOrderRemoteDataSource
import com.example.dundermifflinapp.data.create.order.CreateOrderRepository
import com.example.dundermifflinapp.data.create.order.CreateOrderService
import com.example.dundermifflinapp.data.feed.FeedRemoteDataSource
import com.example.dundermifflinapp.data.feed.FeedRepository
import com.example.dundermifflinapp.data.feed.FeedService
import com.example.dundermifflinapp.ui.create.customer.CreateCustomerViewModel
import com.example.dundermifflinapp.ui.create.order.CreateOrderViewModel
import com.example.dundermifflinapp.ui.feed.FeedViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val feedModule = module {
    factory { get<Retrofit>().create(FeedService::class.java) as FeedService }
    factory { FeedRemoteDataSource(get()) }
    factory { FeedRepository(get(), get(), get(), get(), get()) }
    viewModel { FeedViewModel(get()) }
}

val createCustomerModule = module {
    factory { get<Retrofit>().create(CreateCustomerService::class.java) as CreateCustomerService }
    factory { CreateCustomerRemoteDataSource(get()) }
    factory { CreateCustomerRepository(get()) }
    viewModel { CreateCustomerViewModel(get()) }
}

val createOrderModule = module {
    factory { get<Retrofit>().create(CreateOrderService::class.java) as CreateOrderService }
    factory { CreateOrderRemoteDataSource(get()) }
    factory { CreateOrderRepository(get(), get()) }
    viewModel { CreateOrderViewModel(get()) }
}