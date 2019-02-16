package net.hdhuu.splee.home

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import net.hdhuu.splee.R
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class MainActivity : AppCompatActivity(), MainContract.View {

    val presenter: MainContract.Presenter by inject{ parametersOf(this)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.testPresenter()


    }

    @SuppressLint("LongLogTag")
    override fun testView() {
        Toast.makeText(applicationContext,"b",Toast.LENGTH_LONG).show()
    }
}
