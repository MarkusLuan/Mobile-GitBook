package br.com.mkgcriacoes.gitbook.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.mkgcriacoes.gitbook.adapters.RepositorioAdapter
import br.com.mkgcriacoes.gitbook.databinding.ActivityMainBinding
import br.com.mkgcriacoes.gitbook.viewModels.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val binding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel by viewModel<MainViewModel>()

    private val adapter by lazy {
        RepositorioAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.recvRepositorio.adapter = adapter
        viewModel.getRepositorios("MarkusLuan")

        viewModel.repositorios
            .observe(this) {
                when (it){
                    MainViewModel.State.Carregando -> {

                    }
                    is MainViewModel.State.Erro -> {

                    }
                    is MainViewModel.State.Sucesso -> {
                        adapter.submitList(it.lista)
                    }
                }
        }
    }
}