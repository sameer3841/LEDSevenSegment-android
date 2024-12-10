package com.saunakpatel.sevensegmentled

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var sevenSegmentView: MyViewM11
    private lateinit var SSView: MyViewM11
    private lateinit var myButton: Button


    private var currentNum = 0
    private var tally = 0
    private val handler = Handler(Looper.getMainLooper())
    private var update: Long = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sevenSegmentView = findViewById(R.id.myViewM11)
        SSView = findViewById(R.id.SSnum)
        SSView.secondDigit = true
        SSView.activeColor = Color.RED

        SSView.invalidate()

        sevenSegmentView.secondDigit = true

        sevenSegmentView.invalidate()
        myButton = findViewById(R.id.myButton)

        myButton.setOnClickListener{ restartTimer() }
        startCounting()
    }

    private fun startCounting() {
        handler.postDelayed(object : Runnable {
            override fun run() {
                sevenSegmentView.setNumber(currentNum)
                if(currentNum % 10 == 0 && currentNum != 0) {
                    tally++
                    SSView.setNumber(tally)
                }
                currentNum = (currentNum + 1) % 100
                handler.postDelayed(this, update)
            }
        }, update)
    }

    private fun restartTimer() {
        currentNum = 0
        tally = 0
        sevenSegmentView.invalidate()
        SSView.invalidate()
    }
}