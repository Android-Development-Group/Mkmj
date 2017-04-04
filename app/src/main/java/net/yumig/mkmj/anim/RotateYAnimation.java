package net.yumig.mkmj.anim;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * Y轴旋转动画类
 *
 * @author widebluesky
 */
public class RotateYAnimation extends Animation {

    private float fromDegree; // 旋转起始角度
    private float toDegree; // 旋转终止角度
    private float mCenterX; // 旋转中心x
    private float mCenterY; // 旋转中心y

    private Camera mCamera;

    public RotateYAnimation(float fromDegree, float toDegree, float centerX,
                            float centerY) {

        this.fromDegree = fromDegree;
        this.toDegree = toDegree;
        this.mCenterX = centerX;
        this.mCenterY = centerY;

        setDuration(1500);
    }

    @Override
    public void initialize(int width, int height, int parentWidth,
                           int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        mCamera = new Camera();
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        float fromDegree = this.fromDegree;

        float degrees = fromDegree + (this.toDegree - fromDegree)
                * interpolatedTime;

        float centerX = mCenterX;
        float centerY = mCenterY;

        Matrix matrix = t.getMatrix();
        mCamera.save();
        mCamera.rotateY(degrees);
        mCamera.getMatrix(matrix);

        matrix.preTranslate(-centerX, -centerY);
        matrix.postTranslate(centerX, centerY);
        mCamera.restore();
    }

}
