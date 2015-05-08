package com.gta.tools.switchbutton;

import android.R.integer;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;

public class SwitchButton extends View {
	// 背景的画笔
	private Paint paint = null;
	// 中间园的画笔
	private Paint paint_circle = null;
	// 左右的直径
	private int diameter = 150;
	// 内圆的图片
	private int radius = 70;
	// 内圆的圆心
	private Point point = new Point();
	// 中间矩形的长度
	private int len = 170;
    //
	private boolean flag=false;
	public SwitchButton(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initPaint();
	}

	public SwitchButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		initPaint();
	}

	public SwitchButton(Context context) {
		super(context);
		initPaint();
	}

	private void initPaint() {
		paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		// 去掉锯齿
		paint.setAntiAlias(true);
		// 设置填充
		paint.setStyle(Style.FILL);
		// 设置颜色
		paint.setColor(Color.parseColor("#24B31A"));
		// ---------------------------------------
		paint_circle = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint_circle.setAntiAlias(true);
		paint_circle.setStyle(Style.FILL);
		paint_circle.setColor(Color.parseColor("#ffffff"));
		point=new Point(diameter / 2, diameter / 2);
	}

	/**
	 * 构造控件的显示大小
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int widmeasure = MeasureSpec.makeMeasureSpec(diameter + len,
				MeasureSpec.EXACTLY);
		int heimeasure = MeasureSpec.makeMeasureSpec(diameter,
				MeasureSpec.EXACTLY);
		super.onMeasure(widmeasure, heimeasure);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if(!flag){
			flag=true;
//			canvas.save();
			// 开始画背景
			RectF rectF_left = new RectF(0, 0, diameter, diameter);
			// 画左边弧线
			canvas.drawArc(rectF_left, 90, 180, true, paint);
			// 画矩形
			canvas.drawRect(diameter / 2, 0, len + diameter / 2, diameter, paint);
			// 画右边的弧线
			RectF rectF_right = new RectF(len, 0, len + diameter, diameter);
			canvas.drawArc(rectF_right, 270, 180, true, paint);
			// ---------------------------------
			canvas.drawCircle(point.x,point.y, radius, paint_circle);
			setAnimation();
		}else{
			canvas.drawCircle(point.x,point.y, radius, paint_circle);
		}
	}

//	private void setAnimation() {
//		Point point1 = new Point(diameter / 2, diameter / 2);
//		Point point2 = new Point(diameter / 2 + len, diameter / 2);
//		ValueAnimator vAnimator = ValueAnimator.ofObject(new PointEvaluator(
//				diameter / 2), point1,point2);
//		vAnimator.setDuration(10000);
//		vAnimator.addUpdateListener(new AnimatorUpdateListener() {
//
//			@Override
//			public void onAnimationUpdate(ValueAnimator animation) {
//				point = (Point) animation.getAnimatedValue();
//				Log.e("info","X="+point.x);
//				invalidate();
//			}
//		});
//		
//		// 设置先慢，中快，后慢
////		vAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
//        vAnimator.start();
//	}
	private void setAnimation() {
		ValueAnimator anim = ValueAnimator.ofFloat(0f, 1f);  
		anim.setDuration(300);  
		anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {  
		    @Override  
		    public void onAnimationUpdate(ValueAnimator animation) {  
		        float currentValue = (Float) (animation.getAnimatedValue());  
		        Log.d("TAG", "cuurent value is " + currentValue);  
		    }  
		});  
		anim.start();
	}
}
