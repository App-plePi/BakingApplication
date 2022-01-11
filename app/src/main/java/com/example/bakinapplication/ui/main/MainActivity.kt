package com.example.bakinapplication.ui.main

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.bakinapplication.R
import com.example.bakinapplication.databinding.ActivityMainBinding
import com.example.bakinapplication.ui.profile.ProfileActivity
import com.example.bakinapplication.viewmodel.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }
    private val adapter = RecipeAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.mainRecipeCreateButton.setOnClickListener {
            startActivity(Intent(this, RecipeCreateActivity::class.java))
        }

        binding.mainProfileImage.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        binding.mainRecycler.adapter = adapter
        adapter.removeAll()
        CoroutineScope(Dispatchers.Main).launch {
            adapter.addItem(viewModel.getList()!!)
        }
    }
}
