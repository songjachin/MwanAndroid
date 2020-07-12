package com.songjachin.mwanandroid.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;

/**
 * Created by matthew
 */
public class RoundRectImageView extends AppCompatImageView {

    private float roundRatio = 0.1f;
    private Path path;

    public RoundRectImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        if (path == null) {
            path = new Path();
            path.addRoundRect(new RectF(0, 0, getWidth(), getHeight()), roundRatio * getWidth(), roundRatio * getHeight(), Path.Direction.CW);
        }
        canvas.save();//保持画布状态
        canvas.clipPath(path);//裁剪不规则区域
        //设置完之后执行on draw 方法
        super.onDraw(canvas);//这个不是构造方法，所以super可以放在这里
        canvas.restore();//恢复画布状态
    }
}
