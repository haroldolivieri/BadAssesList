package com.haroldolivieri.badasseslist.di

import com.haroldolivieri.badasseslist.feature.details.DetailsActivity
import com.haroldolivieri.badasseslist.feature.details.DetailsActivityModule
import dagger.Module
import com.haroldolivieri.badasseslist.feature.main.MainActivity
import com.haroldolivieri.badasseslist.feature.main.MainActivityModule
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {
    @ContributesAndroidInjector(modules = arrayOf(MainActivityModule::class))
    internal abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = arrayOf(DetailsActivityModule::class))
    internal abstract fun bindDetailsActivity(): DetailsActivity
}