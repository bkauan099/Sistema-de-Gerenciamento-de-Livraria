package Livraria.bancoDados;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class ConexaoBD {

    protected String url = "jdbc:mysql://localhost:3307/libra_tech";
    protected String usuario = "root";
    protected String senha = "root123";
    protected ConexaoBD dataBase;
    public Connection conectar(){
        try{
            return DriverManager.getConnection(url, usuario, senha);
        } catch (SQLException e){
            System.out.println("Erro ao se conectar ao banco de dados: " + e.getMessage());
            return null;
        }
    }

    public void desconectar(Connection conexao){
        if (conexao != null){
            try {
                conexao.close();
            } catch (SQLException e){
                System.out.println("Erro ao desconectar: " + e.getMessage());
            }
        }
    }
}
