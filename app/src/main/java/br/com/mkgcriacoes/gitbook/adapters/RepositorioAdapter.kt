package br.com.mkgcriacoes.gitbook.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.mkgcriacoes.gitbook.databinding.ItemReposBinding
import br.com.mkgcriacoes.gitbook.models.Repositorio
import com.bumptech.glide.Glide

class RepositorioAdapter: ListAdapter<Repositorio, RepositorioAdapter.ViewHolder>(DiffCallback()) { // RecyclerView.Adapter<RepositorioAdapter.ViewHolder>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemReposBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class ViewHolder(
        private val binding: ItemReposBinding
    ): RecyclerView.ViewHolder(binding.root) {
        private val context: Context = binding.root.context

        fun bind(item: Repositorio){
            binding.txtRepositorio.text = item.nome
            binding.txtDescricao.text = item.descricao
            binding.txtUser.text = item.usuario?.nome
            binding.chipFork.text = item.forks.toString()
            binding.chipStar.text = item.estrelas.toString()

            Glide.with(context)
                .load(item.usuario?.img)
                .into(binding.imgUser)

            if (item.linguagem != null && item.linguagem != "") {
                item.linguagem = item.linguagem?.lowercase()
                item.linguagem = item.linguagem?.replace("++", "plusplus")
                item.linguagem = item.linguagem?.replace("#", "sharp")
                item.linguagem = item.linguagem?.replace("javascript", "js")
                item.linguagem = item.linguagem?.replace("typescript", "ts")
                item.linguagem = item.linguagem?.replace("-", "_")

                val imgResource = context.resources
                    .getIdentifier("ic_lang_${item.linguagem}", "drawable", context.packageName)

                Glide.with(context)
                    .load(imgResource)
                    .into(binding.imgLang)

                // Baixar imagens em SVG usando o Glide
//                Log.i("Linguagem_url", "https://cdn.jsdelivr.net/gh/devicons/devicon/icons/${item.linguagem}/${item.linguagem}-original.svg")
//
//                requestBuilder
//                    .load("https://cdn.jsdelivr.net/gh/devicons/devicon/icons/${item.linguagem}/${item.linguagem}-original.svg")
//                    .into(binding.imgLang)
            }
        }
    }
}

class DiffCallback: DiffUtil.ItemCallback<Repositorio>() {
    override fun areItemsTheSame(oldItem: Repositorio, newItem: Repositorio) = (oldItem == newItem)
    override fun areContentsTheSame(oldItem: Repositorio, newItem: Repositorio) = (oldItem.id == newItem.id)
}