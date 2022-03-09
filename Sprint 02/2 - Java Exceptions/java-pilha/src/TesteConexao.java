public class TesteConexao {

    public static void main(String[] args) {


        try(Conexao conexao = new Conexao()){ //Utilizando essa sintaxe e implementando a interface "AutoClosable" o try fica implícito

            conexao.leDados();

        }catch(IllegalStateException ex){
            System.out.println("Deu erro na conexão");
        }


        //------------------------------------------------------ Método "antigo"
/*
        Conexao con = null;

        try{
            con = new Conexao();
            con.leDados();
            //con.fecha(); ----- Apago daqui para não repetir e uso no finally
        } catch(IllegalStateException ex){
            System.out.println("Deu erro na conexão");
            //con.fecha(); ----- Apago daqui para não repetir e uso no finally
        } finally {
            con.close();
        }

        //Não tem como fugir do finally! A maquina virtual garante que independente da condição
        // (COM ou SEM erro, ocorre a condição dentro do finally)
*/
    }
}
