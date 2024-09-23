package com.example.retrofitappforproduct.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitappforproduct.Modals.Product
import com.example.retrofitappforproduct.R
import com.example.retrofitappforproduct.databinding.ProductItemsBinding
import com.squareup.picasso.Picasso

class MyAdapters(var context : Context, var productList : List<Product>) : RecyclerView.Adapter<MyAdapters.MyAdapterViewHolder>(){
    inner class MyAdapterViewHolder(val binding: ProductItemsBinding) : RecyclerView.ViewHolder(binding.root){
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapterViewHolder {
        var binding = ProductItemsBinding.inflate(LayoutInflater.from(context), parent, false)
        return MyAdapterViewHolder(binding)
    }

    override fun getItemCount(): Int =productList.size

    override fun onBindViewHolder(holder: MyAdapterViewHolder, position: Int) {
        val modals = productList[position]
        holder.binding.productTitle.text = modals.title
        Picasso.get().load(modals.thumbnail).into(holder.binding.productImg);
    }
}