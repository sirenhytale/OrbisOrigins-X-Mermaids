package plugin.siren.Events;

import com.hexvane.orbisorigins.data.PlayerSpeciesData;
import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.server.core.event.events.player.PlayerReadyEvent;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import plugin.siren.api.MermaidsAPI;

public class PlayerReadyEventOOM {
    public static void onPlayerReadyEvent(PlayerReadyEvent event){
        World world = event.getPlayer().getWorld();
        world.execute(() -> {
            Ref<EntityStore> ref = event.getPlayerRef();
            Store<EntityStore> store = ref.getStore();

            PlayerSpeciesData.SpeciesSelection speciesSelection = PlayerSpeciesData.getSpeciesSelection(ref, store, world);
            if(speciesSelection != null){
                String id = speciesSelection.getSpeciesId();
                if(id.equalsIgnoreCase("mermaid")){
                    if(!MermaidsAPI.isForcedMermaidOrbisOrigin(store,ref)){
                        MermaidsAPI.setForcedMermaidOrbisOrigin(store,ref,true);
                    }
                }else{
                    if(MermaidsAPI.isForcedMermaidOrbisOrigin(store,ref)){
                        MermaidsAPI.setForcedMermaidOrbisOrigin(store,ref,false);
                    }
                }
            }else{
                if(MermaidsAPI.isForcedMermaidOrbisOrigin(store,ref)){
                    MermaidsAPI.setForcedMermaidOrbisOrigin(store,ref,false);
                }
            }
        });
    }
}
