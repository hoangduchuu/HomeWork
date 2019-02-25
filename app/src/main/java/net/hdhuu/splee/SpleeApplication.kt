package net.hdhuu.splee

import android.app.Application
import android.util.Log
import com.google.firebase.FirebaseApp
import io.reactivex.functions.Consumer
import io.reactivex.internal.functions.Functions
import io.reactivex.plugins.RxJavaPlugins
import net.hdhuu.splee.di.*
import org.koin.android.ext.android.startKoin


class SpleeApplication: Application(){
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        startKoin(this, listOf(applicationModule, activitiesModule, postModule,registerModule, firebaseModule))
        RxJavaPlugins.setErrorHandler {
            it->
            run {
                Log.e("Global RxError holder:", it.localizedMessage)
            }
        }
    }
}