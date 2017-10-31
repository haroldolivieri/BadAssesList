package com.haroldolivieri.badasseslist.feature.main

import android.app.Activity
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.request.RequestOptions
import com.haroldolivieri.badasseslist.R
import com.haroldolivieri.badasseslist.domain.BadAss
import android.support.v4.app.ActivityOptionsCompat

@Suppress("NAME_SHADOWING")
class BadAssAdapter(private var badAsses: List<BadAss>? = null,
                    private val context: Context,
                    private val itemClick: (badAss: BadAss,
                                            options: ActivityOptionsCompat) -> Unit?) :
        RecyclerView.Adapter<BadAssAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_grid, parent, false)
        return CategoryViewHolder(view, itemClick, context)
    }

    fun setBadAsses(badAsses: List<BadAss>?) {
        this.badAsses = badAsses
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        badAsses?.get(position)?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int = badAsses?.size ?: 0

    class CategoryViewHolder(view: View,
                             private val itemClick: (category: BadAss,
                                                     options: ActivityOptionsCompat) -> Unit?,
                             private val context: Context) : RecyclerView.ViewHolder(view) {

        val badAssImage by lazy { view.findViewById<ImageView>(R.id.badAssImage) }
        val badAssName by lazy { view.findViewById<TextView>(R.id.badAssName) }
        val contentName by lazy { view.findViewById<View>(R.id.contentName) }

        fun bind(badAss: BadAss) {

            val options = RequestOptions()
                    .placeholder(R.drawable.ic_profile_placeholder)
                    .priority(Priority.HIGH)

            Glide.with(context)
                    .load(badAss.image)
                    .apply(options)
                    .into(badAssImage)

            badAssName.text = badAss.name
            itemView.setOnClickListener {

                val p1 = android.support.v4.util.Pair(badAssImage as View,
                        context.getString(R.string.badass_image_transition_name))
                val p2 = android.support.v4.util.Pair(badAssName as View,
                        context.getString(R.string.badass_name_transition_name))
                val p3 = android.support.v4.util.Pair(contentName as View,
                        context.getString(R.string.content_transition_name))
                val options = ActivityOptionsCompat
                        .makeSceneTransitionAnimation(context as Activity?, p1, p2, p3)

                itemClick(badAss, options)
            }
        }
    }
}