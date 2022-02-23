package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        int contador = 0;
        while(contador <= 10) {
            System.out.println(contador);
            contador = contador + 1;
        }
        System.out.println(contador);


        //Escopo nos laços
        int newContador = 0;
        int total = 0;

        while(newContador <= 10) {
            //int total = 0; -> Não posso declarar aqui!
            total += newContador;
            newContador++;
        }
        System.out.println(total);

    }


}
