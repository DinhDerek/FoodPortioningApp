package com.example.foodportioningapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log.d
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.add_recipe_second.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        home_to_calculate.setOnClickListener {
            startActivity(Intent(this, CalculateProportion::class.java))
        }

        home_to_book.setOnClickListener {
            startActivity(Intent(this, RecipeBook::class.java))
        }

        home_to_recipe.setOnClickListener {
            startActivity(Intent(this, AddRecipe::class.java))
        }

    }
}
