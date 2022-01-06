package com.example.bakinapplication.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.bakinapplication.model.RecipeListModel
import com.example.bakinapplication.repo.ProfileRepo
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {
    val repo = ProfileRepo()
    val nickname = MutableLiveData<String>()
    val image = MutableLiveData<String>()
    var id = ""

    suspend fun getProfile(){
        viewModelScope.async {
            val data = repo.getProfile().body()
            Log.d(TAG, "getProfile: $data")
            nickname.value = data!!.nickname
            image.value = data?.image
            id = data.id
        }.await()
    }
    suspend fun getMyRecipe() = repo.getMyRecipe(id).body()
}