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
                if(players.length > 1){
                    state.setPlayerId(1);
                }
            }
            else{
                playerTot = state.getPlayerOneScore();
                playerTot = playerTot + runningTot;
                state.setPlayerOneScore(playerTot);
                state.setPlayerId(0);
            }

            state.setRunningTotal(0);
            return true;
        }
        else if(action instanceof PigRollAction){
            int val = new Random().nextInt(6) + 1;
            state.setDieVal(val);

            if(state.getDieVal() != 1){
               runningTot = runningTot + state.getDieVal();
               state.setRunningTotal(runningTot);

            }
            else{
                state.setRunningTotal(0);
                if(players.length > 1 && state.getPlayerId() == 0){
                    state.setPlayerId(1);
                }
                else{
                    state.setPlayerId(0);
                }
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
        PigGameState copyState = new PigGameState(state.getPlayerId(),state.getPlayerZeroScore(),state.getPlayerOneScore(), state.getRunningTotal(), state.getDieVal());
        p.sendInfo(copyState);
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
        if(state.getPlayerZeroScore() >= 50){
            return "Player 0 won with a score of " + state.getPlayerZeroScore();
        }
        if(state.getPlayerOneScore() >= 50){
            return "Player 1 won with a score of " + state.getPlayerOneScore();
        }

        return null;
    }

}// class PigLocalGame
