package br.com.mkgcriacoes.gitbook.repositories

import br.com.mkgcriacoes.gitbook.models.Repositorio
import kotlinx.coroutines.flow.Flow

interface GithubRepository {
    suspend fun getRepositorios(user: String): Flow<List<Repositorio>>
}