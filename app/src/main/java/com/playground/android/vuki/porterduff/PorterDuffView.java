package com.playground.android.vuki.porterduff;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by mvukosav
 */
public class PorterDuffView extends View {

    public PorterDuffView( Context context ) {
        super( context );
    }

    public PorterDuffView( Context context, @Nullable AttributeSet attrs ) {
        super( context, attrs );
    }

    public PorterDuffView( Context context, @Nullable AttributeSet attrs, int defStyleAttr ) {
        super( context, attrs, defStyleAttr );
    }

    private Paint paint;
    private boolean initialized;
    private float width;
    private float height;

    private Bitmap dest;

    private Bitmap mic;
    private Bitmap star;

    Canvas container;
    Matrix matrix = new Matrix();
    PorterDuffXfermode xfermode = new PorterDuffXfermode( PorterDuff.Mode.SRC_IN );
    PorterDuffXfermode init = new PorterDuffXfermode( PorterDuff.Mode.ADD );

    @Override
    protected void onDraw( Canvas canvas ) {
        super.onDraw( canvas );
        if ( !initialized ) {
            init();
        }

        dest = Bitmap.createBitmap( star );

        container.setBitmap( dest );
        //canvas background
        // canvas.drawColor( getResources().getColor( android.R.color.holo_blue_light ) );

        paint.setXfermode( xfermode );
        container.drawBitmap( mic, left, 0, paint );

//        paint.setColor( getResources().getColor( android.R.color.holo_orange_light ) );
        paint.setXfermode( null );
        canvas.drawBitmap( dest, 0, 0, paint );

    }

    float left = 0;
    ValueAnimator.AnimatorUpdateListener valueAnimatorListener = new ValueAnimator.AnimatorUpdateListener() {
        @Override
        public void onAnimationUpdate( ValueAnimator animation ) {
            left = (float) animation.getAnimatedValue();
//            Log.d( "dd", "" + left );
            postInvalidate();
        }
    };

    private void init() {

        initialized = true;
        paint = new Paint();
        width = getWidth();
        height = getHeight();

        matrix.setScale( 6, 6 );

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;

        star = BitmapFactory.decodeResource( getResources(), android.R.drawable.btn_star_big_on, options );
        star = Bitmap.createBitmap( star, 0, 0, star.getWidth(), star.getHeight(), matrix, false );

        matrix.setScale( 6, 6 );
        mic = BitmapFactory.decodeResource( getResources(), android.R.drawable.ic_btn_speak_now, options );
        mic = Bitmap.createBitmap( mic, 0, 0, mic.getWidth(), mic.getHeight(), matrix, false );

        container = new Canvas();
        container.setBitmap( star );

        ValueAnimator valueAnimator = ValueAnimator.ofFloat( 0, container.getWidth(), 0 );
        valueAnimator.setDuration( 4000 );
        valueAnimator.setRepeatCount( ValueAnimator.INFINITE );
        valueAnimator.setRepeatMode( ValueAnimator.REVERSE );
        valueAnimator.addUpdateListener( valueAnimatorListener );
        valueAnimator.start();
    }

}
