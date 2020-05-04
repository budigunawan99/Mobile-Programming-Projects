package com.bnawan.submission;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.bnawan.submission.adapter.ListShioAdapter;
import com.bnawan.submission.model.Shio;
import com.bnawan.submission.model.ShioModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvShio;
    private ArrayList<Shio> list = new ArrayList<>();
    private String title = "12 Shio Tiongkok";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setActionBarTitle(title);
        ShioModel shioModel = new ShioModel(this);

        rvShio = findViewById(R.id.v_shio);
        rvShio.setHasFixedSize(true);

        list.addAll(shioModel.getListData());

        showRecyclerCardView();
    }

    private void setActionBarTitle(String title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }

    private void showRecyclerCardView() {
        rvShio.setLayoutManager(new LinearLayoutManager(this));
        ListShioAdapter listShioAdapter = new ListShioAdapter(list);
        rvShio.setAdapter(listShioAdapter);

        listShioAdapter.setOnItemClickCallback(new ListShioAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(Shio data) {
                showDetailShio(data);
                showPageDetail(data);
            }
        });
    }

    private void showDetailShio(Shio data) {
        Toast.makeText(this, "Kamu memilih " + data.getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        setMode(item.getItemId());
        return super.onOptionsItemSelected(item);
    }

    private void setMode(int itemId) {
        switch (itemId) {
            case R.id.action_about:
                Intent about = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(about);
                break;
        }

    }

    private void showPageDetail(Shio data) {
        Intent detail = new Intent(MainActivity.this, DetailActivity.class);
        detail.putExtra(DetailActivity.NAME_SHIO, data.getName());
        detail.putExtra(DetailActivity.IMAGE_SHIO, data.getImage());
        detail.putExtra(DetailActivity.DETAIL_SHIO, data.getDetail());
        detail.putExtra(DetailActivity.DESCRIPTION, data.getDeskripsi());
        startActivity(detail);
    }

}
