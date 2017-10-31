package com.haroldolivieri.badasseslist.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import javax.inject.Singleton


@Module
class ApplicationModule {
    @Provides
    @Singleton
    fun provideContext(application: Application): Context = application
}
