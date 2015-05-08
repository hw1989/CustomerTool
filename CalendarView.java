package org.huwei.calendar;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.Calendar;

/**
 * Created by wei.hu on 15-4-24.
 */
public class CalendarView extends ViewGroup {
    private Context context = null;
    //设置一个方格的大小
    private float size;
    private LinearLayout.LayoutParams params = null;
    private LinearLayout.LayoutParams params1 = null;

    public void setCalendar1(Calendar calendar1) {
        this.calendar1 = calendar1;
//        invalidate();
    }

    //获取选中的时间
//    Calendar calendar = Calendar.getInstance();
    private Calendar calendar1 = Calendar.getInstance();
    private Listener listener = null;

    public void setStyle(Common.Style style) {
        this.style = style;
        invalidate();
    }

    public Common.Style getStyle() {
        return style;
    }

    //设置显示的样式
    private Common.Style style = Common.Style.month;

    public CalendarView(Context context) {
        super(context);
        this.context = context;
        initSetting();
    }

    public CalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initSetting();
    }

    public CalendarView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        initSetting();
    }

    private void initSetting() {
        params = new LinearLayout.LayoutParams(0, LayoutParams.WRAP_CONTENT, 1);
        params1 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        calendar1=Calendar.getInstance();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int cellwid = getMeasuredWidth() / Common.ColCount;
        int cellhei = getMeasuredHeight() / Common.RowCount;
        size = cellhei > cellwid ? cellwid : cellhei;
        int realSize = getMeasuredHeight() > getMeasuredWidth() ? getMeasuredWidth() : getMeasuredHeight();
        if (getMeasuredHeight() > getMeasuredWidth()) {
            //设置空间显示的区域
            if (this.style == Common.Style.month) {
                setMeasuredDimension(getMeasuredWidth(), (int) (size * Common.RowCount));
            } else if (this.style == Common.Style.Week) {
                setMeasuredDimension(getMeasuredWidth(), (int) (size));
            }
        } else {
            //设置空间显示的区域
            setMeasuredDimension((int) (size * Common.ColCount), getMeasuredHeight());
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        calendar1.set(Calendar.DAY_OF_MONTH,1);
        int a = calendar1.get(Calendar.DAY_OF_WEEK);
        int rows = style == Common.Style.month ? Common.RowCount : 1;
        //添加DayView
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < Common.ColCount; j++) {
                int addDay = i * Common.ColCount + j - a + 1;
                DayView dayView = new DayView(context, size / 2, calendar1, addDay);
                addView(dayView, params);
                dayView.layout((int) (j * size), (int) (i * size), (int) ((j + 1) * size), (int) ((i + 1) * size));
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }


    public void setListener(Listener listener) {
        if (listener != null) {
            this.listener = listener;
        }
    }
}
