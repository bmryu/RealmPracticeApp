package org.adroidtown.practiceapp;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.List;

import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by bomeeryu_c on 2017. 5. 24..
 */

public class BottomButton extends LinearLayout implements View.OnClickListener {
    @BindViews({R.id.button1,R.id.button2,R.id.button3,R.id.button4,R.id.button5,R.id.button6})
    List<Button> buttons;
    public BottomButton(Context context) {
        super(context);
        init(context);
        Log.d("CustomView lifeCycle", "파라미터 1개 생성자");
    }

    public BottomButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
        Log.d("CustomView lifeCycle", "파라미터 2개 생성자" + attrs);
    }

    public BottomButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
        Log.d("CustomView lifeCycle", "파라미터 3개 생성자");
    }

    public void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.bottom_button, this, true);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.button1,R.id.button2,R.id.button3,R.id.button4,R.id.button5,R.id.button6})
    public void onClick(View v) {
        ButterKnife.apply(buttons,SETFALSE);
        v.setSelected(true);
    }

   static  final ButterKnife.Action<Button> SETFALSE = new ButterKnife.Action<Button>(){
       @Override
       public void apply(@NonNull Button view, int index) {
         view.setSelected(false);
       }
   };
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.d("CustomView lifecycle", "onMeasure : 크기를 계산해서 세팅해줌");
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        Log.d("CustomView lifecycle", "onLayout : setFrame 함수를 통해 뷰의 구역을 정해준다");
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        Log.d("CustomView lifecycle", "dispatchDraw : 캔버스를 이용해서 그리는 작업");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d("CustomView lifecycle", "onDraw : 그림을 그려넣어준");
    }


    @Override
    public void onViewAdded(View child) {
        super.onViewAdded(child);
        Log.d("CustomView lifecycle", "onViewAdded : 뷰가 추가됨");
    }

    @Override
    public void onViewRemoved(View child) {
        super.onViewRemoved(child);
        Log.d("CustomView lifecycle", "onViewRemoved : 뷰가 제거");
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.d("CustomView lifecycle", "onAttachedToWindow : 윈도우에 붙여짐");
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.d("CustomView lifecycle", "onDetachedFromWindow : 윈도우에서 제거됨 ");
    }

    @Override
    public void requestLayout() {
        super.requestLayout();
        Log.d("CustomView lifecycle", "requestLayout : 레이아웃 요청");
    }

    @Override
    public void invalidate() {
        super.invalidate();
        Log.d("CustomView lifecycle", "invalidate");
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        Log.d("CustomView lifecycle", "onFinishInflate : 인플레이트 종료");
    }
}
