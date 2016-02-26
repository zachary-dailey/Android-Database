package com.example.zachary.database;

/**
 * Created by Zachary on 2/23/2016.
 */
public class Product
{
	private int _id;
	private String _prodName;
	private int _quantity;

	public Product(String _prodName, int _quantity)
	{
		this._prodName = _prodName;
		this._quantity = _quantity;
	}

	public Product(int _id, String _prodName, int _quantity)
	{
		this._id = _id;
		this._prodName = _prodName;
		this._quantity = _quantity;
	}

	public int get_id()
	{
		return _id;
	}

	public void set_id(int _id)
	{
		this._id = _id;
	}

	public String get_prodName()
	{
		return _prodName;
	}

	public void set_prodName(String _prodName)
	{
		this._prodName = _prodName;
	}

	public int get_quantity()
	{
		return _quantity;
	}

	public void set_quantity(int _quantity)
	{
		this._quantity = _quantity;
	}




}
