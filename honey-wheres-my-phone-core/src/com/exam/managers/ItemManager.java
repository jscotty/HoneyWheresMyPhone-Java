package com.exam.managers;

import java.util.ArrayList;
import java.util.List;

import com.exam.items.Item;

public class ItemManager {

    private List<Item> _items = new ArrayList<Item>();

    public void update() {
	System.out.println("works");
    }

    public List<Item> getItems() {
	return _items;
    }
}
