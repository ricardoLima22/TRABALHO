/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexao;

import classe.Carro;
import classe.Cliente;
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
public class ClienteDAO {

    private ConexaoDAO conexao;
    private Connection conn;

    public ClienteDAO() {
        this.conexao = new ConexaoDAO();
        this.conn = this.conexao.conectaBD();
    }

    public void inserirCliente(Cliente cliente) {
        String sql = "insert into cliente(nomecliente ,cpf,idcarro)values (?,?,?)";

        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setInt(3, cliente.getIdcarro().getId());

            stmt.execute();
            stmt.close();

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "ERRO" + erro);

        }

    }

    public void editarCliente(Cliente cliente) {
        String sql = "update cliente set nome =?, idcarro =? where id=?";

        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);

            stmt.setString(1, cliente.getNome());
            stmt.setInt(2, cliente.getIdcarro().getId());
            stmt.setInt(3, cliente.getId());

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

    public List<Cliente> getCliente() {
        String sql = "select cliente.id as id, nomecliente,cpf, idcarro, marca, modelo FROM cliente INNER JOIN carrocad on cliente.idcarro = carrocad.id";

        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            List<Cliente> lista = new ArrayList<>();

            while (rs.next()) {
                Cliente cliente = new Cliente();
                Carro carro = new Carro();

                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("nomecliente"));
                cliente.setCpf(rs.getString("cpf"));
                carro.setId(rs.getInt("idcarro"));
                carro.setMarca(rs.getString("marca"));
                carro.setModelo(rs.getString("modelo"));
                cliente.setIdcarro(carro);
        

                lista.add(cliente);
            }
            return lista;
        } catch (Exception e) {
                JOptionPane.showConfirmDialog(null, "Erro aq"+e);
                return null;
        }

    }
}
