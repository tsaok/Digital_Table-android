package com.example.digitaltablemodels;

import java.util.ArrayList;

public class DTMessage {
	private String message;
	private int sender;
	private ArrayList<Integer> target;
	private int messageId;
	private int sent;
	
	public DTMessage(String d, int s, ArrayList<Integer> target)
	{
		message = d;
		sender = s;
		this.target = target;
	}
	
	
	public String getMessage()
	{
		return message;
	}
	
	public void setMessage(String set)
	{
		message = set;
	}
	
	public int getSender()
	{
		return sender;
	}
	
	public void setSender(int id)
	{
		sender = id;
	}
	
	@Override
	public String toString() {
		return message;
	}
	
	
	
}
