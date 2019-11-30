package com.example.mobilewarehouseman

import android.app.Application
import com.example.mobilewarehouseman.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MobileWarehouseMan : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MobileWarehouseMan)
            modules(
                viewModelModule
            )
        }
    }
}