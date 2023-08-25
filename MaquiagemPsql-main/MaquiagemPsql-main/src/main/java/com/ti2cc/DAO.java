package com.ti2cc;

import java.sql.*;

public class DAO {
    private Connection conexao;

    public DAO() {
        conexao = null;
    }

    public boolean conectar() {
        String driverName = "org.postgresql.Driver";                    
		String serverName = "localhost";
		String mydatabase = "teste";
		int porta = 5432;
		String url = "jdbc:postgresql://" + serverName + ":" + porta +"/" + mydatabase;
		String username = "ti2cc";
		String password = "ti@cc";
		boolean status = false;

		try {
			Class.forName(driverName);
			conexao = DriverManager.getConnection(url, username, password);
			status = (conexao == null);
			System.out.println("Conexão efetuada com o postgres!");
		} catch (ClassNotFoundException e) { 
			System.err.println("Conexão NÃO efetuada com o postgres -- Driver não encontrado -- " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("Conexão NÃO efetuada com o postgres -- " + e.getMessage());
		}

		return status;
        
    }

    public boolean close() {
        boolean status = false;
		
		try {
			conexao.close();
			status = true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return status;
    }

    public boolean inserirMaquiagem(Maquiagem maquiagem) {
        boolean status = false;
        try {
            Statement st = conexao.createStatement();
            st.executeUpdate("INSERT INTO maquiagem (codigo, regiao, marca, produto) "
                    + "VALUES (" + maquiagem.getCodigo() + ", '" + maquiagem.getRegiao() + "', '"
                    + maquiagem.getMarca() + "', '" + maquiagem.getProduto() + "');");
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }

    public boolean atualizarMaquiagem(Maquiagem maquiagem) {
        boolean status = false;
        try {
            Statement st = conexao.createStatement();
            String sql = "UPDATE maquiagem SET regiao = '" + maquiagem.getRegiao() + "', marca = '"
                    + maquiagem.getMarca() + "', produto = '" + maquiagem.getProduto() + "'"
                    + " WHERE codigo = " + maquiagem.getCodigo();
            st.executeUpdate(sql);
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }

    public boolean excluirMaquiagem(int codigo) {
        boolean status = false;
        try {
            Statement st = conexao.createStatement();
            st.executeUpdate("DELETE FROM maquiagem WHERE codigo = " + codigo);
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }

    public Maquiagem[] getMaquiagem() {
        Maquiagem[] maquiagem = null;

        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery("SELECT * FROM maquiagem");
            if (rs.next()) {
                rs.last();
                maquiagem = new Maquiagem[rs.getRow()];
                rs.beforeFirst();

                for (int i = 0; rs.next(); i++) {
                    maquiagem[i] = Maquiagem(rs.getInt("codigo"), rs.getString("regiao"),
                            rs.getString("marca"), rs.getString("produto"));
                }
            }
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return maquiagem;
    }

}