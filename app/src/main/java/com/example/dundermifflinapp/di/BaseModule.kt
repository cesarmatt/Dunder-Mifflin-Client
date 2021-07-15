package com.example.dundermifflinapp.di

import com.example.dundermifflinapp.data.db.AppDatabase
import com.example.dundermifflinapp.data.db.DatabaseLocalDataSource
import org.koin.core.qualifier.named
import org.koin.dsl.module

val baseModule = module {
    factory { get<AppDatabase>(named("db")).salesmanDao() }
    factory { get<AppDatabase>(named("db")).orderItemDao() }
    factory { get<AppDatabase>(named("db")).customerDao() }

    factory { DatabaseLocalDataSource(get(), get(), get()) }
}