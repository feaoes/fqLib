package com.swmofang.fqlib.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.swmofang.fqlib.R;

/**
 * Author :feiqing
 * Created on 2017/4/10
 * Motify on 2017/4/10
 * Version : 1.0
 * Description : 带有箭头和下划线的父控件
 */

public class ArrowRelativeLayout extends RelativeLayout {
    private boolean showArrow = true;
    private boolean showDivider = true;
    private float arrowImageWidth;
    private float arrowImageHeight;
    private int arrowImageSrcId;
    private int dividerColor;
    private float dividerWidth;
    private float dividerMarginStart;
    private float dividerMarginEnd;

    public ArrowRelativeLayout(Context context) {
        this(context,null);
    }

    public ArrowRelativeLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ArrowRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs,defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ArrowRelativeLayout);

        for (int i = 0; i < typedArray.getIndexCount(); i++) {
            int index = typedArray.getIndex(i);
            switch (index) {
                case R.styleable.ArrowRelativeLayout_showArrow:
                    showArrow = typedArray.getBoolean(index,true);
                    break;
                case R.styleable.ArrowRelativeLayout_showDivider:
                    showDivider = typedArray.getBoolean(index,true);
                    break;
                case R.styleable.ArrowRelativeLayout_arrowImageWidth:
                    arrowImageWidth = typedArray.getDimension(index,0);
                    break;
                case R.styleable.ArrowRelativeLayout_arrowImageHeight:
                    arrowImageHeight = typedArray.getDimension(index,0);
                    break;
                case R.styleable.ArrowRelativeLayout_arrowImageSrc:
                    arrowImageSrcId = typedArray.getResourceId(index, 0);
                    break;
                case R.styleable.ArrowRelativeLayout_dividerColor:
                    dividerColor = typedArray.getColor(index, 0);
                    break;
                case R.styleable.ArrowRelativeLayout_dividerWidth:
                    dividerWidth = typedArray.getDimension(index, 0);
                    break;
                case R.styleable.ArrowRelativeLayout_dividerMarginStart:
                    dividerMarginStart = typedArray.getDimension(index, 0);
                    break;
                case R.styleable.ArrowRelativeLayout_dividerMarginEnd:
                    dividerMarginEnd = typedArray.getDimension(index, 0);
                    break;

            }
        }
        typedArray.recycle();

        ImageView img_arrow = new ImageView(context);
        LayoutParams params = new LayoutParams((int)arrowImageWidth,(int)arrowImageHeight);
        params.addRule(ALIGN_PARENT_RIGHT);
        params.addRule(CENTER_VERTICAL);
        img_arrow.setLayoutParams(params);
        img_arrow.setBackgroundResource(arrowImageSrcId);


        LayoutParams divider_layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) dividerWidth);
        divider_layoutParams.addRule(ALIGN_PARENT_BOTTOM);
        View view = new View(context);
        view.setLayoutParams(divider_layoutParams);
        view.setBackgroundColor(dividerColor);
        view.setPadding((int)dividerMarginStart,0,(int)dividerMarginEnd,0);

        addView(view,0);
        addView(img_arrow,0);
        if(!showArrow){
            img_arrow.setVisibility(View.INVISIBLE);
        }
        if(!showDivider){
            view.setVisibility(View.INVISIBLE);
        }
    }
}
