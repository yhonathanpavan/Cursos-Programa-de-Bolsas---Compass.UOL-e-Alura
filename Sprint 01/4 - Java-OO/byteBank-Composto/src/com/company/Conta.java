package com.company;

public class Conta {
    //Atributos
    private double saldo;
    int agencia;
    int numero;
    Cliente titular;

    //Métodos

    public double getSaldo(){
        return this.saldo;
    }


    public void deposita(double valor){
        this.saldo += valor;
    }

    public boolean saca(double valor){
        if(this.saldo - valor >= 0){
            this.saldo -= valor;
            return true;
        }
        return false;
    }

    public boolean transfere(double valor, Conta destino){
        if(this.saldo - valor >= 0){ //this.saldo >= valor
            this.saldo -= valor;
            //destino.deposita(valor);
            destino.saldo += valor;
            return true;
        }
        return false;
    }
}
