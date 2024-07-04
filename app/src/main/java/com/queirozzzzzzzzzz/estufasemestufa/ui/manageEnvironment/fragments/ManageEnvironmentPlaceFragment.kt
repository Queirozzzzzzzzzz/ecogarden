package com.queirozzzzzzzzzz.estufasemestufa.ui.manageEnvironment.fragments

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.queirozzzzzzzzzz.estufasemestufa.R
import com.queirozzzzzzzzzz.estufasemestufa.databinding.FragmentManageEnvironmentPlaceBinding
import com.queirozzzzzzzzzz.estufasemestufa.utils.Const.CAMERA_REQUEST_CODE
import com.queirozzzzzzzzzz.estufasemestufa.utils.TemporaryManageEnvironmentData

class ManageEnvironmentPlaceFragment : Fragment() {
    private var _binding: FragmentManageEnvironmentPlaceBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentManageEnvironmentPlaceBinding.inflate(inflater, container, false)

        setElements()

        return binding.root
    }

    private fun setElements() {
        // Header
        if (TemporaryManageEnvironmentData.isEditing) {
            binding.headerTitle.text = resources.getString(R.string.manage_environment_edit_title)
            binding.picture.visibility = View.GONE
        } else {
            // Picture
            binding.pictureIcon.setOnClickListener {
                takePicture()
            }

            binding.pictureExplainMe.setOnClickListener {
                dialogBox(
                    resources.getString(R.string.picture_explain_me_title),
                    resources.getString(R.string.picture_explain_me_message),
                )
            }
        }

        // Checkbox
        binding.openEnvironmentOption.setOnClickListener {
            changeCheckbox(
                false,
                binding.openEnvironmentCheckbox,
                binding.closedEnvironmentCheckbox,
            )
        }

        binding.closedEnvironmentOption.setOnClickListener {
            changeCheckbox(true, binding.closedEnvironmentCheckbox, binding.openEnvironmentCheckbox)
        }

        if (TemporaryManageEnvironmentData.closed != null) {
            if (TemporaryManageEnvironmentData.closed!!) {
                changeCheckbox(
                    true,
                    binding.closedEnvironmentCheckbox,
                    binding.openEnvironmentCheckbox,
                )
            }
            if (!TemporaryManageEnvironmentData.closed!!) {
                changeCheckbox(
                    false,
                    binding.openEnvironmentCheckbox,
                    binding.closedEnvironmentCheckbox,
                )
            }
        }

        // Biome
        binding.biomeExplainMe.setOnClickListener {
            dialogBox(
                resources.getString(R.string.biome_explain_me_title),
                resources.getString(R.string.biome_explain_me_message),
            )
        }

        if (TemporaryManageEnvironmentData.biome != null) {
            binding.biome.setSelection(
                resources.getStringArray(R.array.biomes_array).indexOf(TemporaryManageEnvironmentData.biome),
            )
        }

        // Save Button
        binding.btnSave.setOnClickListener {
            if (TemporaryManageEnvironmentData.closed == null) {
                Toast.makeText(requireContext(), R.string.closed_required, Toast.LENGTH_SHORT).show()
            } else {
                binding.btnNext.performClick()
            }
        }
    }

    private fun changeCheckbox(
        closed: Boolean,
        activateView: ImageView,
        deactivateView: ImageView,
    ) {
        activateView.setImageResource(R.drawable.checkbox_checked)
        deactivateView.setImageResource(R.drawable.checkbox_unchecked)

        if (!closed) {
            setBiome()
        } else {
            removeBiome()
        }

        TemporaryManageEnvironmentData.closed = closed
    }

    private fun removeBiome() {
        binding.biomeOption.visibility = View.GONE
        TemporaryManageEnvironmentData.biome = null
    }

    private fun setBiome() {
        binding.biomeOption.visibility = View.VISIBLE
        val biomesList = resources.getStringArray(R.array.biomes_array)
        val adapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, biomesList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.biome.adapter = adapter

        binding.biome.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    val selectedBiome = parent?.getItemAtPosition(position).toString()
                    TemporaryManageEnvironmentData.biome = selectedBiome
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    println("Nothing selected")
                }
            }
    }

    fun dialogBox(
        title: String,
        message: String,
    ) {
        val alert: AlertDialog.Builder = AlertDialog.Builder(activity)
        alert.setTitle(title)
        alert.setMessage(message)

        alert.setNegativeButton(
            "Voltar",
        ) { dialog, whichButton -> }

        alert.show()
    }

    private fun takePicture() {
        if (ContextCompat.checkSelfPermission(
                requireContext(), Manifest.permission.CAMERA,
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent, CAMERA_REQUEST_CODE)
        } else {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.CAMERA),
                CAMERA_REQUEST_CODE,
            )
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?,
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CAMERA_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap?
            TemporaryManageEnvironmentData.picture = imageBitmap
        }
    }
}
