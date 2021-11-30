package in.cpp.picoimg;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

public class PicoDrawable extends Drawable implements Runnable
{
    private BaseState mConstantState;
    private final Paint mPaint = new Paint();
    private final Matrix mMatrix = new Matrix();
    private final boolean mPaintFilterByDefault = mPaint.isFilterBitmap();

    // animation
    private long mDrawableNextFrameTime;
    private int mPlayTimes;

    // size
    private int mWidth;
    private int mHeight;
    private int mScaleType;
    private float mScale;
    private int mShiftX;
    private int mShiftY;

    // transition
    private int mAlpha = 255;
    private Drawable mPlaceholder;
    private boolean mTransitionRunning;
    private double mTransitionProgress;
    private double mTransitionStep;
    private int mTransitionDelay;


    PicoDrawable(BaseState state)
    {
        super();
        mConstantState = state;
        if (!mConstantState.isAnimated() && !mPaintFilterByDefault)
            mPaint.setFilterBitmap(true);
        mDrawableNextFrameTime = mConstantState.mNextFrameTime;
        mWidth = mConstantState.mWidth;
        mHeight = mConstantState.mHeight;
    }

    PicoDrawable()
    {
        super();
    }

    void init(Drawable placeholder, int width, int height, int scale)
    {
        boolean invalidate = (mPlaceholder != null) || (mConstantState != null);
        mPlaceholder = placeholder;
        mWidth = width;
        mHeight = height;
        mScaleType = scale;
        mConstantState = null;
        mPaint.setAlpha(mAlpha = 255);
        mTransitionRunning = false;
        if (invalidate)
            invalidateSelf();
    }

    public void recycle()
    {
        mPlaceholder = null;
        mConstantState = null;
        PicoImg.sDrawableRecycler.push(this);
    }

    @Override
    public ConstantState getConstantState()
    {
        return mConstantState;
    }

    void setConstantState(BaseState state, int fadeSteps, int fadeMillis)
    {
        // attach to constant state
        mConstantState = state;
        mDrawableNextFrameTime = mConstantState.mNextFrameTime;
        mPaint.setFilterBitmap(!mConstantState.isAnimated() || mPaintFilterByDefault);

        // decide on fading in
        if (fadeSteps > 1)
        {
            mTransitionProgress = 0;
            mTransitionStep = 1.0 / fadeSteps;
            mTransitionDelay = Math.max(1, fadeMillis / fadeSteps);
            mTransitionRunning = true;
            PicoImg.sHandler.postDelayed(this, mTransitionDelay);
        }
        else
            mPlaceholder = null;

        // calculate scaling
        Rect r = getBounds();
        scale(r.left, r.top, r.right, r.bottom);

        // done
        invalidateSelf();
    }

    public boolean isAnimated()
    {
        return (mConstantState != null) && mConstantState.isAnimated();
    }

