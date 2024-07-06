package com.queirozzzzzzzzzz.estufasemestufa.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.queirozzzzzzzzzz.estufasemestufa.R
import com.queirozzzzzzzzzz.estufasemestufa.models.tables.Environment

class EnvironmentsAdapter(private val environments: List<Environment>) :
    RecyclerView.Adapter<EnvironmentsAdapter.EnvironmentViewHolder>() {
    class EnvironmentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val environmentButton: TextView = itemView.findViewById(R.id.environment_btn)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): EnvironmentViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.environment_item, parent, false)
        return EnvironmentViewHolder(itemView)
    }

    override fun onBindViewHolder(
        holder: EnvironmentViewHolder,
        position: Int,
    ) {
        val environment = environments[position]
        holder.environmentButton.text = environment.name
        holder.environmentButton.tag = environment.id
    }

    override fun getItemCount(): Int = environments.size
}
