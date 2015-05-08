package com.huwei.customertool;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import org.huwei.calendar.CalendarView;
import org.huwei.calendar.Common;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends Activity {
    private ViewPager vp_calendar = null;
    private CalendarPagerAdapter adapter = null;
    private ArrayList<CalendarView> list = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vp_calendar = (ViewPager) findViewById(R.id.vp_calendar);
        list = new ArrayList<CalendarView>();
        for (int i = 0; i < 21; i++) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MONTH,i-10);
            CalendarView view = new CalendarView(this);
            view.setStyle(Common.Style.month);
            view.setCalendar1(calendar);
            list.add(view);
        }
        adapter = new CalendarPagerAdapter(list);
        vp_calendar.setAdapter(adapter);
        vp_calendar.setCurrentItem(10);
    }


}
