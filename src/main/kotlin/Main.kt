package org.example

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

    //  q   rmazena o corpo da respota JSON em uma variável
    val json = response.body()
    println(json)
}