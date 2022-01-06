package com.example.bakinapplication.ui.account

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.bakinapplication.R
import com.example.bakinapplication.databinding.ActivitySignUp2Binding
import com.example.bakinapplication.viewmodel.SignUpViewModel2
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class SignUpActivity2 : AppCompatActivity() {

    private lateinit var binding:ActivitySignUp2Binding
    private val viewModel by lazy {
        ViewModelProvider(this).get(SignUpViewModel2::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_2)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_sign_up_2)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this


        binding.signUp2Button.setOnClickListener {
            if(!viewModel.checkPassword()){
                binding.signUp2RepeatPasswordWarning.visibility = View.VISIBLE
            }else{
                binding.signUp2RepeatPasswordWarning.visibility = View.INVISIBLE
                CoroutineScope(Dispatchers.Main).launch {
                    if(!viewModel.signUp(
                            intent.getStringExtra("nickname")!!,
                            intent.getStringExtra("phone")!!
                        )!!
                    ) {
                        Toast.makeText(applicationContext,"회원가입의 실패하였습니다", Toast.LENGTH_SHORT).show()
                    }else{
                        startActivity(Intent(applicationContext, LoginActivity::class.java))
                    }
                }
            }
        }
        binding.signUp2LoginButton.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}