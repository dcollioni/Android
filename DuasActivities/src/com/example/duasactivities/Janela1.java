package com.example.duasactivities;

import com.example.duasactivities.R.id;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Build;

public class Janela1 extends Activity {

	private EditText etUsuario;
	private EditText etSenha;
	private Button btnEntrar;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_janela1);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
        
        etUsuario = (EditText)findViewById(id.etUsuario);
        etSenha = (EditText)findViewById(id.etSenha);
        btnEntrar = (Button)findViewById(id.btnEntrar);
        
        btnEntrar.setOnClickListener(new EntrarListener());
    }

    class EntrarListener implements View.OnClickListener {

		@Override
		public void onClick(View v) {		
			String usuario = etUsuario.getText().toString();
			String senha = etSenha.getText().toString();
			
			Intent intent = new Intent(Janela1.this, Janela2.class);
			intent.putExtra("usuario", usuario);
			intent.putExtra("senha", senha);
			//startActivity(intent);
			startActivityForResult(intent, 10);
			//finish();
		}    	
    }
    
    @Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	if (resultCode == 10) {
    		
    		Bundle params = data != null ? data.getExtras() : null;
    		
    		if (params != null) {
    			Toast.makeText(getBaseContext(), params.getString("retorno"), Toast.LENGTH_SHORT).show();    			
    		}
   		}
    	
    	super.onActivityResult(requestCode, resultCode, data);
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.janela1, menu);
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
            View rootView = inflater.inflate(R.layout.fragment_janela1, container, false);
            return rootView;
        }
    }

}
