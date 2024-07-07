package com.queirozzzzzzzzzz.estufasemestufa.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.queirozzzzzzzzzz.estufasemestufa.R
import com.queirozzzzzzzzzz.estufasemestufa.models.tables.Task

class TaskAdapter(private val tasks: List<Task>) :
    RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {
    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val taskNameTextView: TextView = itemView.findViewById(R.id.task_name)
        val editButton: Button = itemView.findViewById(R.id.task_edit_btn)
        val deleteButton: Button = itemView.findViewById(R.id.task_delete_btn)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): TaskViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.task_item, parent, false)
        return TaskViewHolder(itemView)
    }

    override fun onBindViewHolder(
        holder: TaskViewHolder,
        position: Int,
    ) {
        val task = tasks[position]
        holder.taskNameTextView.text = task.name
        holder.editButton.tag = task.id
        holder.deleteButton.tag = task.id
    }

    override fun getItemCount(): Int = tasks.size
}
