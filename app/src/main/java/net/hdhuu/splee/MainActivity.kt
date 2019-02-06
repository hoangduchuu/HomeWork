package net.hdhuu.splee

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import net.hdhuu.splee.home.MainContract
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    val presenter: MainContract.Presenter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.e("HUU: ", presenter.toString())

    }
}
