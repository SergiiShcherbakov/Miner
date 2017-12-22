package ua.com.strannikmail.miner.miner;

import java.util.List;

/**
 * Created by Sergii Shcherbakov on 06.12.2017.
 */
class BombMap {
    private Matrix<BombIcon> bombMatrix;
    private int totalBombs;

    void init(){
        bombMatrix = new Matrix(BombIcon.ZERO);
        fixBombsCount();
        for (int i=0 ; i <  totalBombs; i++) {
            placeBomb();
        }
    }

    BombMap(int totalBombs) {
        this.totalBombs = totalBombs;
    }

    BombIcon getBombIcon(Coord coord){
        return bombMatrix.get(coord);
    }


    private void placeBomb() {
        while (true) {
            Coord coord = Ranges.getRandomCoord();
            if (getBombIcon(coord) == BombIcon.BOMB) {
                continue;
            }
            bombMatrix.set(coord, BombIcon.BOMB);
            setNumAroundCoord(coord);
            break;
        }
    }

    private void setNumAroundCoord(Coord coord) {
        for (Coord coordAround : Ranges.getCoordsAround(coord)) {
            if(bombMatrix.get(coordAround) != BombIcon.BOMB){
                bombMatrix.set(coordAround, getBombIcon(coordAround).nextNumberBox());
            }
        }
    }

    private void fixBombsCount(){
        int maxBombs = ( Ranges.getSize().getX() * Ranges.getSize().getY())/2;
        if(totalBombs > maxBombs){
            totalBombs = maxBombs;
        }

    }

    int getCountBombs() {
        return totalBombs;
    }

    List<Coord> getAllBomb() {
        return bombMatrix.getBoxByName(BombIcon.BOMB);
    }
}
