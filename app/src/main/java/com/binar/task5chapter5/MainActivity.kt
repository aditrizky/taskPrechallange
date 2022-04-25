package com.binar.task5chapter5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.binar.task5chapter5.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}