package com.example.exemplomenu;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends Activity {

	private static final int TELA0 = 0;
	private static final int TELA1 = 1;
	private static final int TELA2 = 2;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
    	
        MenuItem a = menu.add(TELA0, TELA0, 300, "Tela 0");
        menu.add(TELA1, TELA1, 400, "Tela 1");
    	menu.add(TELA2, TELA2, 500, "Tela 2");
    	
    	a.setIcon(R.drawable.ic_launcher);
    	
    	getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        
        //Toast.makeText(getBaseContext(), item.getTitle() + " id: " + id, Toast.LENGTH_SHORT).show();
        
        Intent intent;
        
        switch (id) {
        case TELA0:
			intent = new Intent(this, MainActivity.class);
			startActivity(intent);
			break;
        
        case TELA1:
			intent = new Intent(this, Tela1.class);
			startActivity(intent);
			break;
			
		case TELA2:
			intent = new Intent(this, Tela2.class);
			startActivity(intent);
			break;

		default:
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
