import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class TestaListaDeAula {
    public static void main(String[] args) {
        Aula a1 = new Aula("Revisando as ArrayList", 21);
        Aula a2 = new Aula("Lista de objetos", 20);
        Aula a3 = new Aula("Relacionamento de listas e objetos", 15);

        ArrayList<Aula> aulas = new ArrayList();
        aulas.add(a1);
        aulas.add(a2);
        aulas.add(a3);

        System.out.println(aulas);
        Collections.sort(aulas);
        System.out.println(aulas);

        //Ordenando pelo tempo
        //A frase aqui é semelhante a "ordene estas aulas utilizando como comparação o retorno do método getTempo de cada Aula".
        Collections.sort(aulas, Comparator.comparing(Aula::getTempo));
        //ou
        aulas.sort(Comparator.comparing(Aula::getTempo));
        System.out.println(aulas);


        //Exemplo de comparação com Strings
        String s1 = "Paulo";
        String s2 = "Silveira";
        System.out.println(s1.compareTo(s2));

    }
}
