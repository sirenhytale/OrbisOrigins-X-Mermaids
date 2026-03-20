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
        super("mermaidonlyinwater", "server.commands.oom.originsmermaids.mermaidonlyinwater.desc");

        this.requirePermission("orbisoriginsmermaids.mermaidonlyinwater");
    }

    RequiredArg<Boolean> mermaidOnlyInWaterArg = this.withRequiredArg("Mermaid only in water", "server.commands.oom.originsmermaids.mermaidonlyinwater.arg0.desc", ArgTypes.BOOLEAN);

    @Override
    protected void execute(@Nonnull CommandContext commandContext, @Nonnull Store<EntityStore> store, @Nonnull Ref<EntityStore> ref, @Nonnull PlayerRef playerRef, @Nonnull World world) {
        Player player = store.getComponent(ref, Player.getComponentType());

        boolean mermaidOnlyInWater = mermaidOnlyInWaterArg.get(commandContext);

        OrbisOriginsMermaids.getConfig().get().setMermaidOnlyInWater(mermaidOnlyInWater);
        OrbisOriginsMermaids.getConfig().save();

        MermaidRegister.mermaidOnlyInWater();

        String playerTranslationId = "";
        String consoleTranslationId = "";
        if (mermaidOnlyInWater) {
            playerTranslationId = "server.commands.oom.originsmermaids.mermaidonlyinwater.playerMsg.enabled";
            consoleTranslationId = "server.commands.oom.originsmermaids.mermaidonlyinwater.consoleMsg.enabled";
        } else {
            playerTranslationId = "server.commands.oom.originsmermaids.mermaidonlyinwater.playerMsg.disabled";
            consoleTranslationId = "server.commands.oom.originsmermaids.mermaidonlyinwater.consoleMsg.disabled";
        }

        if(player != null) {
            player.sendMessage(Message.translation(playerTranslationId));

            String consoleMessage = Message.translation(consoleTranslationId).param("username", player.getDisplayName()).getAnsiMessage();
            Mermaids.LOGGER.atInfo().log(consoleMessage);
        }else{
            String consoleMessage = Message.translation(consoleTranslationId).param("username", "Unknown").getAnsiMessage();
            Mermaids.LOGGER.atInfo().log(consoleMessage);
        }
    }
}