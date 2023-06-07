package com.acer.iplant.ui

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import com.acer.iplant.R
import com.acer.iplant.databinding.ActivityResultBinding
import com.acer.iplant.helper.AppExecutors
import com.acer.iplant.helper.Classifier
import com.acer.iplant.helper.reduceFileImage
import com.acer.iplant.helper.rotateBitmap
import com.acer.iplant.ui.CameraActivity.Companion.CAMERA_X_RESULT
import java.io.File
import java.io.FileOutputStream

class ResultActivity : AppCompatActivity() {

    private var file: File? = null
    private var isBack: Boolean = true
    private var reducingDone: Boolean = false

    private val mInputSize = 256
    private val mModelPath = "model_4.tflite"
    private val mLabelPath = "label1.txt"
    private lateinit var classifier: Classifier
    lateinit var bitmap: Bitmap

    private val appExecutor: AppExecutors by lazy {
        AppExecutors()
    }

    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bindResult()
        initClassifier()

        supportActionBar?.hide()
    }

    private fun initClassifier() {
        classifier = Classifier(assets, mModelPath, mLabelPath, mInputSize)
    }

    private fun bindResult() {
        file = intent.getSerializableExtra(PHOTO_RESULT_EXTRA) as File
        isBack = intent.getBooleanExtra(IS_CAMERA_BACK_EXTRA, true)

        val result = rotateBitmap(BitmapFactory.decodeFile((file as File).path), isBack)
        result.compress(Bitmap.CompressFormat.JPEG, 100, FileOutputStream(file))

        appExecutor.diskIO.execute {
            file = reduceFileImage(file as File)
            reducingDone = true
        }

        binding.btnPredict.setOnClickListener {
            val predict = classifier.recognizeImage(result)

            val text = findViewById<TextView>(R.id.tv_result)

            runOnUiThread{text.setText("Your leaf is " + predict.get(0).title)}
        }

        binding.previewImageView.setImageBitmap(result)
    }

    companion object {
        const val PHOTO_RESULT_EXTRA = "photo_result_extra"
        const val IS_CAMERA_BACK_EXTRA = "is_camera_back_extra"
    }
}