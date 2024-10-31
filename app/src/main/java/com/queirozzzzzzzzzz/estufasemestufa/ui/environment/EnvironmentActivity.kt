package com.queirozzzzzzzzzz.estufasemestufa.ui.environment

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.lifecycleScope
import com.queirozzzzzzzzzz.estufasemestufa.R
import com.queirozzzzzzzzzz.estufasemestufa.data.Preferences
import com.queirozzzzzzzzzz.estufasemestufa.databinding.ActivityEnvironmentBinding
import com.queirozzzzzzzzzz.estufasemestufa.repository.CompletedTaskRepository
import com.queirozzzzzzzzzz.estufasemestufa.repository.EnvironmentRepository
import com.queirozzzzzzzzzz.estufasemestufa.repository.PictureRepository
import com.queirozzzzzzzzzz.estufasemestufa.repository.PlantRepository
import com.queirozzzzzzzzzz.estufasemestufa.repository.TaskRepository
import com.queirozzzzzzzzzz.estufasemestufa.repository.TimetableRepository
import com.queirozzzzzzzzzz.estufasemestufa.ui.environment.fragments.EnvironmentCreateTaskFragment
import com.queirozzzzzzzzzz.estufasemestufa.ui.environment.fragments.EnvironmentEditTaskFragment
import com.queirozzzzzzzzzz.estufasemestufa.ui.environment.fragments.EnvironmentGalleryFragment
import com.queirozzzzzzzzzz.estufasemestufa.ui.environment.fragments.EnvironmentMainFragment
import com.queirozzzzzzzzzz.estufasemestufa.ui.environment.fragments.EnvironmentManageTasksFragment
import com.queirozzzzzzzzzz.estufasemestufa.ui.environment.fragments.EnvironmentTasksFragment
import com.queirozzzzzzzzzz.estufasemestufa.ui.environment.fragments.EnvironmentTasksHistoryFragment
import com.queirozzzzzzzzzz.estufasemestufa.ui.home.HomeActivity
import com.queirozzzzzzzzzz.estufasemestufa.ui.manageEnvironment.ManageEnvironmentActivity
import com.queirozzzzzzzzzz.estufasemestufa.ui.manageEnvironment.ManageEnvironmentViewModel
import com.queirozzzzzzzzzz.estufasemestufa.utils.TemporaryData
import kotlinx.coroutines.launch

class EnvironmentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEnvironmentBinding
    private lateinit var manageEnvironmentViewModel: ManageEnvironmentViewModel
    private lateinit var viewModel: EnvironmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEnvironmentBinding.inflate(layoutInflater)
        val view = binding.root

        viewModel =
            EnvironmentViewModel(
                EnvironmentRepository(application),
                PictureRepository(application),
                TaskRepository(application),
                CompletedTaskRepository(application),
            )
        manageEnvironmentViewModel =
            ManageEnvironmentViewModel(
                EnvironmentRepository(application),
                PictureRepository(application),
                PlantRepository(application),
                TimetableRepository(application),
            )

        setEnvironment()

        setContentView(view)
    }

    private fun setEnvironment() {
        viewModel.setEnvironmentName {
            mainFragment(null)
        }
    }

    private fun startFragment(fragment: Fragment) {
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.replace(R.id.frameLayout, fragment)

        fragmentTransaction.commit()
    }

    fun editEnvironmentActivity(view: View) {
        lifecycleScope.launch {
            manageEnvironmentViewModel.setEditEnvironment()
        }

        val intent = Intent(this, ManageEnvironmentActivity::class.java)
        startActivity(intent)
    }

    fun mainFragment(view: View?) {
        startFragment(EnvironmentMainFragment())
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
        TemporaryData.selectedTaskId = view.tag.toString().toInt()
        startFragment(EnvironmentEditTaskFragment())
    }

    fun deleteTask(view: View) {
        viewModel.deleteTask(view.tag.toString().toInt())
        manageTasksFragment(view)
    }

    fun deleteEnvironment(view: View) {
        dialogBox()
    }

    private fun deleteEnvironmentAccepted() {
        lifecycleScope.launch {
            manageEnvironmentViewModel.deleteEnvironment {
                deleteEnvironmentActivity()
            }
        }
    }

    private fun dialogBox() {
        val alert: AlertDialog.Builder = AlertDialog.Builder(this)
        alert.setTitle(title)
        alert.setMessage(getString(R.string.delete_environment_confirmation))

        alert.setNegativeButton(
            getString(R.string.cancel)
        ) { dialog, whichButton -> }

        alert.setPositiveButton(
            getString(R.string.yes)
        ) { dialog, whichButton ->
            deleteEnvironmentAccepted()
        }

        alert.show()
    }

    private fun deleteEnvironmentActivity() {
        val intent = Intent(this, HomeActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    fun deletePicture(view: View) {
        TemporaryData.selectedPictureId = view.tag.toString().toInt()
        viewModel.deletePicture()
        galleryFragment(view)
    }

    fun completeTask(view: View) {
        viewModel.completeTask(view.tag.toString().toInt())
        tasksFragment(view)
    }
}
