package com.briantanabe.fd.dp.nfl.position;

/**
 * Created by Brian on 10/13/14.
 */
public enum Position {
    QUARTERBACK,
    RUNNING_BACK,
    WIDE_RECEIVER,
    TIGHT_END,
    KICKER,
    DEFENSE,
    UNKNOWN;

    private Position(){}

    @Override
    public String toString() {
        switch(this){
            case QUARTERBACK:
                return "QB";
            case RUNNING_BACK:
                return "RB";
            case WIDE_RECEIVER:
                return "WR";
            case TIGHT_END:
                return "TE";
            case KICKER:
                return "K";
            case DEFENSE:
                return "DEF";
            default:
            case UNKNOWN:
                return "UNKNOWN";
        }
    }
}
