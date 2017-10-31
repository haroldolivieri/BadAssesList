package com.haroldolivieri.badasseslist.feature.details

import android.content.Intent
import android.widget.TextView
import com.haroldolivieri.badasseslist.BuildConfig
import com.haroldolivieri.badasseslist.R
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.Robolectric
import com.haroldolivieri.badasseslist.domain.BadAss
import com.haroldolivieri.badasseslist.feature.details.DetailsActivity.Companion.INTENT_BAD_ASS_SELECTED
import com.haroldolivieri.badasseslist.feature.main.MainActivity
import junit.framework.Assert.assertNotNull
import junit.framework.Assert.assertTrue
import org.robolectric.Shadows
import org.robolectric.shadows.ShadowApplication
import java.util.*


@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class)
class DetailsActivityTest {

    private lateinit var detailsActivity: DetailsActivity

    @Test
    fun testWithDataOnIntentExtra() {
        val badAss = BadAss("123", "John", "image", Date(), "bio")
        val (badAssName, badAssBirthday, badAssBio) = getActivityViewElements(badAss)
        assertNotNull(detailsActivity)

        assertTrue(badAssName.text.toString() == badAss.name)
        assertTrue(badAssBirthday.text.toString() == badAss.birthday?.formatToString("dd/MM/yyyy"))
        assertTrue(badAssBio.text.toString() == badAss.bio)
    }

    @Test
    fun testWithNullDataOnIntentExtra() {
        val badAss = BadAss("123", null, null, null, null)
        val (badAssName, badAssBirthday, badAssBio) = getActivityViewElements(badAss)
        assertNotNull(detailsActivity)

        assertTrue(badAssName.text.toString() == "John Doe")
        assertTrue(badAssBirthday.text.toString() == "Not Informed")
        assertTrue(badAssBio.text.toString() == "Not Informed")
    }

    @Test
    fun testBackButtonClosesActivity() {
        startDetailsActivityWithExtras(BadAss("123", "John", "image", Date(), "bio"))
        assertNotNull(detailsActivity)

        val activityShadow = Shadows.shadowOf(detailsActivity)
        detailsActivity.onBackPressed()
        assertTrue(activityShadow.isFinishing)
    }

    private fun getActivityViewElements(badAss: BadAss): Triple<TextView, TextView, TextView> {
        startDetailsActivityWithExtras(badAss)

        val badAssName = (detailsActivity.findViewById<TextView>(R.id.badAssName))
        val badAssBirthday = (detailsActivity.findViewById<TextView>(R.id.badAssBirthday))
        val badAssBio = (detailsActivity.findViewById<TextView>(R.id.badAssBio))
        return Triple(badAssName, badAssBirthday, badAssBio)
    }

    private fun startDetailsActivityWithExtras(badAss: BadAss) {
        val mainActivity = Robolectric.setupActivity(MainActivity::class.java)
        mainActivity.startActivity(mainActivity.DetailsIntent(badAss))

        val intent = Intent(ShadowApplication.getInstance()
                .applicationContext, DetailsActivity::class.java)
                .putExtra(INTENT_BAD_ASS_SELECTED, badAss)

        detailsActivity = Robolectric
                .buildActivity(DetailsActivity::class.java)
                .withIntent(intent)
                .create()
                .get()
    }
}