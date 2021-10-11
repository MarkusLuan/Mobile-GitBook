package br.com.mkgcriacoes.gitbook.modules

import br.com.mkgcriacoes.gitbook.repositories.GithubRepository
import br.com.mkgcriacoes.gitbook.repositories.GithubRepositoryImpl
import br.com.mkgcriacoes.gitbook.services.GithubService
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DataModule {
    fun load(){
        loadKoinModules(networkModules + repositoriesModule)
    }

    private val networkModules = module {
        single {
            val interceptor = HttpLoggingInterceptor{
                println(it)
            }
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()
        }

        single {
            GsonConverterFactory.create(
                GsonBuilder().create()
            )
        }

        single {
            criarServico<GithubService>(get(), get())
        }
    }

    private val repositoriesModule = module {
        single<GithubRepository> { GithubRepositoryImpl(get()) }
    }

    private inline fun <reified T> criarServico(httpClient: OkHttpClient, factory: GsonConverterFactory): T {
        return Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .client(httpClient)
            .addConverterFactory(factory)
            .build()
            .create(T::class.java)
    }
}