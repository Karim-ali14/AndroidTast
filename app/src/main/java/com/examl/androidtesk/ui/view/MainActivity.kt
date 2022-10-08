package com.examl.androidtesk.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.examl.androidtesk.R
import com.examl.androidtesk.common.binder.viewBinding
import com.examl.androidtesk.common.utils.Resource
import com.examl.androidtesk.common.utils.Status
import com.examl.androidtesk.data.model.PlayerModel
import com.examl.androidtesk.databinding.ActivityMainBinding
import com.examl.androidtesk.ui.adapter.PlayerAdapter
import com.examl.androidtesk.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel : MainViewModel by viewModels()

    private val binding by viewBinding(ActivityMainBinding::inflate)

    private lateinit var playerAdapter: PlayerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initAdapter()

        bindData()

    }


    private fun initAdapter() {
        playerAdapter = PlayerAdapter(
            arrayListOf()
        )
    }

    private fun bindData() {
        binding.lifecycleOwner = this
        binding.mainViewModel = mainViewModel
        binding.playerAdapter = playerAdapter
    }

}