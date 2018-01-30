package com.playground.android.vuki.porterduff;

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

    private Bitmap star;
    private Bitmap mic;

    @Override
    protected void onDraw( Canvas canvas ) {
        super.onDraw( canvas );
        if ( !initialized ) {
            init();
        }

        //canvas background
       // canvas.drawColor( getResources().getColor( android.R.color.holo_blue_light ) );


        paint.setColor( getResources().getColor( android.R.color.holo_orange_light ) );
        paint.setXfermode( null );

        canvas.drawBitmap( mic, width / 3, 0, paint );
        paint.setXfermode( new PorterDuffXfermode( PorterDuff.Mode.DST_IN ) );
        canvas.drawBitmap( star, width / 3, 0, paint );
    }

    private void init() {
        initialized = true;
        paint = new Paint();
        width = getWidth();
        height = getHeight();

        Matrix matrix = new Matrix();
        matrix.setScale( 5, 5 );

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;

        star = BitmapFactory.decodeResource( getResources(), android.R.drawable.btn_star_big_on, options );
        star = Bitmap.createBitmap( star, 0, 0, star.getWidth(), star.getHeight(), matrix, false );

        mic = BitmapFactory.decodeResource( getResources(), android.R.drawable.ic_btn_speak_now, options );
        mic = Bitmap.createBitmap( mic, 0, 0, mic.getWidth(), mic.getHeight(), matrix, false );
    }

}
