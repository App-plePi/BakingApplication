package com.example.bakinapplication.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import com.example.bakinapplication.R
import com.example.bakinapplication.databinding.DialogIngredientBinding

class IngredientDialog(
    context: Context,
    private val posClick: (content: String) -> Unit
) : Dialog(context) {

    private lateinit var binding: DialogIngredientBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.dialog_ingredient,
            null,
            false
        )
        setContentView(binding.root)
        onBind()
    }

    fun onBind() {
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        binding.negButton.setOnClickListener {
            dismiss()
        }
        binding.posButton.setOnClickListener {
            posClick(binding.IngredientDialogEdit.text.toString())
            dismiss()
        }
    }
}