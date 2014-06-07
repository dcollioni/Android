package com.dcollioni.livro;

import com.dcollioni.livro.R.id;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.os.Build;

public class TelaCadastro extends Activity {

	public static final int CONFIRM = 1;
	public static final int CANCEL = 0;
	
	private int acao;
	private Livro livro;
	
	private EditText etTitle;
	private EditText etAuthor;
	private EditText etChapter;
	private EditText etPage;
	private Button btnConfirm;
	private Button btnCancel;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tela_cadastro);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		
		etTitle = (EditText)findViewById(id.etTitle);
		etAuthor = (EditText)findViewById(id.etAuthor);
		etChapter = (EditText)findViewById(id.etChapter);
		etPage = (EditText)findViewById(id.etPage);
		
		btnConfirm = (Button)findViewById(id.btnConfirm);
		btnCancel = (Button)findViewById(id.btnCancel);
		
		acao = getIntent().getIntExtra("action", 0);
		
		if (acao == TelaInicial.UPDATE) {
			livro = (Livro)getIntent().getSerializableExtra("livro");
			
			etTitle.setText(livro.getTitle());
			etAuthor.setText(livro.getAuthor());
			etChapter.setText(livro.getChapter());
			etPage.setText(livro.getPage());
		}
		else {
			livro = new Livro();
		}
		
		btnConfirm.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
					//livro = new Livro();
					livro.setTitle(etTitle.getText().toString());
					livro.setAuthor(etAuthor.getText().toString());
					livro.setChapter(etChapter.getText().toString());
					livro.setPage(etPage.getText().toString());
					
					Intent intent = new Intent();
					intent.putExtra("livro", livro);
					setResult(CONFIRM, intent);
					finish();
				
			}
		});
		
		btnCancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				setResult(CANCEL, intent);
				finish();
			}
		});
		
		//Livro livro = (Livro)getIntent().getSerializableExtra("livro");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tela_cadastro, menu);
		
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_tela_cadastro,
					container, false);
			return rootView;
		}
	}

}
