package com.example.foodportioningapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class CalculateProportion: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calculate_portion)
        supportActionBar?.title = "Calculate Proportion";
        supportActionBar?.setDisplayHomeAsUpEnabled(true);

    }
}

