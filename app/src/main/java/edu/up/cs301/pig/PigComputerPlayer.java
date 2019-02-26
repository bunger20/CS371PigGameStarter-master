package edu.up.cs301.pig;

import java.util.Random;

import edu.up.cs301.game.GameComputerPlayer;
import edu.up.cs301.game.actionMsg.GameAction;
import edu.up.cs301.game.infoMsg.GameInfo;
import edu.up.cs301.game.infoMsg.NotYourTurnInfo;
import edu.up.cs301.game.util.Tickable;

/**
 * An AI for Pig
 *
 * @author Andrew M. Nuxoll
 * @version August 2015
 */
public class PigComputerPlayer extends GameComputerPlayer {

    /**
     * ctor does nothing extra
     */
    public PigComputerPlayer(String name) {
        super(name);
    }

    /**
     * callback method--game's state has changed
     *
     * @param info
     * 		the information (presumably containing the game's state)
     */
    @Override
    protected void receiveInfo(GameInfo info) {
       if(info instanceof NotYourTurnInfo){
           return;
       }
       else{
           int val = new Random().nextInt(1);
           if(val == 1){
               PigHoldAction hold = new PigHoldAction(this);
               game.sendAction(hold);
           }
           else{
               PigRollAction roll = new PigRollAction(this);
               game.sendAction(roll);
           }

       }

    }//receiveInfo

}
