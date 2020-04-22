
package com.example.grocerylist.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.grocerylist.CustomAdapter;
import com.example.grocerylist.R;
import com.example.grocerylist.model.Item;
import com.example.grocerylist.viewmodel.ItemListViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    EditText name, count;
    Button add;
    RecyclerView recyclerView;
    ItemListViewModel itemListViewModel;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.et_name);
        count = findViewById(R.id.et_count);
        add = findViewById(R.id.btn_add);
        recyclerView = findViewById(R.id.recycler_view);
        itemListViewModel = new ViewModelProvider(this).get(ItemListViewModel.class);
        customAdapter = new CustomAdapter();

        // Adding item to list
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText().toString().equals("") || count.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
                }
                else {
                    String value = count.getText().toString();
                    int countValue = Integer.parseInt(value);
                    itemListViewModel.addItem(name.getText().toString(), countValue);
                    name.setText("");
                    count.setText("");
                }
            }
        });

        itemListViewModel.getList().observe(this, new Observer<List<Item>>() {
            @Override
            public void onChanged(List<Item> items) {
                customAdapter.setDataSet(items);
            }
        });

        // Method to delete Item
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                itemListViewModel.removeItem(customAdapter.getPosition(viewHolder.getAdapterPosition()));
            }
        }).attachToRecyclerView(recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(customAdapter);
    }

}
