package com.queirozzzzzzzzzz.estufasemestufa.ui.manageEnvironment

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.queirozzzzzzzzzz.estufasemestufa.R
import com.queirozzzzzzzzzz.estufasemestufa.databinding.ActivityManageEnvironmentBinding
import com.queirozzzzzzzzzz.estufasemestufa.ui.environments.EnvironmentsActivity
import com.queirozzzzzzzzzz.estufasemestufa.ui.home.HomeActivity
import com.queirozzzzzzzzzz.estufasemestufa.ui.manageEnvironment.fragments.ManageEnvironmentEditPlantFragment
import com.queirozzzzzzzzzz.estufasemestufa.ui.manageEnvironment.fragments.ManageEnvironmentEditTimetableFragment
import com.queirozzzzzzzzzz.estufasemestufa.ui.manageEnvironment.fragments.ManageEnvironmentGoalFragment
import com.queirozzzzzzzzzz.estufasemestufa.ui.manageEnvironment.fragments.ManageEnvironmentNameFragment
import com.queirozzzzzzzzzz.estufasemestufa.ui.manageEnvironment.fragments.ManageEnvironmentNewPlantFragment
import com.queirozzzzzzzzzz.estufasemestufa.ui.manageEnvironment.fragments.ManageEnvironmentNewTimetableFragment
import com.queirozzzzzzzzzz.estufasemestufa.ui.manageEnvironment.fragments.ManageEnvironmentPlaceFragment
import com.queirozzzzzzzzzz.estufasemestufa.ui.manageEnvironment.fragments.ManageEnvironmentPlantsFragment
import com.queirozzzzzzzzzz.estufasemestufa.ui.manageEnvironment.fragments.ManageEnvironmentTimetablesFragment
import com.queirozzzzzzzzzz.estufasemestufa.utils.TemporaryManageEnvironmentData

class ManageEnvironmentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityManageEnvironmentBinding
    private lateinit var fragmentManager: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityManageEnvironmentBinding.inflate(layoutInflater)
        val view = binding.root

        placeFragment(null)

        setContentView(view)
    }

    private fun startFragment(fragment: Fragment) {
        fragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, fragment)
        fragmentTransaction.commit()
    }

    fun cancelManageEnvironment(view: View) {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }

    fun placeFragment(view: View?) {
        startFragment(ManageEnvironmentPlaceFragment())
    }

    fun goalFragment(view: View) {
        startFragment(ManageEnvironmentGoalFragment())
    }

    fun plantsFragment(view: View) {
        startFragment(ManageEnvironmentPlantsFragment())
    }

    fun newPlantFragment(view: View) {
        startFragment(ManageEnvironmentNewPlantFragment())
    }

    fun editPlantFragment(view: View) {
        TemporaryManageEnvironmentData.selectedPlant = view.tag.toString()
        startFragment(ManageEnvironmentEditPlantFragment())
    }

    fun deletePlant(view: View) {
        var plants = TemporaryManageEnvironmentData.plants?.toMutableList()
        for (plant in plants!!) {
            if (plant.name == view.tag.toString()) {
                plants.remove(plant)
                TemporaryManageEnvironmentData.plants = plants
                break
            }
        }
        plantsFragment(view)
    }

    fun timetablesFragment(view: View) {
        startFragment(ManageEnvironmentTimetablesFragment())
    }

    fun newTimetableFragment(view: View) {
        startFragment(ManageEnvironmentNewTimetableFragment())
    }

    fun editTimetableFragment(view: View) {
        startFragment(ManageEnvironmentEditTimetableFragment())
    }

    fun nameFragment(view: View) {
        startFragment(ManageEnvironmentNameFragment())
    }

    fun finish(view: View) {
        val intent = Intent(this, EnvironmentsActivity::class.java)
        startActivity(intent)
    }
}
