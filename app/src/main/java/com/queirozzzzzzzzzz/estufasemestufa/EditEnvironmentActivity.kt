package com.queirozzzzzzzzzz.estufasemestufa

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.queirozzzzzzzzzz.estufasemestufa.databinding.ActivityEditEnvironmentBinding
import com.queirozzzzzzzzzz.estufasemestufa.fragments.EditEnvironmentGoalFragment
import com.queirozzzzzzzzzz.estufasemestufa.fragments.EditEnvironmentPlaceFragment
import com.queirozzzzzzzzzz.estufasemestufa.fragments.EditEnvironmentPlantsFragment
import com.queirozzzzzzzzzz.estufasemestufa.fragments.EditEnvironmentTimetablesFragment
import com.queirozzzzzzzzzz.estufasemestufa.fragments.EditEnvironmentEditPlantFragment
import com.queirozzzzzzzzzz.estufasemestufa.fragments.EditEnvironmentEditTimetableFragment
import com.queirozzzzzzzzzz.estufasemestufa.fragments.EditEnvironmentNameFragment
import com.queirozzzzzzzzzz.estufasemestufa.fragments.EditEnvironmentNewPlantFragment
import com.queirozzzzzzzzzz.estufasemestufa.fragments.EditEnvironmentNewTimetableFragment

class EditEnvironmentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditEnvironmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditEnvironmentBinding.inflate(layoutInflater)
        val view = binding.root

        placeFragment(null)

        setContentView(view)
    }

    private fun startFragment(fragment: Fragment) {
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.replace(R.id.frameLayout, fragment)

        fragmentTransaction.commit()
    }

    fun cancelEditEnvironment(view: View) {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }

    fun placeFragment(view: View?) {
        startFragment(EditEnvironmentPlaceFragment())
    }

    fun goalFragment(view: View) {
        startFragment(EditEnvironmentGoalFragment())
    }

    fun plantsFragment(view: View) {
        startFragment(EditEnvironmentPlantsFragment())
    }

    fun newPlantFragment(view: View) {
        startFragment(EditEnvironmentNewPlantFragment())
    }

    fun editPlantFragment(view: View) {
        startFragment(EditEnvironmentEditPlantFragment())
    }

    fun timetablesFragment(view: View) {
        startFragment(EditEnvironmentTimetablesFragment())
    }

    fun newTimetableFragment(view: View) {
        startFragment(EditEnvironmentNewTimetableFragment())
    }

    fun editTimetableFragment(view: View) {
        startFragment(EditEnvironmentEditTimetableFragment())
    }

    fun nameFragment(view: View) {
        startFragment(EditEnvironmentNameFragment())
    }

    fun finish(view: View) {
        val intent = Intent(this, EnvironmentsActivity::class.java)
        startActivity(intent)
    }
}