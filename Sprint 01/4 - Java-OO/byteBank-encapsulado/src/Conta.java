public class Conta {
    //Atributos
    private double saldo;
    private int agencia;
    private int numero;
    private Cliente titular;
    private static int totalConta;

    //Construtor

    //Posso fazer mais de um construtor!

//    No Java é possível fazer a chamada de um construtor dentro de outro e faz-se
//    isso para evitar duplicações de códigos e regras. Afinal uma regra aplicada em um
//    construtor normalmente será a mesma para o outro caso. Para isso usa-se o this( )
//    passando os parâmetros correspondentes ao construtor que se queira chamar.

    public Conta(int agencia, int numero){
        Conta.totalConta++;
        System.out.println("Total de contas é: " + Conta.totalConta);
        this.agencia = agencia;
        this.numero = numero;
        System.out.println("Criando conta... nº " + this.numero);
    }

    public Conta(int agencia){
        this(agencia, 555); //Reaproveitei o primeiro construtor com um valor definido para numero.
    }

    //Métodos

    public static int getTotalConta(){ //Informo que o método é da classe, com static.

        //Dentro de um contexto estático, não existe "this"
        return Conta.totalConta;
    }

    public double getSaldo(){
        return this.saldo;
    }

    public int getNumero(){
        return numero;
    }

    public void setNumero(int numero){
        if(numero <= 0){
            System.out.println("Não pode valor <= 0");
            return;
        }
        this.numero = numero;
    }

    public int getAgencia() {
        return agencia;
    }

    public void setAgencia(int agencia) {
        if(agencia <= 0){
         System.out.println("Não pode valor menor igual a 0");
         return; // somente para parar a execução
        }
        this.agencia = agencia;
    }

    public void setTitular(Cliente titular){
        this.titular = titular;
    }

    public Cliente getTitular() {
        return titular;
    }

    public void deposita(double valor){
        this.saldo += valor;
    }

    public boolean saca(double valor){
        if(this.saldo - valor >= 0){
            this.saldo -= valor;
            return true;
        }
        return false;
    }

    public boolean transfere(double valor, Conta destino){
        if(this.saldo - valor >= 0){ //this.saldo >= valor
            this.saldo -= valor;
            //destino.deposita(valor);
            destino.saldo += valor;
            return true;
        }
        return false;
    }
}
