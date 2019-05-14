package com.lc.amounts.selection.layout;


import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author : lc
 *         date : 19/5/9
 */
public class ScopeSelectLayout extends LinearLayout implements View.OnClickListener {

  public ScopeSelectLayout(Context context) {
    super(context);
  }

  public ScopeSelectLayout(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public static ScopeSelectLayout newInstance(ViewGroup viewGroup) {
    return (ScopeSelectLayout) LayoutInflater.from(viewGroup.getContext())
        .inflate(R.layout.scope_select_layout, viewGroup, false);
  }

  private List<TextView> mScopeTextViewList = new ArrayList<>(4);
  private DistanceClickListener mListener;

  @Override
  protected void onFinishInflate() {
    super.onFinishInflate();
    TextView half = findViewById(R.id.half);
    TextView one = findViewById(R.id.one);
    TextView three = findViewById(R.id.three);
    TextView five = findViewById(R.id.five);

    mScopeTextViewList.add(half);
    mScopeTextViewList.add(one);
    mScopeTextViewList.add(three);
    mScopeTextViewList.add(five);

    half.setOnClickListener(this);
    one.setOnClickListener(this);
    three.setOnClickListener(this);
    five.setOnClickListener(this);

    setScopeView(0.5);

  }

  public void setScopeView(double currentValue) {
    int index = convertIndex(currentValue);
    for (int i = 0; i < mScopeTextViewList.size(); i++) {
      mScopeTextViewList.get(i).setSelected(index == i);
    }
  }

  public int convertIndex(double value) {
    int i = (int) value;
    int index;
    switch (i) {
      case 0:
        index = 0;
        break;
      case 1:
        index = 1;
        break;
      case 3:
        index = 2;
        break;
      case 5:
        index = 3;
        break;
      default:
        index = 0;
        break;
    }
    return index;
  }

  @Override
  public void onClick(View v) {

    double currentDistance;
    switch (v.getId()) {

      case R.id.half:
        currentDistance = 0.5d;
        break;

      case R.id.one:
        currentDistance = 1.0d;
        break;

      case R.id.three:
        currentDistance = 3.0d;
        break;

      case R.id.five:
        currentDistance = 5.0d;
        break;

      default:
        currentDistance = 0.5d;
        break;

    }

    setScopeView(currentDistance);
    if (mListener != null) {
      mListener.distanceClick(currentDistance);
    }

  }

  public interface DistanceClickListener {

    void distanceClick(double currentDistance);
  }

  public void setDistanceClickListener(DistanceClickListener listener) {
    mListener = listener;
  }
}
