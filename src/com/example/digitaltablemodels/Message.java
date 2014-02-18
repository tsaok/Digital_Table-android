package com.example.digitaltablemodels;

import java.util.ArrayList;

public class Message {
	private String data;
	private int sender;
	private ArrayList<Integer> target;
	private int messageId;
	private int sent;
	
	public Message(String d, int s, ArrayList<Integer> target)
	{
		data = d;
		sender = s;
		this.target = target;
	}
	
	
	public String getData()
	{
		return data;
	}
	
	public void setData(String set)
	{
		data = set;
	}
	
	public int getSender()
	{
		return sender;
	}
	
	public void setSender(int id)
	{
		sender = id;
	}
	
	
	
}
