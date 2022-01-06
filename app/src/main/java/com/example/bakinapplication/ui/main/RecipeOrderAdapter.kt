package com.example.bakinapplication.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.bakinapplication.databinding.ItemRecipeOrderBinding
import com.example.bakinapplication.model.RecipeOrderModel
import com.example.bakinapplication.utils.FirebaseUtil

class RecipeOrderAdapter : RecyclerView.Adapter<RecipeOrderAdapter.ViewHolder>() {
    private val list = ArrayList<RecipeOrderModel>()
    class ViewHolder(private val binding: ItemRecipeOrderBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: RecipeOrderModel){
            binding.recipeOrderItemText.text  = item.content
            if(item.image != ""){
                FirebaseUtil.firebaseDownlode(binding.root.context,item.image,binding.recipeOrderItemImage)
            }else{
                binding.recipeOrderItemImage.visibility = View.GONE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemRecipeOrderBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size

    fun addItem(item: RecipeOrderModel){
        list.add(item)
        notifyDataSetChanged()
    }
}