package com.feylabs.lindungipeduli.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.dicoding.tourismapp.core.data.Resource
import com.feylabs.lindungipeduli.R
import com.feylabs.lindungipeduli.databinding.ActivityMainBinding
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    val mainViewModel: MainViewModel by viewModel()

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        mainViewModel.news.observe(this, Observer {
            when (it) {
                is Resource.Loading -> binding.helloWorld.text = "loading...."
                is Resource.Success -> binding.helloWorld.text = it.data.toString()
                is Resource.Error -> binding.helloWorld.text = "error bro"
            }
        })
    }
}