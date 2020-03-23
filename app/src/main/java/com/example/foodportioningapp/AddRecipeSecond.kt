package com.example.foodportioningapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.add_ingredient_10_popup.view.*
import kotlinx.android.synthetic.main.add_ingredient_blank_popup.view.*
import kotlinx.android.synthetic.main.add_recipe_name_popup.*
import kotlinx.android.synthetic.main.add_recipe_name_popup_error.*
import kotlinx.android.synthetic.main.add_recipe_second.*
import kotlinx.android.synthetic.main.add_recipe_second_ingredient_edit.view.*


class AddRecipeSecond: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_recipe_second)
        supportActionBar?.title = "Creating new recipe"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        val ingredientList = intent.getSerializableExtra("IngredientList") as IngredientArrayClass
        var buttonArray = mutableListOf<Button>()
        val ingredientText = StringBuilder()
        val addRecipeSecondLinear = findViewById<LinearLayout>(R.id.add_ingredient_second_linear)
        var counter = 0
        for (ingredient in ingredientList.ingredientList){
            if (ingredient.ingredientAmount == ingredient.ingredientAmount.toInt().toDouble()){
                ingredientText.append(ingredient.ingredientAmount.toInt())
            } else {
                ingredientText.append(ingredient.ingredientAmount)
            }
            ingredientText.append(" ").append(ingredient.measurementUnit).append(" of ").append(ingredient.ingredientName)
            var newButton = Button(this)
            newButton.text = ingredientText
            newButton.id = counter
            buttonArray.add(newButton)
            addRecipeSecondLinear.addView(newButton)
            ingredientText.setLength(0)
            newButton.setOnClickListener {
                val editIngredientDialog = LayoutInflater.from(this).inflate(R.layout.add_recipe_second_ingredient_edit, null)

                editIngredientDialog.ingredient_name_edit.setText(ingredientList.ingredientList[newButton.id].ingredientName)
                if (ingredientList.ingredientList[newButton.id].ingredientAmount == ingredientList.ingredientList[newButton.id].ingredientAmount.toInt().toDouble()) {
                    editIngredientDialog.ingredient_amount_edit.setText(ingredientList.ingredientList[newButton.id].ingredientAmount.toInt().toString())
                } else {
                    editIngredientDialog.ingredient_amount_edit.setText(ingredientList.ingredientList[newButton.id].ingredientAmount.toString())
                }
                editIngredientDialog.ingredient_calories_edit.setText(ingredientList.ingredientList[newButton.id].ingredientCalories.toString())


                val editIngredientDialogBuilder = AlertDialog.Builder(this).setView(editIngredientDialog).setTitle("Edit Ingredient")
                val editIngredientAlertDialog = editIngredientDialogBuilder.show()

                val spinner: Spinner = editIngredientDialog.spinner_ingredient_unit_edit
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

                var spinnerPos = 0
                for (item in resources.getStringArray(R.array.units)) {
                    if (item == ingredientList.ingredientList[newButton.id].measurementUnit) {
                        break
                    }
                    spinnerPos += 1
                }
                editIngredientDialog.spinner_ingredient_unit_edit.setSelection(spinnerPos)

                editIngredientDialog.ingredient_edit_save.setOnClickListener {
                    if (editIngredientDialog.ingredient_amount_edit.text.toString() == "" || editIngredientDialog.ingredient_name_edit.text.toString() == "" ||editIngredientDialog.spinner_ingredient_unit_edit.selectedItem.toString() == "Select unit of measurement") {
                        val addIngredientBlankDialog = LayoutInflater.from(this).inflate(R.layout.add_ingredient_blank_popup, null)
                        val addIngredientBlankDialogBuilder = AlertDialog.Builder(this).setView(addIngredientBlankDialog).setTitle("Possible Error!")
                        val addIngredientBlankAlertDialog = addIngredientBlankDialogBuilder.show()
                        addIngredientBlankDialog.ingredient_blank_warning_text.text = "Warning! You left one or more fields blank.  Please fill them out before saving!"

                        addIngredientBlankDialog.add_ingredient_blank_ok.setOnClickListener {
                            addIngredientBlankAlertDialog.dismiss()
                        }
                    }
                    else if (editIngredientDialog.ingredient_amount_edit.text.toString().toDouble() >= 10) {
                        val addIngredient10Dialog = LayoutInflater.from(this).inflate(R.layout.add_ingredient_10_popup, null)
                        val addIngredient10DialogBuilder = AlertDialog.Builder(this).setView(addIngredient10Dialog).setTitle("Possible Error!")
                        val addIngredient10AlertDialog = addIngredient10DialogBuilder.show()
                        addIngredient10Dialog.ingredient_10_warning_text.text = "Warning! You entered 10 or more for this ingredient.  Are you sure you want to do that?"
                        addIngredient10Dialog.add_ingredient_10_yes.setOnClickListener {
                            addIngredient10AlertDialog.dismiss()
                            ingredientList.ingredientList[newButton.id].ingredientName = editIngredientDialog.ingredient_name_edit.text.toString()
                            ingredientList.ingredientList[newButton.id].ingredientAmount = editIngredientDialog.ingredient_amount_edit.text.toString().toDouble()
                            ingredientList.ingredientList[newButton.id].measurementUnit = editIngredientDialog.spinner_ingredient_unit_edit.selectedItem.toString()
                            if (editIngredientDialog.ingredient_calories_edit.text.toString() != "") {
                                ingredientList.ingredientList[newButton.id].ingredientCalories = Integer.parseInt(editIngredientDialog.ingredient_calories_edit.text.toString())
                            }
                            ingredientText.setLength(0)
                            if (ingredientList.ingredientList[newButton.id].ingredientAmount == ingredientList.ingredientList[newButton.id].ingredientAmount.toInt().toDouble()){
                                ingredientText.append(ingredientList.ingredientList[newButton.id].ingredientAmount.toInt())
                            } else {
                                ingredientText.append(ingredientList.ingredientList[newButton.id].ingredientAmount)
                            }
                            ingredientText.append(" ").append(ingredientList.ingredientList[newButton.id].measurementUnit).append(" of ").append(ingredientList.ingredientList[newButton.id].ingredientName)
                            newButton.text = ingredientText
                            editIngredientAlertDialog.dismiss()
                        }

                        addIngredient10Dialog.add_ingredient_10_no.setOnClickListener {
                            addIngredient10AlertDialog.dismiss()
                        }
                    } else {
                        ingredientList.ingredientList[newButton.id].ingredientName = editIngredientDialog.ingredient_name_edit.text.toString()
                        ingredientList.ingredientList[newButton.id].ingredientAmount = editIngredientDialog.ingredient_amount_edit.text.toString().toDouble()
                        ingredientList.ingredientList[newButton.id].measurementUnit = editIngredientDialog.spinner_ingredient_unit_edit.selectedItem.toString()
                        if (editIngredientDialog.ingredient_calories_edit.text.toString() != "") {
                            ingredientList.ingredientList[newButton.id].ingredientCalories = Integer.parseInt(editIngredientDialog.ingredient_calories_edit.text.toString())
                        }
                        ingredientText.setLength(0)
                        if (ingredientList.ingredientList[newButton.id].ingredientAmount == ingredientList.ingredientList[newButton.id].ingredientAmount.toInt().toDouble()){
                            ingredientText.append(ingredientList.ingredientList[newButton.id].ingredientAmount.toInt())
                        } else {
                            ingredientText.append(ingredientList.ingredientList[newButton.id].ingredientAmount)
                        }
                        ingredientText.append(" ").append(ingredientList.ingredientList[newButton.id].measurementUnit).append(" of ").append(ingredientList.ingredientList[newButton.id].ingredientName)
                        newButton.text = ingredientText
                        editIngredientAlertDialog.dismiss()
                    }
                }

                editIngredientDialog.ingredient_edit_delete.setOnClickListener {
                    ingredientList.ingredientList.removeAt(newButton.id)
                    addRecipeSecondLinear.removeView(newButton)
                    buttonArray.removeAt(newButton.id)
                    counter -= 1
                    for (button in buttonArray) {
                        if (button.id > newButton.id) {
                            button.id -= 1
                        }
                    }
                    editIngredientAlertDialog.dismiss()
                }
            }
            counter += 1
        }

        add_ingredient_save.setOnClickListener {
            val addRecipeDialog = LayoutInflater.from(this).inflate(R.layout.add_recipe_name_popup, null)
            val addRecipeDialogBuilder = AlertDialog.Builder(this).setView(addRecipeDialog).setTitle("Save Recipe")
            val addRecipeAlertDialog = addRecipeDialogBuilder.show()
            addRecipeAlertDialog.add_recipe_name_text.text = "Name your recipe:"

            addRecipeAlertDialog.add_recipe_save.setOnClickListener{
                if (addRecipeAlertDialog.add_recipe_name.text.toString() == "") {
                    val addRecipeErrorDialog = LayoutInflater.from(this).inflate(R.layout.add_recipe_name_popup_error, null)
                    val addRecipeErrorDialogBuilder = AlertDialog.Builder(this).setView(addRecipeErrorDialog).setTitle("Save Recipe")
                    val addRecipeErrorAlertDialog = addRecipeErrorDialogBuilder.show()
                    addRecipeErrorAlertDialog.add_recipe_name_text_error.text = "You left the field blank, please fill it in before saving the recipe!"
                    addRecipeErrorAlertDialog.add_recipe_name_error_ok.setOnClickListener{
                        addRecipeErrorAlertDialog.dismiss()
                    }
                } else {
                    addRecipeAlertDialog.dismiss()
                    startActivity(Intent(this, RecipeBook::class.java))
                    ingredientList.recipeName = addRecipeAlertDialog.add_recipe_name.text.toString()
                    val dbHandler = RecipeDatabase(this, null, null, 1)
                    dbHandler.addRecipe(ingredientList)
                }
            }
        }

        add_ingredient_second.setOnClickListener {
            val intent = Intent(this, AddIngredientSecond::class.java)
            intent.putExtra("IngredientList", ingredientList)
            startActivity(intent)
        }

    }
}