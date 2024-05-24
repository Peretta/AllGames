package br.com.vinicius.allgames.modelo

import java.util.Scanner
import kotlin.random.Random

// Classe Gamer com dois atributos obrigatórios (nome, email), ambos do tipo String
class Gamer(var nome:String,
            var email: String) {
    // Atributo dataNascimento é uma String e é opcional (inicialmente nulo)
    var dataNascimento: String? = null

    // Atributo usuario com um setter encapsulado. Se idInterno estiver vazio ou nulo, chama o método criarIdInterno
    var usuario: String? = null
        set(value) {
            field = value
            if (idInterno.isNullOrBlank()){
                criarIdInterno()
            }
        }

    // Atributo idInterno com setter privado, só pode ser lido, não modificado externamente
    var idInterno: String?= null
        private set

    // Declaração de uma lista mutável para armazenar elementos do tipo Jogo
    val jogosBuscados = mutableListOf<Jogo?>()

    // Construtor secundário, que permite inicializar todos os atributos da classe
    constructor(nome: String, email: String, dataNascimento: String, usuario: String): this(nome, email){
        this.dataNascimento = dataNascimento
        this.usuario = usuario
        criarIdInterno()
    }

    // Bloco init, executado durante a inicialização do objeto, para validar nome e email
init {
    if (nome.isNullOrBlank()){
        // Lança uma exceção se o nome estiver em branco
        throw IllegalArgumentException("O nome está em branco")
    }
        // Valida o email e atribui ao atributo email
    this.email = validarEmail()
}
    // Converte o objeto Gamer para uma representação em String
    override fun toString(): String {
        return "Gamer(nome='$nome', email='$email', dataNascimento=$dataNascimento, usuario=$usuario, idInterno=$idInterno)"
    }

    // Gera um ID interno para o usuário
    fun criarIdInterno(){

        // Gera um número inteiro aleatório entre 0 e 9999
        val numero = Random.nextInt(10000)

        // Formata o número para 4 dígitos com zeros à esquerda
        val tag = String.format("%04d", numero)

        // Concatena a variável usuario com a tag para formar o idInterno
        idInterno = "$usuario#$tag"
    }
    // Valida se o email está no formato correto
    fun validarEmail():String{
        // Critério de validação do email usando expressão regular
        val regex = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")

        // Se o email respeitar o padrão, retorna o email
        if (regex.matches(email)){
            return email
        }else{
            // Lança uma exceção se o email for inválido
            throw IllegalArgumentException("Email Inválido")
        }
    }

    companion object{
        fun criarGamer(leitura: Scanner): Gamer {
            println("Boas vindas ao AluGames! Vamos fazer seu cadastro. Digite seu nome:")
            val nome = leitura.nextLine()
            println("Digite seu e-mail:")
            val email = leitura.nextLine()
            println("Deseja completar seu cadastro com usuário e data de nascimento? (S/N)")
            val opcao = leitura.nextLine()

            if (opcao.equals("s", true)) {
                println("Digite sua data de nascimento(DD/MM/AAAA):")
                val nascimento = leitura.nextLine()
                println("Digite seu nome de usuário:")
                val usuario = leitura.nextLine()

                return Gamer(nome, email, nascimento, usuario)

            }else{
                return Gamer(nome, email)
            }
        }
    }
}