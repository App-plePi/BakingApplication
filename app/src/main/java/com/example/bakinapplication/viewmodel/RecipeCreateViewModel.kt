package com.example.bakinapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bakinapplication.model.RecipeCreateModel
import com.example.bakinapplication.repo.RecipeCreateRepo
import kotlinx.coroutines.async

class RecipeCreateViewModel : ViewModel() {
    val repo = RecipeCreateRepo()
    val title = MutableLiveData<String>()
    val describe = MutableLiveData<String>()

    suspend fun recipeCreate(thumbnail:String, images: String, ingredient: String, content: String): Boolean{
        return repo.recipeCreate(RecipeCreateModel(title.value!!,thumbnail,images,describe.value!!,ingredient,content,0,0,false)).isSuccessful

    }
}