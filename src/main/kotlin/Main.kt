package org.example

import com.google.gson.Gson
import org.example.org.example.InfoApiShark
import org.example.org.example.InfoJogo
import org.example.org.example.Jogo
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse.BodyHandlers


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {

    // cria um novo cliente http
    val client: HttpClient = HttpClient.newHttpClient()

    // Prepara uma solicitação para API externa com o método GET
    val request = HttpRequest.newBuilder()
        .uri(URI.create("https://www.cheapshark.com/api/1.0/games?id=146"))
        .build()


    // envia a solicitaão para a API e retorna a resposta como uma String
    val response = client
        .send(request, BodyHandlers.ofString())

    //  armazena o corpo da respota JSON em uma variável
    val json = response.body()
    println(json)

    // Instanciando o Gson para realizar a desserialização do JSON para objetos Kotlin
    val gson = Gson()

    // Desserializando o JSON para um objeto da classe InfoJogo usando o Gson
    val meuInfoJogo = gson.fromJson(json, InfoJogo::class.java)

    // Instanciando um novo objeto Jogo utilizando os dados obtidos do objeto InfoJogo
    val meuJogo = Jogo(
        meuInfoJogo.info.title,
        meuInfoJogo.info.thumb
    )
    print(meuJogo)
    }