package com.jesufertez.elbajnmstico.ui

import android.app.Application
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.jesufertez.elbajnmstico.data.model.Producto
import com.jesufertez.elbajnmstico.databinding.ItemListLayoutBinding
import com.squareup.picasso.Picasso

class ProductoAdapter(
    private val producto: List<Producto>,
    private val recyclerClickInterface: RecyclerClickInterface
) : RecyclerView.Adapter<ProductosViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductosViewHolder {
        val binding =
            ItemListLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductosViewHolder(binding,recyclerClickInterface)
    }

    override fun onBindViewHolder(holder: ProductosViewHolder, position: Int) {
        holder.bind(producto[position])
    }

    override fun getItemCount() = producto.size
}

class ProductosViewHolder(
    private val binding: ItemListLayoutBinding,
     private val recyclerClickInterface :RecyclerClickInterface
) : RecyclerView.ViewHolder(binding.root) {
    var valor: Int = 0

    fun bind(producto: Producto) {
        binding.run {
            tvProducto.text = producto.name
            tvIngredientes.text = producto.description
            tvPrecio.text = "$" + producto.price.toString()
            Picasso.get().load(producto.urlImage).into(ivProducto)


            itemView.setOnClickListener { view: View? ->

                recyclerClickInterface.onItemClick(adapterPosition,producto.price,producto.estado)

                valor = producto.price
                if (itemView.isPressed && producto.estado==false) {
                    producto.estado = true

                } else if (itemView.isPressed && producto.estado) {
                    producto.estado = false
                }
            }

        }
    }
}