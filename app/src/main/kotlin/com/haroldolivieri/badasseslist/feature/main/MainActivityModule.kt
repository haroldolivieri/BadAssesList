package com.haroldolivieri.badasseslist.feature.main

import android.content.Context
import com.haroldolivieri.badasseslist.repository.BadAssLocalRepository
import com.haroldolivieri.badasseslist.repository.BadAssRepository
import dagger.Module
import dagger.Provides
import java.io.InputStream
import javax.inject.Singleton

@Module
class MainActivityModule {

    @Provides
    fun provideView(activity: MainActivity): MainView = activity

    @Provides
    fun providePresenter(presenter: MainPresenterImpl): MainPresenter = presenter

    @Provides
    fun provideAssetsData(context: Context) : InputStream = context.assets.open("badasses.json")

    @Provides
    fun provideLocalBadAssRepository(localRepository: BadAssLocalRepository): BadAssRepository =
            localRepository
}