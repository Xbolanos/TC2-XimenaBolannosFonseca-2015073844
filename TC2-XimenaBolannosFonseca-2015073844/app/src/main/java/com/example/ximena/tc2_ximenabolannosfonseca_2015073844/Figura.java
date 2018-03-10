package com.example.ximena.tc2_ximenabolannosfonseca_2015073844;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ximena on 7/3/2018.
 */

public class Figura {
    protected List<Integer> positions= new ArrayList<>();
    protected int color;
    protected int pos=positions.get(0)-1;
    protected int pos1=positions.get(1)-1;
    protected int pos2=positions.get(2)-1;
    protected int pos3=positions.get(3)-1;
    public void girar(){};
    public void moveLeft(){
        pos=positions.get(0)-1;
        pos1=positions.get(1)-1;
        pos2=positions.get(2)-1;
        pos3=positions.get(3)-1;
    };
    public void moveRight(){
        pos=positions.get(0)+1;
        pos1=positions.get(1)+1;
        pos2=positions.get(2)+1;
        pos3=positions.get(3)+1;
    };
    public void moveDown(){
        if(pos/15<15 && pos1/15<15 && pos2/15<15 && pos3/15<15) {
            pos = positions.get(0) + 15;
            pos1 = positions.get(1) + 15;
            pos2 = positions.get(2) + 15;
            pos3 = positions.get(3) + 15;
        }

    };



}
