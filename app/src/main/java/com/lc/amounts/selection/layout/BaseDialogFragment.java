package com.lc.amounts.selection.layout;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;


/**
 * @author : lc
 *         date : 19/5/14
 *         自定义dialog的基类，想要自定义一个dialog继承该类
 */
public abstract class BaseDialogFragment extends DialogFragment {

  protected View mContainer;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    // setStyle(DialogFragment.STYLE_NO_TITLE, R.style.PopupDialog);

  }

  @Override
  public View onCreateView(@Nullable LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    if (inflater == null) {
      return null;
    }
    setDialogAttributes(getDialog());
    return mContainer = inflater.inflate(getDialogLayoutResId(), container, false);
  }

  protected void setDialogAttributes(Dialog dialog) {
    if (dialog != null) {
      dialog.setCanceledOnTouchOutside(getCanceledOnTouchOutside());
      dialog.setCancelable(getCancelable());
    }
  }

  /**
   * 点击返回的物理按键dialog是否消失
   */
  protected boolean getCancelable() {
    return true;
  }

  /**
   * 点击dialog外部是否消失 默认不消失
   * 
   */
  protected boolean getCanceledOnTouchOutside() {
    return false;
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    if (mContainer != null) {
      onInflated(mContainer, savedInstanceState);
      setWindowStyle(getDialog().getWindow());
    }
  }

  /**
   * 返回当前自定义dialog的view布局
   *
   * @return int
   */
  protected abstract int getDialogLayoutResId();

  /**
   * 在此方法中对view进行初始化
   *
   * @param container 当前的view，通过该view findViewById可以找到子view
   * @param savedInstanceState 保存的Fragment状态
   */
  protected abstract void onInflated(View container, Bundle savedInstanceState);

  /**
   * 设置当前window的样式
   *
   * @param window 可以通过该window设置dialog window
   *
   */
  protected void setWindowStyle(Window window) {
    if (window == null || getContext() == null) {
      return;
    }
    window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    window.getDecorView().setPadding(30, 30, 30, 30);
    window.setAttributes(getLayoutParams(window.getAttributes()));
  }

  /**
   * 设置dialog布局参数
   *
   * @param params 通过该参数设置相应的属性
   *
   */
  protected WindowManager.LayoutParams getLayoutParams(WindowManager.LayoutParams params) {
    if (params == null) {
      return new WindowManager.LayoutParams();
    }
    // 这里可以设置dialog布局的大小
    params.width = ViewGroup.LayoutParams.MATCH_PARENT;
    params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
    params.gravity = Gravity.CENTER;
    return params;
  }

}
