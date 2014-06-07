package com.dcollioni.livro;

import java.util.ArrayList;

import org.apache.http.impl.client.RequestWrapper;

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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.os.Build;

public class TelaInicial extends Activity {

	public static final int INSERT = 1;
	public static final int UPDATE = 2;
	public static final int DELETE = 3;
	
	private LivroDao livroDao;
	private ArrayList<Livro> livros;
	private LivroAdapter livroAdapter;
	
	private ListView lstBooks;
	private Button btnAdd;
	
	private int selectedIndex = -1;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
        
        livroDao = new LivroDao(this);
        livroDao.openDB();
        
        livros = livroDao.select();
        livroAdapter = new LivroAdapter(this, livros);
        
        lstBooks = (ListView)findViewById(id.lstBooks);
        btnAdd = (Button)findViewById(id.btnAdd);
        
        lstBooks.setAdapter(livroAdapter);
        
        lstBooks.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				selectedIndex = position;
			}
		});
        
        btnAdd.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try {
					Intent intent = new Intent(TelaInicial.this, TelaCadastro.class);
					intent.putExtra("action", INSERT);
					startActivityForResult(intent, INSERT);
				} catch (Exception e) {
					trace("btnAdd.onClick: " + e.getMessage());
				}
			}
		});
    }
    
    @Override
    protected void onResume() {
    	super.onResume();
    	livroDao.openDB();
    }
    
    @Override
    protected void onDestroy() {
    	super.onPause();
    	livroDao.closeDB();
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	super.onActivityResult(requestCode, resultCode, data);
    	Livro livro = null;
    	
    	try {
			if (resultCode == TelaCadastro.CONFIRM) {
				livro = (Livro)data.getExtras().getSerializable("livro");
				
				if (requestCode == INSERT) {
					livroDao.insert(livro);
					livros.add(livro);
				}
				else if (requestCode == UPDATE) {
					livroDao.update(livro);
					livros.set(selectedIndex, livro);
				}
				
				livroAdapter.notifyDataSetChanged();
			}
		} catch (Exception e) {
			trace(e.getMessage());
		}
    }
    
    private void trace(String msg) {
    	Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tela_inicial, menu);
        
        menu.add(UPDATE, UPDATE, 100, "UPDATE");
        menu.add(DELETE, DELETE, 200, "DELETE");
        
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        
        Livro l = livros.get(selectedIndex);
        
        switch (id) {
        case UPDATE:
        	Intent intent = new Intent(TelaInicial.this, TelaCadastro.class);
            intent.putExtra("livro", l);
        	intent.putExtra("action", UPDATE);
        	startActivityForResult(intent, UPDATE);
        	break;
        case DELETE:
        	livroDao.delete(l.getId());
        	livros.remove(selectedIndex);
        	livroAdapter.notifyDataSetChanged();
        	break;
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
            View rootView = inflater.inflate(R.layout.fragment_tela_inicial, container, false);
            return rootView;
        }
    }

}
