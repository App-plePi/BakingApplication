package com.example.bakinapplication.repo

import com.example.bakinapplication.severdb.ServerApi
import com.example.bakinapplication.severdb.ServerBuilder

class MainRepo {
    suspend fun getList() = ServerBuilder.serverApi.getRecipeList(-1)
}