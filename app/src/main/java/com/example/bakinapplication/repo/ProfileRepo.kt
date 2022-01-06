package com.example.bakinapplication.repo

import com.example.bakinapplication.severdb.ServerBuilder
import com.example.bakinapplication.utils.UtilCode.Companion.token

class ProfileRepo {
    suspend fun getProfile() = ServerBuilder.serverApi.getProfile(token)
    suspend fun getMyRecipe(id: String) = ServerBuilder.serverApi.getMyRecipeList(id)
}