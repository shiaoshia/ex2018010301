package tw.com.shiaoshia.ex2018010301;
/*
    RatingBar 大的用來評分,用戶用小的顯示,0~5(一次+0.5);
    RatingBar 自制;
    ProgressBar(Horizontal) 0~10(一次+1);

 */
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    CheckBox chk1;
    Switch sw;
    ProgressBar pb,pb2;
    SeekBar sb;
    TextView tv;
    int pi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView)findViewById(R.id.textView);
        sb = (SeekBar)findViewById(R.id.seekBar);
        pb = (ProgressBar)findViewById(R.id.progressBar);
        pb2 = (ProgressBar)findViewById(R.id.progressBar2);
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

        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                tv.setText(String.valueOf(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

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
        Toast.makeText(MainActivity.this,String.valueOf(sb.getProgress()),Toast.LENGTH_SHORT).show();
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

    public void click04(View v) {

        pb2.setProgress(pb2.getProgress()+10);
    }

    public void click03(View v) {
        pb2.setProgress(pb2.getProgress()-10);
    }

    public void goClick01(View v) {
        new Thread(){
         @Override
         public void run() {
             super.run();

             for(pi=0;pi<=100;pi++) {
                 runOnUiThread(new Runnable() {
                     @Override
                     public void run() {
                         pb2.setProgress(pi);
                     }
                 });
                 try {
                     Thread.sleep(100);
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
             }

         }
        }.start();
    }

}
