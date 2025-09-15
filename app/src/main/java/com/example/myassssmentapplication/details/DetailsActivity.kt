package com.example.myassssmentapplication.details

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myassssmentapplication.R
import com.example.myassssmentapplication.dashboard.Entity
import com.example.myassssmentapplication.ui.login.LoginActivity
import android.content.Intent

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val entity = intent.getSerializableExtra("entity") as? Entity
        val tvDetails = findViewById<TextView>(R.id.tvDetails)
        val btnBack = findViewById<Button>(R.id.btnBack)
        val btnLogout = findViewById<Button>(R.id.btnLogout)

        if (entity != null) {
            val sb = StringBuilder()
            sb.appendLine("Title: ${entity.title}")
            entity.subtitle?.let { sb.appendLine("Subtitle: $it") }
            entity.description?.let { sb.appendLine("Description: $it") }
            sb.appendLine("Student Keypass: ${entity.ownerKeypass}")

            if (entity.extra.isNotEmpty()) {
                sb.appendLine("\nOther Properties:")
                entity.extra.forEach { (k, v) ->
                    sb.appendLine("- $k: $v")
                }
            }

            tvDetails.text = sb.toString()
        } else {
            tvDetails.text = "No details available"
        }

        btnBack.setOnClickListener { finish() }

        btnLogout.setOnClickListener {
            val i = Intent(this, LoginActivity::class.java)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(i)
            finish()
        }
    }
}
