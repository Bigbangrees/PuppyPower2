package com.example.puppypower.viewmodels

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import coil.load
import com.example.puppypower.R
import com.example.puppypower.database.PuppyPowerEntity
import com.example.puppypower.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val viewModel: DogViewModel by viewModels {
        DogViewModel.FileNameViewModelFactory((application as PuppyApplication).database.fileNameDao())
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getRandomDogPhoto()
    }

    private fun getRandomDogPhoto() {
        val randomPhotoButton = binding.button
        viewModel.dogPhoto.observe(this, {
            val imgUri = it.imageUrl!!.toUri().buildUpon().scheme("https").build()
            binding.imageView.load(imgUri)
            randomPhotoButton.setOnClickListener {
                viewModel.getNewPhoto()
            }
        })

        val newDogImage = PuppyPowerEntity(id = 1)
        viewModel.addDog(newDogImage)

        val prevDogButton : Button = findViewById(R.id.button2)
        prevDogButton.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }
    }
}



