package com.example.bakinapplication.ui.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.bakinapplication.R
import com.example.bakinapplication.databinding.ActivityProfileBinding
import com.example.bakinapplication.ui.main.RecipeAdapter
import com.example.bakinapplication.utils.FirebaseUtil
import com.example.bakinapplication.viewmodel.ProfileViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private val viewModel by lazy {
        ViewModelProvider(this).get(ProfileViewModel::class.java)
    }
    private val adapter = RecipeAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        binding.profileRecycler.adapter = adapter

        viewModel.image.observe(this){
            if(it != null){
                FirebaseUtil.firebaseDownlode(this,it, binding.profileImage)
            }
        }


        CoroutineScope(Dispatchers.Main).launch {
            viewModel.getProfile()
            adapter.addItem(viewModel.getMyRecipe()!!)
        }
        binding.profileBackButton.setOnClickListener {
            finish()
        }


    }
}