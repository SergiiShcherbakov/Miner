package ua.com.strannikmail.miner.miner;

import java.awt.*;
import java.util.List;

/**
 * Created by Sergii Shcherbakov on 06.12.2017.
 */
public class Game {
    private BombMap bombMap;
    private FlagMap flagMap;
    private GameState state;

    public Game(int cols, int rows, int bombs) {
        Ranges.setSize( new Coord( cols, rows));
        bombMap = new BombMap(bombs);
        flagMap = new FlagMap();
    }

    public GameState getState() {
        return state;
    }

    public void start(){
        bombMap.init();
        flagMap.init();
        state = GameState.PLAYED;
    }


    public Image getBox(Coord coord) {
        if(flagMap.get(coord) == FlagIcon.OPENED){
            return (Image) bombMap.getBombIcon(coord).image;
        }else {
            return (Image) flagMap.get(coord).image;
        }
    }

    public void pressLeftButton(Coord coord) {
        opendBox(coord);
    }

    private void opendBox(Coord coord){
        FlagIcon currentFlagIcon = flagMap.get(coord);
        if(currentFlagIcon == FlagIcon.FLAGED || currentFlagIcon == FlagIcon.OPENED){
            return;
        } else if(currentFlagIcon == FlagIcon.CLOSED){
            openBombMap(coord);
        }
    }

    private void openBombMap(Coord coord){
        BombIcon currentBombIcon = bombMap.getBombIcon(coord);
        if(currentBombIcon == BombIcon.BOMB ){
            state = GameState.BOMBED;
            flagMap.setBombed(coord);
            openAllBombs();
            return;
        } else if(currentBombIcon == BombIcon.ZERO ){
            flagMap.setOpenedToBox(coord);
            openBoxesArround( coord);
            return;
        } else {
            flagMap.setOpenedToBox(coord);
            if( checkWinner()){
                state = GameState.WINNER;
                flagMap.openAllField();
            }
        }
    }

    private void openAllBombs() {
        List<Coord> bombs = bombMap.getAllBomb();
        for(Coord coord : bombs){
            flagMap.setBombWhenLose(coord);
        }
    }

    private boolean checkWinner() {
        return bombMap.getCountBombs() == flagMap.getClosedBox();
    }

    private void openBoxesArround(Coord coord){
        for(Coord currentCoord : Ranges.getCoordsAround(coord) ){
            opendBox(currentCoord);
        }
    }

    public void pressRightButton(Coord coord) {
        flagMap.togleToBox(coord);
    }
}
