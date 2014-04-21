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


public class WebViewActivity extends Activity {

	private WebView myWebView;
	
	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    System.out.println("Launching setContent view");
	    setContentView(R.layout.activity_webview);
	    System.out.println("Initialuizing Parse");
	    Parse.initialize(this, "p5987h8D68oVGaaODQvrQfJxZvMKUj7rjiEAHsQf", "mTzcm86SoIlrXnOxZMzweAQljw3l3bslx6ZrAvTX");
		System.out.println("Grabbing from bundle");
	    Bundle bundle = getIntent().getExtras();
	    System.out.println("Setting webview");
		myWebView = (WebView) findViewById(R.id.webview);
		
		
		if(bundle.getString("bc")!=null) {
			System.out.println("Inside found bc");
			final String bc = bundle.getString("bc");
			System.out.println("Got from bundle: "+ bc);
			ParseQuery<ParseObject> query = ParseQuery.getQuery("Bc2Web");  
			query.findInBackground(new FindCallback<ParseObject>() {
				@Override
				public void done(List<ParseObject> list, ParseException e) {
			        if (e == null) {
			        	for(ParseObject p: list) {
			        		System.out.println("Inside For loop");
			        		if(p.getString("Barcode") != null && p.getString("Barcode").equals(bc)) {
					            System.out.println("Item found! Website: " + p.getString("Website").toString());
								myWebView.loadUrl(p.getString("Website"));
			        		}
			        	}
			        } else {
			            Log.d("score", "Error: " + e.getMessage());
			        }
				}
			});
		} else{
			System.out.println("No idea how i was able to get in here");
		}
	}
	

	
	
}