package com.company;

public class testaContaSemCliente {

    public static void main(String[] args) {

        Conta contaMarcela = new Conta();

        System.out.println(contaMarcela.titular); //Null porque ainda não associei a nenhum cliente!

        //Não preciso instanciar um cliente primeiro e associar depois, posso fazer direto!
        contaMarcela.titular = new Cliente();
        System.out.println(contaMarcela.titular);

        contaMarcela.titular.nome = "Marcela";
        System.out.println(contaMarcela.titular.nome);
    }
}
