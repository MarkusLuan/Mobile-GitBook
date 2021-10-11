package br.com.mkgcriacoes.gitbook

import android.app.Application
import br.com.mkgcriacoes.gitbook.modules.DataModule
import br.com.mkgcriacoes.gitbook.modules.DomainModule
import br.com.mkgcriacoes.gitbook.modules.ViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)

            DataModule.load()
            DomainModule.load()
            ViewModelModule.load()
        }
    }
}