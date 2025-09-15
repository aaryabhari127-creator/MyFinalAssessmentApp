package com.example.myassssmentapplication.dashboard

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myassssmentapplication.R
import com.example.myassssmentapplication.details.DetailsActivity
import com.example.myassssmentapplication.ui.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardActivity : AppCompatActivity() {

    private val viewModel: DashboardViewModel by viewModels()
    private lateinit var adapter: DashboardAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val recyclerView = findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val btnBack = findViewById<Button>(R.id.btnBack)
        val btnLogout = findViewById<Button>(R.id.btnLogout)

        btnBack.setOnClickListener { finish() }
        btnLogout.setOnClickListener {
            val i = Intent(this, LoginActivity::class.java)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(i)
            finish()
        }

        adapter = DashboardAdapter { clicked: Entity ->
            val i = Intent(this, DetailsActivity::class.java)
            i.putExtra("entity", clicked as java.io.Serializable)
            startActivity(i)
        }
        recyclerView.adapter = adapter

        val keypass = intent.getStringExtra("keypass")
        if (keypass != null) {
            viewModel.load(keypass)
        }

        viewModel.items.observe(this) { entities ->
            adapter.updateItems(entities)
        }

        viewModel.error.observe(this) {
            Toast.makeText(this, it ?: "Unknown error", Toast.LENGTH_SHORT).show()
        }
    }
}
