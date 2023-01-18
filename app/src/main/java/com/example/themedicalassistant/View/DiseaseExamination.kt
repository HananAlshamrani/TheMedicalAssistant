package com.example.themedicalassistant.View

import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.themedicalassistant.Model.Diseases
import com.example.themedicalassistant.Model.Repository
import com.example.themedicalassistant.Model.Results
import com.example.themedicalassistant.SymptomsAdapter
import com.example.themedicalassistant.databinding.ActivityDiseaseExaminationBinding


class DiseaseExamination : AppCompatActivity() , SymptomsAdapter.ClickListener {

    lateinit var binding: ActivityDiseaseExaminationBinding
    private var rvAdapter = SymptomsAdapter(this)
    var listSymptoms = listOf<String>()
    private var resultsList = listOf<Results>()
    private var counter = -1
    private  var answers = mutableMapOf<String , Int>()
    private var repository = Repository()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDiseaseExaminationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var result = repository.getResults()

        result.observe(this){
                results -> resultsList = results
        }

        var disease = intent.getSerializableExtra("disease") as Diseases
        listSymptoms = disease.Symptoms


        binding.btnResult.isEnabled = false
        binding.btnResult.isClickable = false

        binding.apply {

            rvAdapter.submitList(listSymptoms)

            rvSymptoms.adapter = rvAdapter


            btnResult.setOnClickListener(){
                dialogResult()
            }

            btnBack.setOnClickListener{
                var intent = Intent(this@DiseaseExamination , Home ::class.java)
                startActivity(intent)
            }


        }
    }

    override fun onClick(state: Int , position : String) {
        answers.put(position , state)
        counter = answers.values.sum()

        if (answers.size <5){
            binding.btnResult.isEnabled = false
            binding.btnResult.isClickable = false
        }else {
            binding.btnResult.isEnabled = true
            binding.btnResult.isClickable = true
        }

    }

    private fun dialogResult(){

        var resultExamination =""
        for (result in resultsList){
            if (result.Answers.toInt() == counter)
                resultExamination = result.Action
        }
        val dialogBuilder = AlertDialog.Builder(this)
        if (counter > 0){
        dialogBuilder.setMessage(resultExamination)
            .setPositiveButton("Call 937", DialogInterface.OnClickListener {
                    dialog, _ ->
                val intent = Intent(Intent.ACTION_DIAL)
                intent.data = Uri.parse("tel:937")
                startActivity(intent)
            })
            .setNegativeButton("Cancel", DialogInterface.OnClickListener{
                    dialog, _ ->  dialog.cancel()
            })}
        else
            dialogBuilder.setMessage(resultExamination)
                .setPositiveButton("OK", DialogInterface.OnClickListener {
                        dialog, _ -> dialog.cancel()
                })

        val alert = dialogBuilder.create()

        alert.setTitle("Result:")
        alert.show()

    }
}