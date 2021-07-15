package com.example.dundermifflinapp

import android.app.Application
import com.example.dundermifflinapp.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class DunderMifflinApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@DunderMifflinApplication)
            modules(
                listOf(
                    networkModule,
                    feedModule,
                    databaseModule,
                    baseModule,
                    createCustomerModule,
                    createOrderModule
                )
            )
        }
    }
}