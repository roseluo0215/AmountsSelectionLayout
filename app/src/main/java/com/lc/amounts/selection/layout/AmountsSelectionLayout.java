package com.lc.amounts.selection.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class AmountsSelectionLayout extends LinearLayout implements View.OnClickListener {

  private List<RechargeAmountsState> list = new ArrayList<>(8);
  private int mCurrentAmounts = 10;// 默认的选中

  public AmountsSelectionLayout(Context context) {
    super(context);
  }

  public AmountsSelectionLayout(Context context, AttributeSet attrs) {
    super(context, attrs);
  }


  @Override
  protected void onFinishInflate() {
    super.onFinishInflate();

    TextView tvTen = findViewById(R.id.tv_ten);
    TextView tvTwenty = findViewById(R.id.tv_twenty);
    TextView tvFifty = findViewById(R.id.tv_fifty);
    TextView tvOneHundred = findViewById(R.id.tv_one_hundred);
    TextView tvTwoHundred = findViewById(R.id.tv_two_hundred);
    TextView tvFiveHundred = findViewById(R.id.tv_five_hundred);
    tvTen.setOnClickListener(this);
    tvTwenty.setOnClickListener(this);
    tvFifty.setOnClickListener(this);
    tvOneHundred.setOnClickListener(this);
    tvTwoHundred.setOnClickListener(this);
    tvFiveHundred.setOnClickListener(this);
    //给当前的各个view设置初始化状态，isSelect 为true代表选中
    changeViewState(tvTen, true, 10, R.id.tv_ten);
    changeViewState(tvTwenty, false, 20, R.id.tv_twenty);
    changeViewState(tvFifty, false, 50, R.id.tv_fifty);
    changeViewState(tvOneHundred, false, 100, R.id.tv_one_hundred);
    changeViewState(tvTwoHundred, false, 200, R.id.tv_two_hundred);
    changeViewState(tvFiveHundred, false, 500, R.id.tv_five_hundred);
    setCurrentState(-1);// 初始化view
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.tv_ten:
        setCurrentState(R.id.tv_ten);
        break;
      case R.id.tv_twenty:
        setCurrentState(R.id.tv_twenty);
        break;
      case R.id.tv_fifty:
        setCurrentState(R.id.tv_fifty);
        break;
      case R.id.tv_one_hundred:
        setCurrentState(R.id.tv_one_hundred);
        break;
      case R.id.tv_two_hundred:
        setCurrentState(R.id.tv_two_hundred);
        break;
      case R.id.tv_five_hundred:
        setCurrentState(R.id.tv_five_hundred);
        break;
    }
  }

  private void changeViewState(TextView view, boolean isSelect, int amounts, int amountsId) {
    RechargeAmountsState rechargeAmounts = new RechargeAmountsState();
    rechargeAmounts.setTextView(view);
    rechargeAmounts.setAmounts(amounts);
    rechargeAmounts.setAmountsId(amountsId);
    rechargeAmounts.setSelect(isSelect);
    list.add(rechargeAmounts);
  }

  private void setCurrentState(int resId) {
    for (int i = 0; i < list.size(); i++) {
      RechargeAmountsState rechargeAmountsState = list.get(i);
      // 判断当前点击选中的view的id是选中或者被选中
      if (resId == rechargeAmountsState.getAmountsId() || rechargeAmountsState.isSelect()) {
        mCurrentAmounts = rechargeAmountsState.getAmounts();// 将选中的item的值记录下来
        rechargeAmountsState.setSelect(false);//将选中的这个是否选中变成false，以防下次选中其他的item的时候这个item的状态可以改变
        setAmountsState(rechargeAmountsState, R.color.color_ffffff,
            R.drawable.text_border_green_solid_bg);
      } else {
        setAmountsState(rechargeAmountsState, R.color.color_666666,
            R.drawable.text_border_green_bg);
      }

    }
  }

  // 设置字体颜色和背景
  private void setAmountsState(RechargeAmountsState rechargeAmountsState, int colorResId,
                               int backgroundResId) {
    rechargeAmountsState.getTextView()
        .setTextColor(getContext().getResources().getColor(colorResId));
    rechargeAmountsState.getTextView().setBackgroundResource(backgroundResId);

  }

  public int getCurrentAmounts() {
    return mCurrentAmounts;
  }

  private class RechargeAmountsState {
    private TextView textView;
    private boolean isSelect;
    private int amounts;
    private int amountsId;

    private TextView getTextView() {
      return textView;
    }

    private void setTextView(TextView textView) {
      this.textView = textView;
    }

    private boolean isSelect() {
      return isSelect;
    }

    private void setSelect(boolean select) {
      isSelect = select;
    }

    private int getAmounts() {
      return amounts;
    }

    private void setAmounts(int amounts) {
      this.amounts = amounts;
    }

    private int getAmountsId() {
      return amountsId;
    }

    private void setAmountsId(int amountsId) {
      this.amountsId = amountsId;
    }
  }
}
