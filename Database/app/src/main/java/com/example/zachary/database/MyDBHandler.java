package com.example.zachary.database;

import android.content.Context;
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
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db)
	{

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{

	}
}
