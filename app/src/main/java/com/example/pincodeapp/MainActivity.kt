package com.example.pincodeapp

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap

const val CORRECT_PIN = "1567"
const val PIN_LENGTH = 4
const val PIN_CODE_TEXT = "pin_code_text"
const val PIN_CODE_COLOR = "pin_code_color"
const val PIN_CODE_BG = "pin_code_bg"

class MainActivity : AppCompatActivity() {

    private var pinText = ""

    private lateinit var codeNum1: TextView
    private lateinit var codeNum2: TextView
    private lateinit var codeNum3: TextView
    private lateinit var codeNum4: TextView

    private var btnClickBg: Drawable? = null
    private var btnDefBg: Drawable? = null
    private var pinDefBg: Drawable? = null

    private var wrongColor: Int = 0
    private var correctColor: Int = 0
    private var btnClickTextColor: Int = 0
    private var btnDefTextColor: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initColors()
        initPinCodeText()
        initButtons()
        getSaveInstanceState(savedInstanceState)
    }

    private fun getSaveInstanceState(savedInstanceState: Bundle?) {
        savedInstanceState?.apply {
            getString(PIN_CODE_TEXT)?.let { pinCode ->
                pinText = pinCode
                updatePinTextView()
            }
            getInt(PIN_CODE_COLOR)?.let { color ->
                changeTextColor(color)
            }
            getString(PIN_CODE_BG)?.let { bgColor ->
                when (bgColor){
                    "pinDefBg" -> {
                        changeBgColor(pinDefBg)
                    }
                    else -> {
                        changeBgColor(btnClickBg)
                    }
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(PIN_CODE_TEXT, pinText)
        outState.putInt(PIN_CODE_COLOR, codeNum1.currentTextColor)

        when (codeNum1.background) {
            pinDefBg -> {
                outState.putString(PIN_CODE_BG, "pinDefBg")
            }
            btnClickBg -> {
                outState.putString(PIN_CODE_BG, "btnClickBg")
            }
        }
    }

    private fun initColors() {
        wrongColor = ContextCompat.getColor(this, R.color.wrong_code)
        correctColor = ContextCompat.getColor(this, R.color.right_code)
        btnClickBg = ContextCompat.getDrawable(this, R.drawable.clicked_btn_background)
        btnDefBg = ContextCompat.getDrawable(this, R.drawable.btn_background)
        btnClickTextColor = ContextCompat.getColor(this, R.color.btn_background)
        btnDefTextColor = ContextCompat.getColor(this, R.color.btn_blue)
        pinDefBg = ContextCompat.getDrawable(this, R.drawable.code_num_bg)
    }

    private fun initPinCodeText() {
        codeNum1 = findViewById(R.id.code_num1)
        codeNum2 = findViewById(R.id.code_num2)
        codeNum3 = findViewById(R.id.code_num3)
        codeNum4 = findViewById(R.id.code_num4)
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initButtons() {
        val oneBtn = findViewById<Button>(R.id.one)
        oneBtn.setOnTouchListener { view, motionEvent ->
            handleTouchEvent(view, motionEvent)
        }

        val twoBtn = findViewById<Button>(R.id.two)
        twoBtn.setOnTouchListener { view, motionEvent ->
            handleTouchEvent(view, motionEvent)
        }

        val threeBtn = findViewById<Button>(R.id.three)
        threeBtn.setOnTouchListener { view, motionEvent ->
            handleTouchEvent(view, motionEvent)
        }

        val fourBtn = findViewById<Button>(R.id.four)
        fourBtn.setOnTouchListener { view, motionEvent ->
            handleTouchEvent(view, motionEvent)
        }

        val fifeBtn = findViewById<Button>(R.id.fife)
        fifeBtn.setOnTouchListener { view, motionEvent ->
            handleTouchEvent(view, motionEvent)
        }

        val sixBtn = findViewById<Button>(R.id.six)
        sixBtn.setOnTouchListener { view, motionEvent ->
            handleTouchEvent(view, motionEvent)
        }

        val sevenBtn = findViewById<Button>(R.id.seven)
        sevenBtn.setOnTouchListener { view, motionEvent ->
            handleTouchEvent(view, motionEvent)
        }

        val eightBtn = findViewById<Button>(R.id.eight)
        eightBtn.setOnTouchListener { view, motionEvent ->
            handleTouchEvent(view, motionEvent)
        }

        val nineBtn = findViewById<Button>(R.id.nine)
        nineBtn.setOnTouchListener { view, motionEvent ->
            handleTouchEvent(view, motionEvent)
        }

        val zeroBtn = findViewById<Button>(R.id.zero)
        zeroBtn.setOnTouchListener { view, motionEvent ->
            handleTouchEvent(view, motionEvent)
        }

        val delBtn = findViewById<Button>(R.id.del)
        delBtn.setOnTouchListener { view, motionEvent ->
            handleTouchEvent(view, motionEvent)
        }

        val okBtn = findViewById<Button>(R.id.ok)
        okBtn.setOnTouchListener { view, motionEvent ->
            handleTouchEvent(view, motionEvent)
        }
    }

    private fun handleTouchEvent(view: View, event: MotionEvent): Boolean {

        if (view.id != R.id.ok) {
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    changeBtnColor(view, btnClickBg, btnClickTextColor)
                    return true
                }

                MotionEvent.ACTION_UP -> {
                    changeBtnColor(view, btnDefBg, btnDefTextColor)
                    when (view.id) {
                        R.id.del -> onDelBtnClick(view)
                        else -> onNumBtnClick(view)
                    }
                    return true
                }
            }
        } else {
            onOkBtnClick(view)
            return true
        }
        return false
    }

    private fun onOkBtnClick(view: View) {
        if (view !is Button) {
            return
        }
        if (pinText == CORRECT_PIN) {
            Toast.makeText(this, R.string.correct_pin, Toast.LENGTH_SHORT).show()
            val intent = Intent(this@MainActivity, ResultActivity::class.java)
            startActivity(intent)
        } else {
            changeTextColor(wrongColor)
            changeBgColor(btnClickBg)
        }
    }

    private fun changeBgColor(bgColor: Drawable?) {
        codeNum1.background = bgColor
        codeNum2.background = bgColor
        codeNum3.background = bgColor
        codeNum4.background = bgColor
    }

    private fun onDelBtnClick(view: View) {
        if (view !is Button) {
            return
        }
        pinText = pinText.dropLast(1)

        updatePinTextView()
    }

    private fun changeBtnColor(view: View, btnBackground: Drawable?, btnTextColor: Int) {
        view.background = btnBackground
        if (view is Button)
            view.setTextColor(btnTextColor)
    }

    private fun onNumBtnClick(view: View) {
        if (view !is Button) {
            return
        }
        val clickedNum = view.text
        pinText += clickedNum

        updatePinTextView()
    }

    private fun changeTextColor(color: Int) {
        codeNum1.setTextColor(color)
        codeNum2.setTextColor(color)
        codeNum3.setTextColor(color)
        codeNum4.setTextColor(color)
    }

    private fun updatePinTextView() {
        for (i in 0 until PIN_LENGTH) {
            val textView = when (i) {
                0 -> codeNum1
                1 -> codeNum2
                2 -> codeNum3
                3 -> codeNum4
                else -> null
            }

            if (textView != null) {
                if (i < pinText.length) {
                    textView.text = pinText[i].toString()
                    textView.setTextColor(correctColor)
                    changeBgColor(pinDefBg)

                } else {
                    textView.text = ""
                }
            }
        }
    }
}

