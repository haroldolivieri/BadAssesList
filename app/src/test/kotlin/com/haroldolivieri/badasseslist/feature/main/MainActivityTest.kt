package com.haroldolivieri.badasseslist.feature.main

import com.haroldolivieri.badasseslist.BuildConfig
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.Robolectric
import android.support.v7.widget.RecyclerView
import com.haroldolivieri.badasseslist.R
import com.haroldolivieri.badasseslist.TestBadAssesApplication
import com.haroldolivieri.badasseslist.domain.BadAss
import org.junit.Before
import com.haroldolivieri.badasseslist.feature.details.DetailsActivity
import junit.framework.Assert.*
import org.hamcrest.core.IsEqual.equalTo
import org.junit.Assert.assertThat
import org.robolectric.Shadows.shadowOf
import java.util.*


@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, application = TestBadAssesApplication::class)
class MainActivityTest {

    private lateinit var mainActivity: MainActivity
    private lateinit var recyclerView: RecyclerView

    @Before
    fun setUp() {
        mainActivity = Robolectric.setupActivity(MainActivity::class.java)
        recyclerView = (mainActivity.findViewById(R.id.badAssesRecyclerView))
    }

    @Test
    fun recyclerShouldNotBeNull() {
        assertNotNull(recyclerView)
    }

    @Test
    fun recyclerViewItemCount() {
        assertTrue(2 == recyclerView.childCount)
    }

    @Test
    fun adapterItem() {

        val firsItem = BadAss("97d16abc-1569-43e0-929b-cef05cd850fb",
                "Steve Jobs", "http://adsoftheworld.com/files/steve-jobs.jpg",
                Date(),
                "Steven Paul Jobs was an American businessman, inventor, and industrial designer. " +
                        "He was the co-founder, chairman,and chief executive officer (CEO) of Apple Inc.")

        val viewHolder = recyclerView.findViewHolderForAdapterPosition(0) as BadAssAdapter.CategoryViewHolder
        assertTrue(viewHolder.badAssName.text==firsItem.name)
    }

    @Test
    fun onItemClickedTest() {
        recyclerView.findViewHolderForAdapterPosition(0)?.itemView?.performClick()
        val shadowActivity = shadowOf(mainActivity)
        val startedIntent = shadowActivity.nextStartedActivity
        val shadowIntent = shadowOf(startedIntent)
        assertThat(shadowIntent.intentClass.name, equalTo(DetailsActivity::class.java.name))
    }
}