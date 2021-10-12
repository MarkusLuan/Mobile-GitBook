package br.com.mkgcriacoes.gitbook.ui

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.inputmethod.InputMethodManager
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import br.com.mkgcriacoes.gitbook.R
import br.com.mkgcriacoes.gitbook.adapters.RepositorioAdapter
import br.com.mkgcriacoes.gitbook.databinding.ActivityMainBinding
import br.com.mkgcriacoes.gitbook.viewModels.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {
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

        setSupportActionBar(binding.toolbar)

        binding.recvRepositorio.adapter = adapter
        viewModel.getRepositorios("MarkusLuan")

        viewModel.repositorios
            .observe(this) {
                when (it){
                    MainViewModel.State.Carregando -> {
                        binding.progressbar.visibility = ProgressBar.VISIBLE
                    }
                    is MainViewModel.State.Erro -> {
                        binding.progressbar.visibility = ProgressBar.GONE
                        Toast.makeText(this, "Ocorreu um erro ao obter informações do github!", Toast.LENGTH_LONG).show()
                    }
                    is MainViewModel.State.Sucesso -> {
                        binding.progressbar.visibility = ProgressBar.GONE
                        adapter.submitList(it.lista)
                    }
                }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu);

        val searchView = menu.findItem(R.id.action_search).actionView as SearchView
        searchView.setOnQueryTextListener(this)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        println(newText)
        return true
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        query?.let { viewModel.getRepositorios(it) }

        (getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager)
            .hideSoftInputFromWindow(binding.root.windowToken, 0)
        return true
    }
}