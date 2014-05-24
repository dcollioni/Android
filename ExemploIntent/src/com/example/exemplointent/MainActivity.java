package com.example.exemplointent;

import com.example.exemplointent.R.id;

import android.app.Activity;
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

public class MainActivity extends Activity {

	private EditText etTelefone;
	private Button btnOK;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
        
        etTelefone = (EditText)findViewById(id.etTelefone);
        btnOK = (Button)findViewById(id.btnOk);
        btnOK.setOnClickListener(new OkButtonClickListener());
    }
    
    class OkButtonClickListener implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			String telefone = etTelefone.getText().toString();
			
			Uri uri = Uri.parse("tel:" + telefone);
			Intent intent = new Intent(Intent.ACTION_CALL, uri);
			startActivity(intent);
			
			Uri uriHttp = Uri.parse("http://qi.edu.br");
			Intent intentBrowser = new Intent(Intent.ACTION_VIEW, uriHttp);
			startActivity(intentBrowser);
			finish();
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
