package com.haroldolivieri.badasseslist.feature.main

import com.haroldolivieri.badasseslist.repository.BadAssLocalRepository
import com.haroldolivieri.badasseslist.repository.BadAssRepository
import dagger.Module
import dagger.Provides
import diffItemsFile
import java.io.FileInputStream
import java.io.InputStream

@Module
class TestMainActivityModule {

    @Provides
    fun provideView(activity: MainActivity): MainView = activity

    @Provides
    fun providePresenter(presenter: MainPresenterImpl): MainPresenter = presenter

    @Provides
    fun provideAssetsData(): InputStream = FileInputStream(diffItemsFile(javaClass))

    @Provides
    fun provideLocalBadAssRepository(localRepository: BadAssLocalRepository): BadAssRepository =
            localRepository
}