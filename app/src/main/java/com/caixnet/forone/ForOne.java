package com.caixnet.forone;

import android.content.Context;
import android.content.res.Resources;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.drawable.shapes.RoundRectShape;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by caixnet on 12/22/17.
 */


public class ForOne extends View {
//public class ForOne extends ViewGroup {
//public class ForOne {

    private float mSize;//w=h
    private String mKeyvalue = "";
    private boolean mTap; // Click
    private boolean mActionDown = false;
    private boolean mActionMove = false;
    private boolean mActionUp = false;
    private boolean mShowInOne = false; // Show Inner One
    private boolean mVibratoOn = false; // Vibrator on/off
    private boolean mSoundOn = false; // Sound on/off
    private boolean mImageOn = false; // Show on/off in One
    private boolean mTipToolOn = false; // Show on/off TipTool
    private String mSetBackgroundColor ="#88ffffcc";
    private String mSetActiveBackgroundColor ="#4000ffee";
    private String mSetLineColor ="#66000000";
    //private String mSetLineColor ="#4400ff00";
    private final static float CORNER_RADIUS = 40.0f;
    private Path roundViewPath;
    private final float MBIGRADIUSFRACTION = 40 / 300.0f;
    private final float MLITTLERADIUSFRACTION = 15 / 300.0f;

    private float mInRadius;//小圆半径
    private float mOutRadius;//大圆半径
    private double mCornerRadius;

    private List<One> mBaseOnes = new ArrayList<>();
    private List<Integer> mSelectedIds = new ArrayList<>();

    private Paint mOutCirclePaint;
    private Paint mInCirclePaint;
    private Paint mLinePaint;//滑动过程中的折线和指引线paint

    private Path mPath;//滑动过程中的折线
    private float mNodeLineX;//折线的节点位置
    private float mNodeLineY;
    private float mLineTmpX;//指引线的终点
    private float mLineTmpY;
    private ChaiCode cai = new ChaiCode(); //设置ChaiCode

    //String mAnswer = "012";//预设密码


    /**
     * 手势锁回调
     */
    public interface OnForOneListener{
        void onOneHitted(int index);//one被触摸到
        void onForOneSuccess(String password);
        String onGetKey();
        //String onGetKey(String keyvalue);
        //void onForOneFail();
    }
    //
    private OnForOneListener mForOneListener;

    //    public void setmForOneListener(OnForOneListener listener){
    public void setForOneListener(OnForOneListener listener){
        mForOneListener = listener;
    }

    //
    public ForOne(Context context, AttributeSet attrs) {
        super(context, attrs);
        //设置背景颜色
        this.setBackgroundColor(Color.parseColor(mSetBackgroundColor));
        //this.setBackgroundColor(Color.argb(104,255,255,204));
        //this.setBackgroundColor(Color.GRAY);


    }
    //
    public String onGetKey(){
        return this.mKeyvalue;
    }
    //
    public String onSetKey(String keyValue){
        this.mKeyvalue = cai.get(keyValue);
        return this.mKeyvalue;
    }
    ////圆角背景
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        float conerRaidus = 40.0f;
        this.roundViewPath = new Path();
        this.roundViewPath.addRoundRect(new RectF(0, 0, w, h), conerRaidus, conerRaidus, Path.Direction.CW);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        if (this.roundViewPath != null){
            canvas.clipPath(this.roundViewPath);
        }
        super.dispatchDraw(canvas);
    }
    //


    public ForOne(Context context) {
        this(context, null, 0);
    }

