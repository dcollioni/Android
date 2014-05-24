package com.dcollioni.projetocliente;

import com.dcollioni.projetocliente.R.id;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

public class MainActivity extends Activity {

	private EditText etNome;
	private RadioGroup rgEstadoCivil;
	private CheckBox cbContaCorrente;
	private ToggleButton tbCartaoCredito;
	private Button btnCriar;
	
	private Cliente model;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
        
        model = new Cliente();
        
        btnCriar = (Button)findViewById(id.btnCriar);
        btnCriar.setOnClickListener(new CriarListener(this));
    }
    
    class CriarListener implements View.OnClickListener {

    	private Activity activity;
    	
    	public CriarListener(Activity activity) {
    		this.activity = activity;
    	}
    	
		@Override
		public void onClick(View v) {
			
			etNome = (EditText)activity.findViewById(id.etNome);
			rgEstadoCivil = (RadioGroup)activity.findViewById(id.rgEstadoCivil);
			cbContaCorrente = (CheckBox)activity.findViewById(id.cbContaCorrente);
			tbCartaoCredito = (ToggleButton)activity.findViewById(id.tbCartaoCredito);
			
			model.setNome(etNome.getText().toString());
			
			switch (rgEstadoCivil.getCheckedRadioButtonId()) {
				case id.rdCasado:
					model.setEstadoCivil(EstadoCivil.Casado);
					break;
				case id.rdSolteiro:
					model.setEstadoCivil(EstadoCivil.Solteiro);
					break;
			}

			model.setContaCorrente(cbContaCorrente.isChecked());
			model.setCartaoCredito(tbCartaoCredito.isChecked());
			
			Toast.makeText(getBaseContext(), model.toString(), Toast.LENGTH_LONG).show();
		}
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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }

}
