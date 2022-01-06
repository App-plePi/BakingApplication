package com.example.bakinapplication.ui.main

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.bakinapplication.R
import com.example.bakinapplication.databinding.ActivityRecipeBinding
import com.example.bakinapplication.model.RecipeOrderModel
import com.example.bakinapplication.utils.FirebaseUtil
import com.example.bakinapplication.viewmodel.RecipeViewModel
import kotlinx.coroutines.*

class RecipeActivity : AppCompatActivity() {

    private lateinit var binding : ActivityRecipeBinding
    private val viewModel by lazy {
        ViewModelProvider(this).get(RecipeViewModel::class.java)
    }

    private val ingredientAdapter = RecipeIngredientAdapter()
    private val orderAdapter = RecipeOrderAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_recipe)
        binding.viewModel = viewModel

        binding.recipeIngredientRecycler.adapter = ingredientAdapter
        binding.recipeOrderRecycler.adapter = orderAdapter

        binding.recipeBackButton.setOnClickListener {
            finish()
        }

        CoroutineScope(Dispatchers.Main).launch {
            CoroutineScope(Dispatchers.IO).async{
                viewModel.getData(intent.getStringExtra("id")!!)
            }.await()
            Log.d(TAG, "onCreate: ${viewModel.recipe.value!!}")
            FirebaseUtil.firebaseDownlode(this@RecipeActivity,viewModel.recipe.value!!.thumbnail,binding.recipeThumbnailImage)
            if(viewModel.recipe.value!!.user.image != null){
                FirebaseUtil.firebaseDownlode(this@RecipeActivity,
                    viewModel.recipe.value!!.user.image!!,binding.recipeProfileImage)
            }
            binding.recipeTitleText.text = viewModel.recipe.value!!.name
            binding.recipeDescribeText.text = viewModel.recipe.value!!.description

            val imgList = viewModel.recipe.value!!.image.split("|")
            val contentList = viewModel.recipe.value!!.contents.split("|")
            val ingredientList = viewModel.recipe.value!!.ingredients.split("|")

            val len = if(imgList.size < contentList.size){contentList.size}else{imgList.size}
            for(i in 0 until len){
                if(imgList[i].isNotEmpty() || contentList[i].isNotEmpty()){
                    orderAdapter.addItem(RecipeOrderModel(imgList[i], contentList[i]))
                }
            }
            for(i in ingredientList.indices-1){
                ingredientAdapter.addItem(ingredientList[i])
            }
        }
    }
}