package br.ultraboost;

import br.ultraboost.client.ClientEvents;
import br.ultraboost.config.UltraBoostConfig;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

@Mod(UltraBoost.MOD_ID)
public final class UltraBoost {
    public static final String MOD_ID = "ultraboost";

    public UltraBoost() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, UltraBoostConfig.SPEC);
        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> ClientEvents::register);
    }
}
