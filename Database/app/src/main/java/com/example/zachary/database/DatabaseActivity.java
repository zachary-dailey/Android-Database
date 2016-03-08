package com.example.zachary.database;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

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
	}

	public void addProduct (View view) {
		MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);

		int quantity = Integer.parseInt(quantityBox.getText().toString());

		Product product = new Product(productBox.getText().toString(), quantity);

		dbHandler.addProduct(product);
		productBox.setText("");
		quantityBox.setText("");
	}

	public void findProduct (View view) {
		MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);

		Product product = dbHandler.findProduct(productBox.getText().toString());

		if (product != null) {
			idView.setText(String.valueOf(product.get_id()));
			quantityBox.setText(String.valueOf(product.get_quantity()));
		} else {
			idView.setText(R.string.no_match_found);
		}
	}

	public void deleteProduct (View view) {
		MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);

		boolean result = dbHandler.deleteProduct(productBox.getText().toString());

		if (result)
		{
			idView.setText(R.string.deleted);
			productBox.setText("");
			quantityBox.setText("");
		}
		else
			idView.setText(R.string.no_match_found);
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
