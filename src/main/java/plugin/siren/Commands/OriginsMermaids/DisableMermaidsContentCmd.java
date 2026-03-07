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

public class DisableMermaidsContentCmd extends AbstractPlayerCommand {
    public DisableMermaidsContentCmd() {
        super("disablemermaidscontent", "Toggles to disable normal content from the Mermaids mod.");

        this.requirePermission("orbisoriginsmermaids.disablemermaidscontent");
    }

    RequiredArg<Boolean> msgDisMerContentArg = this.withRequiredArg("Disable Mermaids Content", "Boolean to disable normal content from the Mermaids mod.", ArgTypes.BOOLEAN);

    @Override
    protected void execute(@Nonnull CommandContext commandContext, @Nonnull Store<EntityStore> store, @Nonnull Ref<EntityStore> ref, @Nonnull PlayerRef playerRef, @Nonnull World world) {
        Player player = store.getComponent(ref, Player.getComponentType());

        boolean disMerContent = msgDisMerContentArg.get(commandContext);

        OrbisOriginsMermaids.getConfig().get().setDisableMermaidsContent(disMerContent);
        OrbisOriginsMermaids.getConfig().save();

        String toggledStr = "";
        if (disMerContent) {
            toggledStr = "Enabled";
        } else {
            toggledStr = "Disabled";
        }
        player.sendMessage(Message.raw("You have " + toggledStr + " to disable Mermaids mod content."));

        Mermaids.LOGGER.atInfo().log(player.getDisplayName() + " has toggled to disable Mermaids mod content: " + String.valueOf(disMerContent) + ".");

        MermaidRegister.requireForcedMermaids();
    }
}