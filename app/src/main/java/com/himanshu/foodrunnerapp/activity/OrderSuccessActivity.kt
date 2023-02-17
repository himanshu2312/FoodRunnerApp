package com.himanshu.foodrunnerapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.himanshu.foodrunnerapp.R

@Suppress("DEPRECATION")
class OrderSuccessActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_success)
        val handler = Handler()
        handler.postDelayed(
            {
                val intent = Intent(this@OrderSuccessActivity,MainActivity::class.java)
                startActivity(intent)
            },
            2000
        )
    }

    override fun onPause() {
        super.onPause()
        finish()
    }
}