package ua.com.strannikmail.miner.miner;

/**
 * Created by Sergii Shcherbakov on 06.12.2017.
 */
public enum FlagIcon {
    OPENED,
    CLOSED,
    FLAGED,
    BOMBED,
    NOBOMB;

    public Object image;

    FlagIcon nextNumberBox(){
        return FlagIcon.values()[this.ordinal()+1];
    }
}
