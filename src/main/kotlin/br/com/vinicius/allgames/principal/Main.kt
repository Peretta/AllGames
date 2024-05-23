package org.example.br.com.vinicius.allgames.principal

import br.com.vinicius.allgames.modelo.Jogo
import br.com.vinicius.allgames.servicos.ConsumoApi
import java.util.*


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {

    val leitura = Scanner(System.`in`)
    println("Digite um código de jogo para buscar: ")
    val busca = leitura.nextLine()

    var meuJogo: Jogo? = null

    val buscaApi = ConsumoApi()

    val resultado = runCatching {
        val informacaoJogo = buscaApi.buscaJogo(busca)
        // Instanciando o Jogo utilizando os dados obtidos do objeto InfoJogo
            meuJogo = Jogo(
                informacaoJogo.info.title,
                informacaoJogo.info.thumb
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