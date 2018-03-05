package com.example.ximena.tc2_ximenabolannosfonseca_2015073844;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridLayout;
import android.widget.ImageView;



public class MainActivity extends AppCompatActivity {

    public void initialState(){
        GridLayout gridLayout=findViewById(R.id.gridLayout);
        for(int i=0;i<gridLayout.getChildCount();i++) {
            ImageView imview=(ImageView) gridLayout.getChildAt(i);
            imview.setImageResource(R.drawable.tg);
        }
    }
    public void getRandomFigure(int position, GridLayout gridLayout){
        int rand = (int) (Math.random() * 7);

        ImageView imview;
        ImageView imview1;
        ImageView imview2;
        ImageView imview3;
        switch (rand) {
            case 0:
                imview=(ImageView) gridLayout.getChildAt(position);
                imview1=(ImageView) gridLayout.getChildAt(position+1);
                imview2=(ImageView) gridLayout.getChildAt(position+15);
                imview3=(ImageView) gridLayout.getChildAt(position+16);
                imview.setImageResource(R.drawable.taz);
                imview1.setImageResource(R.drawable.taz);
                imview2.setImageResource(R.drawable.taz);
                imview3.setImageResource(R.drawable.taz);

                break;
            case 1:
                imview=(ImageView) gridLayout.getChildAt(position);
                imview1=(ImageView) gridLayout.getChildAt(position+15);
                imview2=(ImageView) gridLayout.getChildAt(position+30);
                imview3=(ImageView) gridLayout.getChildAt(position+45);
                imview.setImageResource(R.drawable.tc);
                imview1.setImageResource(R.drawable.tc);
                imview2.setImageResource(R.drawable.tc);
                imview3.setImageResource(R.drawable.tc);
                break;
            case 2:
                imview=(ImageView) gridLayout.getChildAt(position);
                imview1=(ImageView) gridLayout.getChildAt(position+15);
                imview2=(ImageView) gridLayout.getChildAt(position+16);
                imview3=(ImageView) gridLayout.getChildAt(position+17);
                imview.setImageResource(R.drawable.tam);
                imview1.setImageResource(R.drawable.tam);
                imview2.setImageResource(R.drawable.tam);
                imview3.setImageResource(R.drawable.tam);

                break;
            case 3:
                imview=(ImageView) gridLayout.getChildAt(position);
                imview1=(ImageView) gridLayout.getChildAt(position+1);
                imview2=(ImageView) gridLayout.getChildAt(position+2);
                imview3=(ImageView) gridLayout.getChildAt(position+16);
                imview.setImageResource(R.drawable.tm);
                imview1.setImageResource(R.drawable.tm);
                imview2.setImageResource(R.drawable.tm);
                imview3.setImageResource(R.drawable.tm);
                break;
            case 4:
                imview=(ImageView) gridLayout.getChildAt(position);
                imview1=(ImageView) gridLayout.getChildAt(position+15);
                imview2=(ImageView) gridLayout.getChildAt(position+16);
                imview3=(ImageView) gridLayout.getChildAt(position+31);
                imview.setImageResource(R.drawable.tn);
                imview1.setImageResource(R.drawable.tn);
                imview2.setImageResource(R.drawable.tn);
                imview3.setImageResource(R.drawable.tn);
                break;
            case 5:

                imview=(ImageView) gridLayout.getChildAt(position+1);
                imview1=(ImageView) gridLayout.getChildAt(position+15);
                imview2=(ImageView) gridLayout.getChildAt(position+16);
                imview3=(ImageView) gridLayout.getChildAt(position+30);
                imview.setImageResource(R.drawable.tr);
                imview1.setImageResource(R.drawable.tr);
                imview2.setImageResource(R.drawable.tr);
                imview3.setImageResource(R.drawable.tr);
                break;
            default:
                imview=(ImageView) gridLayout.getChildAt(position);
                imview1=(ImageView) gridLayout.getChildAt(position+1);
                imview2=(ImageView) gridLayout.getChildAt(position+16);
                imview3=(ImageView) gridLayout.getChildAt(position+31);
                imview.setImageResource(R.drawable.tv);
                imview1.setImageResource(R.drawable.tv);
                imview2.setImageResource(R.drawable.tv);
                imview3.setImageResource(R.drawable.tv);
                break;

        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialState();

        new Thread() {
            GridLayout gridLayout=findViewById(R.id.gridLayout);
            ImageView imview;
            int i=0;
            public void run() {
                while (true) {
                    try {
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {


                                    getRandomFigure(i, gridLayout);
                                    i++;


                            }
                        });
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }


            }
        }.start();




    }




}


