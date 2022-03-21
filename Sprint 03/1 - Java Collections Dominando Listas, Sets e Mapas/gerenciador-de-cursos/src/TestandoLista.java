import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class TestandoLista {

    public static void main(String[] args) {
        String aula1 = "Conhecendo mais de listas";
        String aula2 = "Modelando a classe Aula";
        String aula3 = "Trabalhando com Cursos e Sets";

        //Adicionando no array
        ArrayList<String> aulas = new ArrayList();
        aulas.add(aula1);
        aulas.add(aula2);
        aulas.add(aula3);

        System.out.println(aulas);

        //Remover no array
        aulas.remove(0);

        System.out.println(aulas);

        //ForEach
        for (String aula : aulas) {
            System.out.println("Aula: " + aula);
        }

        String primeiraAula = aulas.get(0);
        System.out.println("A primeira aula é: " + primeiraAula);

        for (int i = 0; i < aulas.size(); i++) {
            System.out.println("aula : " + aulas.get(i));
        }

        System.out.println(aulas.size());

        //ForEach da lista
        aulas.forEach(aula -> { //Lambda
            System.out.println("Percorrendo : ");
            System.out.println("Aula : " + aula);
        });

        //Classe Collections é um conjunto de métodos estáticos auxiliares as coleções. Dentro dela há o método
        //Dentro dela há o método sort, que nos ajuda com esse tipo de ordenação natural.
        aulas.add("Aumentando nosso conhecimento");
        System.out.println( "Antes de ordenado:");
        System.out.println(aulas);

        Collections.sort(aulas);
        System.out.println( "Depois de ordenado:");
        System.out.println(aulas);
    }
}
