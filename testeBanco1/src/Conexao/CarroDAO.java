package Conexao;

import classe.Carro;
import classe.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CarroDAO {

    private ConexaoDAO conexao;
    private Connection conn;

    public CarroDAO() {
        this.conexao = new ConexaoDAO();
        this.conn = this.conexao.conectaBD();
    }

    public void inserirCarro(Carro carro) {
        String sql = "insert into carrocad(marca, modelo, cor, ano)values (?,?,?,?)";

        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, carro.getMarca());
            stmt.setString(2, carro.getModelo());
            stmt.setString(3, carro.getCor());
            stmt.setInt(4, carro.getAno());

            stmt.execute();
            stmt.close();

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "ERRO" + erro);

        }

    }

    public void editarCarro(Carro carro) {
        String sql = "update carrocad set marca=?, modelo =?, cor =?, ano=? where id=?";

        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);

            stmt.setString(1, carro.getMarca());
            stmt.setString(2, carro.getModelo());
            stmt.setString(3, carro.getCor());
            stmt.setInt(4, carro.getAno());
            stmt.setInt(5, carro.getId());

            stmt.execute();
        } catch (Exception e) {
            System.out.println("erro aqui" + e);
        }

    }

    public void excluirCarro(int id) {
        String sql = "delete from carrocad where id = ?";

        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, id);

            stmt.execute();
        } catch (Exception e) {
        }

    }

    public Carro getCarro(int id) {
        String sql = "SELECT * FROM carrocad WHERE id = ?";

        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Carro carro = new Carro();
                carro.setId(id);
                carro.setMarca(rs.getString("marca"));
                carro.setModelo(rs.getString("modelo"));
                carro.setCor(rs.getString("cor"));
                carro.setAno(rs.getInt("ano"));

                return carro;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    public List<Carro> getCarro(String marca) {
        String sql = "SELECT * FROM carrocad WHERE marca LIKE ?";

        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);

            stmt.setString(1, "%" + marca + "%");

            List<Carro> carroLista = new ArrayList();
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Carro carro = new Carro();
                carro.setId(rs.getInt("id"));
                carro.setMarca(rs.getString("marca"));
                carro.setModelo(rs.getString("modelo"));
                carro.setCor(rs.getString("cor"));
                carro.setAno(rs.getInt("ano"));

                carroLista.add(carro);
            }
            return carroLista;

        } catch (Exception e) {
            return null;
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
