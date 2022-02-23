public class ControleBonificacao {

    private double soma;

    public void registro(Funcionario f){
        double boni = f.getBonificacao();
        this.soma += boni;
    }

    public double getSoma() {
        return soma;
    }
}
