package com.queirozzzzzzzzzz.estufasemestufa

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.queirozzzzzzzzzz.estufasemestufa.databinding.ActivityEnvironmentBinding
import com.queirozzzzzzzzzz.estufasemestufa.fragments.EditPlantFragment
import com.queirozzzzzzzzzz.estufasemestufa.fragments.EnvironmentCreateTaskFragment
import com.queirozzzzzzzzzz.estufasemestufa.fragments.EnvironmentEditTaskFragment
import com.queirozzzzzzzzzz.estufasemestufa.fragments.EnvironmentGalleryFragment
import com.queirozzzzzzzzzz.estufasemestufa.fragments.EnvironmentMainFragment
import com.queirozzzzzzzzzz.estufasemestufa.fragments.EnvironmentManageTasksFragment
import com.queirozzzzzzzzzz.estufasemestufa.fragments.EnvironmentTasksFragment
import com.queirozzzzzzzzzz.estufasemestufa.fragments.EnvironmentTasksHistoryFragment

class EnvironmentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEnvironmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEnvironmentBinding.inflate(layoutInflater)
        val view = binding.root

        startFragment(EnvironmentMainFragment())

        setContentView(view)
    }

    private fun startFragment(fragment: Fragment) {
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.replace(R.id.frameLayout, fragment)

        fragmentTransaction.commit()
    }

    fun mainFragment(view: View?) {
        startFragment(EnvironmentMainFragment())
    }

    fun editPlantFragment(view: View) {
        startFragment(EditPlantFragment())
    }

    fun galleryFragment(view: View) {
        startFragment(EnvironmentGalleryFragment())
    }

    fun tasksFragment(view: View) {
        startFragment(EnvironmentTasksFragment())
    }

    fun tasksHistoryFragment(view: View) {
        startFragment(EnvironmentTasksHistoryFragment())
    }

    fun manageTasksFragment(view: View) {
        startFragment(EnvironmentManageTasksFragment())
    }

    fun createTaskFragment(view: View) {
        startFragment(EnvironmentCreateTaskFragment())
    }

    fun editTaskFragment(view: View) {
        startFragment(EnvironmentEditTaskFragment())
    }
}