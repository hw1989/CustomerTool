package org.huwei.calendar;

import java.util.Date;

/**
 * Created by wei.hu on 15-4-24.
 */
public interface Listener {
    /**
     * 选中的单个View
     *
     * @param view选中的view
     * @param date当前view对应的日期
     * @param selected是否选中
     */
    void selectDayView(DayView view, Date date, boolean selected);

    /**
     * 切换到的月份
     *
     * @param date切换到的当前日期
     * @param flag正1为下一月,负1为上一月
     * @param style当前显示的样式
     */
    void changePage(Date date, int flag, Common.Style style);

    void changeStyle(Common.Style style);
}
