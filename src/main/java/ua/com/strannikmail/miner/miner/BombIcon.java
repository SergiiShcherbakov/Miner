package ua.com.strannikmail.miner.miner;

/**
 * Created by Sergii Shcherbakov on 06.12.2017.
 */
public enum BombIcon {
    ZERO,
    NUM1,
    NUM2,
    NUM3,
    NUM4,
    NUM5,
    NUM6,
    NUM7,
    NUM8,
    BOMB,;
//    OPENED,
//    CLOSED,
//    FLAGED,
//    BOMBED,
//    NOBOMB;

    public Object image;

    BombIcon nextNumberBox(){
        return BombIcon.values()[this.ordinal()+1];
    }
}
