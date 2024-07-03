package com.queirozzzzzzzzzz.estufasemestufa.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.queirozzzzzzzzzz.estufasemestufa.R
import com.queirozzzzzzzzzz.estufasemestufa.models.tables.Plant

class PlantAdapter(private val plants: List<Plant>) :
    RecyclerView.Adapter<PlantAdapter.PlantViewHolder>() {
    class PlantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val plantNameTextView: TextView = itemView.findViewById(R.id.plant_name)
        val editButton: Button = itemView.findViewById(R.id.plant_edit_btn)
        val deleteButton: Button = itemView.findViewById(R.id.plant_delete_btn)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): PlantViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.plant_item, parent, false)
        return PlantViewHolder(itemView)
    }

    override fun onBindViewHolder(
        holder: PlantViewHolder,
        position: Int,
    ) {
        val plant = plants[position]
        holder.plantNameTextView.text = plant.name
        holder.editButton.tag = plant.name
        holder.deleteButton.tag = plant.name
    }

    override fun getItemCount(): Int = plants.size
}
