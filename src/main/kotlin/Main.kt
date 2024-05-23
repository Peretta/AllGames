package org.example

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import org.example.org.example.InfoJogo
import org.example.org.example.Jogo
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse.BodyHandlers
import java.util.*


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {

    val leitura = Scanner(System.`in`)
    println("Digite um código de jogo para buscar: ")
    val busca = leitura.nextLine()

    val endereco = "https://www.cheapshark.com/api/1.0/games?id=$busca"

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

    var meuJogo: Jogo? = null

    val resultado = runCatching {

        // Desserializando o JSON para um objeto da classe InfoJogo usando o Gson
        val meuInfoJogo = gson.fromJson(json, InfoJogo::class.java)

        // Instanciando o Jogo utilizando os dados obtidos do objeto InfoJogo
            meuJogo = Jogo(
            meuInfoJogo.info.title,
            meuInfoJogo.info.thumb
        )
    }
    resultado.onFailure {
        println("Jogo Inexistente. Tente outro id.")
    }
    resultado.onSuccess {
        println("Deseja inserir uma descrição personalizada? S/N")
        val opcao = leitura.nextLine()
        if (opcao.equals("s", true)){
            println("Insira a descrição personalizada para o Jogo: ")
            val descricao_personalizada = leitura.nextLine()
            meuJogo?.descricao = descricao_personalizada
        }else{
            meuJogo?.descricao = meuJogo?.titulo
        }
        print(meuJogo)
    }
    resultado.onSuccess {
        println("Busca realizada com sucesso")
    }
}