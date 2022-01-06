package com.example.bakinapplication.repo

import com.example.bakinapplication.severdb.ServerBuilder

class RecipeRepo {
    suspend fun getRecipe(id: String) = ServerBuilder.serverApi.getRecipe(id)
}