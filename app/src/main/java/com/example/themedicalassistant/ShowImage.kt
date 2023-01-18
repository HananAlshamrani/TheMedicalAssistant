package com.example.themedicalassistant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.themedicalassistant.Model.Diseases
import com.example.themedicalassistant.View.Home
import com.example.themedicalassistant.databinding.ActivityShowImageBinding

class ShowImage : AppCompatActivity() {

    lateinit var binding: ActivityShowImageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowImageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var disease = intent.getSerializableExtra("disease") as Diseases

        binding.apply {

            tvDisease.text = disease.name

            Glide.with(this@ShowImage)
                .load(disease.image)
                .into(ivDisease)

            btnBack.setOnClickListener{
                var intent = Intent(this@ShowImage , Home :: class.java)
                startActivity(intent)
            }

        }

    }
}