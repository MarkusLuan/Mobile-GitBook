package br.com.mkgcriacoes.gitbook.modules

import br.com.mkgcriacoes.gitbook.viewModels.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

object ViewModelModule {
    fun load(){
        loadKoinModules(viewModelModule)
    }

    val viewModelModule = module {
        viewModel {
            MainViewModel(get())
        }
    }
}