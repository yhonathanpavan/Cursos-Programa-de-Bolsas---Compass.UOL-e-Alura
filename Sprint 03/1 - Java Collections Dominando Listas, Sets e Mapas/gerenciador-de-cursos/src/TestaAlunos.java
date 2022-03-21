import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class TestaAlunos {

    public static void main(String[] args) {

        Collection<String> alunos = new HashSet<String>();
        alunos.add("Rodrigo Turini");
        alunos.add("Alberto Souza");
        alunos.add("Nico Steppat");
        alunos.add("Sergio Lopes");
        alunos.add("Renan Saggio");
        alunos.add("Mauricio Aniche");
        alunos.add("Paulo Silveira");
        alunos.add("Alberto Souza"); //Mesmo inserindo valor repetido, o HashSet não permite valores duplicados.

        boolean pauloEstaMatriculado = alunos.contains("Paulo Silveira");
        System.out.println("Contem paulo na lista: " + pauloEstaMatriculado);

        System.out.println("Tamanho da lista: " + alunos.size());

        /*
        for (String aluno : alunos) {
            System.out.println(aluno);
        }*/

        // Collections.sort(alunos); --- Não funciona

        alunos.forEach(aluno -> System.out.println(aluno));


    }
}
