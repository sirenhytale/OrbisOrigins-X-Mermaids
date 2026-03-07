package plugin.siren.Dependencies.Mermaids;

import plugin.siren.OrbisOriginsMermaids;
import plugin.siren.api.MermaidsAPI;

public class MermaidRegister {
    private MermaidRegister(){}

    public static void register(){
        requireForcedMermaids();
        mermaidOnlyInWater();
    }

    public static void requireForcedMermaids(){
        boolean requireForcedMermaids = OrbisOriginsMermaids.getConfig().get().getDisabledMermaidContent();
        MermaidsAPI.setRequireForcedMermaid(requireForcedMermaids);
    }

    public static void mermaidOnlyInWater(){
        boolean onlyInWater = OrbisOriginsMermaids.getConfig().get().ifMermaidOnlyInWater();
        MermaidsAPI.setForcedMermaidOnlyInWater(onlyInWater);
    }
}
