package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        System.out.println("testando condicionais");

        int idade = 20;
        int quantidadePessoas = 3;
        boolean acompanhado = true;

        if (quantidadePessoas >= 2) {
             acompanhado = true;
        }else {
             acompanhado = false;
        }

        if (idade >=18 || acompanhado){

            System.out.println("Seja bem vindo");
        } else {
            System.out.println("infelizmente voce nao pode entrar");
        }
    }
}
