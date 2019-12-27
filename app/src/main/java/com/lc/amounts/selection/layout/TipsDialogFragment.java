package com.lc.amounts.selection.layout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * @author : lc
 *         date : 2019-12-27
 */
public class TipsDialogFragment extends BaseDialogFragment {

  /**
   * title view
   */
  private TextView mTitle;

  /**
   * 内容文本
   */
  private TextView mContent;

  /**
   * 底部button layout
   */
  private LinearLayout mButtons;

  /**
   * 左边的text button
   */
  private TextView mLeft;

  /**
   * 右边的text button
   */
  private TextView mRight;

  /**
   * 设置layout params
   */
  private WindowManager.LayoutParams mLayoutParams;

  private String mTitleText;

  private String mContentText;

  private String mLeftButtonText;

  private String mRightButtonText;

  /**
   * 底部button的显示状态，0，代表不显示，1代表显示一个，2代表显示两个。
   */
  private int mButtonState = 2;

  /**
   * 设置点击dialog外部是否会显示
   */
  private boolean mIsCanceledOnTouchOutside;

  /**
   * 设置点击dialog外部是否会显示
   */
  private boolean mCancelable;

  /**
   * 点击事件的监听接口引用
   *
   */
  private DialogButtonClickListener mListener;

  public TipsDialogFragment() {}

  @SuppressLint("ValidFragment")
  public TipsDialogFragment(Builder builder) {
    if (builder == null) {
      return;
    }
    mLayoutParams = builder.mLayoutParams;
    mTitleText = builder.mTitleText;
    mContentText = builder.mContentText;
    mLeftButtonText = builder.mLeftButtonText;
    mRightButtonText = builder.mRightButtonText;
    mButtonState = builder.mButtonState;
    mIsCanceledOnTouchOutside = builder.mIsCanceledOnTouchOutside;
    mCancelable = builder.mCancelable;
  }

  @Override
  protected int getDialogLayoutResId() {
    return R.layout.dialog_tips;
  }

  @Override
  protected void onInflated(View container, Bundle savedInstanceState) {
    findView(container);
    setView();
    setListener();
  }

  /**
   * 通过父view找到子view
   *
   * @param container 父view
   */
  private void findView(View container) {
    mTitle = container.findViewById(R.id.tv_title);
    mContent = container.findViewById(R.id.tv_content);
    mButtons = container.findViewById(R.id.ll_buttons);
    mLeft = container.findViewById(R.id.tv_left);
    mRight = container.findViewById(R.id.tv_right);
  }

  /**
   * 设置view内容及是否显示
   *
   */
  private void setView() {
    if (TextUtils.isEmpty(mTitleText)) {
      mTitle.setVisibility(View.GONE);
    } else {
      mTitle.setVisibility(View.VISIBLE);
      mTitle.setText(mTitleText);
    }

    mContent.setText(mContentText);

    setButtons();

  }

  /**
   * 设置点击事件
   *
   */
  private void setListener() {

    mLeft.setOnClickListener(v -> {
      if (mListener != null) {
        mListener.leftOnClick(this);
      }
    });

    mRight.setOnClickListener(v -> {
      if (mListener != null) {
        mListener.rightOnClick(this);
      }
    });

  }

  private void setButtons() {
    switch (mButtonState) {
      case 0:
        mButtons.setVisibility(View.GONE);
        break;
      case 1:
        mLeft.setVisibility(View.GONE);
        mRight.setVisibility(View.VISIBLE);
        mRight.setText(mRightButtonText);
        break;
      default:
      case 2:
        mLeft.setVisibility(View.VISIBLE);
        mRight.setVisibility(View.VISIBLE);
        mLeft.setText(mLeftButtonText);
        mRight.setText(mRightButtonText);
        break;
    }

  }

  @Override
  protected WindowManager.LayoutParams getLayoutParams(WindowManager.LayoutParams params) {
    if (mLayoutParams != null) {
      return mLayoutParams;
    }
    return super.getLayoutParams(params);
  }

  @Override
  protected boolean getCanceledOnTouchOutside() {
    if (mIsCanceledOnTouchOutside) {
      return true;
    }
    return super.getCanceledOnTouchOutside();
  }

  @Override
  protected boolean getCancelable() {
    if (mCancelable) {
      return true;
    }
    return super.getCancelable();
  }

  public void show(FragmentManager manager) {
    if (manager == null) {
      return;
    }
    show(manager, "TipsDialogFragment");

  }

  /**
   * 点击事件的监听接口，默认情况不用复写方法，用户可根据实际需求复写。
   *
   */
  public interface DialogButtonClickListener {

    /**
     * 左边点击事件回调方法
     *
     * @param tipsDialogFragment 当前dialog实例
     */
    default void leftOnClick(TipsDialogFragment tipsDialogFragment) {}

    /**
     * 右边点击事件回调方法
     *
     * @param tipsDialogFragment 当前dialog实例
     */
    default void rightOnClick(TipsDialogFragment tipsDialogFragment) {}

  }

  /**
   * 设置按钮的点击事件监听
   *
   */
  public void setDialogButtonListener(DialogButtonClickListener listener) {
    mListener = listener;
  }

  /***
   * 静态内部类，用builder模式来设置该dialog的相关属性
   *
   */
  public static class Builder {

    /**
     * 设置dialog的layout params
     */
    private WindowManager.LayoutParams mLayoutParams;

    /**
     * 设置title的文本
     */
    private String mTitleText;

    /**
     * 设置dialog文本内容
     */
    private String mContentText;

    /**
     * 设置dialog底部左边button的文本，默认显示cancle
     */
    private String mLeftButtonText = "cancel";

    /**
     * 设置dialog底部左边button的文本，默认显示ok
     */
    private String mRightButtonText = "ok";

    /**
     * 设置dialog底部三个button显示状态，1，2 ？
     */
    private int mButtonState = 2;

    /**
     * 设置点击dialog外部是否会显示
     */
    private boolean mIsCanceledOnTouchOutside;

    /**
     * 设置点击dialog外部是否会显示
     */
    private boolean mCancelable;

    public Builder setLayoutParams(WindowManager.LayoutParams layoutParams) {
      mLayoutParams = layoutParams;
      return this;
    }

    public Builder setTitleText(String titleText) {
      mTitleText = titleText;
      return this;
    }

    public Builder setContentText(String contentText) {
      mContentText = contentText;
      return this;
    }

    public Builder setLeftButtonText(String leftButtonText) {
      mLeftButtonText = leftButtonText;
      return this;
    }

    public Builder setRightButtonText(String rightButtonText) {
      mRightButtonText = rightButtonText;
      return this;
    }

    public Builder setButtonState(int buttonState) {
      mButtonState = buttonState;
      return this;
    }

    public Builder setCanceledOnTouchOutside(boolean isCanceledOnTouchOutside) {
      mIsCanceledOnTouchOutside = isCanceledOnTouchOutside;
      return this;
    }

    public Builder setCancelable(boolean cancelable) {
      mCancelable = cancelable;
      return this;
    }

    public TipsDialogFragment build() {
      return new TipsDialogFragment(this);
    }
  }
}
