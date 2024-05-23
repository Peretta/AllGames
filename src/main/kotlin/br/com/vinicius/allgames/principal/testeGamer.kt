import br.com.vinicius.allgames.modelo.Gamer
import com.sun.source.tree.Scope

fun main() {
    val gamer1 = Gamer("Vini", "teste")
    println(gamer1)
    val gamer2 = Gamer("Pedro", "pedro@gmail", "02/12/2004", "matador_de_porco")
    println(gamer2)

    gamer1.let {
        it.dataNascimento="18/09/2000"
        it.usuario = "vinixius"
        it.idInterno = "000-1"
    }
    print(gamer1)
}