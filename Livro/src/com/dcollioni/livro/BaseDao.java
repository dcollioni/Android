package com.dcollioni.livro;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class BaseDao extends SQLiteOpenHelper {
	
	public static final String TBL_LIVRO = "livro";
	public static final String LIVRO_ID = "id";
	public static final String LIVRO_TITULO = "titulo";
	public static final String LIVRO_AUTOR = "autor";
	public static final String LIVRO_CAPITULO = "capitulo";
	public static final String LIVRO_PAGINA = "pagina";
	
	public static final String DATABASE_NAME = "bancolivro.sqlite";
	public static final int DATABASE_VERSION = 2;
	
	public static final String CREATE_LIVRO = "create table " + TBL_LIVRO +
												" ( " +
													LIVRO_ID + " integer not null primary key autoincrement, " +
													LIVRO_AUTOR + " text not null, " +       
													LIVRO_TITULO + " text not null, " +       
													LIVRO_CAPITULO + " text not null, " +       
													LIVRO_PAGINA + " text not null " +
												" ); ";
	
	public static final String DROP_LIVRO = "drop table if exists " + TBL_LIVRO;
		
	public BaseDao(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_LIVRO);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(DROP_LIVRO);
		onCreate(db);
	}
}
