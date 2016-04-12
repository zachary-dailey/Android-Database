package com.example.zachary.database;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Zachary on 3/8/2016.
 */
public class DatabaseActivity extends AppCompatActivity
{
	TextView idView;
	EditText productBox;
	EditText quantityBox;


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		idView = (TextView) findViewById(R.id.prodID);
		productBox = (EditText) findViewById(R.id.prodName);
		quantityBox = (EditText) findViewById(R.id.prodQuant);

		IntentFilter mStatusIntentFilter = new IntentFilter(Constants.BROADCAST_ACTION);

		DownloadStateReceiver mDownloadStateReceiver = new DownloadStateReceiver();

		LocalBroadcastManager.getInstance(this).registerReceiver(
				mDownloadStateReceiver,
				mStatusIntentFilter
		);
	}

	public void addProduct (View view) {
		MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);

		int quantity = Integer.parseInt(quantityBox.getText().toString());

		Product product = new Product(productBox.getText().toString(), quantity);

		dbHandler.addProduct(product);
		idView.setText(R.string.added);
		productBox.setText("");
		//quantityBox.setText(String.valueOf(0));
	}

	public void findProduct (View view) {
		MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);

		Product product = dbHandler.findProduct(productBox.getText().toString());

		if (product != null) {
			idView.setText(String.valueOf(product.get_id()));
			quantityBox.setText(String.valueOf(product.get_quantity()));
		} else {
			idView.setText(R.string.no_match_found);
			quantityBox.setText(String.valueOf(0));
		}
	}

	public void findAllProduct (View view)
	{
		Intent intent = new Intent(this, ListerActivity.class);
		startActivity(intent);
	}

	public void loadProducts (View view)
	{
		Intent intent = new Intent(this, MyIntentService.class);
		startService(intent);
	}


	public void deleteProduct (View view) {
		MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);

		boolean result = dbHandler.deleteProduct(productBox.getText().toString());

		if (result)
		{
			idView.setText(R.string.deleted);
			productBox.setText("");
			quantityBox.setText(String.valueOf(R.string.deleted));
		}
		else
			idView.setText(R.string.no_match_found);
			quantityBox.setText(String.valueOf(0));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}
}
