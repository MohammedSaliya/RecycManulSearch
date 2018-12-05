package com.spider.saliya.recycmanulsearch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.spider.saliya.recycmanulsearch.RecycAdapter.RecycAdapter;

import java.util.ArrayList;
import java.util.Collections;

import static android.widget.Toast.LENGTH_LONG;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    EditText editsearch;
    RecycAdapter adapter;
    ArrayList<String> arrayList;
    Toolbar toolbar;

    Button asc;
    Button des;
    private boolean ascending = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        editsearch = findViewById(R.id.editsearch);
        toolbar = findViewById(R.id.toolbar);
        this.initializeViews();
        setSupportActionBar(toolbar);

//        asc = findViewById(R.id.asc);

        //  toolbar.inflateMenu(R.menu.menu);

/*
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());*/

        arrayList = new ArrayList();

        arrayList.clear();
        arrayList.add("Apple");
        arrayList.add("Banana");
        arrayList.add("Pineapple");
        arrayList.add("Orange");
        arrayList.add("Lychee");
        arrayList.add("Gavava");
        arrayList.add("Peech");
        arrayList.add("Melon");
        arrayList.add("Watermelon");
        arrayList.add("Papaya");


        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new RecycAdapter(this, arrayList);
        recyclerView.setAdapter(adapter);

        editsearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                //after the change calling the method and passing the search input
                filter(editable.toString());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menus) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menus);
        //setTitle("SELECT A PRODUCT");
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.atoz:
                Toast.makeText(getApplicationContext(), "You Selected Ascending", Toast.LENGTH_LONG).show();
                Collections.sort(arrayList);
                initializeViews();
                return true;
            case R.id.ztoa:
                Toast.makeText(getApplicationContext(), "You Selected Descending", Toast.LENGTH_LONG).show();
                Collections.reverse(arrayList);
                initializeViews();

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


   private void initializeViews() {

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        asc = (Button) findViewById(R.id.asc);

        asc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Collections.sort(arrayList);
                adapter = new RecycAdapter(MainActivity.this, arrayList);
                recyclerView.setAdapter(adapter);

            }
        });

        des = findViewById(R.id.des);

        des.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.reverse(arrayList);
                adapter = new RecycAdapter(MainActivity.this, arrayList);
                recyclerView.setAdapter(adapter);


            }
        });
    }


    private void filter(String text) {
        ArrayList<String> filterdNames = new ArrayList<>();

        //looping through existing elements
        for (String s : arrayList) {
            //if the existing elements contains the search input
            if (s.toLowerCase().contains(text.toLowerCase())) {
                //adding the element to filtered list
                filterdNames.add(s);
            }
        }

        //calling a method of the adapter class and passing the filtered list
        adapter.filterList(filterdNames);
    }


}
