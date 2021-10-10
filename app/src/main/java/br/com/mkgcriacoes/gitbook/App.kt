package br.com.mkgcriacoes.gitbook

import android.app.Application
import br.com.mkgcriacoes.gitbook.services.DataModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            DataModule.load()
        }
    }
}