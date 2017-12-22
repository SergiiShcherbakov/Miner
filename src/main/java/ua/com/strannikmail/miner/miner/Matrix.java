package ua.com.strannikmail.miner.miner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Sergii Shcherbakov on 06.12.2017.
 */
class Matrix<T> {
   private List<List<T>> matrix;

    Matrix(T icon) {
        this.matrix = new ArrayList<List<T>>(Ranges.getSize().getX());
        for (int i =0; i < Ranges.getSize().getX(); i++){
           this.matrix.add( new ArrayList<T>(Collections.nCopies( Ranges.getSize().getY(), icon)));
        }
    }

    T get(Coord coord){
        if(Ranges.inRange(coord)){
        return matrix.get(coord.getX()).get(coord.getY());
        } else {
            return null;
        }

    }

    void set(Coord coord, T icon){
        matrix.get(coord.getX()).set(coord.getY(), icon);
    }

    public List<Coord> getBoxByName(T iconType) {
        List<Coord> result = new LinkedList<Coord>();
        for (int x = 0; x < Ranges.getSize().getX(); x++ ){
            for (int y = 0; y < Ranges.getSize().getY(); y++ ){
                if( matrix.get(x).get(y) == iconType){
                     result.add(new Coord(x,y));
                }
            }
        }
        return result;
    }
}
