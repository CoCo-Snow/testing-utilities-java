package testing.util;

import mindustry.*;
import mindustry.game.*;
import mindustry.mod.Mods.*;

import static arc.Core.*;

public class TUVars{
    public static float longPress;

    public static Team curTeam = Team.sharded;

    /** Offset for when sk7725/timecontrol is enabled */
    public static float TCOffset;
    public static float iconSize = 40f, buttonSize = 24f, sliderWidth = 140f, fieldWidth = 80f;

    public static void setDefaults(){
        longPress = settings.getInt("tu-long-press", 2) * 60f / 4f;
        LoadedMod timeControl = Vars.mods.getMod("time-control");
        TCOffset = timeControl != null && timeControl.isSupported() && timeControl.enabled() ? 68 : 0;
    }
}
