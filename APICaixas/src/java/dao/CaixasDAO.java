package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import modelo.Caixas;
import util.ConectaBanco;

/**
 *
 * @author luanc
 */
public class CaixasDAO {

    public static final String INSERT = "INSERT INTO caixas (descricao, quantidade, responsavel) VALUES(?, ?, ?)";
    public static final String SELECT_ALL = "SELECT * FROM caixas";
    public static final String SELECT_ID = "SELECT * FROM caixas WHERE id = ?";
    public static final String DELETE = "DELETE FROM caixas WHERE id = ?";
    public static final String UPDATE = "UPDATE caixas SET descricao = ?,  quantidade = ?, responsavel = ?  WHERE id = ?";

    public void cadastrar(Caixas caixa) {
        Connection conexao = null;
        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(INSERT);
            pstmt.setString(1, caixa.getDescricao());
            pstmt.setInt(2, caixa.getQuantidade());
            pstmt.setString(3, caixa.getResponsavel());
            pstmt.execute();

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                conexao.close();
            } catch (SQLException el) {
                throw new RuntimeException(el);
            }
        }
    }

    public ArrayList<Caixas> listar() {
        ArrayList<Caixas> resultado = new ArrayList();
        try {
            Connection conexao = ConectaBanco.getConexao();
            Statement stmt = conexao.createStatement();
            ResultSet rs = stmt.executeQuery(SELECT_ALL);

            while (rs.next()) {
                Caixas c = new Caixas();
                c.setId(rs.getInt("id"));
                c.setDescricao(rs.getString("descricao"));
                c.setQuantidade(rs.getInt("quantidade"));
                c.setResponsavel(rs.getString("responsavel"));
                resultado.add(c);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return resultado;
    }

    public Caixas listarId(Caixas caixa) {
        Connection conexao = null;
        Caixas c = new Caixas();

        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(SELECT_ID);
            pstmt.setInt(1, caixa.getId());
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                c.setId(rs.getInt("id"));
                c.setDescricao(rs.getString("descricao"));
                c.setQuantidade(rs.getInt("quantidade"));
                c.setResponsavel(rs.getString("responsavel"));
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return c;
    }

    public void deletar(Caixas caixa) {
        Connection conexao = null;
        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(DELETE);
            pstmt.setInt(1, caixa.getId());
            pstmt.execute();
            conexao.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                conexao.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public void atualizar(Caixas caixa) {
        Connection conexao = null;
        try {

            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(UPDATE);
            pstmt.setString(1, caixa.getDescricao());
            pstmt.setInt(2, caixa.getQuantidade());
            pstmt.setString(3, caixa.getResponsavel());
            pstmt.setInt(4, caixa.getId());
            pstmt.execute();

            conexao.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
