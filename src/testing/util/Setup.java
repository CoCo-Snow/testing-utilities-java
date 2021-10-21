package testing.util;

import arc.*;
import arc.scene.ui.layout.*;
import mindustry.game.EventType.*;
import testing.buttons.*;

import static mindustry.Vars.*;

public class Setup{
    static boolean selfInit;

    public static Table[]

    folder = newTables(),
    team = newTables(),
    death = newTables(),
    sandbox = newTables(),
    status = newTables(),
    units = newTables();

    public static Table[] newTables(){
        Table t1 = new Table().bottom().left();
        t1.fillParent = true;
        t1.visibility = ButtonVisibility.unfoldedVisibility;

        Table t2 = new Table().bottom().left();
        t2.fillParent = true;
        t2.visibility = ButtonVisibility.foldedVisibility;

        return new Table[]{t1, t2};
    }

    public static void add(Table[] tables){
        ui.hudGroup.addChild(tables[0]);
        ui.hudGroup.addChild(tables[1]);
    }

    public static void init(){
        TUVars.folded = Core.settings.getBool("tu-startfolded", false);

        Folding.add(folder);
        add(folder);

        TeamChanger.add(team);
        add(team);

        Death.init();
        Death.add(death);
        add(death);

        Sandbox.add(sandbox);
        add(sandbox);

        StatusMenu.init();
        StatusMenu.add(status);
        add(status);

        UnitMenu.init();
        UnitMenu.add(units);
        add(units);

        Events.on(WorldLoadEvent.class, e -> {
            if(!selfInit){
                //lmao
                Table healthUI = placement();
                healthUI.row();
                Self.healing(healthUI).size(96, 40).color(TUVars.curTeam.color).pad(0).left().padLeft(4);
                Self.invincibility(healthUI).size(164, 40).color(TUVars.curTeam.color).pad(0).left().padLeft(-20);
                selfInit = true;
            }
        });
    }

    public static Table placement(){
        return ui.hudGroup
            .<Table>find("overlaymarker")
            .<Stack>find("waves/editor")
            .<Table>find("waves")
            .find("status");
    }
}