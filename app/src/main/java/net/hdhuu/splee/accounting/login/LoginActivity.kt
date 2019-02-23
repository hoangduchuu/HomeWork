package net.hdhuu.splee.accounting.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*
import net.hdhuu.splee.R
import net.hdhuu.splee.accounting.forgot.ForgotPasswordActivity
import net.hdhuu.splee.accounting.register.RegisterActivity
import net.hdhuu.splee.homepage.HomePageActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
        btnForgotPassword.setOnClickListener {
            startActivity(Intent(this, ForgotPasswordActivity::class.java))
        }

        btnLogin.setOnClickListener {
            startActivity(Intent(this, HomePageActivity::class.java))
        }

    }
}