    private void scale(int l, int t, int r, int b)
    {
        mScale = 1;
        mShiftX = 0;
        mShiftY = 0;

        // calc scale
        int bits = mScaleType & PicoImg.SCALE_S_MASK;
        if (bits == PicoImg.SCALE_FILL)
            return;
        else if (bits == PicoImg.SCALE_FIT)
            mScale = Math.min(((float) (r - l)) / mConstantState.mWidth, ((float) (b - t)) / mConstantState.mHeight);
        else if (bits == PicoImg.SCALE_CROP)
            mScale = Math.max(((float) (r - l)) / mConstantState.mWidth, ((float) (b - t)) / mConstantState.mHeight);

        // calculate shifts
        int width = (int) (mConstantState.mWidth * mScale);
        int height = (int) (mConstantState.mHeight * mScale);
        bits = mScaleType & PicoImg.SCALE_H_MASK;
        if (bits == PicoImg.SCALE_LEFT)
            mShiftX = l;
        else if (bits == PicoImg.SCALE_RIGHT)
            mShiftX = r - width;
        else
            mShiftX = (r + l - width) / 2;
        bits = mScaleType & PicoImg.SCALE_V_MASK;
        if (bits == PicoImg.SCALE_TOP)
            mShiftY = t;
        else if (bits == PicoImg.SCALE_BOTTOM)
            mShiftY = b - height;
        else
            mShiftY = (b + t - height) / 2;

        // write matrix
        if ((mScale != 1) || (mConstantState.mOrientation > 1))
        {
            // op #3, scale to size
            mMatrix.setScale(mScale, mScale);
            // op #2, mirror horizontally
            if((2 == mConstantState.mOrientation) || (4 == mConstantState.mOrientation) || (5 == mConstantState.mOrientation) || (7 == mConstantState.mOrientation))
                mMatrix.preScale(-1, 1, (5 == mConstantState.mOrientation) ? -(mConstantState.mWidth / 2f) : (mConstantState.mWidth / 2f), mConstantState.mHeight / 2f);
            // op #1, rotate
            if (mConstantState.mOrientation >= 7)
            {
                mMatrix.preRotate(270);
                mShiftY += height;
            }
            else if (mConstantState.mOrientation >= 5)
            {
                mMatrix.preRotate(90);
                mShiftX += width;
            }
            else if (mConstantState.mOrientation >= 3)
                mMatrix.preRotate(180, mConstantState.mWidth / 2f, mConstantState.mHeight / 2f);
            // op #4, move into view
            mMatrix.postTranslate(mShiftX, mShiftY);
        }
    }

    @Override
    public void setBounds(int left, int top, int right, int bottom)
    {
        super.setBounds(left, top, right, bottom);
        if (null != mPlaceholder)
            mPlaceholder.setBounds(left, top, right, bottom);
        if (null != mConstantState)
            scale(left, top, right, bottom);
    }

    @Override
    public void draw(Canvas canvas)
    {
        if (null != mPlaceholder)
        {
            if (mTransitionRunning)
                mPlaceholder.setAlpha((int)(mAlpha * (1 - mTransitionProgress)));
            mPlaceholder.draw(canvas);
            if (mTransitionRunning)
                mPlaceholder.setAlpha(255);
        }
        if (null != mConstantState)
        {
            if (mTransitionRunning)
                mPaint.setAlpha((int)(mAlpha * mTransitionProgress));

            if ((mScaleType & PicoImg.SCALE_S_MASK) == PicoImg.SCALE_FILL)
                canvas.drawBitmap(mConstantState.mOutput, null, getBounds(), mPaint);
            else if ((mScale == 1) && (mConstantState.mOrientation <= 1))
                canvas.drawBitmap(mConstantState.mOutput, mShiftX, mShiftY, mPaint);
            else
                canvas.drawBitmap(mConstantState.mOutput, mMatrix, mPaint);

            if (!mTransitionRunning && (0 != mDrawableNextFrameTime))
            {
                scheduleSelf(this, mDrawableNextFrameTime);
                mDrawableNextFrameTime = 0;
            }
        }
    }

    @Override
    public void run()
    {
        if (mTransitionRunning)
        {
            mTransitionProgress += mTransitionStep;
            if (mTransitionProgress >= 1)
            {
                mTransitionRunning = false;
                mPlaceholder = null;
                mPaint.setAlpha(mAlpha);
            }
            else
                PicoImg.sHandler.postDelayed(this, mTransitionDelay);
            invalidateSelf();
        }
        else if (null != mConstantState)
        {
            mConstantState.step();
            mDrawableNextFrameTime = mConstantState.mNextFrameTime;

            if ((0 == mConstantState.mPlayFrame) && (0 != mConstantState.mNumPlays) && (++mPlayTimes == mConstantState.mNumPlays))
                mDrawableNextFrameTime = 0;

            invalidateSelf();
        }
    }

    @Override
    public void setAlpha(int alpha)
    {
        mAlpha = alpha;
        mPaint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter)
    {
        mPaint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity()
    {
        return PixelFormat.TRANSLUCENT;
    }

    @Override
    public int getIntrinsicWidth()
    {
        return mWidth;
    }

    @Override
    public int getIntrinsicHeight()
    {
        return mHeight;
    }
}
