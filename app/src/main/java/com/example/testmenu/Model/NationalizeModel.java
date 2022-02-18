package com.example.testmenu.Model;

import java.util.List;

public class NationalizeModel extends CountriesModel{
    private String name;
    private List<CountriesModel> country;

    public String getName() {
        return name;
    }

    public List<CountriesModel> getCountries() {
        return country;
    }
}
