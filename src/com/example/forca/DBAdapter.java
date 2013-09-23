package com.example.forca;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter {

	public static final String KEY_ROWID = "_id";
	public static final String KEY_PONTOS = "pontos";
	public static final String KEY_JOGADOR = "jogador";
	private static final String TAG = "Jogadores";

	private static final String DATABASE_NAME = "Jogadores";
	private static final String DATABASE_TABLE = "Pontos";
	private static final int DATABASE_VERSION = 1;

	private static final String DATABASE_CREATE = "create table titles (_id integer primary key autoincrement, "
			+ "pontos text not null, "
			+ "jogador text not null);";

	private final Context context;

	private DatabaseHelper DBHelper;
	private SQLiteDatabase db;

	public DBAdapter(Context ctx) {
		this.context = ctx;
		DBHelper = new DatabaseHelper(context);
	}
	
	private static class DatabaseHelper extends SQLiteOpenHelper {


		DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}


		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(DATABASE_CREATE);
			
			ContentValues initialValues;
			
			
			//vamos inserir 10 jogadores
			for(int i = 0; i < 10; i++){

				initialValues= new ContentValues();
				initialValues.put(KEY_PONTOS,"Pontos: " + Integer.toString(i));
				initialValues.put(KEY_JOGADOR, "Jogador: " + Integer.toString(i));
				db.insert(DATABASE_TABLE, null, initialValues);
			}
			
		}


		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
					+ newVersion + ", which will destroy all old data");
			db.execSQL("DROP TABLE IF EXISTS titles");
			onCreate(db);
		}
	}

	//Abre o Banco de Dados
	public DBAdapter open() throws SQLException {
		db = DBHelper.getWritableDatabase();
		return this;
	}

	//Fecha o banco de Dados
	public void close() {
		DBHelper.close();
	}

	//Insere um score
	public long insertRanking(int pontos, String jogador) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_PONTOS, pontos);
		initialValues.put(KEY_JOGADOR, jogador);
		return db.insert(DATABASE_TABLE, null, initialValues);
	}

	//Deleta um score
	public boolean deleteRanking(long rowId) {
		return db.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
	}

	//Mostra todos os scores no banco de dados
	public Cursor getAllRanking() {
		return db.query(DATABASE_TABLE, new String[] { KEY_ROWID, KEY_PONTOS, KEY_JOGADOR }, null, null, null, null, null);
	}

	//Retorna um score dado um ROWID
	public Cursor getRanking(long rowId) throws SQLException {
		Cursor mCursor = db.query(true, DATABASE_TABLE, new String[] {
				KEY_ROWID, KEY_PONTOS, KEY_JOGADOR}, KEY_ROWID
				+ "=" + rowId, null, null, null, null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}

	public boolean updateRanking(long rowId, String pontos, String jogador) {
		ContentValues args = new ContentValues();
		args.put(KEY_PONTOS, pontos);
		args.put(KEY_JOGADOR, jogador);
		return db.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
	}

}
