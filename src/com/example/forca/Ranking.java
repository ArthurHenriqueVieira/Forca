package com.example.forca;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.app.Activity;
import android.database.Cursor;

public class Ranking extends Activity {
	
	Button ButtonNovaAtividade;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ranking);
		ListView jogadores = (ListView) findViewById(R.id.listViewJogadores);

		DBAdapter adapter = new DBAdapter(this.getApplicationContext());

		adapter.open();

		Cursor c = adapter.getAllRanking();

		startManagingCursor(c);

		String[] columns = new String[] {"Pontos", "Jogador"};
		int[] to = new int[] { R.id.txtPontos, R.id.txtJogador};

		ListAdapter listAdapter = new SimpleCursorAdapter(this, R.layout.linha,
				c, columns, to);

		jogadores.setAdapter(listAdapter);

		adapter.close();
	}
}
