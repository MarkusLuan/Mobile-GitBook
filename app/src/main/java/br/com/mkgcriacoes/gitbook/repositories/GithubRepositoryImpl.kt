package br.com.mkgcriacoes.gitbook.repositories

import br.com.mkgcriacoes.gitbook.services.GithubService
import kotlinx.coroutines.flow.flow

class GithubRepositoryImpl (
    private val service: GithubService
): GithubRepository {
    override suspend fun getRepositorios(user: String) = flow {
        val repositorios = service.getRepositorios(user)
        emit(repositorios)
    }
}