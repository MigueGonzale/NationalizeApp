package com.example.testmenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import com.example.testmenu.nav.RecyclerViewNavAdapter;

/**
 * This class shows the navigation Menu
 * History and Search new name
 * @layout main_activity.xml
 */
public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Context context;
    RecyclerView.Adapter recyclerViewAdapter;
    RecyclerView.LayoutManager recLayoutManager;

    String[] nav = {
            "Nueva Busqueda",
            "Historial"
    };

    /**
     * OnInit
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();

        // Select view to show
        recyclerView = (RecyclerView) findViewById(R.id.menuNavRecycler);
        recLayoutManager = new GridLayoutManager(context, 1);

        recyclerView.setLayoutManager(recLayoutManager);
        recyclerViewAdapter = new RecyclerViewNavAdapter(context, nav);

        recyclerView.setAdapter(recyclerViewAdapter);

    }
}