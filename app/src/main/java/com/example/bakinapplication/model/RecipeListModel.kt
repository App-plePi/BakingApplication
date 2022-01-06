package com.example.bakinapplication.model

data class RecipeListModel (
    val id: String,
    val name: String,
    val thumbnail: String,
    val user: User,
    val image: String,
    val description: String,
    val ingredients: String,
    val contents: String,
        )