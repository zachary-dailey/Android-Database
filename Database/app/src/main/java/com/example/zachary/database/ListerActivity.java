package com.example.zachary.database;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.zachary.database.provider.MyContentProvider;

public class ListerActivity extends ActionBarActivity implements LoaderManager.LoaderCallbacks<Cursor>
{
	private static final int LIST_LOADER = 0;
	private SimpleCursorAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.items_list);

		String[] mFromColumns = {
				MyDBHandler.COLUMN_ID,
				MyDBHandler.COLUMN_PRODUCTNAME,
				MyDBHandler.COLUMN_QUANTITY
		};

		int[] mToFields = {
				R.id.idOfProd,
				R.id.nameOfProd,
				R.id.quantOfProd
		};

		mAdapter = new SimpleCursorAdapter(
				this,
				R.layout.item_row,
				null,
				mFromColumns,
				mToFields,
				0
		);
		ListView mListView = (ListView) findViewById(R.id.ListView);
		mListView.setAdapter (mAdapter);

		getLoaderManager().initLoader(LIST_LOADER, null, this);
	}



	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args)
	{
		switch (id)
		{
			case LIST_LOADER:
				return new CursorLoader(
						this,
						MyContentProvider.CONTENT_URI,
						MyDBHandler.PROJECTION,
						null,
						null,
						null
				);
			default:
				return null;
		}
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor cursor)
	{
		if (cursor != null)
		{
			mAdapter.changeCursor(cursor);
		}
	}

	@Override
	public void onLoaderReset(Loader<Cursor> loader)
	{
		mAdapter.changeCursor(null);
	}
}
