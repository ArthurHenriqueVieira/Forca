package com.example.forca;

import java.util.ArrayList;

public class MinhaLogica implements ILogica {
	private int _vidas;
	private int _pontos;
	private String _palavra;
	private String _palavraFormatada;
	private ArrayList<String> _letrasTentadas;
	
	MinhaLogica() {
		_vidas = 6;
		_pontos = 0;
		_palavra = "teto";
		_palavraFormatada = "____";
		_letrasTentadas = new ArrayList<String>();
	}
	
	MinhaLogica(String palavra) {
		_vidas = 6;
		_pontos = 0;
		_palavra = palavra;
		_palavraFormatada = "";
		for(int n = palavra.length(); n > 0; n--)
			_palavraFormatada += "_";
		_letrasTentadas = new ArrayList<String>();
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
		if(_letrasTentadas.indexOf(letra) < 0) {
			_letrasTentadas.add(letra);
			
			if(_palavra.indexOf(letra) >= 0) {
				atualizaPalavraFormatada(letra);
				return true;
			} else {
				_vidas--;
				
				// Tira pontos quando o usuário erra uma letra (para pontuação mais complexa, com pontos iniciando em algum valor)
				addPontos(0);
				return false;
			}
		} else {
			return false;
		}
	}
	
	private void atualizaPalavraFormatada(String letra) {
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
			// Da 10 pontos para cada letra encontrada
			addPontos(10);
		}
		_palavraFormatada = new String(tempString);
	}
	
	public void addPontos(int pontos) { _pontos += pontos; }
	
	public String espacaString(String string) {
		if(string.length() > 1) {
			// Lê a quantidade de letras que a 'string' possui e aloca um vetor com tamanho suficiente para colocar os espaços entre as letras
			char[] tempString = new char[string.length()*2 - 1];
			
			// Zera o vetor com espaços
			for(int i = 0; i < tempString.length-1; i++) {
				tempString[i] = ' ';
			}
			
			// Coloca os caracteres de 'string' em seus respectivos índices, deixando sempre um espaço entre eles
			int s = 0;
			int t = 0;
			while(s < string.length()) {
				tempString[t] = string.charAt(s);
				s += 1;
				t += 2;
			}
			
			return new String(tempString);
		} else {
			return string;
		}
	}
	
	public String getTentativas() {
		String tempString = "";
		
		for(String s : _letrasTentadas)
			tempString += s;
		
		return tempString;
	}
}
