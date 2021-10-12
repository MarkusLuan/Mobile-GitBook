package br.com.mkgcriacoes.gitbook.adapters

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
        fun bind(item: Repositorio){
            binding.txtRepositorio.text = item.nome
            binding.txtDescricao.text = item.descricao
            binding.txtUser.text = item.usuario?.nome
            binding.chipFork.text = item.forks.toString()
            binding.chipStar.text = item.estrelas.toString()

            Glide.with(binding.root.context)
                .load(item.usuario?.img)
                .into(binding.imgUser)
        }
    }
}

class DiffCallback: DiffUtil.ItemCallback<Repositorio>() {
    override fun areItemsTheSame(oldItem: Repositorio, newItem: Repositorio) = (oldItem == newItem)
    override fun areContentsTheSame(oldItem: Repositorio, newItem: Repositorio) = (oldItem.id == newItem.id)
}