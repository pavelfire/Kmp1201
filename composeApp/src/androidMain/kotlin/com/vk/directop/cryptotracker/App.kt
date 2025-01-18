package com.vk.directop.cryptotracker

import android.app.Application
import com.vk.directop.cryptotracker.di.initKoin
import org.koin.android.ext.koin.androidContext

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@App)
        }
    }
}