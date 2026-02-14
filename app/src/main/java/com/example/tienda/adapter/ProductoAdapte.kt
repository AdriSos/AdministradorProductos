package com.example.tienda.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.tienda.R
import com.example.tienda.model.CarritoManager
import com.example.tienda.model.Producto

class ProductoAdapter(private val listaProductos: List<Producto>) :
    RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder>() {

    class ProductoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nombre: TextView = view.findViewById(R.id.tvNombreProducto)
        val marca: TextView = view.findViewById(R.id.tvMarcaProducto)
        val precio: TextView = view.findViewById(R.id.tvPrecioProducto)
        val btnAgregar: Button = view.findViewById(R.id.btnAgregarCarrito)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_producto, parent, false)
        return ProductoViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductoViewHolder, position: Int) {
        val producto = listaProductos[position]
        holder.nombre.text = producto.nombre
        holder.marca.text = producto.marca
        holder.precio.text = "$${producto.precio}"

        holder.btnAgregar.setOnClickListener {
            // AGREGADO: Llamamos al manager para guardar el producto
            CarritoManager.agregarProducto(producto)

            // Opcional: Mostrar un aviso rápido al usuario
            Toast.makeText(holder.itemView.context,
                "${producto.nombre} añadido al carrito",
                Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount() = listaProductos.size
}