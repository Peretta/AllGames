package org.example.br.com.vinicius.allgames.principal

import br.com.vinicius.allgames.modelo.Gamer
import br.com.vinicius.allgames.modelo.Jogo
import br.com.vinicius.allgames.servicos.ConsumoApi
import java.util.*


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

// FUNÇÃO PRINCIPAL DO PROGRAMA
fun main() {

    // Leitura permite ao Sistema receber informações do teclado do usuário
    val leitura = Scanner(System.`in`)

    val gamer = Gamer.criarGamer(leitura)
    println("Cadastro concluido com sucesso. Dados do gamer")
    println(gamer)

    do{
        println("Digite um código de jogo para buscar: ")

        // variável 'busca' recebe a informação do usuário
        val busca = leitura.nextLine()

        // a variável meu jogo é criada, é da classe Jogo, inicialmente vazia
        var meuJogo: Jogo? = null

        // Instancia o objeto que consome a API
        val buscaApi = ConsumoApi()

        // Tratamento de exceção para a busca na API
        val resultado = runCatching {

            // 'informacaoJogo' recebe os dados da API usando o código de busca fornecido pelo usuário
            val informacaoJogo = buscaApi.buscaJogo(busca)

            // Instancia um novo Jogo utilizando os dados obtidos do objeto InfoJogo
            meuJogo = Jogo(
                informacaoJogo.info.title,
                informacaoJogo.info.thumb
            )
        }
        // caso ocorra algum erro na formação do objeto
        resultado.onFailure {
            println("Jogo Inexistente. Tente outro id.")
        }
        // se o objeto for criado com sucesso
        resultado.onSuccess {
            println("Deseja inserir uma descrição personalizada? S/N")
            val opcao = leitura.nextLine()
            // se a opção for s , o ignore case permite que a verificação não seja sensível a maiúsculas/minúsculas
            if (opcao.equals("s", true)){

                println("Insira a descrição personalizada para o Jogo: ")
                val descricao_personalizada = leitura.nextLine()
                meuJogo?.descricao = descricao_personalizada
            }else{
                meuJogo?.descricao = meuJogo?.titulo
            }
            gamer.jogosBuscados.add(meuJogo)
        }

        println("Deseja buscar um novo jogo? S/N")
        val resposta = leitura.nextLine()

    } while (resposta.equals("s", true))

    println("JOGOS BUSCADOS:")
    println(gamer.jogosBuscados)

    println("\n Jogos ordenados por título:")
    gamer.jogosBuscados.sortBy {
        it?.titulo
    }

    gamer.jogosBuscados.forEach {
        println("Titulo: " + it?.titulo)
    }

    val jogosFiltrados = gamer.jogosBuscados.filter { it?.titulo?.contains("batman", true) ?: false }

    println("\n Jogos filtrados: ")
    println(jogosFiltrados)

    print(" Deseja excluir algum jogo da lista Original?")

    val opcao = leitura.nextLine()

    if (opcao.equals("s", true)){
        print(gamer.jogosBuscados)

        println("Informe a posição do jogo que deseja excluir")

        val posicao = leitura.nextInt()
        gamer.jogosBuscados.removeAt(posicao)
    }

    println("\n LISTA ATUALIZADA ")
    print(gamer.jogosBuscados)


}