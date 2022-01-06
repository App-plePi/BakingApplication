package com.example.bakinapplication.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bakinapplication.model.RecipeListModel
import com.example.bakinapplication.repo.RecipeRepo
import com.example.bakinapplication.severdb.ServerBuilder
import kotlinx.coroutines.async

class RecipeViewModel : ViewModel() {
    val repo = RecipeRepo()
    val recipe = MutableLiveData<RecipeListModel>()

    suspend fun getData(id: String) {
        recipe.postValue(repo.getRecipe(id).body())
    }
}