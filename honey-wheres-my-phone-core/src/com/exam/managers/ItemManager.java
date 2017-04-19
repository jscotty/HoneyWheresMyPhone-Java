package com.exam.managers;

import java.util.ArrayList;
import java.util.List;

import com.exam.items.Item;

public class ItemManager extends Singleton {
	
	private List<Item> items = new ArrayList<Item>();
	
	public void update(){
		System.out.println("works");
	}

	public List<Item> getItems() {
		return items;
	}
}
