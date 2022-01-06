package com.example.bakinapplication.ui.main

import android.content.ContentValues.TAG
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bakinapplication.databinding.ItemRecipeBinding
import com.example.bakinapplication.model.RecipeListModel
import com.example.bakinapplication.utils.FirebaseUtil

class RecipeAdapter : RecyclerView.Adapter<RecipeAdapter.ViewHolder>() {
    private var list = ArrayList<RecipeListModel>()
    class ViewHolder(private val binding: ItemRecipeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(item : RecipeListModel){
            binding.recipeItemTitleText.text = item.name
            FirebaseUtil.firebaseDownlode(binding.root.context, item.thumbnail,binding.recipeItemImage)
            binding.root.setOnClickListener {
                val intent = Intent(binding.root.context, RecipeActivity::class.java)
                intent.putExtra("id", item.id)
                binding.root.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemRecipeBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size

    fun addItem(list: ArrayList<RecipeListModel>){
        this.list.addAll(list)
        notifyDataSetChanged()
    }
    fun removeAll(){
        list.clear()
        notifyDataSetChanged()
    }
}