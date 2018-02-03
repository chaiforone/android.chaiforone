package com.caixnet.forone;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

/**
 * Created by caixnet on 12/21/2017.
 */

public class One {

    float mCenterPointX;//圆心x
    float mCenterPointY;//圆心y
    float mOutRadius;//大圆半径
    float mInRadius;//小圆半径
    boolean mdrawArrowOff = false; //是否画箭头
    One.OneSate mState = One.OneSate.IDLE;//默认空闲

    int mId;//索引

    //空闲状态颜色
    int mIdleOutCircleColor = Color.parseColor("#440000ff");
    int mIdleInCircleColor = Color.parseColor("#ee0000ff");

    //选中状态颜色
    int mHittedOutCircleColor = Color.parseColor("#8800ffff");
    int mHittedInCircleColor = Color.parseColor("#ee00ffff");

    //密码通过的颜色
    int mSuccessOutCircleColor = Color.parseColor("#1100ff00");
    int mSuccessdInCircleColor = Color.parseColor("#00ff00");

    //密码错误时的颜色
    int mErroOutCircleColor = Color.parseColor("#11ff0000");
    int mErroInCircleColor = Color.parseColor("#ff0000");

    //三角
    Path mArrow = new Path();
    //三角指向角度,水平向右为0度，顺时针方向为正
    double mArrowAngle;

    public void setArrowAngle(double angle) {
        mArrowAngle = angle;
    }
    // 关闭箭头
    public void drawArrowOff(){
        this.mdrawArrowOff = true;
    }
    public void drawArrow(Canvas canvas, Paint paint) {
        //关闭箭头
        drawArrowOff();
        if (mdrawArrowOff) return;
        //没有松手，则不画三角
        /*
        if(mState != OneSate.SUCCESS && mState != OneSate.ERRO){
            return;
        }
        */

        float arrowLen = (mOutRadius - mInRadius) * 0.5f;
        float arrowLeftX = mCenterPointX + mInRadius + (mOutRadius - mInRadius - arrowLen) / 2;
        float arrowRightX = arrowLeftX + arrowLen;
        float topY = mCenterPointY - arrowLen;
        float bottomY = mCenterPointY + arrowLen;
        mArrow.moveTo(arrowRightX, mCenterPointY);
        mArrow.lineTo(arrowLeftX, topY);
        mArrow.lineTo(arrowLeftX, bottomY);
        mArrow.close();

        canvas.save();
        canvas.rotate((float) mArrowAngle, mCenterPointX, mCenterPointY);
        canvas.drawPath(mArrow, paint);
        canvas.restore();
    }

    public enum OneSate {
        IDLE,//空闲
        HITTED,//手指触摸
        ERRO,//密码错误
        SUCCESS;//密码正确
    }

}