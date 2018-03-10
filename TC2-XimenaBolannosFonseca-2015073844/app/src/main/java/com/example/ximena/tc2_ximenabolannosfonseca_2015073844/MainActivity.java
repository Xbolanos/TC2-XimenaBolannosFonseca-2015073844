package com.example.ximena.tc2_ximenabolannosfonseca_2015073844;


import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;


public class MainActivity extends AppCompatActivity {
    private int matriz[][] = new int[17][15];
    private ArrayList<Integer> colors=new ArrayList<>(asList(R.drawable.taz,R.drawable.tc,R.drawable.tam,R.drawable.tm,R.drawable.tn,R.drawable.tr,R.drawable.tv));
    private boolean figure=false;
    private List<Integer> positions= new ArrayList<>();
    private int colorf;
    private  int typefigure;
    private List<ImageView> list=new ArrayList<>();
    private  int position=0;
    private  int score=0;
    private  boolean gameover=false;
    private RelativeLayout rlGO;
    private MediaPlayer mPlayer;
    public void initialState(){
        GridLayout gridLayout=findViewById(R.id.gridLayout);
        for(int i=0;i<gridLayout.getChildCount();i++) {
            ImageView imview=(ImageView) gridLayout.getChildAt(i);
            imview.setImageResource(R.drawable.tg);
        }
        for(int i=0;i<matriz.length;i++){
            for(int j=0;j<matriz[i].length;j++){
                matriz[i][j]=-1;
            }
        }
    }

    public void goBack(View view){
        super.onBackPressed();
    }
    public List<ImageView> getRandomFigure(int position, GridLayout gridLayout){


            int rand = (int) (Math.random() * 7);

            ImageView imview;
            ImageView imview1;
            ImageView imview2;
            ImageView imview3;
            int color=0;
            int pos;
            int pos1;
            int pos2;
            int pos3;
            switch (rand) {
                case 0:
                    pos=position;
                    pos1=position+1;
                    pos2=position+15;
                    pos3=position+16;
                    color=R.drawable.taz;
                    break;
                case 1:
                    pos=position;
                    pos1=position+15;
                    pos2=position+30;
                    pos3=position+45;
                    color=R.drawable.tc;

                    break;
                case 2:
                    pos=position;
                    pos1=position+15;
                    pos2=position+16;
                    pos3=position+17;
                    color=R.drawable.tam;



                    break;
                case 3:
                    pos=position;
                    pos1=position+1;
                    pos2=position+2;
                    pos3=position+16;
                    color=R.drawable.tm;

                    break;
                case 4:
                    pos=position;
                    pos1=position+15;
                    pos2=position+16;
                    pos3=position+31;
                    color=R.drawable.tn;

                    break;
                case 5:

                    pos=position+1;
                    pos1=position+15;
                    pos2=position+16;
                    pos3=position+30;
                    color=R.drawable.tr;

                    break;
                default:
                    pos=position;
                    pos1=position+1;
                    pos2=position+16;
                    pos3=position+31;
                    color=R.drawable.tv;

                    break;

            }
            positions.add(pos);
            positions.add(pos1);
            positions.add(pos2);
            positions.add(pos3);
            gameover=setGameOver((ArrayList<Integer>) positions);
            if(!setGameOver((ArrayList<Integer>) positions)) {
                imview=(ImageView) gridLayout.getChildAt(pos);
                imview1=(ImageView) gridLayout.getChildAt(pos1);
                imview2=(ImageView) gridLayout.getChildAt(pos2);
                imview3=(ImageView) gridLayout.getChildAt(pos3);





                setMatriz(pos, pos1, pos2, pos3, rand);


                colorf = color;

                imview.setImageResource(color);
                imview1.setImageResource(color);
                imview2.setImageResource(color);
                imview3.setImageResource(color);

                list.add(imview);
                list.add(imview1);
                list.add(imview2);
                list.add(imview3);

                typefigure = rand;
                position = 0;
            }
            else{
                Button bl=findViewById(R.id.btnleft);
                Button br=findViewById(R.id.btnright);
                Button bt=findViewById(R.id.btnturn);
                Button bd=findViewById(R.id.btndown);
                bl.setEnabled(false);
                br.setEnabled(false);
                bt.setEnabled(false);
                bd.setEnabled(false);
                mPlayer.stop();
                rlGO.setAlpha(.9f);
                rlGO.setVisibility(View.VISIBLE);
            }
            return list;


    }

