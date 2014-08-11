package collioni.douglas.projetobanco;

import java.util.ArrayList;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends Activity {

	ObjectContainer db;
	final String DB4OFILENAME = "/MeuBanco.yap";
	
	ListView lvTarefas;
	EditText etDescricao;
	Button btnIncluir;
	
	ArrayList<Tarefa> tarefas;
	ArrayAdapter<Tarefa> adapter;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        lvTarefas = (ListView)findViewById(R.id.lvTarefas);
        etDescricao = (EditText)findViewById(R.id.etDescricao);
        btnIncluir = (Button)findViewById(R.id.btnIncluir);
        
        String file = getDir("data", 0) + DB4OFILENAME;
        db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), file);
        
        tarefas = new ArrayList<Tarefa>();
        
        adapter = new ArrayAdapter<Tarefa>(getBaseContext(),	
				android.R.layout.simple_dropdown_item_1line,
				tarefas);

    	adapter.setDropDownViewResource(android.R.layout.simple_list_item_activated_1);
    	adapter.setNotifyOnChange(true);
    	
    	lvTarefas.setAdapter(adapter);
        
        btnIncluir.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				incluirTarefa();
			}
		});
        
        lvTarefas.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				Tarefa t = (Tarefa)parent.getItemAtPosition(position);
				db.delete(t);
				
				atualizarLista();
				
				return false;
			}
		});
    }
    
    private void incluirTarefa() {
    	String descricao = etDescricao.getText().toString();
    	Tarefa tarefa = new Tarefa(descricao);
    	
    	db.store(tarefa);
    	db.commit();
    	
    	etDescricao.setText(null);
    	
    	atualizarLista();
    }
    
    private void atualizarLista() {
    	try {
        	tarefas.clear();
        	
    		ObjectSet<Tarefa> ts = db.query(Tarefa.class);
        	
        	for (Tarefa tarefa : ts) {
				tarefas.add(tarefa);
			}
        	
        	adapter.notifyDataSetChanged();
        }
    	catch (Exception e) {
    		Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
    	}
    }
    
    @Override
    protected void onResume() {
    	atualizarLista();
    	
    	super.onResume();
    }
    
    @Override
    protected void onDestroy() {
    	
    	if (db != null) {
    		db.close();
    	}
    	
    	super.onDestroy();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
}
