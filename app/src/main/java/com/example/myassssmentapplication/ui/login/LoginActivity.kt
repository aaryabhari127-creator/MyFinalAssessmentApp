package com.example.myassssmentapplication.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.myassssmentapplication.R
import com.example.myassssmentapplication.dashboard.DashboardActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private val vm: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val etUser = findViewById<EditText>(R.id.etUsername)
        val etPass = findViewById<EditText>(R.id.etPassword)
        val btn = findViewById<Button>(R.id.btnLogin)

        btn.setOnClickListener {
            vm.login(etUser.text.toString().trim(), etPass.text.toString().trim())
        }

        vm.loginResult.observe(this) { keypass ->
            keypass ?: return@observe
            startActivity(Intent(this, DashboardActivity::class.java).putExtra("keypass", keypass))
            finish()
        }
        vm.error.observe(this) { Toast.makeText(this, it ?: "", Toast.LENGTH_SHORT).show() }
    }
}
