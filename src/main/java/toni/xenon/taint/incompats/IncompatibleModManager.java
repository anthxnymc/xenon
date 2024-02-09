package toni.xenon.taint.incompats;

import com.google.common.collect.ImmutableList;
import me.jellysquid.mods.sodium.client.SodiumClientMod;
import net.minecraftforge.fml.ModLoader;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.ModLoadingStage;
import net.minecraftforge.fml.ModLoadingWarning;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.forgespi.language.IModInfo;

import java.util.List;

/**
 * Detect mods that are known to cause bugs (particularly with Xenon present) and complain to the user.
 */
public class IncompatibleModManager {
    private static final List<ModDeclaration> INCOMPATIBLE_MODS = ImmutableList.<ModDeclaration>builder()
            .add(new ModDeclaration.Single("entityculling", "Entity Culling"))
            .add(new ModDeclaration.Single("sound_physics_remastered", "Sound Physics Remastered"))
            .build();

    public static void checkMods(FMLClientSetupEvent event) {
        // TODO: Enable
        if (true || SodiumClientMod.options().advanced.disableIncompatibleModWarnings) {
            return;
        }
        IModInfo selfInfo = ModLoadingContext.get().getActiveContainer().getModInfo();
        String[] modDeclarationList = INCOMPATIBLE_MODS.stream().filter(ModDeclaration::matches).map(ModDeclaration::toString).toArray(String[]::new);
        if(modDeclarationList.length > 0) {
            event.enqueueWork(() -> {
                ModLoader.get().addWarning(new ModLoadingWarning(selfInfo, ModLoadingStage.SIDED_SETUP, "xenon.conflicting_mod", String.join(", ", modDeclarationList)));
                ModLoader.get().addWarning(new ModLoadingWarning(selfInfo, ModLoadingStage.SIDED_SETUP, "xenon.conflicting_mod_list", String.join(", ", modDeclarationList)));
            });
        }
    }
}
