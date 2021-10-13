package br.com.mkgcriacoes.gitbook.useCases

import br.com.mkgcriacoes.gitbook.models.Repositorio
import br.com.mkgcriacoes.gitbook.repositories.GithubRepository
import kotlinx.coroutines.flow.Flow

class ListarRepositoriosUseCase(
    private val repository: GithubRepository
): UseCase<String, List<Repositorio>>() {
    override suspend fun execute(param: String): Flow<List<Repositorio>> {
        return  repository.getRepositorios(param)
    }
}