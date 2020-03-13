package com.example.foodportioningapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class RecipeBook: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recipe_book)
        supportActionBar?.title = "Recipe Book";
        supportActionBar?.setDisplayHomeAsUpEnabled(true);

    }
}