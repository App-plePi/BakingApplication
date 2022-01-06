package com.example.bakinapplication.ui.account

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.bakinapplication.R
import com.example.bakinapplication.databinding.ActivityLoginBinding
import com.example.bakinapplication.ui.main.MainActivity
import com.example.bakinapplication.viewmodel.LoginViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel by lazy {
        ViewModelProvider(this).get(LoginViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_login)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        binding.loginButton.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                if(viewModel.login()){
                    startActivity(Intent(applicationContext, MainActivity::class.java))
                    finish()
                }else{
                    Toast.makeText(applicationContext,"로그인에 실패하였습니다", Toast.LENGTH_SHORT).show()
                }
            }
        }
        binding.loginSignUpButton.setOnClickListener {
            startActivity(Intent(applicationContext,SignUpActivity1::class.java))
        }
    }
}