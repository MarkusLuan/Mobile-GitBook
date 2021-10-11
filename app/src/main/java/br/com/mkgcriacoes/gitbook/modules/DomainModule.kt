package br.com.mkgcriacoes.gitbook.modules

import br.com.mkgcriacoes.gitbook.useCases.ListarRepositoriosUseCase
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

object DomainModule {
    fun load() {
        loadKoinModules(useCaseModule)
    }

    val useCaseModule = module {
        factory {
            ListarRepositoriosUseCase(get())
        }
    }
}