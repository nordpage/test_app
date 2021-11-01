package xyz.mobcoder.testapp.di.modules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import xyz.mobcoder.testapp.sqlite.DBOpenHelper
import javax.inject.Singleton

@Module
class AppModule(private val app: Application) {

    @Provides
    @Singleton
    fun provideContext(): Context = app

    @Provides
    @Singleton
    fun provideDBOpenHelper(context: Context) : DBOpenHelper = DBOpenHelper(context, null)

}