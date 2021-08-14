package com.example.couroutinesample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.couroutinesample.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.startBtn.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            CoroutineScope(Main).launch {
                val deferred1 = async(IO) {
                    downloadImages()
                }
                val deferred2 = async(IO) {
                    getUserCount()
                }
                val deferred3 = async(IO) {
                    isCompleted()
                }
                val message = " ${deferred1.await()} . User count is ${deferred2.await()} . received completed as ${deferred3.await()}"
                Toast.makeText(applicationContext,message,Toast.LENGTH_SHORT).show()
                binding.progressBar.visibility = View.GONE
            }
        }

    }

    private suspend fun downloadImages():String{
        delay(5000)
        return "Data Downloaded"
    }
    private suspend fun getUserCount():Int{
        delay(7000)
        return 65 //some random value return
    }
    private suspend fun isCompleted():Boolean{
        delay(9000)
        return true
    }
}