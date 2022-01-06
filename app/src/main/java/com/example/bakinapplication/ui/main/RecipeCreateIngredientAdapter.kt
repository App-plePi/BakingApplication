package com.example.bakinapplication.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bakinapplication.databinding.ItemRecipeCreateBinding
import com.example.bakinapplication.databinding.ItemRecipeCreateIngredientBinding

class RecipeCreateIngredientAdapter :
    RecyclerView.Adapter<RecipeCreateIngredientAdapter.ViewHolder>() {
    var list = ArrayList<String>()
    inner class ViewHolder(private val binding: ItemRecipeCreateIngredientBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: String){
            binding.recipeIngredientItemText.text = item
            binding.root.setOnLongClickListener {
                list.removeAt(adapterPosition)
                notifyItemRemoved(adapterPosition)
                notifyItemRangeChanged(adapterPosition,list.size)
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemRecipeCreateIngredientBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size

    fun addItem(item:String){
        list.add(item)
        notifyDataSetChanged()
    }
}