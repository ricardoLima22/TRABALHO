/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexao;

import classe.Carro;
import classe.Cliente;
import classe.Montadora;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author ricar
 */
public class MontadoraDAO {

    private ConexaoDAO conexao;
    private Connection conn;

    public MontadoraDAO() {
        this.conexao = new ConexaoDAO();
        this.conn = this.conexao.conectaBD();
    }

    public void inserirMontadora(Montadora montadora) {
        String sql = "insert into montadora(nome, cnpj, telefone, endereco)values (?,?,?,?)";

        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, montadora.getNome());
            stmt.setString(2, montadora.getCnpj());
            stmt.setString(3, montadora.getTelefone());
            stmt.setString(4, montadora.getEndereco());

            stmt.execute();
            stmt.close();

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "ERRO" + erro);

        }

    }

    public void editarMontadora(Montadora montadora) {
 
        String sql = "update montadora set nome=?, cnpj =?, telefone =?, endereco=? where id=?";

        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);

            stmt.setString(1, montadora.getNome());
            stmt.setString(2, montadora.getCnpj());
            stmt.setString(3, montadora.getTelefone());
            stmt.setString(4, montadora.getEndereco());
            stmt.setInt(5, montadora.getId());
            stmt.execute();
        } catch (Exception e) {
            System.out.println("erro aqui" + e);
        }

    }

    public void excluirCarro(int id) {
        String sql = "delete from cliente where id = ?";

        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, id);

            stmt.execute();
        } catch (Exception e) {
        }

    }

    public List<Montadora> getMontadora() {
        String sql = "SELECT id, nome, cnpj, telefone, endereco FROM montadora";

        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            List<Montadora> lista = new ArrayList<>();

            while (rs.next()) {
                Montadora montadora = new Montadora();

                montadora.setId(rs.getInt("id"));
                montadora.setNome(rs.getString("nome"));
                montadora.setCnpj(rs.getString("cnpj"));
                montadora.setTelefone(rs.getString("telefone"));
                montadora.setEndereco(rs.getString("endereco"));

                lista.add(montadora);
            }

            return lista;
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, "Erro ao obter montadoras: " + e);
            return null;
        }
    }
    
    public Montadora getMontadora(int id) {
        String sql = "SELECT * FROM montadora WHERE id = ?";

        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Montadora montadora = new Montadora();
                montadora.setId(id);
                montadora.setNome(rs.getString("nome"));
                montadora.setCnpj(rs.getString("cnpj"));
                montadora.setTelefone(rs.getString("telefone"));
                montadora.setEndereco(rs.getString("endereco"));

                return montadora;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }
}
