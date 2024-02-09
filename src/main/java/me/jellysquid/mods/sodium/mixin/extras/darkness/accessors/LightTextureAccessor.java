package me.jellysquid.mods.sodium.mixin.extras.darkness.accessors;

import net.minecraft.client.renderer.LightTexture;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(LightTexture.class)
public interface LightTextureAccessor {
    @Accessor("blockLightRedFlicker")
    float getFlicker();

    @Accessor("updateLightTexture")
    boolean isDirty();
}
