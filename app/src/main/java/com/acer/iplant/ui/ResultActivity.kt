package com.acer.iplant.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import com.acer.iplant.R
import com.acer.iplant.databinding.ActivityResultBinding
import com.acer.iplant.helper.AppExecutors
import com.acer.iplant.helper.Classifier
import com.acer.iplant.helper.reduceFileImage
import com.acer.iplant.helper.rotateBitmap
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

        binding.cvInfo.visibility = View.INVISIBLE

        supportActionBar?.setTitle("Result")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun initClassifier() {
        classifier = Classifier(assets, mModelPath, mLabelPath, mInputSize)
    }

    @SuppressLint("SetTextI18n")
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

            val disease = binding.tvResult
            val medicine = binding.tvRecomendation
            val marks = binding.titleMarks

            if (predict.get(0).title == "Early Blight"){
                runOnUiThread{disease.setText("Your leaf is " + predict.get(0).title)}
                binding.btnPredict.visibility = View.GONE
                disease.setOnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/search?q=${predict.get(0).title}"))
                    startActivity(intent)
                }
                runOnUiThread{medicine.setText("Amistar Top 325 SC 250 ml, Miravis Duo 100 ml, Ripnazol 422EC, Cabrio 250 EC, Rampart 25 WP 100 gr")}
                runOnUiThread { marks.setText(R.string.marks_early) }
                binding.cvInfo.visibility = View.VISIBLE
            } else if(predict.get(0).title == "Late Blight"){
                runOnUiThread{disease.setText("Your leaf is " + predict.get(0).title)}
                binding.btnPredict.visibility = View.GONE
                disease.setOnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/search?q=${predict.get(0).title}"))
                    startActivity(intent)
                }
                runOnUiThread{medicine.setText("Bion M 1/48 WP 500 gr, Dorum 350 SC, Kapuas 560 SC, Zampro 525 SC, Ridomild  Gold MZ 4/64 WG, Phytoklor 82.5 WG")}
                runOnUiThread { marks.setText(R.string.marks_late) }
                binding.cvInfo.visibility = View.VISIBLE
            } else{
                runOnUiThread{disease.setText("Your leaf is " + predict.get(0).title)}
                binding.btnPredict.visibility = View.GONE
                disease.setOnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/search?q=${predict.get(0).title}"))
                    startActivity(intent)
                }
                runOnUiThread{medicine.setText(R.string.healthy)}
                binding.cvInfo.visibility = View.VISIBLE
            }
        }
        binding.previewImageView.setImageBitmap(result)
    }

    companion object {
        const val PHOTO_RESULT_EXTRA = "photo_result_extra"
        const val IS_CAMERA_BACK_EXTRA = "is_camera_back_extra"
    }
}