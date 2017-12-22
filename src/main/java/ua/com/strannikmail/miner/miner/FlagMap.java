package ua.com.strannikmail.miner.miner;

/**
 * Created by Sergii Shcherbakov on 06.12.2017.
 */
class FlagMap {
    private Matrix<FlagIcon> flagMap;
    int countOfClosedBoxes;

    void init(){
        flagMap = new Matrix(FlagIcon.CLOSED);
        countOfClosedBoxes = Ranges.getSize().getX() * Ranges.getSize().getY();
    }
    FlagIcon get(Coord coord){
        return flagMap.get(coord);
    }

    void setOpenedToBox(Coord coord) {
        countOfClosedBoxes --;
        flagMap.set(coord, FlagIcon.OPENED);
    }

    public void setFlagetToBox(Coord coord) {
        flagMap.set(coord, FlagIcon.FLAGED);
    }

    public void setClosedBox(Coord coord) {
        flagMap.set(coord, FlagIcon.FLAGED);
    }

    public void togleToBox(Coord coord) {
        if(flagMap.get(coord) == FlagIcon.FLAGED){
            flagMap.set(coord, FlagIcon.CLOSED);
        } else if(flagMap.get(coord) == FlagIcon.CLOSED){
            flagMap.set(coord, FlagIcon.FLAGED);
        }
    }

    public void setBombed(Coord coord) {
        flagMap.set(coord, FlagIcon.BOMBED);
    }

    public int getClosedBox() {
        return countOfClosedBoxes;
    }

    public void openAllField(){
        flagMap = new Matrix(FlagIcon.OPENED);
    }

    public void setBombWhenLose(Coord coord) {
        FlagIcon currentFlagIcon = flagMap.get(coord);
        if(currentFlagIcon == FlagIcon.FLAGED){
            flagMap.set(coord, FlagIcon.NOBOMB);
        } else if(currentFlagIcon == FlagIcon.CLOSED){
            flagMap.set(coord, FlagIcon.OPENED);
        }
    }
}
