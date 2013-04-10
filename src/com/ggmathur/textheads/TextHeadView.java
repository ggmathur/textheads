package com.ggmathur.textheads;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

/**
 * TODO: document your custom view class.
 */
public class TextHeadView extends View {
    private String mMessageBody;
    private String mContactName;
    
    private int mExampleColor = Color.RED; // TODO: use a default from
                                           // R.color...
    private float mExampleDimension = 0; // TODO: use a default from R.dimen...
    private Drawable mExampleDrawable;

    private TextPaint mTextPaint;
    private float mTextWidth;
    private float mTextHeight;

    public TextHeadView(final Context context) {
        super(context);
        init(null, 0);
    }

    public TextHeadView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public TextHeadView(final Context context, final AttributeSet attrs, final int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }
    
    public TextHeadView(final Context context, final String contactName, final String messageBody) {
        super(context);
        
        mMessageBody = messageBody;
        mContactName = contactName;
        
        // Use getDimensionPixelSize or getDimensionPixelOffset when dealing
        // with
        // values that should fall on pixel boundaries.
        mExampleDimension = 100;

        // Set up a default TextPaint object
        mTextPaint = new TextPaint();
        mTextPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextAlign(Paint.Align.LEFT);
        
        // Update TextPaint and text measurements from attributes
        invalidateTextPaintAndMeasurements();
    }

    private void init(final AttributeSet attrs, final int defStyle) {
        // Load attributes
        final TypedArray attributes = getContext().obtainStyledAttributes(attrs,
                R.styleable.TextHeadView, defStyle, 0);

        mExampleColor = attributes.getColor(R.styleable.TextHeadView_exampleColor,
                mExampleColor);
        
        mMessageBody = attributes.getString(R.styleable.TextHeadView_messageBody);
        mContactName = attributes.getString(R.styleable.TextHeadView_contactName);
        
        // Use getDimensionPixelSize or getDimensionPixelOffset when dealing
        // with
        // values that should fall on pixel boundaries.
        mExampleDimension = attributes.getDimension(
                R.styleable.TextHeadView_exampleDimension, mExampleDimension);

        attributes.recycle();

        // Set up a default TextPaint object
        mTextPaint = new TextPaint();
        mTextPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextAlign(Paint.Align.LEFT);
        
        // Update TextPaint and text measurements from attributes
        invalidateTextPaintAndMeasurements();
    }

    private void invalidateTextPaintAndMeasurements() {
        mTextPaint.setTextSize(mExampleDimension);
        mTextPaint.setColor(mExampleColor);
        mTextWidth = 20 * (mContactName.length() + mMessageBody.length() + 1);
        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
        mTextHeight = fontMetrics.bottom;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // TODO: consider storing these as member variables to reduce
        // allocations per draw cycle.
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();

        int contentWidth = getWidth() - paddingLeft - paddingRight;
        int contentHeight = getHeight() - paddingTop - paddingBottom;

        // Draw the text.
        canvas.drawText(mContactName +": " + mMessageBody, paddingLeft
                + (contentWidth - mTextWidth) / 2, paddingTop
                + (contentHeight + mTextHeight) / 2, mTextPaint);

        // Draw the example drawable on top of the text.
        if (mExampleDrawable != null) {
            mExampleDrawable.setBounds(paddingLeft, paddingTop, paddingLeft
                    + contentWidth, paddingTop + contentHeight);
            mExampleDrawable.draw(canvas);
        }
    }

    /**
     * Gets the example color attribute value.
     * 
     * @return The example color attribute value.
     */
    public int getExampleColor() {
        return mExampleColor;
    }

    /**
     * Sets the view's example color attribute value. In the example view, this
     * color is the font color.
     * 
     * @param exampleColor
     *            The example color attribute value to use.
     */
    public void setExampleColor(int exampleColor) {
        mExampleColor = exampleColor;
        invalidateTextPaintAndMeasurements();
    }

    /**
     * Gets the example dimension attribute value.
     * 
     * @return The example dimension attribute value.
     */
    public float getExampleDimension() {
        return mExampleDimension;
    }

    /**
     * Sets the view's example dimension attribute value. In the example view,
     * this dimension is the font size.
     * 
     * @param exampleDimension
     *            The example dimension attribute value to use.
     */
    public void setExampleDimension(float exampleDimension) {
        mExampleDimension = exampleDimension;
        invalidateTextPaintAndMeasurements();
    }

    /**
     * Gets the example drawable attribute value.
     * 
     * @return The example drawable attribute value.
     */
    public Drawable getExampleDrawable() {
        return mExampleDrawable;
    }

    /**
     * Sets the view's example drawable attribute value. In the example view,
     * this drawable is drawn above the text.
     * 
     * @param exampleDrawable
     *            The example drawable attribute value to use.
     */
    public void setExampleDrawable(Drawable exampleDrawable) {
        mExampleDrawable = exampleDrawable;
    }
    
    public String getMessageBody() {
        return mMessageBody;
    }

    public void setMessageBody(final String mMessageBody) {
        this.mMessageBody = mMessageBody;
    }

    public String getContactName() {
        return mContactName;
    }

    public void setContactName(final String mContactName) {
        this.mContactName = mContactName;
    }
}
