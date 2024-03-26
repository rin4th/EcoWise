package com.example.ecowise.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecowise.R
import com.example.ecowise.model.Product
import com.google.android.material.materialswitch.MaterialSwitch

class ProductAdapter : ListAdapter<Product, ProductAdapter.ViewHolder>(DIFFCALLBACK) {

    
    private lateinit var onItemClickCallback: OnItemClickListener

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickListener) {
        this.onItemClickCallback = onItemClickCallback
    }



    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var icon: ImageView = itemView.findViewById(R.id.iv_product)
        private var name: TextView = itemView.findViewById(R.id.tv_product)
        private var price: TextView = itemView.findViewById(R.id.tv_price)
        private var off: TextView = itemView.findViewById(R.id.tv_discount)

        val price_desc = "Rp. "

        fun bind(product: Product, onItemCallback: OnItemClickListener) {
            Glide.with(itemView.context)
                .load(product.icon)
                .into(icon)
            //icon.setImageResource(R.drawable.ac)
            name.text = product.name
            price.text = price_desc.plus(product.price.toString())
            off.text = product.off.toString().plus(" %")
        }

    }

    interface OnItemClickListener {
        fun onItemClick()
    }



    companion object {
        val DIFFCALLBACK = object : DiffUtil.ItemCallback<Product>() {
            override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.card_product, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), onItemClickCallback)
    }
}