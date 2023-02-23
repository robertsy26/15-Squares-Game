package com.example.gridclicker;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

 /*
    Author: Yutaka Roberts
    Date: 2-23-23
 */

public class GameView extends MainActivity{
    private Button[][] buttons;
    private GameModel gameModel;
    private ArrayList<Integer> numbers;

    public GameView(GameModel m){
        buttons = new Button[4][4];
        Button b = findViewById(R.id.restart);
        gameModel = m;
        numbers = new ArrayList<Integer>();

        //Makes ArrayList of numbers 0 - 15
        for (int i = 0; i <= 15; i++){
            numbers.add(i);
        }
    }

    public void addButton(int row, int col, Button b) {
        //Adds given button to buttons 2D array
        buttons[row][col] = b;

        //Creates a random number and assigns the button a number from random index of numbers
        Random random = new Random();
        int num = numbers.remove(random.nextInt(numbers.size()));
        buttons[row][col].setText(Integer.toString((num)));

        //Disables button labeled '0' and saves its position
        if (num == 0) {
            disable(buttons[row][col]);
            gameModel.blankRow = row;
            gameModel.blankCol = col;
        }
    }

    //Makes chosen button invisible and unclickable, then makes it zero
    public void disable(Button b){
        b.setClickable(false);
        b.setVisibility(View.INVISIBLE);
        b.setText("0");

        //Searches for chosen button's position and saves it as new blank button
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                if (b.equals(buttons[i][j])){
                    gameModel.blankRow = i;
                    gameModel.blankCol = j;
                }
            }
        }
    }

    //Makes blank button visible and clickable, then changes blank button's number to chosen number
    public void enable(int row, int col, CharSequence num) {
        buttons[row][col].setClickable(true);
        buttons[row][col].setVisibility(View.VISIBLE);
        buttons[row][col].setText(num);
    }

    //Checks every button to see if it can move
    public boolean legalCheck(Button b){
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                if (buttons[i][j].equals(b))
                    //If chosen button is above, to the right, to the left, or  below,
                    //the button can move
                    if ((i + 1 == gameModel.blankRow && j == gameModel.blankCol) ||
                            (i == gameModel.blankRow && j + 1 == gameModel.blankCol) ||
                            (i == gameModel.blankRow && j - 1 == gameModel.blankCol) ||
                            (i - 1 == gameModel.blankRow && j == gameModel.blankCol)){
                        return true;
                    }
            }
        }
        return false;
    }

    //Checks every button to make sure they are in order
    public void winCheck(){
        int check = 1;
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                //Compares the current number to the make sure its the next correct number
                int textNum = Integer.parseInt(buttons[i][j].getText().toString());
                if (textNum == check) {
                    check++;
                }
                else{
                    //Debug print
                    System.out.println(check);
                    break;
                }

                //Skips the final button as it will be 0 instead of 16
                if (i == 3 && j == 3)
                    endGame();
            }
        }
    }

    public void endGame(){
        //Makes all of the buttons unclickable
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                buttons[i][j].setClickable(false);
            }
        }

        //Reveals the win message
        TextView textView = findViewById(R.id.textView);
        textView.setVisibility(View.VISIBLE);
    }

    void setOnClick(GameController game){
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                buttons[i][j].setOnClickListener(game);
            }
        }
    }
}
