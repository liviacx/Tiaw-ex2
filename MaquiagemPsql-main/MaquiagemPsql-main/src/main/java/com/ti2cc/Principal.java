package com.ti2cc;

public class Principal {
	
	public static void main(String[] args) {
		
		DAO dao = new DAO();
		
		dao.conectar();

		
		// Inserir um elemento na tabela
		Maquiagem maquiagem = new Maquiagem(1011, "Olho", "Mac", "Rimel");
		if (dao.inserirMaquiagem(maquiagem)) {
			System.out.println("Inserção com sucesso -> " + maquiagem.toString());
		}
		
		// Mostrar maquiagens
		System.out.println("==== Mostrar maquiagens === ");
		Maquiagem[] maquiagem = dao.getMaquiagem();
		for (int i = 0; i < maquiagem.length; i++) {
			System.out.println(maquiagem[i].toString());
		}

		// Atualizar maquiagem
		maquiagem.setMarca("Marca atualizada");
		dao.atualizarMaquiagem(maquiagem);

		// Mostrar maquiagem
		System.out.println("==== Mostrar maquiagem === ");
		maquiagem = dao.getMaquiagem();
		for (int i = 0; i < maquiagem.length; i++) {
			System.out.println(maquiagem[i].toString());
		}
		
		// Excluir maquiagem
		dao.excluirMaquiagem(maquiagem.getCodigo());
		
		// Mostrar maquiagem
		maquiagem = dao.getMaquiagem();
		System.out.println("==== Mostrar maquiagem === ");		
		for (int i = 0; i < maquiagem.length; i++) {
			System.out.println(maquiagem[i].toString());
		}
		
		dao.close();
	}
}
