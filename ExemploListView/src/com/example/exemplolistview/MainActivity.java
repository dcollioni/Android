package com.example.exemplolistview;

import java.util.ArrayList;

import com.example.exemplolistview.R.id;

import android.R.array;
import android.R.layout;
import android.R.string;
import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils.StringSplitter;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends Activity {

	ListView lvPaises;
	Button btnOk;
	ArrayList<String> paises;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
        
        lvPaises = (ListView)findViewById(id.lvPaises);
        btnOk = (Button)findViewById(id.btnOk);
        paises = new ArrayList<String>();
        fillPaises();
        
		ArrayAdapter<String> la = new ArrayAdapter<String>(getBaseContext(), layout.simple_list_item_checked, paises);
        lvPaises.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		lvPaises.setAdapter(la);
		
		btnOk.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				int p = lvPaises.getCheckedItemPosition();
				String selected = lvPaises.getItemAtPosition(p).toString();
				
				Toast.makeText(getBaseContext(), selected, Toast.LENGTH_SHORT).show();
			}
		});
		
		lvPaises.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				//((ArrayAdapter<String>)lvPaises.getAdapter()).remove(paises.get(position));
				
				Toast.makeText(getBaseContext(), Integer.toString(position), Toast.LENGTH_SHORT).show();
			}
		});
		
		lvPaises.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {

				paises.remove(position);
				((ArrayAdapter<String>)parent.getAdapter()).notifyDataSetChanged();
				
				return true;
			}
		});
    }
    
    private void fillPaises() {
    	paises.add("Argentina");
    	paises.add("Brasil");
    	paises.add("Chile");
    	paises.add("Uruguai");
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
