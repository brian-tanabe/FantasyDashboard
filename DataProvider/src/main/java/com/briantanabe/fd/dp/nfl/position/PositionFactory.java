package com.briantanabe.fd.dp.nfl.position;

/**
 * Created by Brian on 10/13/14.
 */
public class PositionFactory {

    public static Position whatPosition(String positionString) {
        positionString = positionString.toLowerCase();
        if (positionString.contains("qb") || positionString.contains("quarterback"))
            return Position.QUARTERBACK;
        else if (positionString.contains("rb") || positionString.contains("running back") || positionString.contains("tailback") || positionString.contains("tb") || positionString.contains("fb") || positionString.contains("fullback"))
            return Position.RUNNING_BACK;
        else if (positionString.contains("wide receiver") || positionString.contains("wr") || positionString.contains("wideout"))
            return Position.WIDE_RECEIVER;
        else if (positionString.contains("tight end") || positionString.contains("te"))
            return Position.TIGHT_END;
        else if (positionString.contains("kicker") || positionString.contains("k") || positionString.contains("pk"))
            return Position.KICKER;
        else if (positionString.contains("defense") || positionString.contains("def") || positionString.contains("special teams") || positionString.contains("d/st"))
            return Position.DEFENSE;
        else
            return Position.UNKNOWN;
    }
}
