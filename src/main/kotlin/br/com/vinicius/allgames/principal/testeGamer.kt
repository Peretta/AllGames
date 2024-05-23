import br.com.vinicius.allgames.modelo.Gamer
import com.sun.source.tree.Scope

fun main() {
    val gamer1 = Gamer("Vini", "teste@gmail.com")
    println(gamer1)
    val gamer2 = Gamer("Pedro", "pedro@gmail.com", "02/12/2004", "pedrinho")
    println(gamer2)

    gamer1.let {
        it.dataNascimento="18/09/2000"
        it.usuario = "vinixius"
    }.also { print(gamer1.idInterno) }

    gamer1.usuario = "Teste"
    print(gamer1)
}