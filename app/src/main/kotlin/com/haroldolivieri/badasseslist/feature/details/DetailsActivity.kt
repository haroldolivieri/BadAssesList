package com.haroldolivieri.badasseslist.feature.details

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.request.RequestOptions
import com.haroldolivieri.badasseslist.R
import com.haroldolivieri.badasseslist.domain.BadAss
import com.haroldolivieri.badasseslist.feature.BaseActivity
import com.haroldolivieri.badasseslist.feature.details.DetailsActivity.Companion.INTENT_BAD_ASS_SELECTED
import kotlinx.android.synthetic.main.activity_details.*
import java.util.*
import java.text.SimpleDateFormat

fun Context.DetailsIntent(badAss: BadAss): Intent =
        Intent(this, DetailsActivity::class.java).putExtra(INTENT_BAD_ASS_SELECTED, badAss)

class DetailsActivity(override val layout: Int = R.layout.activity_details) :
        BaseActivity(), DetailsView {

    companion object {
        const val INTENT_BAD_ASS_SELECTED = "badAss"
    }

    private val badAss: BadAss? by lazy {
        intent.getSerializableExtra(INTENT_BAD_ASS_SELECTED) as BadAss
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        populateData()
    }

    private fun populateData() {
        if (badAss == null) finish()

        badAss?.apply {
            val options = RequestOptions()
                    .centerCrop()
                    .placeholder(R.drawable.ic_profile_placeholder)
                    .priority(Priority.HIGH)

            Glide.with(this@DetailsActivity)
                    .load(image)
                    .apply(options)
                    .into(badAssImage)

            badAssName.text = name ?: "John Doe"
            badAssBirthday.text = birthday?.formatToString("dd/MM/yyyy") ?: "Not Informed"
            badAssBio.text = bio ?: "Not Informed"
        }
    }
}

@SuppressLint("SimpleDateFormat")
fun Date.formatToString(pattern: String): String {
    val df = SimpleDateFormat(pattern)
    return df.format(this)
}