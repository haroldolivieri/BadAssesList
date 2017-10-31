package com.haroldolivieri.badasseslist.feature.details

import dagger.Module
import dagger.Provides

@Module
class TestDetailsActivityModule {
    @Provides
    fun provideView(activity: DetailsActivity): DetailsView = activity
}