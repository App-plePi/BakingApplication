package com.example.bakinapplication.ui.main

import android.content.ContentValues.TAG
import android.graphics.Bitmap
import android.net.Uri
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import com.example.bakinapplication.databinding.ItemRecipeCreateBinding
import com.example.bakinapplication.viewmodel.RecipeCreateItemViewModel

class RecipeCreateAdapter(val onClick :(Int, ImageButton) -> Unit) : RecyclerView.Adapter<RecipeCreateAdapter.ViewHolder>() {
    var list = ArrayList<RecipeCreateItemViewModel>()
    var imageUri = ArrayList<Uri?>()
    inner class ViewHolder(private val binding: ItemRecipeCreateBinding) : RecyclerView.ViewHolder(binding.root){
        fun onBind(item: RecipeCreateItemViewModel,position: Int, onClick :(Int, ImageButton) -> Unit){
            binding.recipeCreateItemEdit.setText(item.content)
            binding.recipeCreateItemEdit.addTextChangedListener(object : TextWatcher{
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun afterTextChanged(p0: Editable?){
                    item.content = binding.recipeCreateItemEdit.text.toString()
                }

            })
            binding.recipeCreateItemAddButton.setImageBitmap(item.img)
            binding.recipeCreateItemAddButton.setOnClickListener {
                onClick(position, it as ImageButton)
            }
            binding.root.setOnLongClickListener {
                imageUri.removeAt(position)
                list.removeAt(position)
                notifyItemRemoved(position)
                notifyItemRangeChanged(position,list.size)
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemRecipeCreateBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(list[position],position, onClick)
    }

    override fun getItemCount(): Int = list.size
    override fun getItemViewType(position: Int): Int = position
    fun changeImage(pos:Int,uri: Uri, bitmap: Bitmap){
        list[pos].img = bitmap
        Log.d(TAG, "changeImage: $list")
        notifyDataSetChanged()
        imageUri[pos] = uri
    }
    fun addItem(bitmap: Bitmap){
        list.add(RecipeCreateItemViewModel("", bitmap))
        notifyDataSetChanged()
    }
}