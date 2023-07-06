package com.haovu.freakingmath;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    SeekBar skTime;
    TextView Score,Num1,Num2,KQ, operator_text;
    ImageButton Dung,Sai;
    RelativeLayout lo;
    int score = 0;
    int thoigian;
    Timer timer;
    boolean flag = false;
    int operator = 0;
    ArrayList<Integer> bgColor = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }
    public void init()
    {

        flag = false;
        skTime = findViewById(R.id.skTime);
        Score = findViewById(R.id.Score);
        Num1 = findViewById(R.id.Number1);
        Num2 = findViewById(R.id.Number2);
        KQ = findViewById(R.id.KetQua);
        Dung = findViewById(R.id.True);
        Sai = findViewById(R.id.False);
        lo = findViewById(R.id.RL);
        operator_text = findViewById(R.id.textView3);
        pheptoan();
        score = 0;
        Score.setText(String.valueOf(score));
        thoigian = 100;
        skTime.setMax(thoigian);
        skTime.setProgress(thoigian);
        if(thoigian == 0)
        {
            Lose();
        }
        Dung.setOnClickListener(view -> dung());

        Sai.setOnClickListener(view -> sai());
    }
    public int getRandomMinMax(int min,int max)
    {
        return (int)Math.floor(Math.random() * (max - min) + min);
    }
    public void pheptoan()
    {
        int r1 = getRandomMinMax(1,51);
        Num1.setText(String.valueOf(r1));
        int r2 = getRandomMinMax(1,51);
        Num2.setText(String.valueOf(r2));
        operator = getRandomMinMax(1,4);
        int r3 = 0;
        switch (operator){
            case 1:
                operator_text.setText("+");
                r3 = r1 + r2;
                break;
            case 2:
                operator_text.setText("-");
                r3 = r1 - r2;
                break;
            case 3:
                operator_text.setText("*");
                r3 = r1 * r2;
                break;
            case 4:
                operator_text.setText(":");
                r3 = r1 / r2;
                break;
        }

        double kq = (double) Math.round(getRandomMinMax(r3 - 3,r3 + 3) * 10) / 10;
        KQ.setText(String.valueOf(kq));
    }
    public void dung()
    {
        int So1 = Integer.parseInt(Num1.getText().toString());
        int So2 = Integer.parseInt(Num2.getText().toString());
        double ketqua = Double.parseDouble(KQ.getText().toString());
        switch (operator){
            case 1:
                if(So1 + So2 == ketqua)
                {
                    score += 1;
                    pheptoan();
                    Score.setText(String.valueOf(score));
                    thoigian = 100;
                    runThoiGian();
                }
                else
                {
                    Lose();
                }
                break;
            case 2:
                if(So1 - So2 == ketqua)
                {
                    score += 1;
                    pheptoan();
                    Score.setText(String.valueOf(score));
                    thoigian = 100;
                    runThoiGian();
                }
                else
                {
                    Lose();
                }
                break;
            case 3:
                if(So1 * So2 == ketqua)
                {
                    score += 1;
                    pheptoan();
                    Score.setText(String.valueOf(score));
                    thoigian = 100;
                    runThoiGian();
                }
                else
                {
                    Lose();
                }
                break;
            case 4:
                if(So1 / So2 == ketqua)
                {
                    score += 1;
                    pheptoan();
                    Score.setText(String.valueOf(score));
                    thoigian = 100;
                    runThoiGian();
                }
                else
                {
                    Lose();
                }
                break;
        }
    }
    public void sai()
    {
        int So1 = Integer.parseInt(Num1.getText().toString());
        int So2 = Integer.parseInt(Num2.getText().toString());
        double ketqua = Double.parseDouble(KQ.getText().toString());
        switch (operator){
            case 1:
                if(So1 + So2 != ketqua)
                {
                    score += 1;
                    pheptoan();
                    Score.setText(String.valueOf(score));
                    thoigian = 100;
                    runThoiGian();
                }
                else
                {
                    Lose();
                }
                break;
            case 2:
                if(So1 - So2 != ketqua)
                {
                    score += 1;
                    pheptoan();
                    Score.setText(String.valueOf(score));
                    thoigian = 100;
                    runThoiGian();
                }
                else
                {
                    Lose();
                }
                break;
            case 3:
                if(So1 * So2 != ketqua)
                {
                    score += 1;
                    pheptoan();
                    Score.setText(String.valueOf(score));
                    thoigian = 100;
                    runThoiGian();
                }
                else
                {
                    Lose();
                }
                break;
            case 4:
                if((double)So1 / (double)So2 != ketqua)
                {
                    score += 1;
                    pheptoan();
                    Score.setText(String.valueOf(score));
                    thoigian = 100;
                    runThoiGian();
                }
                else
                {
                    Lose();
                }
                break;
        }
    }
    public void Lose()
    {
        if (timer != null){
            timer.cancel();
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Thông báo");// Đây là phần tiêu đề
        builder.setMessage("Bạn thua rồi. Bạn có muốn chơi tiếp không ?");// Nội dung
        builder.setCancelable(false);
        builder.setPositiveButton("Chơi tiếp", (dialogInterface, i) -> init());
        builder.setNegativeButton("Không", (dialogInterface, i) -> {
            finishAffinity();
            System.exit(0);
        });
        builder.create().show();
    }
    public void runThoiGian()
    {
        if(!flag)
        {
            timer = new Timer();
            runRanDomPhepToan runRanDomPhepToan = new runRanDomPhepToan();
            timer.scheduleAtFixedRate(runRanDomPhepToan,100,100);
            flag = true;
        }

    }
    public class runRanDomPhepToan extends TimerTask
    {

        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(thoigian != 0)
                    {
                        thoigian -= 1;
                        skTime.setProgress(thoigian);
                    }
                    else
                    {
                        Lose();
                    }

                }
            });
        }
    }
}