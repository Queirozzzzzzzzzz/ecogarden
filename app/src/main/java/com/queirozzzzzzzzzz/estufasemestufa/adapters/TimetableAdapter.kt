package com.queirozzzzzzzzzz.estufasemestufa.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.queirozzzzzzzzzz.estufasemestufa.R
import com.queirozzzzzzzzzz.estufasemestufa.data.converters.LongTimeConverter
import com.queirozzzzzzzzzz.estufasemestufa.models.tables.Timetable

class TimetableAdapter(private val timetables: List<Timetable>) :
    RecyclerView.Adapter<TimetableAdapter.TimetableViewHolder>() {
    class TimetableViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val timetableNameTextView: TextView = itemView.findViewById(R.id.timetable_name)
        val editButton: Button = itemView.findViewById(R.id.timetable_edit_btn)
        val deleteButton: Button = itemView.findViewById(R.id.timetable_delete_btn)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): TimetableViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.timetable_item, parent, false)
        return TimetableViewHolder(itemView)
    }

    override fun onBindViewHolder(
        holder: TimetableViewHolder,
        position: Int,
    ) {
        val timetable = timetables[position]
        val timetableDate = LongTimeConverter.fromLongTime(
            timetable.startTime.toString().toLong()
        ) + " - " + LongTimeConverter.fromLongTime(timetable.finishTime.toString().toLong())

        holder.timetableNameTextView.text = timetableDate
        holder.editButton.tag =
            timetable.startTime.toString() + "-" + timetable.finishTime.toString()
        holder.deleteButton.tag =
            timetable.startTime.toString() + "-" + timetable.finishTime.toString()
    }

    override fun getItemCount(): Int = timetables.size
}
