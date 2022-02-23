public class TestaValores {

    public static void main(String[] args) {

        Conta conta = new Conta(1337, 24226);
        Conta conta2 = new Conta(1337, 16549);
        Conta conta3 = new Conta(2664, 16795);

        System.out.println(Conta.getTotalConta());

        /*
        //conta está incosistente!
        conta.setAgencia(-50);
        conta.setNumero(-300);
        */

        System.out.println(conta.getAgencia()); //Quando eu inicializo, os valores vem zerados... Estado inconsistente.
    }
}
