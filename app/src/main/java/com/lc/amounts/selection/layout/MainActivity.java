package com.lc.amounts.selection.layout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    final AmountsSelectionLayout amounts = findViewById(R.id.include_amounts_layout);
    findViewById(R.id.btn_select).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
//        String currentAmounts = String.valueOf(amounts.getCurrentAmounts());
//        Toast.makeText(getApplicationContext(), currentAmounts, Toast.LENGTH_LONG).show();
        KotlinActivity.Companion.launch(v.getContext());
//        KotlinActivity.Companion.launch(v.getContext(),1);
      }
    });
  }

}
