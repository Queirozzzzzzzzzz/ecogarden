package com.queirozzzzzzzzzz.estufasemestufa.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.queirozzzzzzzzzz.estufasemestufa.R
import com.queirozzzzzzzzzz.estufasemestufa.models.tables.Task

class TaskCompleteAdapter(private val tasks: List<Task>) :
    RecyclerView.Adapter<TaskCompleteAdapter.TaskViewHolder>() {
    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val task: View = itemView.findViewById(R.id.task)
        val taskNameTextView: TextView = itemView.findViewById(R.id.task_name)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): TaskViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.task_complete_item, parent, false)
        return TaskViewHolder(itemView)
    }

    override fun onBindViewHolder(
        holder: TaskViewHolder,
        position: Int,
    ) {
        val task = tasks[position]
        holder.task.tag = task.id
        holder.taskNameTextView.text = task.name
    }

    override fun getItemCount(): Int = tasks.size
}
