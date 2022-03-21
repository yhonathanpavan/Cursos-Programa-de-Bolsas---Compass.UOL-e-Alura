package exercicio;

import java.util.Comparator;

public class OrdenaPorIdade implements Comparator<Funcionario> {

    @Override
    public int compare(Funcionario fun, Funcionario outroFun) {
        return fun.getIdade() - outroFun.getIdade();
    }
}



