/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author ricar
 */
public class ConexaoDAO {
     public Connection conectaBD() {
        Connection conn = null;
        try {
            String url = "jdbc:mysql://localhost:3306/carrocadastro?user=root&password=";
            conn = DriverManager.getConnection(url);

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Ta errado burro " + erro);
        }
        return conn;
    }

    
}
