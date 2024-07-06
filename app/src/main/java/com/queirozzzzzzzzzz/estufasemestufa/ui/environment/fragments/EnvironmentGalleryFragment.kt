package com.queirozzzzzzzzzz.estufasemestufa.ui.environment.fragments

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.core.content.contentValuesOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.queirozzzzzzzzzz.estufasemestufa.R
import com.queirozzzzzzzzzz.estufasemestufa.adapters.PicturesAdapter
import com.queirozzzzzzzzzz.estufasemestufa.databinding.FragmentEnvironmentGalleryBinding
import com.queirozzzzzzzzzz.estufasemestufa.models.tables.Picture
import com.queirozzzzzzzzzz.estufasemestufa.repository.PictureRepository
import com.queirozzzzzzzzzz.estufasemestufa.utils.Const.CAMERA_REQUEST_CODE
import com.queirozzzzzzzzzz.estufasemestufa.utils.TemporaryData
import com.queirozzzzzzzzzz.estufasemestufa.utils.TemporaryManageEnvironmentData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class EnvironmentGalleryFragment : Fragment() {
    private var _binding: FragmentEnvironmentGalleryBinding? = null
    private val binding get() = _binding!!
    private lateinit var pictureRepository: PictureRepository
    private lateinit var requestPermissionLauncher: ActivityResultLauncher<String>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentEnvironmentGalleryBinding.inflate(inflater, container, false)

        pictureRepository = PictureRepository(this.requireActivity().application)

        requestPermissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
                if (isGranted) {
                    takePicture()
                }
            }

        setElements()

        return binding.root
    }

    private fun setElements() {
        lifecycleScope.launch {
            val recyclerView: RecyclerView = binding.picturesRecyclerView
            recyclerView.layoutManager = LinearLayoutManager(requireContext())

            val pictures =
                pictureRepository.getEnvironmentPictures(TemporaryData.selectedEnvironmentId!!)
            val adapter = PicturesAdapter(pictures)
            recyclerView.adapter = adapter
        }

        binding.takePicture.setOnClickListener {
            takePicture()
        }
    }

    private fun takePicture() {
        if (ContextCompat.checkSelfPermission(
                requireContext(), Manifest.permission.CAMERA,
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            val photoFile: File? =
                try {
                    createImageFile()
                } catch (e: IOException) {
                    null
                }

            photoFile?.also {
                val photoURI: Uri =
                    FileProvider.getUriForFile(
                        requireContext(),
                        "com.queirozzzzzzzzzz.estufasemestufa.fileprovider",
                        it,
                    )

                savePicture(photoURI)

                val takePictureIntent =
                    Intent(MediaStore.ACTION_IMAGE_CAPTURE).apply {
                        putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    }

                startActivityForResult(takePictureIntent, CAMERA_REQUEST_CODE)
            }
        } else {
            requestPermissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }

    private fun savePicture(photoURI: Uri) {
        TemporaryManageEnvironmentData.picturePath = photoURI

        lifecycleScope.launch {
            val picture =
                Picture(
                    0,
                    photoURI,
                    System.currentTimeMillis(),
                    TemporaryData.selectedEnvironmentId!!,
                )

            pictureRepository.insertPicture(
                picture,
            )
        }
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?,
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CAMERA_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val imageUri = TemporaryManageEnvironmentData.picturePath

            lifecycleScope.launch {
                val imageData =
                    getByteArrayFromUri(
                        this@EnvironmentGalleryFragment.requireContext(),
                        imageUri!!,
                    )
                val fileName = "ENVIRONMENT_${System.currentTimeMillis()}.jpg"
                val savedImageUri =
                    savePhotoToMediaStore(
                        this@EnvironmentGalleryFragment.requireContext(),
                        fileName,
                        imageData,
                    )
                TemporaryManageEnvironmentData.picturePath = savedImageUri
            }
        }
    }

    @Throws(IOException::class)
    private fun createImageFile(): File {
        val timeStamp: String =
            SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val storageDir: File? = requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "ENVIRONMENT_${timeStamp}_",
            ".jpg",
            storageDir,
        )
    }

    private suspend fun savePhotoToMediaStore(
        context: Context,
        fileName: String,
        imageData: ByteArray,
    ): Uri? {
        return withContext(Dispatchers.IO) {
            val contentValues =
                contentValuesOf(
                    MediaStore.MediaColumns.DISPLAY_NAME to fileName,
                    MediaStore.MediaColumns.MIME_TYPE to "image/jpeg",
                    MediaStore.MediaColumns.RELATIVE_PATH to "Pictures/${context.getString(R.string.app_name)}",
                )

            contentValues.put(MediaStore.MediaColumns.IS_PENDING, 1)

            val resolver = context.contentResolver
            val imageUri: Uri? =
                resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

            imageUri?.let { uri ->
                resolver.openOutputStream(uri)?.use { outputStream ->
                    outputStream.write(imageData)
                }

                contentValues.clear()
                contentValues.put(MediaStore.MediaColumns.IS_PENDING, 0)
                resolver.update(uri, contentValues, null, null)
            }

            imageUri
        }
    }

    private suspend fun getByteArrayFromUri(
        context: Context,
        uri: Uri,
    ): ByteArray =
        withContext(Dispatchers.IO) {
            context.contentResolver.openInputStream(uri)?.use { inputStream ->
                inputStream.readBytes()
            } ?: byteArrayOf()
        }
}
