package com.example.forca;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	// Variáveis dos campos do XML
	ImageView imgForca;
	TextView txtPontos, txtResposta, txtTentativas;
	EditText txtInput;
	Button btnOk;
	
	MinhaLogica forca;
	static Context contexto;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_forca);
		
		contexto = this;
		
		// Pega os campos do XML
		imgForca = (ImageView)findViewById(R.id.imgForca);
		txtPontos = (TextView)findViewById(R.id.txtPontos);
		txtResposta = (TextView)findViewById(R.id.txtResposta);
		txtInput = (EditText)findViewById(R.id.txtInput);
		btnOk = (Button)findViewById(R.id.btnOk);
		
		// Init jogo
		forca = new MinhaLogica();
		
		// Atualiza dados do XML
		refreshData();
		
		if(btnOk != null) {
			btnOk.setOnClickListener(new OnClickListener(){
				public void onClick(View arg0) {
					forca.conferir(txtInput.getText().toString());
					refreshData();
					if(forca.getFimDeJogo()) {
						AlertDialog.Builder builder = new AlertDialog.Builder(contexto);
						TextView title = new TextView(contexto);
						TextView msg = new TextView(contexto);
						
						// Configura as mensagens de vitória e derrota
						if(forca.getGanhou()) {
							title.setText("Correto!");
							title.setGravity(Gravity.CENTER);
							title.setTextSize(23);
							msg.setText("A palavra é '" + forca.getPalavra() + "'.\nVocê fez " + forca.getPontos() + ".\n Deseja jogar novamente?");
							msg.setGravity(Gravity.CENTER);
							msg.setTextSize(18);
						} else {
							title.setText("Errado!");
							title.setGravity(Gravity.CENTER);
							title.setTextSize(23);
							msg.setText("A palavra era '" + forca.getPalavra() + "'.\nVocê fez " + forca.getPontos() + ".\n Deseja jogar novamente?");
							msg.setGravity(Gravity.CENTER);
							msg.setTextSize(18);
						}
						
						// Aplica o Dialog customizado
						builder.setCustomTitle(title);
						builder.setView(msg);
						
						builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								forca = new MinhaLogica();
								refreshData();
							}
						});
						
						builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								finish();
							}
						});
						
						AlertDialog alert = builder.create();
						alert.show();
					}
				}});
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private void refreshData() {
		imgForca.setImageResource(forca.getImagem());
		txtPontos.setText("Pontos: " + forca.getPontos());
		txtResposta.setText(forca.getPalavraFormatada());
		txtInput.setText("");
	}
	
}
