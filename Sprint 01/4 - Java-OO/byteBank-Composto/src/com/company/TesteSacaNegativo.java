package com.company;

public class TesteSacaNegativo {

    public static void main(String[] args) {

        Conta conta = new Conta();
        conta.deposita(100);
        System.out.println(conta.saca(101)); //Da false, não consigo sacar.
        System.out.println(conta.getSaldo());

        //conta.saldo -= 101; //Burlei o sistema

        System.out.println(conta.getSaldo());
    }
}
