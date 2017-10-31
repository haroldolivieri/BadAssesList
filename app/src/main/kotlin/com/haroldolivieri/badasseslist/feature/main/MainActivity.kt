package com.haroldolivieri.badasseslist.feature.main

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import com.haroldolivieri.badasseslist.R
import com.haroldolivieri.badasseslist.domain.BadAss
import com.haroldolivieri.badasseslist.feature.BaseActivity
import com.haroldolivieri.badasseslist.ui.GridSpacingItemDecoration
import com.haroldolivieri.badasseslist.feature.details.DetailsIntent
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity(override val layout: Int = R.layout.activity_main) :
        BaseActivity(), MainView {

    @Inject lateinit var mainPresenter: MainPresenter

    private val badAssesAdapter by lazy {
        BadAssAdapter(context = this@MainActivity, itemClick = { badAss, options ->
            startActivity(DetailsIntent(badAss), options.toBundle())
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainPresenter.fetchData()
        setupGrid()
    }

    override fun showBadAssData(badAsses: List<BadAss>) {
        badAssesAdapter.setBadAsses(badAsses)
    }

    override fun onError(errorMessage: String?) {
        showSnackBar(badAssesRecyclerView, errorMessage ?: "Um erro inesperado ocorreu")
    }

    private fun setupGrid() {
        badAssesRecyclerView.layoutManager = GridLayoutManager(this, 2)
        badAssesRecyclerView.addItemDecoration(GridSpacingItemDecoration(2, 40, true))
        badAssesRecyclerView.adapter = badAssesAdapter
    }
}
