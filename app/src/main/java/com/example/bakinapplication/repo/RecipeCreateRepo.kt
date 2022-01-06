package com.example.bakinapplication.repo

import com.example.bakinapplication.utils.UtilCode.Companion.token
import com.example.bakinapplication.model.RecipeCreateModel
import com.example.bakinapplication.severdb.ServerBuilder

class RecipeCreateRepo {
    suspend fun recipeCreate(model: RecipeCreateModel) = ServerBuilder.serverApi.recipeCreate(token, model)
}