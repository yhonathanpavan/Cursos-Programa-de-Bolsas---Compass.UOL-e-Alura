public class TesteSaca {

    public static void main(String[] args) {
        Conta conta = new ContaCorrente(123, 321);
        conta.deposita(200.0);
        try {
            conta.saca(201.0);
        }catch(SaldoInsuficienteException ex){
            System.out.println(ex.getMessage());
        }

        System.out.println("Saldo atual: " + conta.getSaldo());

        }
}