//    public ForOne(Context context, AttributeSet attrs) {
//        this(context, attrs, 0);
//    }

    public ForOne(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs, defStyle);
    }

    private void init(Context context, AttributeSet attrs, int defStyle) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
    }

    public double getCornerRadius() {
        return mCornerRadius;
    }

    public void setCornerRadius(double cornerRadius) {
        mCornerRadius = cornerRadius;
    }

    @Override
    public void draw(Canvas canvas) {
        int count = canvas.save();

        final Path path = new Path();
        path.addRoundRect(new RectF(0, 0, canvas.getWidth(), canvas.getHeight()), (float) mCornerRadius, (float) mCornerRadius, Path.Direction.CW);
        canvas.clipPath(path, Region.Op.REPLACE);

        canvas.clipPath(path);
        super.draw(canvas);
        canvas.restoreToCount(count);
    }

    //计算
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int wMode = MeasureSpec.getMode(widthMeasureSpec);
        int hMode = MeasureSpec.getMode(heightMeasureSpec);
        int wSize = MeasureSpec.getSize(widthMeasureSpec);
        int hSize = MeasureSpec.getSize(heightMeasureSpec);
        int resultWidth = wSize;
        int resultHeight = hSize;
        Resources r = Resources.getSystem();
        //lp = wrapcontent时 指定默认值
        if (wMode == MeasureSpec.AT_MOST) {
            resultWidth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 300, r.getDisplayMetrics());
        }
        if (hMode == MeasureSpec.AT_MOST) {
            resultHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 300, r.getDisplayMetrics());
        }
        int size = resultWidth > resultHeight ? resultHeight : resultWidth;
        setMeasuredDimension(size, size);
        initParams();
    }

    /**
     * 绘制涉及参量的初始化操作
     */
    private void initParams() {
        mSize = getMeasuredWidth();
        // Paint One Size
        mOutRadius = MBIGRADIUSFRACTION * mSize + 12;
        mInRadius = MLITTLERADIUSFRACTION * mSize ;

        mOutCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mInCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mLinePaint.setStyle(Paint.Style.STROKE);
        mLinePaint.setStrokeCap(Paint.Cap.ROUND);
        mLinePaint.setStrokeJoin(Paint.Join.ROUND);
        mLinePaint.setStrokeWidth(mInRadius * 2);
        mLinePaint.setColor(Color.parseColor(mSetLineColor));


        mPath = new Path();
        //ones初始化
        if (mBaseOnes.size() == 0) {
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    //构建2*2 one
                    One one = new One();
                    float centerX = mSize * (1 + j * 2) / 4;
                    float centerY = mSize * (1 + i * 2) / 4;
                    one.mCenterPointX = centerX;
                    one.mCenterPointY = centerY;
                    one.mOutRadius = mOutRadius;
                    one.mInRadius = mInRadius;
                    one.mId = i * 2 + j;
                    mBaseOnes.add(one);
                }
            }
        }

    }

    /**
     * 绘制ones、折线、指引线
     *
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        for (int i = 0; i < mBaseOnes.size(); i++) {
            One one = mBaseOnes.get(i);
            drawOne(canvas, one);
        }
        //绘制折线和指引线
        canvas.drawPath(mPath, mLinePaint);
        if (mSelectedIds.size() > 0) {
            canvas.drawLine(mNodeLineX, mNodeLineY, mLineTmpX, mLineTmpY, mLinePaint);
        }

    }

    /**
     * 绘制基本单元
     * 1.大、小圆
     * 2.三角指示
     *
     * @param canvas
     * @param one
     */
    private void drawOne(Canvas canvas, One one) {
        if (one.mState == One.OneSate.IDLE) {
            mOutCirclePaint.setColor(one.mIdleOutCircleColor);
            mInCirclePaint.setColor(one.mIdleInCircleColor);
        } else if (one.mState == One.OneSate.HITTED) {
            mOutCirclePaint.setColor(one.mHittedOutCircleColor);
            mInCirclePaint.setColor(one.mHittedInCircleColor);
        } else if (one.mState == One.OneSate.SUCCESS) {
            mOutCirclePaint.setColor(one.mSuccessOutCircleColor);
            mInCirclePaint.setColor(one.mSuccessdInCircleColor);
        } else if (one.mState == One.OneSate.ERRO) {
            mOutCirclePaint.setColor(one.mErroOutCircleColor);
            mInCirclePaint.setColor(one.mErroInCircleColor);
        }
        // Paint One Circle
        canvas.drawCircle(one.mCenterPointX, one.mCenterPointY, one.mOutRadius, mOutCirclePaint);
        if(mShowInOne)
            canvas.drawCircle(one.mCenterPointX, one.mCenterPointY, one.mInRadius, mInCirclePaint);

        //画三角指示符
        if (mSelectedIds.size() > 0) {
            if (one.mId != mSelectedIds.get(mSelectedIds.size() - 1)) {//最后一个不画三角
//            if (one.mId != mSelectedIds.get(mSelectedIds.size())) {//最后一个不画三角
                one.drawArrow(canvas, mInCirclePaint);
            }
        }
    }

    /**
     *  Touche Moved on One
     */
    private boolean onActionDown(){
        Logd("Action Down:"+!(this.mActionDown));
        return this.mActionDown = !(this.mActionDown);
    }
    /**
     *  Touche Moved on One
     */
    private boolean onActionMove(){
        Logd("Action Move："+!(this.mActionMove));
        return this.mActionMove = !(this.mActionMove);
    }

    /**
     *  Touche Moved on One
     */
    private boolean onActionUp(){
        Logd("Action Up:"+!(this.mActionUp));
        return this.mActionUp = !(this.mActionUp);
    }

    /**
     *  Touche reset action on One
     */
    private boolean resetAction(){
        this.mActionDown = false;
        this.mActionMove = false;
        this.mActionUp = false;
        return true;
    }

    /**
     * 手指点击时，根据选中的one，点击状态
     */
    private boolean onTap(){
        return this.mTap = !(this.mTap);
    }

    /**
     *   Touch move 核心代码
     */
    private boolean onTouchMove(MotionEvent event){
        float x = event.getX();
        float y = event.getY();
        One one = checkHitOne(x, y);
        //探测未选中的one
        //!mSelectedIds.contains(one.mId) // one do not cross
        ///if(one != null && !mSelectedIds.contains(one.mId)){//探测到
        if (one != null) {//探测到
            if (mForOneListener != null) {
                mForOneListener.onOneHitted(one.mId);
            }
            //手指触摸到one,作以下处理:
            //1.one状态处理
            //2.path的节点设置为one的中心
            //3.指引线的终点设为节点位置
            one.mState = One.OneSate.HITTED;
            mSelectedIds.add(one.mId);
            mNodeLineX = one.mCenterPointX;
            mNodeLineY = one.mCenterPointY;//折线变为one的圆心

            if (mSelectedIds.size() == 1) {//手指第一次选中one
                mPath.moveTo(mNodeLineX, mNodeLineY);
            } else {
                mPath.lineTo(mNodeLineX, mNodeLineY);
            }
            mLineTmpX = mNodeLineX;
            mLineTmpY = mNodeLineY;
        } else {//未探测到
            //手指未触摸到one,则只需要设置指引线终点即可
            mLineTmpX = x;
            mLineTmpY = y;
        }
        return true;
    }

    /**
     * 核心代码，控制手势监听的逻辑
     * step1:ACTION_DOWN 做复位操作
     * setp2:ACTION_MOVE 监测手指滑到哪个one，同时更新one状态、指引线及折线
     * step3:ACTION_UP 校验密码、更新选中的one状态、设置选中的one三角角度
     * srep4:前三步都会更改绘制涉及的参数，需要重绘操作
     *
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                onActionDown();
                //onTap();
                reset();
                break;
            case MotionEvent.ACTION_MOVE:
                onTouchMove(event);
                onActionMove();
                break;
            case MotionEvent.ACTION_UP:
                onActionUp();
                onTap();
                //选中的one 改为error/success状态
                changeReleaseOneState();
                //折线处理,终点回退到节点,实现取消指引线的效果
                mLineTmpX = mNodeLineX;
                mLineTmpY = mNodeLineY;
                //三角角度设置
                configOneArrowAngles();
                break;
            default:
                break;
        }
        invalidate();
        return true;
    }


    /**
     * 手指松开时，根据选中的one，设置三角的角度
     */
    private void configOneArrowAngles() {
        for (int i = 0; i < mSelectedIds.size() - 1; i++) {
            int index = mSelectedIds.get(i);
            int nextIndex = mSelectedIds.get(i + 1);
            One curOne = mBaseOnes.get(index);
            One nextOne = mBaseOnes.get(nextIndex);

            float offsetX = nextOne.mCenterPointX - curOne.mCenterPointX;
            float offsetY = nextOne.mCenterPointY - curOne.mCenterPointY;
            double angle = Math.toDegrees(Math.atan2(offsetY, offsetX));
            curOne.setArrowAngle(angle);
            // Log.e("ANGLES", angle + "");
        }
    }

    /**
     * 松手时，检测结果，修改选中的one状态
     */
    private void changeReleaseOneState() {
        resetAction();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < mSelectedIds.size(); i++) {
            sb.append(mSelectedIds.get(i));
        }
        String str = cai.makeCode(sb.toString());
        Logd("path:" + sb.toString());
        Logd("Code:" + str);
        Logd("OnTap:" + this.mTap);

        if (this.mTap){
            //setBackgroundColor(Color.YELLOW);
            this.setBackgroundColor(Color.parseColor(mSetActiveBackgroundColor));
        }else{
            this.setBackgroundColor(Color.parseColor(mSetBackgroundColor));
            //setBackgroundColor(Color.GRAY);
        }
        Logd("ChaiCode:" + cai.get(str));
        //onSetKey(str);
        //boolean isSuccess = TextUtils.equals(mAnswer, sb.toString());

        if (mForOneListener != null) {
            onSetKey(str);
            mForOneListener.onGetKey();
            /*
            if (isSuccess) {
                // mForOneListener.onForOneSuccess(mAnswer);
            } else {
                //mForOneListener.onForOneFail();
            }
            */
        }
        //设置选中的one的状态
        for (int i = 0; i < mBaseOnes.size(); i++) {
            One one = mBaseOnes.get(i);
            /*
            if (mSelectedIds.contains(one.mId)) {
                if (isSuccess) {
                    one.mState = One.OneSate.SUCCESS;
                } else {
                    one.mState = One.OneSate.ERRO;
                }
            }
            */

        }
    }

    /**
     * 复位所有one、path、指引线状态
     */
    private void reset() {
        mPath.reset();
        mSelectedIds.clear();
        for (int i = 0; i < mBaseOnes.size(); i++) {
            One one = mBaseOnes.get(i);
            one.mState = One.OneSate.IDLE;
            one.mArrowAngle = 0;
        }

        mLineTmpX = 0;
        mLineTmpY = 0;
        mNodeLineX = 0;
        mNodeLineY = 0;
    }

    /**
     * 检测位置落在哪个one上
     *
     * @param x
     * @param y
     * @return
     */
    private One checkHitOne(float x, float y) {
        for (int i = 0; i < mBaseOnes.size(); i++) {
            One one = mBaseOnes.get(i);
            float startX = one.mCenterPointX - one.mOutRadius;
            float endX = one.mCenterPointX + one.mOutRadius;
            float startY = one.mCenterPointY - one.mOutRadius;
            float endY = one.mCenterPointY + one.mOutRadius;

            if (x >= startX && x <= endX && y >= startY && y <= endY) {
                return one;
            }
        }
        return null;
    }

    /**
     * Log Debug
     */
    private void Logd(String message){
        Log.i("CAI", message);
    }
}
