package tw.com.shiaoshia.ex2018010301;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    CheckBox chk1;
    Switch sw;
    ProgressBar pb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pb = (ProgressBar)findViewById(R.id.progressBar);
        sw = (Switch)findViewById(R.id.switch1);
        chk1 = (CheckBox)findViewById(R.id.checkBox);
        chk1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    Toast.makeText(MainActivity.this,"打勾了",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this,"取消了",Toast.LENGTH_SHORT).show();
                }
            }
        });
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b) {
                    pb.setVisibility(View.VISIBLE);
                }
                else {
                    pb.setVisibility(View.INVISIBLE);
                }
            }
        });

    }

    public void click01(View v) {
        RadioGroup rg = (RadioGroup)findViewById(R.id.radioGroup);
        switch (rg.getCheckedRadioButtonId()) {
            case R.id.radioButton:
                Toast.makeText(MainActivity.this,"第一個按鈕",Toast.LENGTH_SHORT).show();
                break;
            case R.id.radioButton2:
                Toast.makeText(MainActivity.this,"第二個按鈕",Toast.LENGTH_SHORT).show();
                break;
            case R.id.radioButton3:
                Toast.makeText(MainActivity.this,"第三個按鈕",Toast.LENGTH_SHORT).show();
                break;
        }
    }

    /*
    public void click02(View v) {
        sw.setChecked(true);
        //此方法在pb未顯示時即停止3秒鐘,然後就INVISIBLE,所以不能這樣寫
        //pb.setVisibility(View.VISIBLE);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //pb.setVisibility(View.INVISIBLE);
        sw.setChecked(false);
    }
    */
    public void click02(View v) {
        pb.setVisibility(View.VISIBLE);

        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        pb.setVisibility(View.INVISIBLE);
                    }
                });
            }
        }.start();
    }
}
