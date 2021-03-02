package dao;

import java.sql.*;
import util.TrataException;

public class ConnectionManager {

    // 1) Coloque o valor adequado nas constantes DATABASE, USER, IP e PASSWORD
    // 2) Teste esta classe para garantir que esta funcionando
    private static final String STR_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DATABASE = "clientes?useTimezone=true&serverTimezone=UTC";
    private static final String IP = "127.0.0.1";
    private static final String STR_CON = "jdbc:mysql://" + IP + ":3306/" + DATABASE;
    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    public static Connection getConexao() throws TrataException {
        Connection conn = null;
        try {
            Class.forName(STR_DRIVER);
            conn = DriverManager.getConnection(STR_CON, USER, PASSWORD);
            System.out.println("[ConnectionManager]: Obtendo conexao");
            return conn;
        } catch (ClassNotFoundException e) {
            String errorMsg = "Driver nao encontrado";
            throw new TrataException(errorMsg, e);
        } catch (SQLException e) {
            String errorMsg = "Erro ao obter a conexao";
            throw new TrataException(errorMsg, e);
        }
    }

    public static void closeAll(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            String errorMsg = "Nao foi possivel fechar a conexao com o banco";
            TrataException.print(e, errorMsg);
        }
    }

    public static void closeAll(Connection conn, Statement stmt) {
        try {
            if (stmt != null) {
                stmt.close();
            }
            closeAll(conn);
        } catch (Exception e) {
            String errorMsg = "Nao foi possivel fechar a conexao com o banco";
            TrataException.print(e, errorMsg);
        }
    }
}
