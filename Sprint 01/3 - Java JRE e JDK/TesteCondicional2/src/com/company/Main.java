package com.company;

public class Main {

    public static void main(String[] args) {
        // write your code here
        System.out.println("Testando condicionais");
        int idade = 15;
        int quantidadePessoas = 3;
        boolean acompanhado = quantidadePessoas >= 2;

        System.out.println("Valor de acompanhado = " + acompanhado);

        if(idade >= 18 && acompanhado){
            System.out.println("Seja bem-vindo!");
        } else{
            System.out.println("Infelizmente você não pode entrar!");
        }


    }
}

