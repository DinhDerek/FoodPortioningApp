package com.example.foodportioningapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.add_ingredient.*

class AddIngredient: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_ingredient)
        supportActionBar?.title = "Creating new recipe"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val spinner: Spinner = findViewById(R.id.spinner_unit);
        ArrayAdapter.createFromResource(
            this,
            R.array.units,
            android.R.layout.simple_spinner_dropdown_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

            spinner.adapter = adapter
        }

        fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
            // An item was selected. You can retrieve the selected item using
        }

        fun onNothingSelected(parent: AdapterView<*>) {
            // Another interface callback
        }
        save_ingredient.setOnClickListener {
            /*
        val database = getSharedPreferences("database", Context.MODE_PRIVATE)
        database.edit().apply {
        putString("savedIngredientName", ingredient_name.text.toString())
        }.apply()
             */
        startActivity(Intent(this, AddRecipe::class.java))
        }
    }
}