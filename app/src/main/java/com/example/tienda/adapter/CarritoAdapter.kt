package com.example.tienda.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tienda.R
import com.example.tienda.model.CarritoManager

class CarritoAdapter(
    private var items: List<CarritoManager.ItemCarrito>,
    private val onUpdate: () -> Unit // Función para avisar a la pantalla que el total cambió
) : RecyclerView.Adapter<CarritoAdapter.CarritoViewHolder>() {

    class CarritoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nombre: TextView = view.findViewById(R.id.tvNombreCarrito)
        val precio: TextView = view.findViewById(R.id.tvPrecioUnitario)
        val cantidad: TextView = view.findViewById(R.id.tvCantidadCarrito)
        val btnMas: Button = view.findViewById(R.id.btnMas)
        val btnMenos: Button = view.findViewById(R.id.btnMenos)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarritoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_carrito, parent, false)
        return CarritoViewHolder(view)
    }

    override fun onBindViewHolder(holder: CarritoViewHolder, position: Int) {
        val item = items[position]
        holder.nombre.text = item.producto.nombre
        holder.precio.text = "$${item.producto.precio}"
        holder.cantidad.text = item.cantidad.toString()

        holder.btnMas.setOnClickListener {
            item.cantidad++
            notifyItemChanged(position)
            onUpdate()
        }

        holder.btnMenos.setOnClickListener {
            if (item.cantidad > 1) {
                item.cantidad--
                notifyItemChanged(position)
            } else {
                CarritoManager.eliminarProducto(item.producto.id)
                items = CarritoManager.obtenerItems()
                notifyDataSetChanged()
            }
            onUpdate()
        }
    }

    override fun getItemCount() = items.size
}