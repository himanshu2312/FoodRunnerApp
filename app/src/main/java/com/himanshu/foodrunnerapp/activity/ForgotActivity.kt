package com.himanshu.foodrunnerapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.himanshu.foodrunnerapp.R
import androidx.appcompat.widget.AppCompatButton

class ForgotActivity : AppCompatActivity() {
    private lateinit var btnNext: AppCompatButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot)
        btnNext=findViewById(R.id.btnNext)
        btnNext.setOnClickListener {
            Toast.makeText(
                this@ForgotActivity,
                "Not Implemented Yet!!",
                Toast.LENGTH_SHORT
            ).show()
        }

    }
    override fun onBackPressed() {
        val intent = Intent(this@ForgotActivity, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}