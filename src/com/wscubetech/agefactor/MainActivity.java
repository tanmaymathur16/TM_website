package com.wscubetech.agefactor;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wscubetech.predictmyage.R;

public class MainActivity extends Activity {

	ImageView img1, img2, img3, img4, img5, img6, img7;
	TextView txtPageNumber, txtWhiteBar;
	RelativeLayout relativeAnim;

	Button btnYes, btnNo;
	int btnCount = 1, sum_of_age = 0, noFlag = 0;

	Animation anim;
	AnimationSet aset = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		setVolumeControlStream(AudioManager.STREAM_MUSIC);

		showMyAboutDialog();

		img1 = (ImageView) findViewById(R.id.img1);
		img2 = (ImageView) findViewById(R.id.img2);
		img3 = (ImageView) findViewById(R.id.img3);
		img4 = (ImageView) findViewById(R.id.img4);
		img5 = (ImageView) findViewById(R.id.img5);
		img6 = (ImageView) findViewById(R.id.img6);
		img7 = (ImageView) findViewById(R.id.img7);

		txtWhiteBar = (TextView) findViewById(R.id.whiteBar);

		Typeface custom_font = Typeface.createFromAsset(getAssets(),
				"fonts/LCALLIG.TTF");
		txtWhiteBar.setTypeface(custom_font);

		relativeAnim = (RelativeLayout) findViewById(R.id.relSurrounding);

		txtPageNumber = (TextView) findViewById(R.id.txtPageNumber);

		btnYes = (Button) findViewById(R.id.btnYes);
		btnNo = (Button) findViewById(R.id.btnNo);

