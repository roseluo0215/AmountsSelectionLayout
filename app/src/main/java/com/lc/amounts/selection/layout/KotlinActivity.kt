package com.lc.amounts.selection.layout

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.kotlin_activity.*

/**
Created by luocheng on 18/7/4.

 */
class KotlinActivity : AppCompatActivity() {

    // 这里面是静态方法
    companion object {
        fun launch(context: Context) {
            val intent = Intent(context, KotlinActivity::class.java)
            if (context !is Activity) {
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            context.startActivity(intent)
        }

        fun launch(context: Context, flag: Int) {
            val intent = Intent(context, KotlinActivity::class.java)
            if (context !is Activity) {
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            intent.putExtra("flag", flag)
            context.startActivity(intent)

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.kotlin_activity)
        val amountsSelectionLayout = findViewById<KotlinAmountsSelectionLayout>(R.id.include_amounts_layout)
        btn_select.setOnClickListener {
            val amounts: String = amountsSelectionLayout.getCurrentAmounts().toString()
            Toast.makeText(this, amounts, Toast.LENGTH_LONG).show()

        }
    }
}