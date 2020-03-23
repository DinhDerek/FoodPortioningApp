package com.example.foodportioningapp

import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.Context
import android.content.ContentValues

class RecipeDatabase(context: Context, name: String?, factory: SQLiteDatabase.CursorFactory?, version: Int) : SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_PRODUCTS_TABLE = ("CREATE TABLE $TABLE_RECIPES($COLUMN_RECIPENAME TEXT,$COLUMN_INGREDIENTNAME TEXT,$COLUMN_INGREDIENTAMOUNT REAL,$COLUMN_INGREDIENTCALORIES INTEGER,$COLUMN_MEASUREMENTUNIT TEXT)")
        db.execSQL(CREATE_PRODUCTS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int,
                            newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_RECIPES")
        onCreate(db)
    }

    fun addRecipe(recipe: IngredientArrayClass) {
        val db = this.writableDatabase
        for (ingredient in recipe.ingredientList) {
            val values = ContentValues()
            values.put(COLUMN_RECIPENAME, recipe.recipeName)
            values.put(COLUMN_INGREDIENTNAME, ingredient.ingredientName)
            values.put(COLUMN_INGREDIENTAMOUNT, ingredient.ingredientAmount.toString())
            values.put(COLUMN_INGREDIENTCALORIES, ingredient.ingredientCalories.toString())
            values.put(COLUMN_MEASUREMENTUNIT, ingredient.measurementUnit)
            db.insert(TABLE_RECIPES, null, values)
        }
        db.close()
    }

    fun findRecipe(recipename: String): IngredientArrayClass? {
        val query = "SELECT * FROM $TABLE_RECIPES WHERE $COLUMN_RECIPENAME = \"$recipename\""

        val db = this.writableDatabase
        var recipe = IngredientArrayClass()
        val cursor = db.rawQuery(query, null)
        while (cursor.moveToNext()) {
            recipe.recipeName = cursor.getString(0)
            val ingredientCurrent = IngredientListClass()
            ingredientCurrent.ingredientName = cursor.getString(1)
            ingredientCurrent.ingredientAmount = cursor.getString(2).toDouble()
            ingredientCurrent.ingredientCalories = Integer.parseInt(cursor.getString(3))
            ingredientCurrent.measurementUnit = cursor.getString(4)
            recipe.ingredientList.add(ingredientCurrent)
        }
        db.close()
        cursor.close()
        return recipe
    }

    fun allRecipes(): List<IngredientArrayClass> {
        val query = "SELECT * FROM $TABLE_RECIPES"
        val db = this.writableDatabase
        val cursor = db.rawQuery(query, null)
        var recipeList = mutableListOf<IngredientArrayClass>()

        var recipe = IngredientArrayClass()
        if (cursor.moveToNext()) {
            var currentRecipeName = cursor.getString(0)
            recipe.recipeName = currentRecipeName
            var ingredientCurrent = IngredientListClass()
            ingredientCurrent.ingredientName = cursor.getString(1)
            ingredientCurrent.ingredientAmount = cursor.getString(2).toDouble()
            ingredientCurrent.ingredientCalories = Integer.parseInt(cursor.getString(3))
            ingredientCurrent.measurementUnit = cursor.getString(4)
            recipe.ingredientList.add(ingredientCurrent)

            while (cursor.moveToNext()) {
                if (cursor.getString(0) != currentRecipeName) {
                    recipeList.add(recipe)
                    currentRecipeName = cursor.getString(0)
                    recipe = IngredientArrayClass()
                }
                recipe.recipeName = currentRecipeName
                ingredientCurrent = IngredientListClass()
                ingredientCurrent.ingredientName = cursor.getString(1)
                ingredientCurrent.ingredientAmount = cursor.getString(2).toDouble()
                ingredientCurrent.ingredientCalories = Integer.parseInt(cursor.getString(3))
                ingredientCurrent.measurementUnit = cursor.getString(4)
                recipe.ingredientList.add(ingredientCurrent)
            }
        }
        recipeList.add(recipe)
        db.close()
        cursor.close()

        return recipeList
    }

    companion object {

        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "RECIPEDB.db"
        const val TABLE_RECIPES = "recipes"

        const val COLUMN_RECIPENAME = "recipename"
        const val COLUMN_INGREDIENTNAME = "ingredientname"
        const val COLUMN_INGREDIENTAMOUNT = "ingredientamount"
        const val COLUMN_INGREDIENTCALORIES = "ingredientcalories"
        const val COLUMN_MEASUREMENTUNIT = "measurementunit"
    }


}