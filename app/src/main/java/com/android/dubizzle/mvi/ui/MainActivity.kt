package com.android.dubizzle.mvi.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.dubizzle.R
import com.android.dubizzle.mvi.intent.MainStateEvent
import com.android.dubizzle.mvi.model.DataApiResponse
import com.android.dubizzle.mvi.ui.adapter.DubizzleRecyclerViewAdapter
import com.android.dubizzle.mvi.util.Constants.DUBIZZLE_CREATED
import com.android.dubizzle.mvi.util.Constants.DUBIZZLE_IMAGE
import com.android.dubizzle.mvi.util.Constants.DUBIZZLE_NAME
import com.android.dubizzle.mvi.util.Constants.DUBIZZLE_PRICE
import com.android.dubizzle.mvi.util.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        subscribeObservers()
        viewModel.setStateEvent(MainStateEvent.GetList)
        setupRecyclerView()
    }

    private fun subscribeObservers() {
        viewModel.dataState.observe(this) { dataState ->
            when(dataState) {
                is DataState.Success<DataApiResponse> -> {
                    if (dataState.data.results!!.size>0){
                        dubizzle_progress_bar.visibility = View.GONE
                        (dubizzle_recycler_view.adapter as DubizzleRecyclerViewAdapter).submitList(dataState.data.results)
                    }
                }
                is DataState.Error -> {

                }
                is DataState.Loading -> {

                }
            }
        }
    }

    private fun setupRecyclerView() {
        dubizzle_recycler_view.apply {
            this.layoutManager = LinearLayoutManager(this@MainActivity)
            this.adapter = DubizzleRecyclerViewAdapter { dubizzle ->

                val intent = Intent(this@MainActivity, DetailActivity::class.java)
                intent.putExtra(DUBIZZLE_NAME, dubizzle.name)
                intent.putExtra(DUBIZZLE_PRICE, dubizzle.price)
                intent.putExtra(DUBIZZLE_CREATED, dubizzle.created_at)
                intent.putExtra(DUBIZZLE_IMAGE, dubizzle.image_urls!!.get(0))

                startActivity(intent)

            }
            this.addItemDecoration(
                DividerItemDecoration(
                    this@MainActivity,
                    (this.layoutManager as LinearLayoutManager).orientation
                )
            )
        }
    }
}