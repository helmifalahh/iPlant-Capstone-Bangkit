package com.acer.iplant.ui

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import com.acer.iplant.databinding.ActivityMediaBinding
import com.acer.iplant.helper.createTempFile
import java.io.File

class MediaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMediaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMediaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.btnBack.setOnClickListener{
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
        }

        binding.btnDefCam.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            intent.resolveActivity(packageManager)

            createTempFile(application).also {
                val photoURI: Uri = FileProvider.getUriForFile(
                    this@MediaActivity,
                    "com.acer.iplant",
                    it
                )
                currentPhotoPath = it.absolutePath
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                launcherIntentCamera.launch(intent)
            }
        }

        binding.btnCamx.setOnClickListener {
            val intent = Intent(this, CameraActivity::class.java)
            startActivity(intent)
        }
    }

    private lateinit var currentPhotoPath: String
    private val launcherIntentCamera = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == RESULT_OK) {
            val myFile = File(currentPhotoPath)

            val result =  BitmapFactory.decodeFile(myFile.path)
//            Silakan gunakan kode ini jika mengalami perubahan rotasi
//            val result = rotateBitmap(
//                BitmapFactory.decodeFile(myFile.path),
//                true
//            )

            val intent = Intent(this@MediaActivity, ResultActivity::class.java)
            intent.putExtra(ResultActivity.PHOTO_RESULT_EXTRA, myFile)
            startActivity(intent)
            finish()
        }
    }
}