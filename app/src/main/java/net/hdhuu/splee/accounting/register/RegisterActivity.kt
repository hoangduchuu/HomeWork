package net.hdhuu.splee.accounting.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_register.*
import net.hdhuu.splee.R
import net.hdhuu.splee.home.model.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterActivity : AppCompatActivity() {
    val viewmodel: RegisterViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        subMitNewUser.setOnClickListener {
            viewmodel.register(editTextUserName.text.toString(),editTextPhone.text.toString(), editTextPassword.text.toString())
        }
    }
}
