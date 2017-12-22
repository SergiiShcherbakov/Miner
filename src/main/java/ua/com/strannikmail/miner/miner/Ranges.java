package ua.com.strannikmail.miner.miner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Sergii Shcherbakov on 06.12.2017.
 */
public class Ranges {
    static private  Coord size;
    static private List<Coord> allCoords ;
    static private Random random = new Random();

    static void setSize(Coord _size){
        size =_size;
        allCoords = new ArrayList<Coord>();
        for (int y = 0; y < size.y; y++){
            for (int x = 0; x < size.x; x++){
                allCoords.add(new Coord(x,y));
            }

        }
    }

    static Coord getSize() {
        return size;
    }

    static List<Coord> getAllCoords(){
        return allCoords;
    }

    static boolean inRange(Coord coord) {
        return coord.getX() < size.x && coord.getY() < size.y
                && coord.getX() >= 0 && coord.getY() >= 0 ;
    }

    static Coord getRandomCoord(){
        return new Coord(random.nextInt(size.x), random.nextInt(size.y));
    }

    static List<Coord> getCoordsAround(Coord coord){
        Coord around;
        List<Coord> result = new ArrayList<Coord>();
        for (int x = coord.x - 1; x <= coord.x + 1; x++){
            for (int y = coord.y - 1; y<= coord.y + 1 ; y++){
                if(inRange(around = new Coord(x, y))){
                    if(!around.equals(coord)){
                        result.add(around);
                    }
                }
            }
        }
        return result;
    }
}
