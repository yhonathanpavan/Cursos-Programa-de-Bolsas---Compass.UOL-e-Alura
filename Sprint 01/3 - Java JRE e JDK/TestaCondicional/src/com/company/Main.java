package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        System.out.println("Testando condicionais");
        int idade = 15;
        int quantidadePessoas = 3;

        if(idade >= 18){
            System.out.println("Voce tem mais de 18 anos!");
            System.out.println("Seja bem-vindo!");
        } else{
            if(quantidadePessoas >= 2){
                System.out.println("Voce não tem 18 mas está acompanhando, pode entrar!");
            }else {
                System.out.println("Infelizmente você não pode entrar!");
            }
        }
    }
}
