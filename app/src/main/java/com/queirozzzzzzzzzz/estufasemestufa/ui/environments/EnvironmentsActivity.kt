package com.queirozzzzzzzzzz.estufasemestufa.ui.environments

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.queirozzzzzzzzzz.estufasemestufa.adapters.EnvironmentsAdapter
import com.queirozzzzzzzzzz.estufasemestufa.databinding.ActivityEnvironmentsBinding
import com.queirozzzzzzzzzz.estufasemestufa.repository.EnvironmentRepository
import com.queirozzzzzzzzzz.estufasemestufa.ui.environment.EnvironmentActivity
import com.queirozzzzzzzzzz.estufasemestufa.utils.TemporaryData
import kotlinx.coroutines.launch

class EnvironmentsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEnvironmentsBinding
    private lateinit var environmentRepository: EnvironmentRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEnvironmentsBinding.inflate(layoutInflater)
        val view = binding.root

        environmentRepository = EnvironmentRepository(application)

        setElements()

        setContentView(view)
    }

    private fun setElements() {
        lifecycleScope.launch {
            val environments = environmentRepository.getAllEnvironments()
            val recyclerView = binding.environmentsRecyclerView
            recyclerView.layoutManager = LinearLayoutManager(applicationContext)

            val adapter = EnvironmentsAdapter(environments)
            recyclerView.adapter = adapter
        }
    }

    fun startEnvironment(view: View) {
        TemporaryData.selectedEnvironmentId = view.tag.toString().toInt()
        val intent = Intent(this, EnvironmentActivity::class.java)
        startActivity(intent)
    }
}
