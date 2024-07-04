package com.queirozzzzzzzzzz.estufasemestufa.ui.manageEnvironment.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.queirozzzzzzzzzz.estufasemestufa.R
import com.queirozzzzzzzzzz.estufasemestufa.data.converters.LongTimeConverter
import com.queirozzzzzzzzzz.estufasemestufa.databinding.FragmentManageEnvironmentNewTimetableBinding
import com.queirozzzzzzzzzz.estufasemestufa.models.tables.Timetable
import com.queirozzzzzzzzzz.estufasemestufa.utils.TemporaryManageEnvironmentData
import java.time.LocalDateTime
import java.time.ZoneId

class ManageEnvironmentNewTimetableFragment : Fragment() {
    private var _binding: FragmentManageEnvironmentNewTimetableBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentManageEnvironmentNewTimetableBinding.inflate(inflater, container, false)

        setElements()

        return binding.root
    }

    private fun setElements() {
        if (TemporaryManageEnvironmentData.isEditing) {
            binding.headerTitle.text = resources.getString(R.string.manage_environment_edit_title)
        }

        binding.btnCreate.setOnClickListener {
            createTimetable()
        }
    }

    private fun createTimetable() {

        val startTime = LongTimeConverter.toLongTime(binding.startTime.hour, binding.startTime.minute)
        val finishTime = LongTimeConverter.toLongTime(binding.finishTime.hour, binding.finishTime.minute)

        val timetable = Timetable(0, startTime, finishTime, 0)
        val timetables = TemporaryManageEnvironmentData.timetables?.toMutableList()
        if(timetable in timetables!!) {
            Toast.makeText(requireContext(), R.string.timetable_already_exists, Toast.LENGTH_SHORT)
                .show()
            return
        } else {
            timetables.add(timetable)
            TemporaryManageEnvironmentData.timetables = timetables
            binding.btnCancel.performClick()
        }

    }

}
