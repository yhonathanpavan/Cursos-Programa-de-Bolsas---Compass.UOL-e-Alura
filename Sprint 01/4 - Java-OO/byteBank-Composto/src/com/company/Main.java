package com.company;

public class Main {

    public static void main(String[] args) {

        Cliente paulo = new Cliente();

        //"Popular" o objeto
        paulo.nome = "Paulo Silveira";
        paulo.cpf = "222.222.222-44";
        paulo.profissao = "Programador";


        Conta contaDoPaulo = new Conta();
        //Associa o cliente paulo a conta paulo
        contaDoPaulo.titular = paulo;

        System.out.println(contaDoPaulo.titular.nome);
        System.out.println(contaDoPaulo.titular);
        System.out.println(paulo);


    }
}
