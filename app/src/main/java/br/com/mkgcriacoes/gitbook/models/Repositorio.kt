package br.com.mkgcriacoes.gitbook.models

import com.google.gson.annotations.SerializedName

class Repositorio {
    var id: Int = 0

    @SerializedName("name")
    var nome: String = ""

    @SerializedName("description")
    var descricao: String = ""

    @SerializedName("owner")
    var usuario: Usuario? = null

    @SerializedName("stargazers_count")
    var estrelas: Int = 0

    @SerializedName("forks_count")
    var forks: Int = 0
}