package com.example.duasactivities;

import com.example.duasactivities.R.id;

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
import android.widget.TextView;
import android.os.Build;

public class Janela2 extends Activity {

	private TextView tvNome;
	private Button btnVoltar;
	private final int CODE = 10;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_janela2);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		
		tvNome = (TextView)findViewById(id.tvNome);
		
		String usuario = getIntent().getStringExtra("usuario");
		tvNome.setText(usuario);
		
		btnVoltar = (Button)findViewById(id.btnVoltar);
		btnVoltar.setOnClickListener(new VoltarListener());
	}
	
	class VoltarListener implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.putExtra("retorno", "WTF!");
			setResult(CODE, intent);
			finish();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.janela2, menu);
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
			View rootView = inflater.inflate(R.layout.fragment_janela2,
					container, false);
			return rootView;
		}
	}

}
