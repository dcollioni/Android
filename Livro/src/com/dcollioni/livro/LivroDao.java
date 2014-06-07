package com.dcollioni.livro;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class LivroDao {

	private SQLiteDatabase db;
	private BaseDao dbHelper;
	
	private String[] colunas = { BaseDao.LIVRO_ID,
								 BaseDao.LIVRO_TITULO,
								 BaseDao.LIVRO_AUTOR,
								 BaseDao.LIVRO_CAPITULO,
								 BaseDao.LIVRO_PAGINA };
	
	public LivroDao(Context context) {
		super();
		dbHelper = new BaseDao(context);
	}
	
	public void openDB() {
		db = dbHelper.getWritableDatabase();
		//db = dbHelper.getDatabaseName();
	}
	
	public void closeDB() {
		dbHelper.close();
	}
	
	public long insert(Livro livro) {
		ContentValues values = new ContentValues();
		values.put(BaseDao.LIVRO_TITULO, livro.getTitle());
		values.put(BaseDao.LIVRO_AUTOR, livro.getAuthor());
		values.put(BaseDao.LIVRO_CAPITULO, livro.getChapter());
		values.put(BaseDao.LIVRO_PAGINA, livro.getPage());
		
		return db.insert(BaseDao.TBL_LIVRO, null, values);
	}
	
	public int update(Livro livro) {
		ContentValues values = new ContentValues();
		values.put(BaseDao.LIVRO_TITULO, livro.getTitle());
		values.put(BaseDao.LIVRO_AUTOR, livro.getAuthor());
		values.put(BaseDao.LIVRO_CAPITULO, livro.getChapter());
		values.put(BaseDao.LIVRO_PAGINA, livro.getPage());

		return db.update(BaseDao.TBL_LIVRO, values, BaseDao.LIVRO_ID + "=?", new String[] { Integer.toString(livro.getId()) });
	}
	
	public int delete(int livroId) {
		return db.delete(BaseDao.TBL_LIVRO, BaseDao.LIVRO_ID + "=?", new String[] { Integer.toString(livroId) });
	}
	
	public ArrayList<Livro> select() {
		Cursor c = db.query(BaseDao.TBL_LIVRO, colunas, null, null, null, null, BaseDao.LIVRO_TITULO);
		
		ArrayList<Livro> livros = new ArrayList<Livro>();
		
		c.moveToFirst();
		
		while (c.moveToNext()) {
			Livro l = new Livro();
			l.setId(c.getInt(0));
			l.setTitle(c.getString(1));
			l.setAuthor(c.getString(2));
			l.setChapter(c.getString(3));
			l.setPage(c.getString(4));
			
			livros.add(l);
		}
		
		c.close();
				
		return livros;
	}
}
