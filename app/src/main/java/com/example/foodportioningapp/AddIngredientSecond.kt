package com.example.foodportioningapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.add_ingredient.*
import kotlinx.android.synthetic.main.add_ingredient_10_popup.view.*
import kotlinx.android.synthetic.main.add_ingredient_blank_popup.view.*

class AddIngredientSecond: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_ingredient)
        supportActionBar?.title = "Add an ingredient"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val spinner: Spinner = findViewById(R.id.spinner_ingredient_unit);
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

        val ingredientList = intent.getSerializableExtra("IngredientList") as IngredientArrayClass
        val ingredientCurrent = IngredientListClass()

        save_ingredient.setOnClickListener {
            if (ingredient_amount.text.toString() == "" || ingredient_name.text.toString() == "" ||spinner_ingredient_unit.selectedItem.toString() == "Select unit of measurement") {
                val addIngredientBlankDialog = LayoutInflater.from(this).inflate(R.layout.add_ingredient_blank_popup, null)
                val addIngredientBlankDialogBuilder = AlertDialog.Builder(this).setView(addIngredientBlankDialog).setTitle("Possible Error!")
                val addIngredientBlankAlertDialog = addIngredientBlankDialogBuilder.show()
                addIngredientBlankDialog.ingredient_blank_warning_text.text = "Warning! You left one or more fields blank.  Please fill them out before saving!"

                addIngredientBlankDialog.add_ingredient_blank_ok.setOnClickListener {
                    addIngredientBlankAlertDialog.dismiss()
                }
            }
            else if (ingredient_amount.text.toString().toDouble() >= 10) {
                val addIngredient10Dialog = LayoutInflater.from(this).inflate(R.layout.add_ingredient_10_popup, null)
                val addIngredient10DialogBuilder = AlertDialog.Builder(this).setView(addIngredient10Dialog).setTitle("Possible Error!")
                val addIngredient10AlertDialog = addIngredient10DialogBuilder.show()
                addIngredient10Dialog.ingredient_10_warning_text.text = "Warning! You entered 10 or more for this ingredient.  Are you sure you want to do that?"
                addIngredient10Dialog.add_ingredient_10_yes.setOnClickListener {
                    addIngredient10AlertDialog.dismiss()
                    ingredientCurrent.ingredientName = ingredient_name.text.toString()
                    ingredientCurrent.ingredientAmount = ingredient_amount.text.toString().toDouble()
                    ingredientCurrent.measurementUnit = spinner_ingredient_unit.selectedItem.toString()
                    if (ingredient_calories.text.toString() != "") {
                        ingredientCurrent.ingredientCalories = Integer.parseInt(ingredient_calories.text.toString())
                    }
                    ingredientList.ingredientList.add(ingredientCurrent)
                    val intent = Intent(this, AddRecipeSecond::class.java)
                    intent.putExtra("IngredientList", ingredientList)
                    startActivity(intent)
                }

                addIngredient10Dialog.add_ingredient_10_no.setOnClickListener {
                    addIngredient10AlertDialog.dismiss()
                }
            } else {
                ingredientCurrent.ingredientName = ingredient_name.text.toString()
                ingredientCurrent.ingredientAmount = ingredient_amount.text.toString().toDouble()
                ingredientCurrent.measurementUnit = spinner_ingredient_unit.selectedItem.toString()
                if (ingredient_calories.text.toString() != "") {
                    ingredientCurrent.ingredientCalories = Integer.parseInt(ingredient_calories.text.toString())
                }
                ingredientList.ingredientList.add(ingredientCurrent)
                val intent = Intent(this, AddRecipeSecond::class.java)
                intent.putExtra("IngredientList", ingredientList)
                startActivity(intent)
            }
        }
    }
}