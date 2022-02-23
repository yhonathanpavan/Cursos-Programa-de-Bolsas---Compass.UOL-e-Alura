package com.company;

public class TestaMetodo {

    public static void main(String[] args) {

        Conta contaDoPaulo = new Conta();

        contaDoPaulo.saldo = 100;

        // nomeDaReferencia.nomeDoMetodo();
        contaDoPaulo.deposita(50);
        System.out.println("Saldo: " + contaDoPaulo.saldo);

        boolean conseguiuSacar = contaDoPaulo.saca(20);
        System.out.println("Saldo: " + contaDoPaulo.saldo);

        System.out.println("Sacou o dinheiro: " + conseguiuSacar);


        Conta contaDaMarcela = new Conta();
        contaDaMarcela.deposita(1000);

       boolean sucessoTransferencia = contaDaMarcela.transfere(300, contaDoPaulo);

        if(sucessoTransferencia){
            System.out.println("Transferência feita com sucesso!");
        }else{
            System.out.println("Faltou grana!");
        }

        System.out.println(contaDaMarcela.saldo);
        System.out.println(contaDoPaulo.saldo);




    }
}
