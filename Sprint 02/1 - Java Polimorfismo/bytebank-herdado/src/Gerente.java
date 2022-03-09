//Gerente é uma extensão (herda) funcionario e assina o contrato Autenticavel
public class Gerente extends Funcionario implements Autenticavel{

    private AutenticacaoUtil autenticador;

    public Gerente(){
        this.autenticador = new AutenticacaoUtil();
    }

    public double getBonificacao(){
        System.out.println("Chamando o método de bonificacao do GERENTE");
        return super.getSalario();
    }


    @Override
    public void setSenha(int senha) {
        this.autenticador.setSenha(senha);
    }

    @Override
    public boolean autentica(int senha) {
        return autenticador.autentica(senha);
    }
}
