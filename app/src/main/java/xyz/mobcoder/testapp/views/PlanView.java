package xyz.mobcoder.testapp.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import xyz.mobcoder.testapp.api.models.Data;
import xyz.mobcoder.testapp.api.models.ItemXX;
import xyz.mobcoder.testapp.utils.Utils;

public class PlanView extends View {

    Bitmap bitmapSource;
    Bitmap floorBitmap;
    Matrix matrix;

    Paint fillPaint;
    Paint strokePaint;
    RectF r = new RectF(30, 30, 1000, 500);

    public PlanView(Context context) { this(context, null); }

    public PlanView(Context context, @Nullable AttributeSet attrs) { this(context, attrs, 0); }

    public PlanView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) { this(context, attrs, defStyleAttr, 0); }

    public PlanView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setData(Data data){
        String image_url = "https://planner5d.com/m/t/" + data.getGround().getTexture().toString();
        Bitmap floorSource = Utils.INSTANCE.getBitmapSource(getContext(), image_url);
        init(data, floorSource);
    }

    private void init(Data data, Bitmap floorSource) {
        ItemXX room = Utils.INSTANCE.getRoom(data.getItems().get(0).getItems());
        fillPaint = new Paint();
        strokePaint = new Paint();

        matrix = new Matrix();
        matrix.postScale(20,20);
        floorBitmap = Bitmap.createBitmap(floorSource, 0, 0, 100, 150, matrix, true);
        // stroke
        strokePaint.setStyle(Paint.Style.STROKE);
        strokePaint.setColor(Color.BLACK);
        strokePaint.setStrokeWidth(10);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        // First rectangle
        canvas.drawBitmap(floorBitmap, 0,0, fillPaint);    // fill
        canvas.drawRect(r, strokePaint);  // stroke
    }

    public void reDraw() {
        this.invalidate();
    }


}
