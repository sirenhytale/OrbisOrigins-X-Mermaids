package plugin.siren.Commands;

import com.hypixel.hytale.server.core.command.system.basecommands.AbstractCommandCollection;
import plugin.siren.Commands.OriginsMermaids.*;

public class OriginsMermaidsCmd extends AbstractCommandCollection {
    public OriginsMermaidsCmd(){
        super("originsmermaids","server.commands.oom.originsmermaids.desc");

        this.addSubCommand(new DisableMermaidsContentCmd());
        this.addSubCommand(new OnlyInWaterCmd());
    }
}
