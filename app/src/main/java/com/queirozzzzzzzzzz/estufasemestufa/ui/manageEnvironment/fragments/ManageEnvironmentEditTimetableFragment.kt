package com.queirozzzzzzzzzz.estufasemestufa.ui.manageEnvironment.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.queirozzzzzzzzzz.estufasemestufa.R
import com.queirozzzzzzzzzz.estufasemestufa.data.converters.LongTimeConverter
import com.queirozzzzzzzzzz.estufasemestufa.databinding.FragmentManageEnvironmentEditTimetableBinding
import com.queirozzzzzzzzzz.estufasemestufa.models.tables.Timetable
import com.queirozzzzzzzzzz.estufasemestufa.utils.TemporaryManageEnvironmentData

class ManageEnvironmentEditTimetableFragment : Fragment() {
    private var _binding: FragmentManageEnvironmentEditTimetableBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentManageEnvironmentEditTimetableBinding.inflate(inflater, container, false)

        setElements()

        return binding.root
    }

    private fun setElements() {
        if (TemporaryManageEnvironmentData.isEditing) {
            binding.headerTitle.text = resources.getString(R.string.manage_environment_edit_title)
        }

        setTimetables()

        binding.btnSave.setOnClickListener {
            saveTimetable()
        }
    }

    var selectedStartTime = 0L
    var selectedFinishTime = 0L
    private fun setSelectedTimes() {
        val selectedTime = TemporaryManageEnvironmentData.selectedTimetable
        val selectedTimeParts = selectedTime?.split("-")
        selectedStartTime = selectedTimeParts?.get(0)?.toLong()!!
        selectedFinishTime = selectedTimeParts?.get(1)?.toLong()!!
    }

    var timetable: Timetable? = null
    private var timetableIndex = 0
    private fun setTimetables() {
        setSelectedTimes()
        timetable =
            TemporaryManageEnvironmentData.timetables?.firstOrNull { it.startTime == selectedStartTime && it.finishTime == selectedFinishTime }
        timetableIndex =
            TemporaryManageEnvironmentData.timetables?.indexOfFirst { it.startTime == selectedStartTime && it.finishTime == selectedFinishTime }!!

        binding.startTime.let { it ->
            val timeString =
                timetable?.startTime?.let { LongTimeConverter.fromLongTime(it) } ?: "00:00"
            val (hours, minutes) = timeString.split(":").map { it.toInt() }

            it.hour = hours
            it.minute = minutes
        }

        binding.finishTime.let { it ->
            val timeString =
                timetable?.finishTime?.let { LongTimeConverter.fromLongTime(it) } ?: "00:00"
            val (hours, minutes) = timeString.split(":").map { it.toInt() }

            it.hour = hours
            it.minute = minutes
        }
    }

    private fun saveTimetable() {
        val startTime =
            LongTimeConverter.toLongTime(binding.startTime.hour, binding.startTime.minute)
        val finishTime =
            LongTimeConverter.toLongTime(binding.finishTime.hour, binding.finishTime.minute)
        val editedTimetable = Timetable(0, startTime, finishTime, 0)
        val timetables = TemporaryManageEnvironmentData.timetables?.toMutableList()

        timetables?.set(timetableIndex, editedTimetable)

        TemporaryManageEnvironmentData.timetables = timetables
        binding.btnCancel.performClick()
    }

}
