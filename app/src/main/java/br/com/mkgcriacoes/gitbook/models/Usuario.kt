package br.com.mkgcriacoes.gitbook.models

import com.google.gson.annotations.SerializedName

class Usuario {
    @SerializedName("login")
    var nome: String = ""

    @SerializedName("avatar_url")
    var img: String = ""
}