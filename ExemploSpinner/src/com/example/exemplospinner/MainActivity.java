package com.example.exemplospinner;

import com.example.exemplospinner.R.array;
import com.example.exemplospinner.R.id;

import android.R.layout;
import android.app.Activity;
import android.app.Fragment;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.*;

public class MainActivity extends Activity {

	Button btnOK;
	Spinner spPais;
	final int MSG1 = 0;
	final int MSG2 = 1;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
        
        spPais = (Spinner)findViewById(id.spPais);
        
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, array.paises, layout.simple_spinner_item);
        adapter.setDropDownViewResource(layout.simple_spinner_dropdown_item);
        spPais.setAdapter(adapter);
        
        spPais.setSelection(0, false);
        
        spPais.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {

				Toast.makeText(getBaseContext(), parent.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
        
        btnOK = (Button)findViewById(id.btnOK);
        btnOK.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String selectedItem = spPais.getSelectedItem().toString();
				selectedItem = Long.toString(spPais.getSelectedItemId());
				Toast.makeText(getBaseContext(), selectedItem, Toast.LENGTH_SHORT).show();
			}
		});
        
        this.registerForContextMenu(btnOK);
        this.registerForContextMenu(spPais);
    }
    
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
    		ContextMenuInfo menuInfo) {
    	
    	if (v == btnOK) {
    		//menu.add(MSG1, MSG1, 100, "Mensagem 1");
    		//menu.add(MSG2, MSG2, 200, "Mensagem 2");
    		
    		getMenuInflater().inflate(R.menu.ctx1, menu);
    	}
    	else if (v == spPais) {
    		//menu.add(MSG1, MSG1, 100, "Mensagem 3");
    		//menu.add(MSG2, MSG2, 200, "Mensagem 4");
    		
    		getMenuInflater().inflate(R.menu.ctx2, menu);
    	}
    	
    	super.onCreateContextMenu(menu, v, menuInfo);
    }
    
    @Override
    public boolean onContextItemSelected(MenuItem item) {
    	// TODO Auto-generated method stub
    	
    	Toast.makeText(getBaseContext(), item.getTitle() + " id: " + item.getItemId(), Toast.LENGTH_SHORT).show();
    	
    	int id = item.getItemId();
    	
    	switch (id) {
		case MSG1:
			
			break;
			
		case MSG2:
			
			break;

		default:
			break;
		}
    	
    	return super.onContextItemSelected(item);
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
