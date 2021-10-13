package br.com.mkgcriacoes.gitbook.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.mkgcriacoes.gitbook.models.Repositorio
import br.com.mkgcriacoes.gitbook.useCases.ListarRepositoriosUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class MainViewModel(
    private val useCase: ListarRepositoriosUseCase
): ViewModel() {
    private val _repositorios = MutableLiveData<State>()
    val repositorios: LiveData<State> = _repositorios

    fun getRepositorios(user: String){
        viewModelScope.launch {
            useCase(user)
                .onStart {
                    _repositorios.postValue(
                        State.Carregando
                    )
                }.catch {
                    _repositorios.postValue(
                        State.Erro(it)
                    )
                }.collect {
                    _repositorios.postValue(
                        State.Sucesso(it)
                    )
                }
        }
    }

    sealed class State{
        object Carregando: State()
        data class Sucesso (val lista: List<Repositorio>): State()
        data class Erro (val erro: Throwable): State()
    }
}