package org.adroidtown.practiceapp;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by bomeeryu_c on 2017. 5. 8..
 */


public class CustomButton extends LinearLayout {


    ImageView imageView;
    TextView textView;
    String textID;
    // OnCustomBtnListener listener; //4-2 4-1에 가져온 리스너가 메모리에 로딩됨

    public interface OnCustomBtnListener {
        void onClick(String text);

    }


    public CustomButton(Context context) {

        super(context);
        init(context);

//        textView.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                listener.onClick(textView.getText().toString());
//            }
//        });
    }


    public CustomButton(Context context, @Nullable AttributeSet attrs) { // 3-1.
        super(context, attrs); // 3-2
        init(context); // 3-3 초기화
        getAttrs(attrs); // 3-4 커스텀뷰에서 받은 설정값을 가져온다
//
//        textView.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                listener.onClick(textView.getText().toString());
//            }
//        });

    }

    private void getAttrs(AttributeSet attrs) { //3-5 타입어레이에 내가 만들어준 CustomButton 을 넣어준다
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.CustomButton);

        setTypedArray(typedArray); // 그 타입어레이를 파라미터로 넣어 셋타입어레이 메소드 호출
    }


    private void getAttrs(AttributeSet attrs, int defStyle) {

        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.CustomButton, defStyle, 0);
        setTypedArray(typedArray);
    }


    private void setTypedArray(TypedArray typedArray) { //3-6. 타입어레이에 있는 커스텀버튼 텍스트에 있는 값을 스트링으로 가져온다
        textID = typedArray.getString(R.styleable.CustomButton_text);
        textView.setText(textID); //3-7 가져온 값을 텍스트뷰의 텍스트로 지정

        int imageID = typedArray.getResourceId(R.styleable.CustomButton_imageSource, 0); //3-8 이미지소스 아이디를 겟리소스아이디로 가져온 다음 이미지아이디 변수에 넣어준다
        imageView.setImageResource(imageID); // 3-9 가져온 변수로 이미지 리소스를 설정한다

        String textColorID = typedArray.getString(R.styleable.CustomButton_textColor); //3-9 글씨색을 가져온다
        textView.setTextColor(Color.parseColor(textColorID)); // 3-10 스트링이 아닌 컬러값이 들어가야 하므로 Color.parseColor 클래스를 이용하여 데이터 타입을 칼라로 캐스팅 해준다 -> 텍스트 뷰의 색으로 설정

        typedArray.recycle();

    }

    public CustomButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
        getAttrs(attrs);

    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
//        init(getContext());
    }

    private void init(Context context) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.button, this, true);

//        LayoutInflater.from(mContext).inflate(R.layout.kakao, this, true);


        textView = (TextView) findViewById(R.id.textView);
        imageView = (ImageView) findViewById(R.id.imageView);


    }


    public void setText(String text) {
        textView.setText(text);
    }

    public void setImageView(int resId) {
        imageView.setImageResource(resId);
    }

    public String getText() {
        return textID;
    }

//    public void setOnCustomBtnListener(OnCustomBtnListener tvListener) { //4-1. 받아온 새로 생성된 tv리스너를 넣어서 전역변수 listener로 보내줌
//        this.listener = tvListener;
//    }
}