    public void moveDown(View view){
        if(positions.size()!=0){

            if(positions.get(0)/15<16&&positions.get(1)/15<16&& positions.get(2)/15<16&&positions.get(3)/16<15&&colision(positions.get(0)+15,positions.get(1)+15, positions.get(2)+15, positions.get(3)+15)){

                setMatriz(positions.get(0),positions.get(1),positions.get(2),positions.get(3),-1);

                int pos=positions.get(0)+15;
                int pos1=positions.get(1)+15;
                int pos2=positions.get(2)+15;
                int pos3=positions.get(3)+15;


                rePosition(pos,pos1,pos2,pos3);
                final MediaPlayer ring= MediaPlayer.create(MainActivity.this,R.raw.bip);
                ring.start();
                ring.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        ring.release();
                    }
                });

                ring.setVolume(0, 50);
            }else{
                figure=false;
                ArrayList<Integer> posts= new ArrayList<>(asList(positions.get(0),positions.get(1),positions.get(2),positions.get(3)));
                checkRow(posts);
                positions= new ArrayList<>();
                list=new ArrayList<>();
            }
        }


    }
    public void moveRight(View view){
        if(positions.size()!=0) {
            if (positions.get(0) % 15 < 14 && positions.get(1) % 15 < 14 && positions.get(2) % 15 < 14 && positions.get(3) % 15 < 14 && colision(positions.get(0) + 1, positions.get(1) + 1, positions.get(2) + 1, positions.get(3) + 1)) {
                setMatriz(positions.get(0), positions.get(1), positions.get(2), positions.get(3), -1);
                int pos = positions.get(0) + 1;
                int pos1 = positions.get(1) + 1;
                int pos2 = positions.get(2) + 1;
                int pos3 = positions.get(3) + 1;

                rePosition(pos, pos1, pos2, pos3);
                final MediaPlayer ring = MediaPlayer.create(MainActivity.this, R.raw.bip);
                ring.start();
                ring.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        ring.release();
                    }
                });
                ring.setVolume(0, 50);
            }
        }


    }
    public void moveLeft(View view){
        if(positions.size()!=0) {
            if (positions.get(0) % 15 > 0 && positions.get(1) % 15 > 0 && positions.get(2) % 15 > 0 && positions.get(3) % 15 > 0 && colision(positions.get(0) - 1, positions.get(1) - 1, positions.get(2) - 1, positions.get(3) - 1)) {
                setMatriz(positions.get(0), positions.get(1), positions.get(2), positions.get(3), -1);
                int pos = positions.get(0) - 1;
                int pos1 = positions.get(1) - 1;
                int pos2 = positions.get(2) - 1;
                int pos3 = positions.get(3) - 1;
                rePosition(pos, pos1, pos2, pos3);
                final MediaPlayer ring = MediaPlayer.create(MainActivity.this, R.raw.bip);
                ring.start();
                ring.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        ring.release();
                    }
                });
                ring.setVolume(0, 50);
            }
        }



    }
    public boolean colision(int pos,int pos1, int pos2, int pos3){
        ArrayList<Integer> values=new ArrayList<>(asList(pos,pos1,pos2,pos3));

        for(int i=0;i<4;i++) {
            System.out.println(values.get(i));
            System.out.println(positions.toString());
            if (!positions.contains(values.get(i))) {
                if (matriz[(values.get(i)) / 15][(values.get(i)) % 15] != -1||values.get(i)/15==17) {
                    return false;
                }
            }
        }

            return true;

    }

    public void turn(View view){
        if(positions.size()!=0) {

            switch (typefigure) {
                case 0:
                    break;
                case 1:
                    setMatriz(positions.get(0), positions.get(1), positions.get(2), positions.get(3), -1);
                    turnI();
                    break;
                case 2:
                    setMatriz(positions.get(0), positions.get(1), positions.get(2), positions.get(3), -1);
                    turnL();
                    break;
                case 3:
                    setMatriz(positions.get(0), positions.get(1), positions.get(2), positions.get(3), -1);
                    turnT();
                    break;
                case 4:
                    setMatriz(positions.get(0), positions.get(1), positions.get(2), positions.get(3), -1);
                    turnZInvert();
                    break;
                case 5:
                    setMatriz(positions.get(0), positions.get(1), positions.get(2), positions.get(3), -1);
                    turnZ();
                    break;
                default:
                    setMatriz(positions.get(0), positions.get(1), positions.get(2), positions.get(3), -1);
                    turnLInvertida();
                    break;

            }
        }


    }
    public void turnI(){


        int pos;
        int pos1;
        int pos2;
        int pos3;
        switch(position) {
            case 0:
                    pos = positions.get(2) - 2;
                    pos1 = positions.get(2) - 1;
                    pos2 = positions.get(2);
                    pos3 = positions.get(2) + 1;
                    position = 1;

                break;
            default:
                pos=positions.get(2)-30;
                pos1=positions.get(2)-15;
                pos2=positions.get(2);
                pos3=positions.get(2)+15;
                position=0;
                break;

        }

            rePosition(pos, pos1, pos2, pos3);



    }
    public void turnZ(){


        int pos;
        int pos1;
        int pos2;
        int pos3;
        switch(position) {
            case 0:
                pos=positions.get(2)-16;
                pos1=positions.get(2)-17;
                pos2=positions.get(2);
                pos3=positions.get(2)-1;
                position=1;

                break;
            default:
                pos=positions.get(2)-15;
                pos1=positions.get(2)-1;
                pos2=positions.get(2);
                pos3=positions.get(2)+14;
                position=0;
                break;

        }
        rePosition(pos,pos1,pos2,pos3);


    }

    public void turnZInvert(){


        int pos;
        int pos1;
        int pos2;
        int pos3;
        switch(position) {
            case 0:
                pos=positions.get(2)-14;
                pos1=positions.get(2)-15;
                pos2=positions.get(2)-1;
                pos3=positions.get(2);
                position=1;

                break;
            default:
                pos=positions.get(2)-15;
                pos1=positions.get(2);
                pos2=positions.get(2)+1;
                pos3=positions.get(2)+16;
                position=0;
                break;

        }
        rePosition(pos,pos1,pos2,pos3);


    }
    public void turnT(){


        int pos;
        int pos1;
        int pos2;
        int pos3;
        switch(position) {
            case 0:
                pos=positions.get(3)-15;
                pos1=positions.get(3)-1;
                pos2=positions.get(3)+15;
                pos3=positions.get(3);
                position=1;

                break;
            case 1:
                pos=positions.get(3)-1;
                pos1=positions.get(3)-15;
                pos2=positions.get(3)+1;
                pos3=positions.get(3);
                position=2;
                break;
            case 2:
                pos=positions.get(3)-15;
                pos1=positions.get(3)+1;
                pos2=positions.get(3)+15;
                pos3=positions.get(3);
                position=3;
                break;

            default:
                pos=positions.get(3)-1;
                pos1=positions.get(3)+1;
                pos2=positions.get(3)+15;
                pos3=positions.get(3);
                position=0;
                break;

        }
        rePosition(pos,pos1,pos2,pos3);


    }
    public void turnL(){


        int pos;
        int pos1;
        int pos2;
        int pos3;
        switch(position) {
            case 0:
                pos=positions.get(2)-15;
                pos1=positions.get(2)-14;
                pos2=positions.get(2);
                pos3=positions.get(2)+15;



                position=1;

                break;
            case 1:
                pos=positions.get(2)-1;
                pos1=positions.get(2)+1;
                pos2=positions.get(2);
                pos3=positions.get(2)+16;

                position=2;
                break;
            case 2:
                pos=positions.get(2)-15;
                pos1=positions.get(2)+14;
                pos2=positions.get(2);
                pos3=positions.get(2)+15;
                position=3;
                break;

            default:
                pos=positions.get(2)-16;
                pos1=positions.get(2)-1;
                pos2=positions.get(2);
                pos3=positions.get(2)+1;
                position=0;
                break;

        }
        rePosition(pos,pos1,pos2,pos3);


    }
    public void turnLInvertida(){
        int pos;
        int pos1;
        int pos2;
        int pos3;
        switch(position) {
            case 0:
                pos=positions.get(2)-14;
                pos1=positions.get(2)-1;
                pos2=positions.get(2);
                pos3=positions.get(2)+1;
                position=1;
                break;
            case 1:
                pos=positions.get(2)-15;
                pos1=positions.get(2)+15;
                pos2=positions.get(2);
                pos3=positions.get(2)+16;
                position=2;
                break;
            case 2:
                pos=positions.get(2)-1;
                pos1=positions.get(2)+1;
                pos2=positions.get(2);
                pos3=positions.get(2)+14;
                position=3;
                break;

            default:
                pos=positions.get(2)-16;
                pos1=positions.get(2)-15;
                pos2=positions.get(2);
                pos3=positions.get(2)+15;
                position=0;
                break;

        }
        rePosition(pos,pos1,pos2,pos3);


    }


    public void rePosition(int pos,int pos1,int pos2,int pos3){
        if(colision(pos,pos1, pos2, pos3)) {
            GridLayout gridLayout = findViewById(R.id.gridLayout);
            ImageView imv = list.get(0);
            ImageView imv1 = list.get(1);
            ImageView imv2 = list.get(2);
            ImageView imv3 = list.get(3);


            imv.setImageResource(R.drawable.tg);
            imv1.setImageResource(R.drawable.tg);
            imv2.setImageResource(R.drawable.tg);
            imv3.setImageResource(R.drawable.tg);

            imv = (ImageView) gridLayout.getChildAt(pos);
            imv1 = (ImageView) gridLayout.getChildAt(pos1);
            imv2 = (ImageView) gridLayout.getChildAt(pos2);
            imv3 = (ImageView) gridLayout.getChildAt(pos3);

            imv.setImageResource(colorf);
            imv1.setImageResource(colorf);
            imv2.setImageResource(colorf);
            imv3.setImageResource(colorf);

            list.set(0, imv);
            list.set(1, imv1);
            list.set(2, imv2);
            list.set(3, imv3);

            positions.set(0, pos);
            positions.set(1, pos1);
            positions.set(2, pos2);
            positions.set(3, pos3);

            setMatriz(pos, pos1, pos2, pos3, typefigure);



        }
    }

    public void printMatriz(){
        String fila="\n";
        for(int i=0;i<matriz.length;i++){
            for(int j=0;j<matriz[i].length;j++){
                fila= fila+" "+matriz[i][j];
            }
            System.out.println(fila);
            fila="";
        }


    }
    public void setMatriz(int pos,int pos1, int pos2, int pos3, int value){
        matriz[pos/15][pos%15]=value;
        matriz[pos1/15][pos1%15]=value;
        matriz[pos2/15][pos2%15]=value;
        matriz[pos3/15][pos3%15]=value;
    }
    public void reorderMatrix(int row){

        GridLayout gridLayout=findViewById(R.id.gridLayout);
        int[] auxrow;
        int[] replace={-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
        for(int i=0; i< row+1;i++){
            auxrow=matriz[i];
            matriz[i]=replace;
            replace=auxrow;
        }
        printMatriz();

        for(int i=0;i<gridLayout.getChildCount();i++){
            ImageView imv= (ImageView) gridLayout.getChildAt(i);
            if(matriz[i / 15][i % 15]==-1){
                imv.setImageResource(R.drawable.tg);
            }else {
                imv.setImageResource(colors.get(matriz[i / 15][i % 15]));
            }
        }



    }
    public void checkRow(ArrayList<Integer> posts){
        for(int i=0; i< posts.size();i++){
            Log.d("fila", String.valueOf(posts.get(i)/15));
            Log.d("fullrow:", String.valueOf(fullRow(posts.get(i)/15)));
            if (fullRow(posts.get(i)/15)) {

                deleteRow(posts.get(i)/15);
                reorderMatrix(posts.get(i)/15);
                score+=100;
                TextView textView=findViewById(R.id.txtScore);
                textView.setText(String.valueOf(score));
            }
        }

    }
    public boolean fullRow(int row){
        for(int i=0; i< matriz[row].length;i++){

            if(matriz[row][i]==-1){
                return false;
            }
        }
        return true;
    }

    public void deleteRow(int row){
        Log.d("delete row:", "entre");
        GridLayout gridLayout=findViewById(R.id.gridLayout);
        for(int i=0; i< matriz[row].length;i++){
            System.out.println("Row:"+row);
            System.out.println("Primera:"+matriz[row][i]);
            matriz[row][i]=-1;
            System.out.println("Segunda:"+matriz[row][i]);
            printMatriz();
            Log.d("position Grid:", String.valueOf(row*15+i));
            ImageView imv = (ImageView) gridLayout.getChildAt(row*15+i);
            imv.setImageResource(R.drawable.tg);
        }

    }
    public boolean setGameOver(ArrayList<Integer> posits){
        for(int i=0;i<4;i++){
            if(matriz[posits.get(i)/15][posits.get(i)%15]!=-1){
                return true;
            }
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialState();
        rlGO=findViewById(R.id.rlGameOver);
        rlGO.setVisibility(View.INVISIBLE);
        mPlayer = MediaPlayer.create(MainActivity.this,R.raw.ritari);
        mPlayer.setVolume(0,100);
        mPlayer.setLooping(true);
        mPlayer.start();
        final TextView textView=findViewById(R.id.txtScore);
        textView.setText("0");
        new Thread() {
            GridLayout gridLayout=findViewById(R.id.gridLayout);
            List<ImageView> list;
            public void run() {
                while (!gameover) {
                    try {
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {

                                    if(figure==false){
                                        list=getRandomFigure(7, gridLayout);
                                        figure=true;


                                    }else{
                                        moveDown(findViewById(android.R.id.content));


                                    }
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


