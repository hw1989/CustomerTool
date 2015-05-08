package org.huwei.calendar;

/**
 * Created by wei.hu on 15-4-24.
 */
public class Common {
    //日历头部
    public  static  final  String[] Header={"日","一","二","三","四","五","六"};
    //日历有6行
    public  static  final  int RowCount=6;
    //日历有7列
    public  static  final int ColCount=7;
    //日历的样式
    public  enum  Style{month,Week};
    //日的选中状态:选中，默认，其他月份
    public  enum  DayState{Selected,Default,Other,Today};
}
