package com.example.gridclicker;

import android.widget.Button;

import java.util.Random;

 /*
    Author: Yutaka Roberts
    Date: 2-23-23
 */

public class GameModel {
    int blankRow;
    int blankCol;

    public GameModel(){
        blankRow = 0;
        blankCol = 0;
    }

    public int blankRow(){
        return blankRow;
    }

    public int blankCol(){
        return blankCol;
    }

}
