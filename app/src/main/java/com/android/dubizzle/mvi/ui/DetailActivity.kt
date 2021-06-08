package com.android.dubizzle.mvi.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.android.dubizzle.R;
import com.android.dubizzle.mvi.util.Constants.DUBIZZLE_CREATED
import com.android.dubizzle.mvi.util.Constants.DUBIZZLE_IMAGE
import com.android.dubizzle.mvi.util.Constants.DUBIZZLE_NAME
import com.android.dubizzle.mvi.util.Constants.DUBIZZLE_PRICE
import com.android.dubizzle.mvi.util.DateUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity  : AppCompatActivity() {

     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         setContentView(R.layout.activity_detail)
         val adName:String = intent.getStringExtra(DUBIZZLE_NAME).toString()
         val adPrice:String = intent.getStringExtra(DUBIZZLE_PRICE).toString()
         val adCreated:String = intent.getStringExtra(DUBIZZLE_CREATED).toString()
         val adImageUrl:String = intent.getStringExtra(DUBIZZLE_IMAGE).toString()
         dubizzle_name_text_view.text = adName
         dubizzle_price_text_view.text = adPrice
         dubizzle_time_since_text_view.text = DateUtil.timeSince(adCreated)
         Glide.with(this).load(adImageUrl).diskCacheStrategy(
             DiskCacheStrategy.ALL).into(dubizzle_image_view)
     }
}