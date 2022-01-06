package com.example.bakinapplication.model

data class RecipeCreateModel (
    val name:String,
    val thumbnail: String,
    val image: String,
    val description: String,
    val ingredients: String,
    val contents: String,
    val category: Int,
    val time: Int,
    val useOven: Boolean
        )