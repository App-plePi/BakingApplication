package com.example.bakinapplication.ui.account

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.bakinapplication.R
import com.example.bakinapplication.databinding.ActivitySignUp1Binding
import com.example.bakinapplication.viewmodel.SignUpViewModel1

class SignUpActivity1 : AppCompatActivity() {

    private lateinit var binding : ActivitySignUp1Binding
    private val viewModel by lazy {
        ViewModelProvider(this).get(SignUpViewModel1::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_1)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up_1)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.nickname.observe(this){
            if(viewModel.nickname.value!!.length in 4..7){
                viewModel.checkNickname()
            }
        }
        binding.signUpButton.setOnClickListener {
            if(viewModel.nickname.value!!.length < 3 || viewModel.nickname.value!!.length > 8|| !viewModel.isCheck){
                Toast.makeText(this,"유효한 닉네임을 입력해주세요",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            viewModel.confirm()
            val intent = Intent(this, SignUpActivity2::class.java)
            intent.putExtra("nickname", viewModel.nickname.value.toString())
            intent.putExtra("phone", viewModel.phone.value.toString())
            ActivityCompat.finishAffinity(this)
            startActivity(intent)
        }
        binding.signUpBackButton.setOnClickListener {
            finish()
        }
        binding.signUp1LoginButton.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
        }
    }
}