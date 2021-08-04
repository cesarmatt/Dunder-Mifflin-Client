package com.example.dundermifflinapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.dundermifflinapp.data.models.Customer
import com.example.dundermifflinapp.data.models.OrderItem
import com.example.dundermifflinapp.data.models.Salesman

@Database(
    entities = [
        Salesman::class,
        OrderItem::class,
        Customer::class
    ],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {

    abstract fun salesmanDao(): SalesmanDao
    abstract fun orderItemDao(): OrderItemDao
    abstract fun customerDao(): CustomerDao

    companion object {
        private const val databaseName = "dundermifflin-db"

        fun buildDatabase(context: Context): AppDatabase {
            return Room
                .databaseBuilder(context, AppDatabase::class.java, databaseName)
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}