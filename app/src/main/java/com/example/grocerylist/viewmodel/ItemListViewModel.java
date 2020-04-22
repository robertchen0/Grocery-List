package com.example.grocerylist.viewmodel;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.grocerylist.model.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemListViewModel extends ViewModel {

    private MutableLiveData<List<Item>> groceryList;
    private List<Item> items = new ArrayList<>();

    public LiveData<List<Item>> getList(){
        if (groceryList == null){
            groceryList = new MutableLiveData<>();
            loadData();
        }
        return groceryList;
    }

    private void loadData(){
        groceryList.setValue(items);
    }

    public void addItem(String name, Integer count) {
        Item item = new Item();
        item.setName(name);
        item.setCount(count);
        items.add(item);
        loadData();
    }

    public void removeItem(Item item){
        items.remove(item);
        loadData();
    }

}
