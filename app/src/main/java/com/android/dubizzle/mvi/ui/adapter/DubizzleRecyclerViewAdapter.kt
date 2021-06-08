package com.android.dubizzle.mvi.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.dubizzle.R
import com.android.dubizzle.mvi.util.DateUtil
import com.bumptech.glide.Glide
import com.android.dubizzle.mvi.model.Result
import com.bumptech.glide.load.engine.DiskCacheStrategy

class DubizzleRecyclerViewAdapter(private val onClickListener: (Result) -> Unit) :
    ListAdapter<Result, DubizzleViewHolder>(DubizzleDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DubizzleViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_dubizzle, parent, false)
        return DubizzleViewHolder(view, onClickListener)
    }

    override fun onBindViewHolder(holder: DubizzleViewHolder, position: Int) {
        holder.item = getItem(position)
    }
}

class DubizzleViewHolder(private val view: View, private val onClickListener: (Result) -> Unit) :
    RecyclerView.ViewHolder(view) {

    var item: Result? = null
        set(value) {
            value?.let { newValue ->
                field = newValue
                view.setOnClickListener { onClickListener(newValue) }
                view.findViewById<TextView>(R.id.dubizzle_name_text_view).text = newValue.name
                view.findViewById<TextView>(R.id.dubizzle_price_text_view).text =newValue.price
                view.findViewById<TextView>(R.id.dubizzle_time_since_text_view).text = DateUtil.timeSince(newValue.created_at)
                Glide.with(view.context).load(newValue.image_urls?.get(0)).diskCacheStrategy(
                    DiskCacheStrategy.ALL).into(view.findViewById(R.id.dubizzle_image_view))
            }
        }
}

class DubizzleDiffCallback : DiffUtil.ItemCallback<Result>() {

    override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
        return oldItem.uid == newItem.uid
    }

    override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
        return oldItem == newItem
    }
}
