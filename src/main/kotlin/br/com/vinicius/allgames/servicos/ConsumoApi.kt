package br.com.vinicius.allgames.servicos

import br.com.vinicius.allgames.modelo.InfoJogo
import br.com.vinicius.allgames.modelo.Jogo
import com.google.gson.Gson
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse.BodyHandlers

class ConsumoApi {

    // Desserializando o JSON para um objeto da classe InfoJogo usando o Gson
    fun buscaJogo(id: String): InfoJogo{
        val endereco = "https://www.cheapshark.com/api/1.0/games?id=$id"

        // cria um novo cliente http
        val client: HttpClient = HttpClient.newHttpClient()

        // Prepara uma solicitação para API externa com o método GET
        val request = HttpRequest.newBuilder()
            .uri(URI.create(endereco))
            .build()


        // envia a solicitaão para a API e retorna a resposta como uma String
        val response = client
            .send(request, BodyHandlers.ofString())

        //  armazena o corpo da respota JSON em uma variável
        val json = response.body()

        // Instanciando o Gson para realizar a desserialização do JSON para objetos Kotlin
        val gson = Gson()

        val meuInfoJogo = gson.fromJson(json, InfoJogo::class.java)

        return meuInfoJogo
    }

}