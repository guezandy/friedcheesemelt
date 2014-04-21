package com.example.barcodereader;

import java.util.List;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;


public class MainActivity extends Activity implements OnClickListener {
	private Button scanBtn;
	private TextView formatTxt, contentTxt;	

	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_main);
	    Parse.initialize(this, "p5987h8D68oVGaaODQvrQfJxZvMKUj7rjiEAHsQf", "mTzcm86SoIlrXnOxZMzweAQljw3l3bslx6ZrAvTX");
	 
	    formatTxt = (TextView)findViewById(R.id.scan_format);
	    contentTxt = (TextView)findViewById(R.id.scan_content);
		scanBtn = (Button) findViewById(R.id.scan_button);
		scanBtn.setOnClickListener(this);	
		
		//Make Parse Object here!!
		//ParseObject item = new ParseObject("Bc2Web");
		//item.put("Barcode", "042238302211");
		//item.put("Website", "https://www.haribo.com/enUS/products/haribo/range/136/product/2489/title/gold-bears-5oz.html");
		//item.saveInBackground();
	}


	@Override
	public void onClick(View v) {
		if(v.getId()==R.id.scan_button){
			//scan
			IntentIntegrator scanIntegrator = new IntentIntegrator(this);
			scanIntegrator.initiateScan();
		}
		
	}	
	
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		//retrieve scan result
		IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
		if (scanningResult != null) {
			//we have a result
			String scanContent = scanningResult.getContents();
			String scanFormat = scanningResult.getFormatName();

			formatTxt.setText("FORMAT: " + scanFormat);
			contentTxt.setText("CONTENT: " + scanContent);

			System.out.println("Scan Content: "+ scanContent);
			Intent i = new Intent(MainActivity.this, WebViewActivity.class);
			i.putExtra("bc", scanContent);
			System.out.println("Starting webview activity");
			startActivity(i);
			
		} else {
		    Toast toast = Toast.makeText(getApplicationContext(), 
		            "No scan data received!", Toast.LENGTH_SHORT);
		             toast.show();
		}
	}
	
	
}