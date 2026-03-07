package plugin.siren.Commands.OriginsMermaids;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.arguments.system.RequiredArg;
import com.hypixel.hytale.server.core.command.system.arguments.types.ArgTypes;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractPlayerCommand;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import plugin.siren.Dependencies.Mermaids.MermaidRegister;
import plugin.siren.Mermaids;
import plugin.siren.OrbisOriginsMermaids;

import javax.annotation.Nonnull;

public class OnlyInWaterCmd extends AbstractPlayerCommand {
    public OnlyInWaterCmd() {
        super("mermaidonlyinwater", "Toggles to allow users to only be a mermaid in water.");

        this.requirePermission("orbisoriginsmermaids.mermaidonlyinwater");
    }

    RequiredArg<Boolean> msgMerOnlyWaterArg = this.withRequiredArg("Mermaid only in water", "Boolean to toggle mermaid only in water.", ArgTypes.BOOLEAN);

    @Override
    protected void execute(@Nonnull CommandContext commandContext, @Nonnull Store<EntityStore> store, @Nonnull Ref<EntityStore> ref, @Nonnull PlayerRef playerRef, @Nonnull World world) {
        Player player = store.getComponent(ref, Player.getComponentType());

        boolean merOnlyWater = msgMerOnlyWaterArg.get(commandContext);

        OrbisOriginsMermaids.getConfig().get().setMermaidOnlyInWater(merOnlyWater);
        OrbisOriginsMermaids.getConfig().save();

        String toggledStr = "";
        if (merOnlyWater) {
            toggledStr = "Enabled";
        } else {
            toggledStr = "Disabled";
        }
        player.sendMessage(Message.raw("You have " + toggledStr + " the mermaid specie to transform into a mermaid only in water."));

        Mermaids.LOGGER.atInfo().log(player.getDisplayName() + " has toggled the mermaid specie to transform into a mermaid only in water: " + String.valueOf(merOnlyWater) + ".");

        MermaidRegister.mermaidOnlyInWater();
    }
}