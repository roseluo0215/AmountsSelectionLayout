package com.lc.amounts.selection.layout;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.View;
import android.view.animation.OvershootInterpolator;

/**
 * @author : lc
 *         date : 2019-05-14
 */
public class ViewReversalAnimationUtil {

  private Context mContext;
  private IAnimationListener mListener;

  public ViewReversalAnimationUtil(Context context) {
    mContext = context;
  }

  private void changeCameraDistance(View oldView, View newView) {
    int distance = 8000;
    float scale = mContext.getResources().getDisplayMetrics().density * distance;
    oldView.setCameraDistance(scale);
    newView.setCameraDistance(scale);
  }

  public void flipAnimatorXViewShow(final View oldView, final View newView, final boolean isBack) {
    changeCameraDistance(oldView, newView);
    ObjectAnimator animator1 = ObjectAnimator.ofFloat(oldView, "rotationY", 0, -90);
    final ObjectAnimator animator2 = ObjectAnimator.ofFloat(newView, "rotationY", 90, 0);
    animator2.setInterpolator(new OvershootInterpolator(2.0f));

    animator1.addListener(new Animator.AnimatorListener() {
      @Override
      public void onAnimationStart(Animator animation) {

      }

      @Override
      public void onAnimationEnd(Animator animation) {
        if (isBack) {
          newView.setVisibility(View.GONE);
          animator2.setDuration(500).start();
          oldView.setVisibility(View.VISIBLE);
        } else {
          oldView.setVisibility(View.GONE);
          animator2.setDuration(500).start();
          newView.setVisibility(View.VISIBLE);
        }
        if (mListener != null) {
          mListener.onAnimationEnd();
        }

      }

      @Override
      public void onAnimationCancel(Animator animation) {

      }

      @Override
      public void onAnimationRepeat(Animator animation) {

      }
    });
    animator1.setDuration(500).start();
  }

  public interface IAnimationListener {

    void onAnimationEnd();
  }

  public void setAnimationListener(IAnimationListener listener) {
    mListener = listener;
  }

}
