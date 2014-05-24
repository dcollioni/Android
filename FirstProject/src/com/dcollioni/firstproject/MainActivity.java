package com.dcollioni.firstproject;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.EditText;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //TextView tv = (TextView)findViewById(R.id.tvHelloWorld);
        //Toast.makeText(getBaseContext(), tv.getText(), Toast.LENGTH_SHORT).show();
        
        //Button a = (Button)findViewById(R.id.btnCalculate);
        
        //View.OnClickListener onClick = new View.OnClickListener() {
			
			//@Override
			//public void onClick(View v) {
				// TODO Auto-generated method stub
				//Toast t = Toast.makeText(getBaseContext(), ((TextView)findViewById(R.id.tvHelloWorld)).getText(), Toast.LENGTH_SHORT);
		        //t.setGravity(0, 0, 0);
		        //t.show();
			//}
		//};
		
		//View a = findViewById(R.layout.fragment_main).findViewById(R.id.btnCalculate);
		
		//((Button)findViewById(R.id.btnCalculate)).setOnClickListener(onClick);
		        
        //tvHelloWorld = (TextView)findViewById(R.id.tvHelloWorld);
        //tvHelloWorld.setText("Dummy test...");

        /*
        Toast t = Toast.makeText(getBaseContext(), string.app_name, Toast.LENGTH_SHORT);
        t.setGravity(0, getWindow().getAttributes().width / 2 , getWindow().getAttributes().height / 2);
        t.setGravity(0, 0, 0);
        t.show();
        */
        
        /*
        AlertDialog.Builder msg = new AlertDialog.Builder(this);
        msg.setMessage("Entre seu nome");
        msg.setTitle("Olá");
        msg.setNeutralButton("Ok", null);
        msg.show();
        */
        
        Log.d("Lifecycle", "OnCreate Activity");
        
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }
	
	@Override
	protected void onResume() {
		Log.d("Lifecycle", "onResume Activity");
		super.onResume();
	}
	
	@Override
	protected void onStart() {
		Log.d("Lifecycle", "onStart Activity");
		super.onStart();
	}
	
	@Override
	protected void onRestart() {
		Log.d("Lifecycle", "onRestart Activity");
		super.onRestart();
	}
	
	@Override
	protected void onPause() {
		Log.d("Lifecycle", "onPause Activity");
		super.onPause();
	}
	
	@Override
	protected void onStop() {
		Log.d("Lifecycle", "onStop Activity");
		super.onStop();
	}
	
	@Override
	protected void onDestroy() {
		Log.d("Lifecycle", "onDestroy Activity");
		super.onDestroy();
	}
    
    public void onBtnSayHelloClick(View v) {
    	Toast t = Toast.makeText(getBaseContext(), ((TextView)findViewById(R.id.tvHelloWorld)).getText(), Toast.LENGTH_SHORT);
        t.setGravity(0, 0, 0);
        t.show();
    }
    
    public void btnCalculateClick(View v) {
    	double firstValue = Double.parseDouble(((EditText) findViewById(R.id.firstNumber)).getText().toString());
    	double secondValue = Double.parseDouble(((EditText) findViewById(R.id.secondNumber)).getText().toString());
    	
    	double result = firstValue + secondValue;
    	((EditText) findViewById(R.id.result)).setText(Double.toString(result));
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
            final View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            //View v = inflater.inflate(R.layout.fragment_main, null);
            final Activity activity = this.getActivity();
            
            Log.d("Lifecycle", "OnCreateView");
            
            //TextView tv = (TextView)rootView.findViewById(R.id.tvHelloWorld);
            //String a = tv.getText().toString();
            		
            //Toast.makeText(activity.getBaseContext(), a, Toast.LENGTH_SHORT).show();
            
            final Button b = (Button)rootView.findViewById(R.id.btnCalculate);
            
            View.OnClickListener onClick = new View.OnClickListener() {
    			
    			@Override
    			public void onClick(View v) {
    				double firstValue = Double.parseDouble(((EditText) rootView.findViewById(R.id.firstNumber)).getText().toString());
    		    	double secondValue = Double.parseDouble(((EditText) rootView.findViewById(R.id.secondNumber)).getText().toString());
    		    	
    		    	double result = firstValue + secondValue;
    		    	((EditText) rootView.findViewById(R.id.result)).setText(Double.toString(result));
    			
    				
    			}
    		};
    		
    		b.setOnClickListener(onClick);
    		
    		View.OnLongClickListener onLongClick = new View.OnLongClickListener() {
				
				@Override
				public boolean onLongClick(View v) {
					Toast.makeText(activity.getBaseContext(), b.getText(), Toast.LENGTH_SHORT).show();
					
					return true;
				}
			};
			
			b.setOnLongClickListener(onLongClick);
            
            
            //Button a = (Button)findViewById(R.id.btnCalculate);
            
            return rootView;
        }
    }
    
    

}
