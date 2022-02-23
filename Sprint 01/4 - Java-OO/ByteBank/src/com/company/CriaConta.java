package com.company;

public class CriaConta {

    public static void main(String[] args) {

        /*
        * No Java, assim como em outras linguagens, um objeto nunca está dentro de uma variável.
        * O que tem dentro de uma variável é somente uma indicação a um objeto específico, uma referência
        * a um objeto específico.
        *
        * No nosso código, especificaremos que o valor de saldo a ser exibido é referente à primeiraConta.
        * A navegação entre primeiraConta e saldo ocorre através do caractere "."
        */




        // O objeto é criado e sua referência é atribuída à variável primeiraConta
        Conta primeiraConta = new Conta();

        primeiraConta.saldo = 200;
        System.out.println(primeiraConta.saldo);

        Conta segundaConta = new Conta();
        segundaConta.saldo = 50;

        System.out.println("Saldo primeira conta: " + primeiraConta.saldo);
        System.out.println("Saldo segunda conta: " + segundaConta.saldo);

        //Quando o java instancia objeto, os valores são zerados.
        System.out.println(primeiraConta.agencia);
        System.out.println(primeiraConta.numero);
        System.out.println(primeiraConta.titular);


        if(primeiraConta == segundaConta){
            System.out.println("São a mesma conta!");
        }else{
            System.out.println("Contas diferentes!");
        }
    }
}
