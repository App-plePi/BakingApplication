package com.example.bakinapplication.ui.main

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.databinding.DataBindingUtil
import com.example.bakinapplication.R
import com.example.bakinapplication.databinding.ActivityRecipeCreateBinding

import android.content.Intent
import android.provider.MediaStore

import android.net.Uri
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.ViewModelProvider
import com.example.bakinapplication.dialog.IngredientDialog
import com.example.bakinapplication.utils.FirebaseUtil
import com.example.bakinapplication.viewmodel.RecipeCreateViewModel
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception


class RecipeCreateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecipeCreateBinding

    private val viewModel by lazy {
        ViewModelProvider(this).get(RecipeCreateViewModel::class.java)
    }
    val setImage: (Int, ImageButton) -> Unit = { pos: Int, img: ImageButton ->
        isThumbnail = false
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        recipePosition = pos
        startActivityForResult(intent, 101)
    }

    private val recipeCreateAdapter = RecipeCreateAdapter(setImage)
    private val ingredientAdapter = RecipeCreateIngredientAdapter()
    private var isUpload = false
    private var isThumbnail = false
    private var thumbnailUri: Uri? = null
    private var recipePosition = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_create)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_recipe_create)
        binding.viewModel = viewModel
        binding.activity = this
        binding.lifecycleOwner

        binding.recipeCreateRecipeRecycler.adapter = recipeCreateAdapter
        binding.recipeCreateIngredientRecycler.adapter = ingredientAdapter


        binding.recipeCreateUploadButton.setOnClickListener {
            var contents = ""
            var ingredient = ""
            var imgUri = ""
            if (!isUpload) {
                isUpload = true
                if (thumbnailUri == null || viewModel.title.value!!.length < 3 || viewModel.title.value!!.length > 20 || ingredientAdapter.list.isEmpty()
                    || viewModel.describe.value!!.isEmpty() || viewModel.describe.value!!.length > 100
                ) {
                    Toast.makeText(this, "정확한 내용을 입력해주세요", Toast.LENGTH_SHORT).show()
                    isUpload = false
                    return@setOnClickListener
                }
                for (i in recipeCreateAdapter.list.indices) {
                    contents += recipeCreateAdapter.list[i].content + "|"
                }
                for (i in ingredientAdapter.list.indices) {
                    ingredient += ingredientAdapter.list[i] + "|"
                }
                for (i in recipeCreateAdapter.imageUri.indices) {
                    if (recipeCreateAdapter.imageUri[i] == null) {
                        imgUri += "|"
                    } else {
                        imgUri += FirebaseUtil.firebaseUpload(
                            this,
                            recipeCreateAdapter.imageUri[i]!!
                        ) + "|"
                    }
                }
                Log.d(TAG, "onCreate: $imgUri")
                CoroutineScope(Dispatchers.Main).launch {
                    viewModel.recipeCreate(
                        FirebaseUtil.firebaseUpload(
                            applicationContext,
                            thumbnailUri!!
                        )!!, imgUri, ingredient, contents
                    )
                    finish()
                }
            }
        }

    }

    fun showDialog(view: View) {
        val posClick: (String) -> Unit = { content: String ->
            ingredientAdapter.addItem(content)
        }
        val dialog = IngredientDialog(this, posClick)
        dialog.show()
    }

    fun addItem(view: View) {
        val defaultImg = ContextCompat.getDrawable(this, R.drawable.ic_plus)!!.toBitmap()
        recipeCreateAdapter.imageUri.add(null)
        recipeCreateAdapter.addItem(defaultImg)
    }

    fun setThumbnail(view: View) {
        isThumbnail = true
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent, 101)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 101) {
            if (resultCode == RESULT_OK) {
                var imgData: Uri? = data?.data
                try {
                    val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, imgData)
                    if (isThumbnail) {
                        thumbnailUri = imgData
                        binding.recipeCreateThumbnailButton.visibility = View.INVISIBLE
                        binding.recipeCreateThumbnailCard.visibility = View.VISIBLE
                        binding.recipeCreateThumbnailImage.setImageBitmap(bitmap)
                    } else {
                        Log.d(TAG, "onActivityResult: image")
                        recipeCreateAdapter.changeImage(recipePosition, imgData!!, bitmap)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

        }
    }
}