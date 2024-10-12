package com.example.myapplication

import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var toolbarMain: Toolbar

    private lateinit var firstTimeET: EditText
    private lateinit var secondTimeET: EditText

    private lateinit var buttonSumBTN: Button
    private lateinit var buttonDifBTN: Button

    private lateinit var resultTV: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        toolbarMain = findViewById(R.id.toolbarMain)
        setSupportActionBar(toolbarMain)
        title = "Калькулятор времени"
        toolbarMain.subtitle = "ver. 1.2"
        toolbarMain.setLogo(R.drawable.ic_time)

        firstTimeET = findViewById(R.id.firstTimeET)
        secondTimeET = findViewById(R.id.secondTimeET)

        buttonSumBTN = findViewById(R.id.buttonSumBTN)
        buttonDifBTN = findViewById(R.id.buttonDifBTN)

        resultTV = findViewById(R.id.resultTV)

        buttonSumBTN.setOnClickListener(this)
        buttonDifBTN.setOnClickListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menumain, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.resetMenuMain -> {
                firstTimeET.text.clear()
                secondTimeET.text.clear()
                resultTV.setTextColor(Color.parseColor("#000000"))
                resultTV.text = "Результат"
                Toast.makeText(
                    applicationContext,
                    "Данные очищены",
                    Toast.LENGTH_LONG
                ).show()
            }
            R.id.exitMenuMain -> {
                Toast.makeText(
                    applicationContext,
                    "Работа завершена",
                    Toast.LENGTH_LONG
                ).show()
                finish()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onClick(v: View) {
        val first = firstTimeET.text.toString()
        val second = secondTimeET.text.toString()
        val result = when (v.id) {
            R.id.buttonSumBTN -> {
                if (CheckAndGet().checkTime(first) && CheckAndGet().checkTime(second)) {
                    CheckAndGet().sumTime(first, second)
                } else "Неверный ввод"
            }
            R.id.buttonDifBTN -> {
                if (CheckAndGet().checkTime(first) && CheckAndGet().checkTime(second)) {
                    CheckAndGet().difTime(first, second)
                } else "Неверный ввод"
            }
            else -> "Ошибка"
        }
        Toast.makeText(
            applicationContext,
            "Результат: $result",
            Toast.LENGTH_LONG
        ).show()
        resultTV.setTextColor(Color.parseColor("#8B0000"))
        resultTV.text = result
    }
}