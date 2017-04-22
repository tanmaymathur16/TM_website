package com.wscubetech.agefactor;

import com.wscubetech.predictmyage.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

public class About extends Activity {

	Activity act;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_about);
		act = this;

	}

	public void visitSite(View v) {
		if (isNetworkOnline()) {

			String url = "http:\\www.wscubetech.com";
			Intent i = new Intent(Intent.ACTION_VIEW);
			i.setData(Uri.parse(url));
			startActivity(i);

		} else {
			AlertDialog.Builder alt_bld = new AlertDialog.Builder(act);
			// Title for AlertDialog
			alt_bld.setTitle("No Internet Access");

			alt_bld.setMessage("Please check your internet connection")
					.setCancelable(false)
					.setPositiveButton("Ok",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									// Action for 'Yes' Button

									Intent intent = new Intent();
									intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
									intent.setAction(android.provider.Settings.ACTION_SETTINGS);
									startActivity(intent);
								}

							});
			// Icon for AlertDialog
			alt_bld.setIcon(R.drawable.ic_launcher);
			alt_bld.show();

		}
	}

	public void mailSending(View v) {
		if (isNetworkOnline()) {
			// String recepient = "info@wscubetech.com";
			String subject = "Your subject come here";
			Intent i = new Intent(Intent.ACTION_SEND);
			i.setType("message/rfc822");
			i.putExtra(Intent.EXTRA_EMAIL,
					new String[] { "info@wscubetech.com" });
			i.putExtra(Intent.EXTRA_SUBJECT, subject);
			i.putExtra(Intent.EXTRA_TEXT, "");
			try {
				startActivity(Intent.createChooser(i, "Send mail..."));
			} catch (android.content.ActivityNotFoundException ex) {
				Toast.makeText(About.this,
						"There are no email clients installed.",
						Toast.LENGTH_SHORT).show();
			}

		} else {
			AlertDialog.Builder alt_bld = new AlertDialog.Builder(act);
			// Title for AlertDialog
			alt_bld.setTitle("No Internet Access");

			alt_bld.setMessage("Please check your internet connection")
					.setCancelable(false)
					.setPositiveButton("Ok",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									// Action for 'Yes' Button

									Intent intent = new Intent();
									intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
									intent.setAction(android.provider.Settings.ACTION_SETTINGS);
									startActivity(intent);
								}

							});
			// Icon for AlertDialog
			alt_bld.setIcon(R.drawable.ic_launcher);
			alt_bld.show();

		}

	}

	public void phoneDial(View v) {
		Intent callIntent = new Intent(Intent.ACTION_DIAL);
		callIntent.setData(Uri.parse("tel:+919269698122"));
		startActivity(callIntent);
	}

	public boolean isNetworkOnline() {
		boolean status = false;
		try {
			ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo netInfo = cm
					.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
			if (netInfo != null
					&& netInfo.getState() == NetworkInfo.State.CONNECTED) {
				status = true;
			}

			else {
				netInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
				if (netInfo != null
						&& netInfo.getState() == NetworkInfo.State.CONNECTED)
					status = true;
			}
		}

		catch (Exception e) {
			// e.printStackTrace();
			return false;
		}
		return status;

	}

}
