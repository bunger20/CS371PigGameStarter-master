package edu.up.cs301.pig;

public class PigGameState {

    private int playerId;
    private int playerZeroScore;
    private int playerOneScore;
    private int runningTotal;
    private int dieVal;

    public PigGameState(){
        playerId = 0;
        playerZeroScore = 0;
        playerOneScore = 0;
        runningTotal = 0;
        dieVal = 0;
    }

    public PigGameState(int id, int zeroScore, int oneScore, int total, int die){
        playerId = id;
        playerZeroScore = zeroScore;
        playerOneScore = oneScore;
        runningTotal = total;
        dieVal = die;
    }

    public int getPlayerId(){

        return playerId;
    }


    public int getPlayerZeroScore(){

        return playerZeroScore;
    }

    public int getPlayerOneScore(){

        return playerOneScore;
    }

    public int getRunningTotal(){

        return runningTotal;
    }

    public int getDieVal(){

        return dieVal;
    }

    public void setPlayerId(int id){
        playerId = id;

    }

    public void setPlayerZeroScore(int score){
        playerZeroScore = score;

    }

    public void setPlayerOneScore(int score){
        playerOneScore = score;

    }
    public void setRunningTotal(int tot){
        runningTotal = tot;

    }
    public void setDieVal(int val){
        dieVal = val;

    }

}


