package com.example.puppypower.viewmodels


import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.puppypower.R
import com.squareup.picasso.Picasso

class MainActivity2 : AppCompatActivity() {
    private val viewModel: DogViewModel by viewModels{
        DogViewModel.FileNameViewModelFactory((application as PuppyApplication).database.fileNameDao())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        viewModel.getAllDogs().observe(this){
            val imageView = findViewById<ImageView>(R.id.imageView2)
            Picasso.get().load(it[0].id).into(imageView)
            val button: Button = findViewById(R.id.button3)
            button.setOnClickListener {
                finish()
            }
        }
    }
}

