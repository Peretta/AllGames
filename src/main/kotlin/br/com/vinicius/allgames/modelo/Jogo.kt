package br.com.vinicius.allgames.modelo

// Classe representando um Jogo, com um contructor primário que aceita dois parâmetros: titulo e capa
class Jogo (val titulo:String, val capa: String) {

    // Especifica que a variável descricao é um atributo opcional (pode ser nulo) para armazenar a descricao do jogo
    var descricao: String? = null

    // Retorna em uma representação String os atributos do objeto Jogo
    override fun toString(): String {
        return "--JOGO-- \n" +
                "Titulo: $titulo\n" +
                "Capa: $capa\n" +
                "Descricao: $descricao"
    }

}
