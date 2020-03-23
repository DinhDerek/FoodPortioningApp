package com.example.foodportioningapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.add_recipe.*

class AddRecipe: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_recipe)
        supportActionBar?.title = "Add a new recipe"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initial_ingredient.setOnClickListener {
            val ingredientList = IngredientArrayClass()
            val intent = Intent(this, AddIngredient::class.java)
            intent.putExtra("IngredientList", ingredientList)
            startActivity(intent)
        }

    }
}