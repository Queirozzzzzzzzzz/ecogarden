package com.queirozzzzzzzzzz.estufasemestufa.ui.environment

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.queirozzzzzzzzzz.estufasemestufa.R
import com.queirozzzzzzzzzz.estufasemestufa.databinding.ActivityEnvironmentBinding
import com.queirozzzzzzzzzz.estufasemestufa.ui.editEnvironment.EditEnvironmentActivity
import com.queirozzzzzzzzzz.estufasemestufa.ui.environment.fragments.EnvironmentCreateTaskFragment
import com.queirozzzzzzzzzz.estufasemestufa.ui.environment.fragments.EnvironmentEditTaskFragment
import com.queirozzzzzzzzzz.estufasemestufa.ui.environment.fragments.EnvironmentGalleryFragment
import com.queirozzzzzzzzzz.estufasemestufa.ui.environment.fragments.EnvironmentMainFragment
import com.queirozzzzzzzzzz.estufasemestufa.ui.environment.fragments.EnvironmentManageTasksFragment
import com.queirozzzzzzzzzz.estufasemestufa.ui.environment.fragments.EnvironmentTasksFragment
import com.queirozzzzzzzzzz.estufasemestufa.ui.environment.fragments.EnvironmentTasksHistoryFragment
import com.queirozzzzzzzzzz.estufasemestufa.ui.newEnvironment.fragments.NewEnvironmentEditPlantFragment

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

    fun editEnvironmentActivity(view: View) {
        val intent = Intent(this, EditEnvironmentActivity::class.java)
        startActivity(intent)
    }

    fun mainFragment(view: View?) {
        startFragment(EnvironmentMainFragment())
    }

    fun editPlantFragment(view: View) {
        startFragment(NewEnvironmentEditPlantFragment())
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
