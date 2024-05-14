package com.example.finalproject.utils;

import com.example.finalproject.R;
import com.example.finalproject.model.SportCategoryModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SharedData {
    public static ArrayList<SportCategoryModel> selectedSports = new ArrayList<>();
    public static double min_radius = 0;
    public static double max_radius = 0;
    public static Date from_date;
    public static Date to_date;



    public static  Map<String, Integer> categories_icons = new HashMap() {{
        put("Swimming", R.drawable.baseline_pool_24);
        put("Hiking", R.drawable.baseline_hiking_24);
        put("Cycling", R.drawable.baseline_directions_bike_24);
        put("Running", R.drawable.baseline_directions_run_24);
        put("Team Sports", R.drawable.baseline_people_24);
        put("Cross Fit", R.drawable.baseline_downhill_skiing_24);
        put("Any", R.drawable.baseline_accessibility_24);
    }};

}
