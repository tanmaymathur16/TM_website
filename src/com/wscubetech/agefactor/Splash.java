package com.wscubetech.agefactor;

import com.wscubetech.predictmyage.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

public class Splash extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.splash);

		Thread splash = new Thread() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				super.run();
				synchronized (this) {
					try {
						wait(3000);

						Intent goToMain = new Intent(Splash.this,
								MainActivity.class);
						startActivity(goToMain);
						finish();

					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			}
		};

		splash.start();

	}

}
