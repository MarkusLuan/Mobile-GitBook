package br.com.mkgcriacoes.gitbook.services

import br.com.mkgcriacoes.gitbook.models.Repositorio
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {
    @GET("users/{user}/repos")
    suspend fun getRepositorios(@Path("user") user: String): List<Repositorio>
}