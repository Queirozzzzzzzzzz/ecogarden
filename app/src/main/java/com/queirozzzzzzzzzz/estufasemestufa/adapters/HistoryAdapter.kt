package com.queirozzzzzzzzzz.estufasemestufa.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.queirozzzzzzzzzz.estufasemestufa.R
import com.queirozzzzzzzzzz.estufasemestufa.data.converters.LongDateConverter
import com.queirozzzzzzzzzz.estufasemestufa.models.tables.Task
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

class HistoryAdapter(private val historyTasks: List<Task>) :
    RecyclerView.Adapter<HistoryAdapter.HistoryTaskViewHolder>() {
    class HistoryTaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.task_name)
        val taskTitleTextView: TextView = itemView.findViewById(R.id.task_title)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): HistoryTaskViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.history_task_item, parent, false)
        return HistoryTaskViewHolder(itemView)
    }

    override fun onBindViewHolder(
        holder: HistoryTaskViewHolder,
        position: Int,
    ) {
        val historyTask = historyTasks[position]
        holder.nameTextView.text = historyTask.name
        holder.taskTitleTextView.text = formatDateTime(LongDateConverter.toDate(historyTask.completionDate))
    }

    override fun getItemCount(): Int = historyTasks.size

    fun formatDateTime(
        dateTime: LocalDateTime?,
        locale: Locale = Locale("pt", "BR"),
    ): String? {
        val formatter = DateTimeFormatter.ofPattern("EEEE (dd/MM/yyyy)", locale)
        val formattedString = dateTime?.format(formatter)
        return formattedString?.replaceFirstChar { it.uppercase() }
    }
}
