package com.swmofang.fqlib.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.swmofang.fqlib.R;

/**
 * Author :feiqing
 * Created on 2017/3/23
 * Motity on 2017/3/23
 * Version : 1.0
 * Description :带有Icon的菜单View
 */

public class ImageMenuView extends LinearLayout {
    private Context mContext;

    private final int SHADOW_DEFAULT_VALUE = 5;
    private final int HEIGHT_DEFAULT_VALUE = 40;
    private final int WIDTH_DEFAULT_VALUE = 40;
    private final int TEXTSIZE_DEFAULT_VALUE = 40;
    private final int IMAGE_MARGIN_RIGHT_DEFAULT_VALUE = 10;

    public ImageMenuAttr getImageMenuAttr() {
        return imageMenuAttr;
    }

    private ImageMenuAttr imageMenuAttr;

    public ImageMenuView(Context context) {
        this(context,null);
    }

    public ImageMenuView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ImageMenuView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        init(mContext,attrs,defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        imageMenuAttr = new ImageMenuAttr();
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ImageMenuView);

        for (int i = 0; i < typedArray.getIndexCount(); i++) {
            int index = typedArray.getIndex(i);
            switch (index){
                case R.styleable.ImageMenuView_imageWidth:
                    imageMenuAttr.setImageWidth(typedArray.getDimension(index, 0));
                    break;
                case R.styleable.ImageMenuView_imageHeight:
                    imageMenuAttr.setImageHeight(typedArray.getDimension(index, 0));
                    break;
                case R.styleable.ImageMenuView_imageResource:
                    imageMenuAttr.setImageResource(typedArray.getResourceId(index, 0));
                    break;
                case R.styleable.ImageMenuView_textColor:
                    imageMenuAttr.setTextColor(typedArray.getResourceId(index, 0));
                    break;
                case R.styleable.ImageMenuView_textSize:
                    imageMenuAttr.setTextSize(typedArray.getDimension(index, 0));
                    break;
                case R.styleable.ImageMenuView_text:
                    imageMenuAttr.setTextContent(typedArray.getString(index));
                    break;
                case R.styleable.ImageMenuView_shadowDx:
                    imageMenuAttr.setShadowDx(typedArray.getDimension(index, 0));
                    break;
                case R.styleable.ImageMenuView_imageMargin:
                    imageMenuAttr.setImageMargin(typedArray.getDimension(index, 0));
                    break;
                case R.styleable.ImageMenuView_orientation:
                    imageMenuAttr.setOrientation(typedArray.getInteger(index, -1));
                    break;
            }
        }
        typedArray.recycle();
        initLayout(imageMenuAttr);
    }
    public void initLayout(){
        initLayout(imageMenuAttr);
    }

    private void initLayout(ImageMenuAttr imageMenuAttr) {
        setOrientation(imageMenuAttr.getOrientation());
        this.removeAllViews();
//        this.setGravity(Gravity.CENTER_VERTICAL);
        ImageView imageView = new ImageView(mContext);
//        MarginLayoutParams params = new MarginLayoutParams((int) imageMenuAttr.getImageWidth(), (int) imageMenuAttr.getImageHeight());
//        params.setMargins(0,0, (int) imageMenuAttr.getImageMargin(),0);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams((int) imageMenuAttr.getImageWidth(), (int) imageMenuAttr.getImageHeight());
        imageView.setLayoutParams(params);
        imageView.setBackgroundResource(imageMenuAttr.getImageResource());
        addView(imageView);

        TextView textView = new TextView(mContext);
        if(imageMenuAttr.getShadowDx()!=0)
//        textView.setTextAppearance(mContext,R.style.txt_content_smaller_white_shadow);//设置TextView阴影
//        TextView textView = new TextView(mContext,null,R.style.txt_content_smaller_white_shadow);
        textView.setIncludeFontPadding(false);
        textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX,imageMenuAttr.getTextSize());
        textView.setTextColor(mContext.getResources().getColor(imageMenuAttr.getTextColor()));
        textView.setText(imageMenuAttr.getTextContent());

        textView.setPadding(imageMenuAttr.getOrientation() == LinearLayout.HORIZONTAL ? (int)imageMenuAttr.getImageMargin() : 0,
                imageMenuAttr.getOrientation() == LinearLayout.VERTICAL ? (int)imageMenuAttr.getImageMargin() : 0,
                0,
                0);
        addView(textView);
//        LayoutInflater.from(mContext).inflate(R.layout.image_menu_view_layout,this,false);
    }

    public int getAttrGravity(AttributeSet attrs) {
        TypedArray typedArray1 = mContext.obtainStyledAttributes(attrs, new int[]{android.R.attr.gravity});
        int indexCount = typedArray1.getIndexCount();
        int gravity = 0;
        if(indexCount>0){
            gravity = typedArray1.getInt(0, 0);
        }
        typedArray1.recycle();
//        Log.e("gravityttttt","indexCount:"+indexCount+",gravityNum:"+anInt);
        return gravity;
    }

    public class ImageMenuAttr{
        private float imageWidth;
        private float imageHeight;
        private int imageResource;
        private int textColor;
        private float textSize;
        private String textContent;
        private float shadowDx;
        private float imageMargin;
        private int orientation;

        public float getImageWidth() {
            return imageWidth;
        }

        public void setImageWidth(float imageWidth) {
            this.imageWidth = imageWidth;
        }

        public float getImageHeight() {
            return imageHeight;
        }

        public void setImageHeight(float imageHeight) {
            this.imageHeight = imageHeight;
        }

        public int getImageResource() {
            return imageResource;
        }

        public void setImageResource(int imageResource) {
            this.imageResource = imageResource;
        }

        public int getTextColor() {
            return textColor;
        }

        public void setTextColor(int textColor) {
            this.textColor = textColor;
        }

        public float getTextSize() {
            return textSize;
        }

        public void setTextSize(float textSize) {
            this.textSize = textSize;
        }

        public String getTextContent() {
            return textContent;
        }

        public void setTextContent(String textContent) {
            this.textContent = textContent;
        }

        public float getShadowDx() {
            return shadowDx;
        }

        public void setShadowDx(float shadowDx) {
            this.shadowDx = shadowDx;
        }

        public float getImageMargin() {
            return imageMargin;
        }

        public void setImageMargin(float imageMargin) {
            this.imageMargin = imageMargin;
        }

        public int getOrientation() {
            return orientation;
        }

        public void setOrientation(int orientation) {
            this.orientation = orientation;
        }
    }
}
