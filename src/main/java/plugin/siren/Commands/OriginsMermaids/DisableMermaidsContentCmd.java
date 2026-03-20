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
        super("disablemermaidscontent", "server.commands.oom.originsmermaids.disablemermaidscontent.desc");

        this.requirePermission("orbisoriginsmermaids.disablemermaidscontent");
    }

    RequiredArg<Boolean> disableMermaidsContentArg = this.withRequiredArg("Disable Mermaids Content", "server.commands.oom.originsmermaids.disablemermaidscontent.arg0.desc", ArgTypes.BOOLEAN);

    @Override
    protected void execute(@Nonnull CommandContext commandContext, @Nonnull Store<EntityStore> store, @Nonnull Ref<EntityStore> ref, @Nonnull PlayerRef playerRef, @Nonnull World world) {
        Player player = store.getComponent(ref, Player.getComponentType());

        boolean disableMermaidContent = disableMermaidsContentArg.get(commandContext);

        OrbisOriginsMermaids.getConfig().get().setDisableMermaidsContent(disableMermaidContent);
        OrbisOriginsMermaids.getConfig().save();

        MermaidRegister.requireForcedMermaids();

        String playerTranslationId = "";
        String consoleTranslationId = "";
        if (disableMermaidContent) {
            playerTranslationId = "server.commands.oom.originsmermaids.disablemermaidscontent.playerMsg.enabled";
            consoleTranslationId = "server.commands.oom.originsmermaids.disablemermaidscontent.consoleMsg.enabled";
        } else {
            playerTranslationId = "server.commands.oom.originsmermaids.disablemermaidscontent.playerMsg.disabled";
            consoleTranslationId = "server.commands.oom.originsmermaids.disablemermaidscontent.consoleMsg.disabled";
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