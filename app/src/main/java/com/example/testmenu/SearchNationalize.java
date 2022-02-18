package com.example.testmenu;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.testmenu.Model.CountriesModel;
import com.example.testmenu.Model.NationalizeModel;
import com.example.testmenu.database.AppDatabase;
import com.example.testmenu.database.NationalizeTable;
import com.example.testmenu.interfaces.NationalizeApi;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import okhttp3.HttpUrl;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Search nationalize
 * @layout activity_search_nationalize.xml
 */
public class SearchNationalize extends AppCompatActivity {
    private MaterialToolbar mToolbar;
    private EditText txtSearch;
    private String search;
    private HashMap<String, String> nameCountry = new HashMap<>();
    private ArrayList<CountriesModel> countries;
    private Context context;
    private TextView loadingText;
    private Timer timer;
    ListView listView;
    Button searchBtn;

    /**
     * OnInit
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getApplicationContext();
        setContentView(R.layout.activity_search_nationalize);

        txtSearch = (EditText) findViewById(R.id.txtSearch);
        searchBtn = (Button) findViewById(R.id.searchBtn);

        loadingText = (TextView) findViewById(R.id.loading);
        searchBtn.setEnabled(false);

         listView = (ListView) findViewById(R.id.listNationalize);

        txtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                System.out.println("Before change Text");
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 0) {
                    searchBtn.setEnabled(true);
                }
                else {
                    searchBtn.setEnabled(false);
                }
                System.out.println("During change Text");
            }

            @Override
            public void afterTextChanged(Editable editable) {
                System.out.println("After change Text");
            }
        });


        mToolbar = (MaterialToolbar) findViewById(R.id.topAppBar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchNationalize.this,
                        MainActivity.class);
                startActivity(intent);
                finish();
                System.out.println("Back biches");
            }
        });

        searchBtn(context, searchBtn);

    }

    /**
     * Animation text loading
     * @method void
     * @param textView
     */
    private void loadingAnimation(TextView textView) {
        timer = new Timer();
        List<String> cadena = new ArrayList<String>();
        cadena.add("Cargando");
        timer.scheduleAtFixedRate(new TimerTask() {
            int i = 0;

            @Override
            public void run() {
                if (i < 3) {
                    i++;
                    cadena.add(".");
                    String newString = String.join(", ", cadena);
                    textView.setText(newString.replace(",", ""));
                    System.out.println("I'm here "+ newString);

                } else {
                    cadena.clear();
                    cadena.add("Cargando");
                    i = 0;
                }
            }
        }, 0, 500);
    }

    /**
     * Search nationalize Button
     * @method void
     * @param context
     * @param searchBtn
     */
    private void searchBtn(Context context, Button searchBtn) {
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // spinner.setOnItemSelectedListener(SearchNationalize.this);
                searchBtn.setEnabled(false);
                getNationalizeResult();
                loadingText.setVisibility(View.VISIBLE);
                listView.setAdapter(null);
                loadingAnimation(loadingText);
            }
        });
    }

    /**
     * Get result after response api
     * @method void
     */
    public void getNationalizeResult() {
        nameCountry.clear();
        search = txtSearch.getText().toString();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.nationalize.io")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        NationalizeApi nationalizeApi = retrofit.create(NationalizeApi.class);

        Call<NationalizeModel> call = nationalizeApi.getNationalizeResult(search);
        call.enqueue(new Callback<NationalizeModel>() {
            @Override
            public void onResponse(Call<NationalizeModel> call, Response<NationalizeModel> response) {
                NationalizeModel nationalizeModel = response.body();
                countries = new ArrayList(nationalizeModel.getCountries());
                HttpUrl uri = response.raw().request().url();
                if (!response.isSuccessful()) {
                    System.out.println("Error papi " + response.isSuccessful());
                    return;
                }

                for (CountriesModel country : countries) {
                    String countryText = "Country: " + country.getCountry_id();
                    String probabilityText = "Probability: " + country.getProbability().toString();
                    nameCountry.put(countryText, probabilityText);
                }
                saveHistory(uri);
                System.out.println("Url papi" + uri);
                generateList();
            }

            @Override
            public void onFailure(Call<NationalizeModel> call, Throwable t) {
                System.out.println("error" + t.getMessage());
            }
        });
    }

    /**
     * Genarate List of items nationalize
     * @method void
     */
    private void generateList() {
        List<HashMap<String, String>> listItems = new ArrayList<>();
        SimpleAdapter adapter = new SimpleAdapter(this, listItems, R.layout.list_items,
                new String[]{"Fist Line", "Second Line"},
                new int[]{R.id.titleNationalize, R.id.idCountry});


        Iterator it = nameCountry.entrySet().iterator();
        while (it.hasNext()){
            HashMap<String, String> resultMap = new HashMap<>();
            Map.Entry pair = (Map.Entry) it.next();
            resultMap.put("Fist Line", pair.getKey().toString());
            resultMap.put("Second Line", pair.getValue().toString());
            listItems.add(resultMap);
        }
        timer.cancel();
        loadingText.setVisibility(View.INVISIBLE);
        listView.setAdapter(adapter);
        searchBtn.setEnabled(true);
    }

    /**
     * Save history in Database
     * @method void
     * @param sInput
     */
    private void saveHistory(HttpUrl sInput) {
        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());
        NationalizeTable nationalizeTable = new NationalizeTable();
        nationalizeTable.uri = sInput.toString();
        db.nationalizeDao().insertUri(nationalizeTable);
    }
}