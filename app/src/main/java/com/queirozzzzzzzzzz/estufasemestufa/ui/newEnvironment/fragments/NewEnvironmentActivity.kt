package com.queirozzzzzzzzzz.estufasemestufa.ui.newEnvironment.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.queirozzzzzzzzzz.estufasemestufa.R
import com.queirozzzzzzzzzz.estufasemestufa.databinding.ActivityNewEnvironmentBinding
import com.queirozzzzzzzzzz.estufasemestufa.ui.environments.EnvironmentsActivity
import com.queirozzzzzzzzzz.estufasemestufa.ui.home.HomeActivity

class NewEnvironmentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewEnvironmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewEnvironmentBinding.inflate(layoutInflater)
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

    fun cancelNewEnvironment(view: View) {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }

    fun placeFragment(view: View?) {
        startFragment(NewEnvironmentPlaceFragment())
    }

    fun goalFragment(view: View) {
        startFragment(NewEnvironmentGoalFragment())
    }

    fun plantsFragment(view: View) {
        startFragment(NewEnvironmentPlantsFragment())
    }

    fun newPlantFragment(view: View) {
        startFragment(NewEnvironmentNewPlantFragment())
    }

    fun editPlantFragment(view: View) {
        startFragment(NewEnvironmentEditPlantFragment())
    }

    fun timetablesFragment(view: View) {
        startFragment(NewEnvironmentTimetablesFragment())
    }

    fun newTimetableFragment(view: View) {
        startFragment(NewEnvironmentNewTimetableFragment())
    }

    fun editTimetableFragment(view: View) {
        startFragment(NewEnvironmentEditTimetableFragment())
    }

    fun nameFragment(view: View) {
        startFragment(NewEnvironmentNameFragment())
    }

    fun finish(view: View) {
        val intent = Intent(this, EnvironmentsActivity::class.java)
        startActivity(intent)
    }
}