		btnYes.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				playerStart(R.raw.tinypush);
				// noFlag = 0;
				sum_of_age += Math.pow(2, btnCount - 1);
				commonEventOfButtons();
			}
		});

		btnNo.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				playerStart(R.raw.tinypush);
				// noFlag = 1;
				commonEventOfButtons();
			}
		});

	}

	public void animatingSet(int animation) {
		aset = (AnimationSet) AnimationUtils.loadAnimation(
				getApplicationContext(), animation);
		for (Animation a : aset.getAnimations()) {
			a.setAnimationListener(new AnimationListener() {

				@Override
				public void onAnimationStart(Animation animation) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onAnimationRepeat(Animation animation) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onAnimationEnd(Animation animation) {
					// TODO Auto-generated method stub

				}
			});
		}
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub

		showMyDialog("Age Factor", "What to do?");

		// if (btnCount <= 1) {
		// super.onBackPressed();
		// } else {
		//
		// pageGone();
		//
		// btnCount = btnCount - 2;
		//
		// if (noFlag == 0) {
		// sum_of_age -= Math.pow(2, btnCount);
		// }
		//
		// commonEventOfButtons();
		//
		// }

	}

	public void showMyAboutDialog() {
		final Dialog dialog = new Dialog(MainActivity.this);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.dialog_about_app);
		dialog.setCancelable(false);

		Button btnAboutApp;
		btnAboutApp = (Button) dialog.findViewById(R.id.btnAboutApp);
		final LinearLayout linearAbout = (LinearLayout) dialog
				.findViewById(R.id.linear_about);

		btnAboutApp.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				playerStart(R.raw.tinypush);

				// dialog.getWindow().setBackgroundDrawable(
				// new ColorDrawable(Color.TRANSPARENT));

				animatingSet(R.anim.slide_up);
				linearAbout.startAnimation(aset);

				btnNo.setEnabled(true);
				btnYes.setEnabled(true);

				Thread wait_count = new Thread() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						super.run();
						synchronized (this) {
							try {
								wait(400);
								runOnUiThread(new Runnable() {
									@Override
									public void run() {
										// TODO Auto-generated method stub
										dialog.dismiss();
										animatingSet(R.anim.fade_in);
										relativeAnim.startAnimation(aset);
									}
								});
							} catch (Exception e) {
								// TODO: handle exception
							}
						}
					}
				};
				wait_count.start();

			}
		});

		// dialog.getWindow().setBackgroundDrawable(
		// getResources().getDrawable(R.drawable.black_trans_dialog_bg));
		dialog.show();
		animatingSet(R.anim.slide_down);
		linearAbout.startAnimation(aset);
	}

	public void showMyDialog(String strHeader, String strAns) {

		final Dialog dialog = new Dialog(MainActivity.this);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.age_dialog);
		dialog.setCancelable(false);

		TextView txtAns, header;
		Button btnReplay, btnExit;
		header = (TextView) dialog.findViewById(R.id.dialogHeader);
		txtAns = (TextView) dialog.findViewById(R.id.dialogAns);
		btnReplay = (Button) dialog.findViewById(R.id.btnDone);
		btnExit = (Button) dialog.findViewById(R.id.btnExit);

		final LinearLayout linearResult = (LinearLayout) dialog
				.findViewById(R.id.linear_result);

		if (strHeader.equals("Age Factor")) {
			Typeface customFontType = Typeface.DEFAULT;
			txtAns.setTypeface(customFontType);
			header.setTypeface(customFontType);
			btnReplay.setTypeface(customFontType);
			btnExit.setTypeface(customFontType);

		} else {

			Typeface customFontType = Typeface.createFromAsset(getAssets(),
					"fonts/LHANDW.TTF");

			txtAns.setTypeface(customFontType);
			header.setTypeface(customFontType);

		}

		header.setText(strHeader);
		txtAns.setText(strAns);
		btnReplay.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Thread wait_count = new Thread() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						super.run();
						synchronized (this) {
							try {
								wait(400);
								runOnUiThread(new Runnable() {
									@Override
									public void run() {
										// TODO Auto-generated method stub
										dialog.dismiss();
									}
								});
							} catch (Exception e) {
								// TODO: handle exception
							}
						}
					}
				};
				wait_count.start();

				animatingSet(R.anim.slide_up);
				linearResult.startAnimation(aset);

				playerStart(R.raw.tinypush);

				btnCount = 0;
				sum_of_age = 0;
				pageGone();
				btnNo.setEnabled(true);
				btnYes.setEnabled(true);
				commonEventOfButtons();
			}
		});

		btnExit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				playerStart(R.raw.tinypush);
				dialog.dismiss();
				finish();
			}
		});

		// dialog.getWindow().setBackgroundDrawable(
		// getResources().getDrawable(R.drawable.black_trans_dialog_bg));

		dialog.show();

		animatingSet(R.anim.slide_down);
		linearResult.startAnimation(aset);

	}

	public void commonEventOfButtons() {
		btnCount += 1;
		if (btnCount >= 8) {

			btnNo.setEnabled(false);
			btnYes.setEnabled(false);

			btnCount = 7;
			showMyDialog("Your age is..", "" + sum_of_age);

		} else {
			changePage();
		}
	}

	public void pageGone() {
		img1.setVisibility(View.GONE);
		img2.setVisibility(View.GONE);
		img3.setVisibility(View.GONE);
		img4.setVisibility(View.GONE);
		img5.setVisibility(View.GONE);
		img6.setVisibility(View.GONE);
		img7.setVisibility(View.GONE);
	}

	public void changePage() {

		// pageGone();

		Thread wait_count = new Thread() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				super.run();
				synchronized (this) {
					try {
						wait(400);
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								// TODO Auto-generated method stub
								txtPageNumber.setText(btnCount + " / 7");
							}
						});
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			}
		};

		switch (btnCount) {
		case 1:
			img7.setVisibility(View.VISIBLE);
			flipCard(img7, img1);
			break;

		case 2:
			img1.setVisibility(View.VISIBLE);
			flipCard(img1, img2);
			break;

		case 3:
			img2.setVisibility(View.VISIBLE);
			flipCard(img2, img3);
			break;

		case 4:
			img3.setVisibility(View.VISIBLE);
			flipCard(img3, img4);
			break;

		case 5:
			img4.setVisibility(View.VISIBLE);
			flipCard(img4, img5);
			break;

		case 6:
			img5.setVisibility(View.VISIBLE);
			flipCard(img5, img6);
			break;

		case 7:
			img6.setVisibility(View.VISIBLE);
			flipCard(img6, img7);
			break;
		}

		wait_count.start();

	}

	public void flipCard(ImageView i1, ImageView i2) {

		FlipAnimation flipAnimation = new FlipAnimation(i1, i2);

		if (i1.getVisibility() == View.GONE) {
			flipAnimation.reverse();
		}

		relativeAnim.startAnimation(flipAnimation);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.option_about:
			Intent intent_about = new Intent(MainActivity.this, About.class);
			startActivity(intent_about);
			break;

		case R.id.option_exit:
			finish();
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	public void playerStart(int audio) {

		MediaPlayer mPlayer = MediaPlayer
				.create(getApplicationContext(), audio);
		mPlayer.start();
		mPlayer.setOnCompletionListener(new OnCompletionListener() {

			@Override
			public void onCompletion(MediaPlayer mp) {
				// TODO Auto-generated method stub
				mp.release();
			}
		});

	}

}
