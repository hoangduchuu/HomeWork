package net.hdhuu.splee

import android.app.Application
import net.hdhuu.splee.di.activitiesModule
import net.hdhuu.splee.di.applicationModule
import net.hdhuu.splee.di.postModule
import org.koin.android.ext.android.startKoin

class SpleeApplication: Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(applicationModule, activitiesModule, postModule))
    }
}