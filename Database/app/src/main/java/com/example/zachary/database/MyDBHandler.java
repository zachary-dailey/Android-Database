package com.example.zachary.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Zachary on 2/23/2016.
 */
public class MyDBHandler extends SQLiteOpenHelper
{
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "products.db";
	private static final String TABLE_PRODUCTS = "products";

	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_PRODUCT_NAME = "_prodName";
	public static final String COLUMN_QUANTITY = "_quantity";

	public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
	{
		super(context, DATABASE_NAME, factory, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db)
	{
		String CREATE_PRODUCTS_TABLE = "CREATE TABLE " + TABLE_PRODUCTS
				+ " ("
					+ COLUMN_ID + " INTEGER primary key, "
					+ COLUMN_PRODUCT_NAME + " TEXT, "
					+ COLUMN_QUANTITY + " INTEGER"
				+ ")";

		db.execSQL(CREATE_PRODUCTS_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		/*
			If this were a production DB, you would migrate your data to the new schema.
			There is no way you would ever want to simply drop the table.
			When you do, all of the data in the table is also deleted.
		 */

		db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
		onCreate(db);
	}

	public void addProduct(Product product)
	{
		ContentValues values = new ContentValues();
		values.put(COLUMN_PRODUCT_NAME, product.get_prodName());
		values.put(COLUMN_QUANTITY, product.get_quantity());

		SQLiteDatabase db = this.getWritableDatabase();

		db.insert(TABLE_PRODUCTS, null, values);
		db.close();
	}

	public Product findProduct(String productname)
	{
		String query = ("SELECT *"
						+ " FROM " + TABLE_PRODUCTS
						+ " WHERE " + COLUMN_PRODUCT_NAME + " = \"" + productname + "\"");

		SQLiteDatabase db = getWritableDatabase();
		Cursor cursor = db.rawQuery(query, null);

		Product product = new Product();

		return(product);
	}
}
