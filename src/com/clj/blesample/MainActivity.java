
package com.clj.blesample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.clj.TestData;

public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_operate).setOnClickListener(this);
        findViewById(R.id.btn_demo).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_operate:
                TestData.genObject();
//                byte[] data1 = TestData.getData(0);
//                byte[] data2 = TestData.getData(1);
//                TestData.genInputData(data1);
//                TestData.genInputData(data2);
//                TestData.handleInputData();
                startActivity(new Intent(MainActivity.this, OperateActivity.class));
                break;

            case R.id.btn_demo:
                startActivity(new Intent(MainActivity.this, DemoActivity.class));
                break;
        }
    }
}
