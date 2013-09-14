public interface ILogica {


	public boolean getFimDeJogo();
	public boolean getGanhou();
	
	public String getPalavraFormatada();
	public String getPalavra();
	public void setPalavra(String Palavra);
	public int getImagem();
	public int getPontos();
	
	public boolean conferir(String letra);
}