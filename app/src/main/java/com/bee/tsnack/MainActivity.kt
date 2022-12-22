package com.bee.tsnack

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.tsnack.R
import com.example.tsnack.databinding.ActivityMainBinding
import com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_INDEFINITE

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var snackbar: TSnack
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.randomButton.setOnClickListener {
            snackbar = TSnack(this).show {
                drawableResource(R.drawable.snackbar_drawable)
                textColorResource(R.color.white)
                message("Custom TSnack")
                textColorResource(R.color.white)
                backgroundColorRes(R.color.black)
                border(3, ContextCompat.getColor(this@MainActivity, R.color.black))
                cornerRadius(8f)
                padding(4)
                duration(LENGTH_INDEFINITE)
                actionTextColorResource(R.color.white)
                withAction(android.R.string.ok) {
                    Toast.makeText(this@MainActivity, "callback", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.messageButton.setOnClickListener {
            snackbar = TSnack(this).show {
                textColorResource(R.color.white)
                message("Custom TSnack")
                textColorResource(R.color.white)
                backgroundColorRes(R.color.black)
                border(3, ContextCompat.getColor(this@MainActivity, R.color.teal_200))
                cornerRadius(8f)
                padding(50)
                duration(7000)
                actionTextColorResource(R.color.teal_200)

            }
        }

        binding.newMessageButton.setOnClickListener {
            snackbar = TSnack(this).show {
                customView(R.layout.new_layout)
            }
        }

        binding.cancelButton.setOnClickListener {
            if (::snackbar.isInitialized) {
                snackbar.dismiss()
            }
        }

    }
}