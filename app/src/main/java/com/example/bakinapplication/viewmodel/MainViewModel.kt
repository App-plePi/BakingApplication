package com.example.bakinapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bakinapplication.model.RecipeListModel
import com.example.bakinapplication.repo.MainRepo
import kotlinx.coroutines.async

class MainViewModel: ViewModel() {
    val repo = MainRepo()
    suspend fun getList() = repo.getList().body()

}