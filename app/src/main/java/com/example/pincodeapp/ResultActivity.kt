package com.example.pincodeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.KeyEvent
import android.view.View
import android.widget.Button

class ResultActivity : AppCompatActivity() {
    private lateinit var shareBtn: Button
    private lateinit var mailToBtn: Button
    private lateinit var cameraBtn: Button
    private lateinit var callBtn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        initButtons()
    }

    private fun initButtons() {
        shareBtn = findViewById(R.id.share)
        shareBtn.setOnClickListener(this::onButtonClick)

        mailToBtn = findViewById(R.id.mailTo)
        mailToBtn.setOnClickListener(this::onButtonClick)

        cameraBtn = findViewById(R.id.camera)
        cameraBtn.setOnClickListener(this::onButtonClick)

        callBtn = findViewById(R.id.call)
        callBtn.setOnClickListener(this::onButtonClick)
    }

    private fun onButtonClick(view: View?) {
       val intent: Intent = Intent().apply {
           when(view){
               shareBtn -> {
                   action = Intent.ACTION_SEND
                   putExtra(Intent.EXTRA_TEXT, "This is my text to send.")
                   type = "text/plain"
               }
               mailToBtn -> {
                   action = Intent.ACTION_SEND
                   putExtra(Intent.EXTRA_EMAIL, arrayOf("example@gmail.com"))
                   putExtra(Intent.EXTRA_TEXT, "Some text")
                   type = "text/plain"
               }
               callBtn -> {
                   action = Intent.ACTION_CALL_BUTTON
                   putExtra(Intent.EXTRA_KEY_EVENT, KeyEvent(
                       KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_CALL)
                   )
               }
               cameraBtn -> {
                   action = MediaStore.ACTION_IMAGE_CAPTURE
               }
           }
       }
        startActivity(intent,null)
    }
}