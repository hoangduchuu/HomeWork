package net.hdhuu.splee

import android.app.Application
import android.util.Log
import io.reactivex.functions.Consumer
import io.reactivex.internal.functions.Functions
import io.reactivex.plugins.RxJavaPlugins
import net.hdhuu.splee.di.activitiesModule
import net.hdhuu.splee.di.applicationModule
import net.hdhuu.splee.di.postModule
import org.koin.android.ext.android.startKoin
import io.reactivex.internal.functions.Functions.emptyConsumer



class SpleeApplication: Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(applicationModule, activitiesModule, postModule))
        RxJavaPlugins.setErrorHandler {
            it->
            run {
                Log.e("Global RxError holder:", it.localizedMessage)
            }
        }
    }
}