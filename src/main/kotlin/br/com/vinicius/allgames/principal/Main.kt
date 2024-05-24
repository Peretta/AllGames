package org.example.br.com.vinicius.allgames.principal

import br.com.vinicius.allgames.modelo.Jogo
import br.com.vinicius.allgames.servicos.ConsumoApi
import java.util.*


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

// FUNÇÃO PRINCIPAL DO PROGRAMA
fun main() {

    // Leitura permite ao Sistema receber informações do teclado do usuário
    val leitura = Scanner(System.`in`)
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
        // Imprime os detalhes do jogo
        print(meuJogo)
    }
    // Mensagem de sucesso após a busca
    resultado.onSuccess {
        println("Busca realizada com sucesso")
    }
}