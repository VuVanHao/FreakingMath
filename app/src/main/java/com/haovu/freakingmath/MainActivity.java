package com.haovu.freakingmath;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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
    TextView Score,Num1,Num2,KQ;
    ImageButton Dung,Sai;
    RelativeLayout lo;
    int score = 0;
    int thoigian;
    Timer timer;
    boolean flag = false;
    ArrayList<Integer> bgColor = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }
    public void init()
    {
        skTime = (SeekBar) findViewById(R.id.skTime);
        Score = (TextView) findViewById(R.id.Score);
        Num1 = (TextView) findViewById(R.id.Number1);
        Num2 = (TextView) findViewById(R.id.Number2);
        KQ = (TextView) findViewById(R.id.KetQua);
        Dung = (ImageButton) findViewById(R.id.True);
        Sai = (ImageButton) findViewById(R.id.False);
        lo = (RelativeLayout) findViewById(R.id.RL);
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
        Dung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dung();
            }
        });

        Sai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sai();
            }
        });
    }
    public int getRandomMinMax(int min,int max)
    {
        return (int)Math.floor(Math.random() * (max - min) + min);
    }
    public void pheptoan()
    {
        int r1 = getRandomMinMax(0,51);
        Num1.setText(String.valueOf(r1));
        int r2 = getRandomMinMax(0,51);
        Num2.setText(String.valueOf(r2));

        int r3 = r1 + r2;
        int kq = getRandomMinMax(r3 - 3,r3 + 3);
        KQ.setText(String.valueOf(kq));
    }
    public void dung()
    {
        int So1 = Integer.parseInt(Num1.getText().toString());
        int So2 = Integer.parseInt(Num2.getText().toString());
        int ketqua = Integer.parseInt(KQ.getText().toString());
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
    }
    public void sai()
    {
        int So1 = Integer.parseInt(Num1.getText().toString());
        int So2 = Integer.parseInt(Num2.getText().toString());
        int ketqua = Integer.parseInt(KQ.getText().toString());
        if(So1 + So2 != ketqua)
        {
            score += 1;
            pheptoan();
            Score.setText(String.valueOf(score));
            thoigian = 100;
            runThoiGian(); // th???i gian b??? ???? khi b???m ????ng.

        }
        else
        {
            Lose();
        }
    }
    public void Lose()
    {
        timer.cancel();
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Th??ng b??o");// ????y l?? ph???n ti??u ?????
        builder.setMessage("B???n thua r???i. B???n c?? mu???n ch??i ti???p kh??ng ?");// N???i dung
        builder.setCancelable(false);
        //Positive l?? ph??m b??n ph???i
        builder.setPositiveButton("Ch??i ti???p", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                init();
            }
        });
        ////Negative l?? ph??m b??n tr??i.
        builder.setNegativeButton("Kh??ng", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finishAffinity();
                System.exit(0);
            }
        });
        builder.create().show();
        // B??n trong l?? h??nh ?????ng s??? th???c ki???n khi m??nh b???m ph??m.
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