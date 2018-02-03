package com.caixnet.forone;

import android.os.Bundle;
import android.widget.TextView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
/**
 * Created by caixnet on 12/22/17.
 */


public class ForOneKeyboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //ChaiCode cai = new ChaiCode();
        setContentView(R.layout.activity_main);

        final TextView tv = (TextView) findViewById(R.id.textone);
         tv.setText("ForOne Chai Code");
        //Log.e("CAI","ChaiCode:"+cai.get("13"));
        final ForOne oneView = (ForOne) findViewById(R.id.forone);
//        lockView.setmForOneListener(new ForOne.OnForOneListener() {
        oneView.setForOneListener(new ForOne.OnForOneListener() {
            @Override
            public void  onOneHitted(int index) {
                Log.e("CAI", index + "");

            }

            @Override
            public void onForOneSuccess(String password) {
                Log.e("CAI", password);
            }

            @Override
            public String onGetKey(){
                tv.setText("ForOne Chai Code: "+oneView.onGetKey());
                return oneView.onGetKey();
            };

/*
            @Override
            public void onForOneFail() {
                Log.e("CAI","erro");
            }
*/
        });


    }
}
