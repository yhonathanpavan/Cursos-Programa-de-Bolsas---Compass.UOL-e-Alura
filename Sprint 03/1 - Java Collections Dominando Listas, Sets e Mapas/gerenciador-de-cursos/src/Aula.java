public class Aula implements Comparable<Aula> { //Tenho que implementar a interface "Comparable" para
    //Comparar classes.

    private String titulo;
    private int tempo;

    public Aula(String titulo, int tempo) {
        this.titulo = titulo;
        this.tempo = tempo;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getTempo() {
        return tempo;
    }

    @Override
    public String toString() {
        return "[Aula: "+ this.titulo + ", "  + this.tempo + " minutos]";
    }

    /*
    É aí que devemos decidir o nosso critério de comparação de duas aulas.
    Quando uma aula virá antes da outra? Bem, eu vou optar por ordenar na ordem alfabética.
    Para isso, vou aproveitar do próprio método compareTo da String, delegando:
    */
    @Override
    public int compareTo(Aula outraAula) {
        return this.titulo.compareTo(outraAula.titulo);
    }
}
