package com.dcollioni.livro;

import java.util.ArrayList;

import com.dcollioni.livro.R.id;
import com.dcollioni.livro.R.layout;
import com.dcollioni.livro.R.string;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class LivroAdapter extends BaseAdapter {

	private Context context;
	private ArrayList<Livro> livros;
	private LayoutInflater inflater;
	
	public TextView tvTitle;
	public TextView tvAuthor;
	public TextView tvChapter;
	public TextView tvPage;
	
	public LivroAdapter(Context context, ArrayList<Livro> livros) {
		super();
		this.context = context;
		this.livros = livros;
		
		inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	@Override
	public void notifyDataSetChanged() {
		try {
			super.notifyDataSetChanged();
		}
		catch (Exception e) {
			trace("notifyDataSetChanged: " + e.getMessage());
		}
	}
	
	public void add(Livro livro) {
		livros.add(livro);
	}
	
	public void remove(Livro livro) {
		livros.remove(livro);
	}
	
	@Override
	public int getCount() {
		return livros.size();
	}

	@Override
	public Object getItem(int position) {
		return livros.get(position);
	}

	@Override
	public long getItemId(int position) {
		return livros.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		try {
			Livro livro = livros.get(position);
			
			convertView = inflater.inflate(layout.livro_row, null);
			
			tvTitle = (TextView)convertView.findViewById(id.tvTitle);
			tvAuthor = (TextView)convertView.findViewById(id.tvAuthor);
			tvChapter = (TextView)convertView.findViewById(id.tvChapter);
			tvPage = (TextView)convertView.findViewById(id.tvPage);
			
			tvTitle.setText("Título: " + livro.getTitle());
			tvAuthor.setText("Autor: " + livro.getAuthor());
			tvChapter.setText("Capítulo: " + livro.getChapter());
			tvPage.setText("Página: " + livro.getPage());
			
			return convertView;
		} catch (Exception e) {
			trace("getView: " + e.getMessage());
		}
		
		return null;
	}
	
	private void trace(String msg) {
		Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
	}
	
}
