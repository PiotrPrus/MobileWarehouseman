package com.piotrprus.mobilewarehouseman

import android.app.Application
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger
import com.piotrprus.data.di.networkModule
import com.piotrprus.domain.di.useCaseModule
import com.piotrprus.mobilewarehouseman.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class MobileWarehouseMan : Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidLogger()
            androidContext(this@MobileWarehouseMan)
            modules(
                listOf(
                    viewModelModule, useCaseModule, networkModule
                )
            )
        }
        FacebookSdk.sdkInitialize(applicationContext)
        AppEventsLogger.activateApp(this)
    }
}