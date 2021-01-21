package com.bnawan.diceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    lateinit var diceImages:ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rollButton:Button = findViewById(R.id.roller)
        rollButton.setOnClickListener {
            rollDice()
        }
        diceImages = findViewById(R.id.dice_images)
    }

    private fun rollDice() {
        val randomInt = Random.nextInt(6)+1
        val currentImage = when(randomInt){
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5->R.drawable.dice_5
            else -> R.drawable.dice_6
        }
        diceImages.setImageResource(currentImage)
    }
}