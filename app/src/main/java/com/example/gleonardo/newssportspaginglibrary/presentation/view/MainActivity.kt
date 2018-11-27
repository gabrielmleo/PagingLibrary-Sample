package com.example.gleonardo.newssportspaginglibrary.presentation.view

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.LinearLayout
import com.example.gleonardo.newssportspaginglibrary.R
import com.example.gleonardo.newssportspaginglibrary.data.enum.State
import com.example.gleonardo.newssportspaginglibrary.presentation.components.NewsListAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    val mainViewModel: MainViewModel by viewModel()
    private lateinit var newsListAdapter: NewsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initAdapter()
        initState()
    }

    private fun initAdapter() {
        newsListAdapter = NewsListAdapter { mainViewModel.retry() }
        recycler_view.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        recycler_view.adapter = newsListAdapter
        mainViewModel.newsList.observe(this, Observer {
            newsListAdapter.submitList(it)
        })
    }

    private fun initState() {
        txt_error.setOnClickListener { mainViewModel.retry() }
        mainViewModel.getState().observe(this, Observer { state ->
            progress_bar.visibility = if (mainViewModel.listIsEmpty() && state == State.LOADING) View.VISIBLE else View.GONE
            txt_error.visibility = if (mainViewModel.listIsEmpty() && state == State.ERROR) View.VISIBLE else View.GONE
            if (!mainViewModel.listIsEmpty()) {
                newsListAdapter.setState(state ?: State.DONE)
            }
        })
    }
}
