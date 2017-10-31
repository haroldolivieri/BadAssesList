package com.haroldolivieri.badasseslist.feature.details

import dagger.Module
import dagger.Provides

@Module
class DetailsActivityModule {

    @Provides
    fun providePresenter(presenter: DetailsPresenterImpl): DetailsPresenter = presenter

    @Provides
    fun provideView(activity: DetailsActivity): DetailsView = activity
}