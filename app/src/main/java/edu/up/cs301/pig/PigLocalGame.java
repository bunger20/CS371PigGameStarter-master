package edu.up.cs301.pig;

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.LocalGame;
import edu.up.cs301.game.actionMsg.GameAction;
import edu.up.cs301.game.infoMsg.GameState;

import android.util.Log;

import java.util.Random;

/**
 * class PigLocalGame controls the play of the game
 *
 * @author Andrew M. Nuxoll, modified by Steven R. Vegdahl
 * @version February 2016
 */
public class PigLocalGame extends LocalGame {

    PigGameState state;

    /**
     * This ctor creates a new game state
     */
    public PigLocalGame() {
        state = new PigGameState();
    }

    /**
     * can the player with the given id take an action right now?
     */
    @Override
    protected boolean canMove(int playerIdx) {
        if(playerIdx == state.getPlayerId()){
            return true;
        }
        return false;
    }

    /**
     * This method is called when a new action arrives from a player
     *
     * @return true if the action was taken or false if the action was invalid/illegal.
     */
    @Override
    protected boolean makeMove(GameAction action) {
        int runningTot = state.getRunningTotal();
        int playerTot;
        if(action instanceof PigHoldAction){
            if(state.getPlayerId() == 0){
                playerTot = state.getPlayerZeroScore();
                playerTot = playerTot + runningTot;
                state.setPlayerZeroScore(playerTot);
            }
            else{
                playerTot = state.getPlayerOneScore();
                playerTot = playerTot + runningTot;
                state.setPlayerOneScore(playerTot);
            }

            state.setRunningTotal(0);
            return true;
        }
        if(action instanceof PigRollAction){
            int val = new Random().nextInt(5) + 1;
            state.setDieVal(val);

            if(state.getDieVal() != 1){
                runningTot = runningTot + state.getDieVal();
            }
            else{
                state.setRunningTotal(0);

            }
            return true;
        }
        return false;
    }//makeMove

    /**
     * send the updated state to a given player
     */
    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {
        //TODO  You will implement this method
    }//sendUpdatedSate

    /**
     * Check if the game is over
     *
     * @return
     * 		a message that tells who has won the game, or null if the
     * 		game is not over
     */
    @Override
    protected String checkIfGameOver() {
        //TODO  You will implement this method
        return null;
    }

}// class PigLocalGame
