package com.huwei.customertool;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import org.huwei.calendar.CalendarView;

import java.util.ArrayList;

/**
 * Created by wei.hu on 15-4-28.
 */
public class CalendarPagerAdapter extends PagerAdapter {
    private ArrayList<CalendarView> list = null;

    public CalendarPagerAdapter(ArrayList<CalendarView> list) {
        if (list == null) {
            this.list = new ArrayList<CalendarView>();
        } else {
            this.list = list;
        }
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view == o;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        if (((ViewPager) container).getChildCount() == list.size()) {
            ((ViewPager) container).removeView(list.get(position % getCount()));
        }
        ((ViewPager) container).addView(list.get(position % getCount()), 0);
        return list.get(position % getCount());
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView(list.get(position % getCount()));
    }
}
