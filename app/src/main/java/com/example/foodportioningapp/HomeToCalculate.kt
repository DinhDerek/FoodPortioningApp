package com.example.foodportioningapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.calculate_portion.*

class HomeToCalculate: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calculate_portion)

        calculate_to_home.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}

