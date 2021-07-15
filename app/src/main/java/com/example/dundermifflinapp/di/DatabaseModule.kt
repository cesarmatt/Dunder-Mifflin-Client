package com.example.dundermifflinapp.di

import com.example.dundermifflinapp.data.db.AppDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.core.qualifier.named
import org.koin.dsl.module

val databaseModule = module {
    single(named("db")) {
        AppDatabase.buildDatabase(androidApplication())
    }
}