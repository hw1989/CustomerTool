package org.huwei.calendar;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by wei.hu on 15-4-24.
 */
public class DayView extends View implements View.OnClickListener {
    private Context context = null;
    private Paint paintbg;
    private Paint paintxt;
    private Common.DayState state = Common.DayState.Default;
    //在状态改变前的状态
    private Common.DayState prestate = Common.DayState.Default;
    float radius;
    //对象对应的时间
    private Calendar calendar = null;
    //操作的当前的月份
    private Calendar current = null;
    //今天的日期
    private Calendar todayCalendar = Calendar.getInstance();
    private int fonthei;

    public DayView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        this.context = context;
        initSetting();
    }

    public DayView(Context context, float radius, Calendar calendar, int addDay) {
        super(context);
        this.context = context;
        this.radius = radius;
        this.current = calendar;
        //需要实例化，不能直接引用
        this.calendar = Calendar.getInstance();
        this.calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
        this.calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH));
        this.calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH));
        this.calendar.add(Calendar.DAY_OF_YEAR, addDay);
        //是否为当前月
        if (this.current.get(Calendar.MONTH) == this.calendar.get(Calendar.MONTH)) {
            state = Common.DayState.Default;
        } else {
            state = Common.DayState.Other;
        }
        if (this.todayCalendar.get(Calendar.YEAR) == this.calendar.get(Calendar.YEAR) &&
                this.todayCalendar.get(Calendar.MONTH) == this.calendar.get(Calendar.MONTH) &&
                this.todayCalendar.get(Calendar.DAY_OF_MONTH) == this.calendar.get(Calendar.DAY_OF_MONTH)) {
            state = Common.DayState.Today;
        }
        init();
        initSetting();
    }

    public DayView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        init();
        initSetting();
    }

    private void init() {
        paintbg = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintxt = new Paint(Paint.ANTI_ALIAS_FLAG);
        //设置字体加粗
        Typeface face = Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD);
        paintxt.setTypeface(face);
        paintxt.setColor(Color.rgb(153, 153, 153));
        paintxt.setTextSize(radius * 2 / 3);
        //获取文字的高度
        fonthei = getFontHeight();
        this.setOnClickListener(this);
    }

    private void initSetting() {
        //去锯齿
        paintbg.setAntiAlias(true);
        switch (state) {
            case Default:
                paintxt.setColor(Color.rgb(5, 5, 5));
                paintbg.setColor(Color.rgb(255, 255, 255));
                break;
            case Selected:
                paintxt.setColor(Color.rgb(255, 255, 255));
                paintbg.setColor(Color.rgb(255, 59, 48));
                break;
            case Other:
                paintxt.setColor(Color.rgb(173, 173, 173));
                paintbg.setColor(Color.rgb(255, 255, 255));
                break;
            case Today:
                paintxt.setColor(Color.rgb(255, 255, 255));
                paintbg.setColor(Color.rgb(79, 167, 24));
                break;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        String content = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        canvas.drawCircle(radius, radius, radius, paintbg);
        canvas.drawText(content,
                (float) (radius - paintxt.measureText(content) / 2),
                (float) (radius + fonthei / 4), paintxt);
    }

    @Override
    public void onClick(View view) {
        if (state != Common.DayState.Selected) {
            prestate = state;
            state = Common.DayState.Selected;
        } else {
            state = prestate;
        }
        initSetting();
        invalidate();
    }

    /**
     * 获取文字的高度
     */
    public int getFontHeight() {
        Paint.FontMetrics metrics = paintxt.getFontMetrics();
        return (int) Math.ceil(metrics.descent - metrics.ascent) + 2;
    }
}
