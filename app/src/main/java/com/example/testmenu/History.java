package com.example.testmenu;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.testmenu.database.AppDatabase;
import com.example.testmenu.database.NationalizeTable;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.List;

/**
 * Controller to view history
 * @referece activity_history.xmñ
 */
public class History extends AppCompatActivity {

    private MaterialToolbar mToolbar;
    private NationalizeListAdapter nationalizeListAdapter;

    /**
     * OnInit
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        mToolbar = (MaterialToolbar) findViewById(R.id.topAppBar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(History.this,
                        MainActivity.class);
                startActivity(intent);
                finish();
                System.out.println("Back biches");
            }
        });

        initRecyclerView();

        loadNationalizeList();
    }

    /**
     * Generate View List with RwcyclerVView
     * @method void
     */
    private void initRecyclerView() {
        nationalizeListAdapter = new NationalizeListAdapter(this);
        RecyclerView rw = findViewById(R.id.listHistory);
        rw.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration dividerItemD = new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL);

        rw.addItemDecoration(dividerItemD);

        rw.setAdapter(nationalizeListAdapter);
    }

    /**
     * Show list saved in DB
     * @method void
     */
    private void loadNationalizeList() {
        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());
        List<NationalizeTable> nationalizeList = db.nationalizeDao().getAllHistory();
        nationalizeListAdapter.setNationalizeList(nationalizeList);

    }
}