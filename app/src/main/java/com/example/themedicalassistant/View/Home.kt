package com.example.themedicalassistant.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.themedicalassistant.DiseasesAdapter
import com.example.themedicalassistant.Model.Advice
import com.example.themedicalassistant.Model.Diseases
import com.example.themedicalassistant.Model.Repository
import com.example.themedicalassistant.ShowImage
import com.example.themedicalassistant.databinding.ActivityHomeBinding
import kotlinx.android.synthetic.main.activity_home.*
import kotlin.random.Random

class Home : AppCompatActivity() , DiseasesAdapter.ClickListener {

    lateinit var binding: ActivityHomeBinding
    private var repository = Repository()
    private var rvAdapter = DiseasesAdapter(this)
    private var advicesList = listOf<Advice>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val advices = repository.getHealthAdvice()
        val diseases = repository.getDiseases()

        advices.observe(this){
            adviceDB -> advicesList = adviceDB
        var randomAdvice = Random.nextInt(advicesList.size-1)
            tvAdvice.text = "Health Advice :\n"+ advicesList[randomAdvice].advice

        }

        diseases.observe(this){
                diseasesDB -> rvAdapter.submitList(diseasesDB)

        }


        binding.apply {
            rvDisease.adapter= rvAdapter

        }

    }

    override fun onClickDisease(disease: Diseases) {
        val intent = Intent(this@Home , DiseaseExamination :: class.java)
        intent.putExtra("disease",disease)
        startActivity(intent)

    }

    override fun onClickImage(disease: Diseases) {
        val intent = Intent(this@Home , ShowImage :: class.java)
        intent.putExtra("disease",disease)
        startActivity(intent)
    }
}