package com.example.foodportioningapp

import java.io.Serializable

class IngredientArrayClass : Serializable {
    var ingredientList = mutableListOf<IngredientListClass>()
    var recipeName = ""
}