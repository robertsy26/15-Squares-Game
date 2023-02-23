package com.example.gridclicker;

import android.view.View;
import android.widget.Button;

 /*
    Author: Yutaka Roberts
    Date: 2-23-23
 */

public class GameController implements View.OnClickListener {
    GameView gameView;
    GameModel gameModel;

    public GameController(GameModel m, GameView v){
        gameModel = m;
        gameView = v;
    }

    public void changeButton(Button b){
        gameView.enable(gameModel.blankRow(), gameModel.blankCol(), b.getText());
        gameView.disable(b);
    }

    @Override
    public void onClick(View v) {
        Button b = v.findViewById(v.getId());
        //Checks if the chosen button is allowed to move before checking if the player won
        if (gameView.legalCheck(b))
            changeButton(b);
        gameView.winCheck();
    }
}
