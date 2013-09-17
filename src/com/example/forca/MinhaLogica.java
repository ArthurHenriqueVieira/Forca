package com.example.forca;

import java.util.ArrayList;

public class MinhaLogica implements ILogica {
	private int _vidas;
	private int _pontos;
	private String _palavra;
	private String _palavraFormatada;
	
	MinhaLogica() {
		_vidas = 6;
		_pontos = 0;
		_palavra = "teto";
		_palavraFormatada = "____";
	}
	
	MinhaLogica(String palavra) {
		_vidas = 6;
		_pontos = 0;
		_palavra = palavra;
		_palavraFormatada = "";
		for(int n = palavra.length(); n > 0; n--)
			_palavraFormatada += "_";
	}
	
	@Override
	public boolean getFimDeJogo() {
		return _palavra.equals(_palavraFormatada) || _vidas <= 0;
	}

	@Override
	public boolean getGanhou() {
		return _vidas == 0 ? false:true;
	}

	@Override
	public String getPalavraFormatada() {
		return _palavraFormatada;
	}

	@Override
	public String getPalavra() {
		return _palavra;
	}

	@Override
	public void setPalavra(String Palavra) {
		_palavra = Palavra;
	}

	@Override
	public int getImagem() {
		int id = MainActivity.contexto.getResources().getIdentifier("forca" + (6-_vidas), "drawable", MainActivity.contexto.getPackageName());
		return id;
	}

	@Override
	public int getPontos() {
		return _pontos;
	}

	@Override
	public boolean conferir(String letra) {
		if(_palavra.indexOf(letra) >= 0) {
			// Lê e armazena os índices das 'letra's
			ArrayList<Integer> indexs = new ArrayList<Integer>();
			int nextIndex = _palavra.indexOf(letra);
			while(nextIndex != -1) {
				indexs.add(nextIndex);
				nextIndex = _palavra.indexOf(letra, nextIndex + 1);
			}
			
			// Coloca a 'letra' nos índices da palavraFormatada
			char[] tempString = _palavraFormatada.toCharArray();
			for(int i : indexs) {
				tempString[i] = letra.charAt(0);
				_pontos += 10;
			}
			_palavraFormatada = new String(tempString);
			return true;
		} else {
			_vidas--;
			return false;
		}
	}
}
