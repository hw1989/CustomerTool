package org.huwei.calendar;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by wei.hu on 15-4-24.
 */
public class HeaderView extends LinearLayout {
    private LinearLayout.LayoutParams params = null;

    public HeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        InitSetting(context);
    }

    public HeaderView(Context context) {
        super(context);
        InitSetting(context);
    }

    public HeaderView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        InitSetting(context);
    }

    public void InitSetting(Context context) {
        params = new LinearLayout.LayoutParams(0, LayoutParams.WRAP_CONTENT,1.0f);
        //设置横向显示
        this.setOrientation(HORIZONTAL);
        this.setGravity(Gravity.CENTER);
        for (int i = 0; i < Common.ColCount; i++) {
            TextView tv_calendar = new TextView(context);
            tv_calendar.setTextSize(16);
            tv_calendar.setGravity(Gravity.CENTER);
            tv_calendar.setTextColor(Color.BLACK);
            tv_calendar.setText(Common.Header[i]);
            addView(tv_calendar, params);
        }

    }
}
