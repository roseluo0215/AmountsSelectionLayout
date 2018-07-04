package com.lc.amounts.selection.layout

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView


class KotlinAmountsSelectionLayout : LinearLayout, View.OnClickListener {

    private val list = ArrayList<RechargeAmountsState>(6)
    private var currentAmounts = 10

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

     fun getCurrentAmounts(): Int {
        return currentAmounts
    }

    override fun onFinishInflate() {
        super.onFinishInflate()

        val tvTen = findViewById<TextView>(R.id.tv_ten)
        val tvTwenty = findViewById<TextView>(R.id.tv_twenty)
        val tvFifty = findViewById<TextView>(R.id.tv_fifty)
        val tvOneHundred = findViewById<TextView>(R.id.tv_one_hundred)
        val tvTwoHundred = findViewById<TextView>(R.id.tv_two_hundred)
        val tvFiveHundred = findViewById<TextView>(R.id.tv_five_hundred)
        tvTen.setOnClickListener(this)
        tvTwenty.setOnClickListener(this)
        tvFifty.setOnClickListener(this)
        tvOneHundred.setOnClickListener(this)
        tvTwoHundred.setOnClickListener(this)
        tvFiveHundred.setOnClickListener(this)
        //给当前的各个view设置初始化状态，isSelect 为true代表选中
        changeViewState(tvTen, true, 10, R.id.tv_ten)
        changeViewState(tvTwenty, false, 20, R.id.tv_twenty)
        changeViewState(tvFifty, false, 50, R.id.tv_fifty)
        changeViewState(tvOneHundred, false, 100, R.id.tv_one_hundred)
        changeViewState(tvTwoHundred, false, 200, R.id.tv_two_hundred)
        changeViewState(tvFiveHundred, false, 500, R.id.tv_five_hundred)
        setCurrentState(-1)// 初始化view
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.tv_ten -> setCurrentState(R.id.tv_ten)
            R.id.tv_twenty -> setCurrentState(R.id.tv_twenty)
            R.id.tv_fifty -> setCurrentState(R.id.tv_fifty)
            R.id.tv_one_hundred -> setCurrentState(R.id.tv_one_hundred)
            R.id.tv_two_hundred -> setCurrentState(R.id.tv_two_hundred)
            R.id.tv_five_hundred -> setCurrentState(R.id.tv_five_hundred)
        }
    }

    private fun changeViewState(view: TextView, isSelect: Boolean, amounts: Int, amountsId: Int) {
        val rechargeAmounts = RechargeAmountsState()
        rechargeAmounts.textView = view
        rechargeAmounts.amounts = amounts
        rechargeAmounts.amountsId = amountsId
        rechargeAmounts.isSelect = isSelect
        list.add(rechargeAmounts)
    }

    private fun setCurrentState(resId: Int) {
        for (i in list.indices) {
            val rechargeAmountsState = list[i]
            // 判断当前点击选中的view的id是选中或者被选中
            if (resId == rechargeAmountsState.amountsId || rechargeAmountsState.isSelect) {
                currentAmounts = rechargeAmountsState.amounts// 将选中的item的值记录下来
                rechargeAmountsState.isSelect = false//将选中的这个是否选中变成false，以防下次选中其他的item的时候这个item的状态可以改变
                setAmountsState(rechargeAmountsState, R.color.color_ffffff,
                        R.drawable.text_border_green_solid_bg)
            } else {
                setAmountsState(rechargeAmountsState, R.color.color_666666,
                        R.drawable.text_border_green_bg)
            }

        }
    }

    // 设置字体颜色和背景
    private fun setAmountsState(rechargeAmountsState: RechargeAmountsState, colorResId: Int,
                                backgroundResId: Int) {
        rechargeAmountsState.textView!!
                .setTextColor(context.resources.getColor(colorResId))
        rechargeAmountsState.textView!!.setBackgroundResource(backgroundResId)

    }

    private inner class RechargeAmountsState {
        var textView: TextView? = null
        var isSelect: Boolean = false
        var amounts: Int = 0
        var amountsId: Int = 0
    }
}
