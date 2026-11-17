package br.ultraboost.client;

import br.ultraboost.config.UltraBoostConfig;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.RenderNameTagEvent;
import net.minecraftforge.common.MinecraftForge;

public final class ClientEvents {
    private ClientEvents() {}

    public static void register() {
        MinecraftForge.EVENT_BUS.register(ClientEvents.class);
    }

    private static boolean shouldCull(LivingEntity entity) {
        if (!UltraBoostConfig.ENABLE_ENTITY_CULLING.get()) return false;

        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.level == null || minecraft.getCameraEntity() == null) return false;
        if (entity == minecraft.getCameraEntity()) return false;
        if (entity.isRemoved()) return false;

        if (entity instanceof Player && !UltraBoostConfig.CULL_PLAYER_ENTITIES.get()) {
            return false;
        }

        Camera camera = minecraft.gameRenderer.getMainCamera();
        double maxDistance = UltraBoostConfig.ENTITY_RENDER_DISTANCE.get();
        return entity.distanceToSqr(camera.getPosition()) > maxDistance * maxDistance;
    }

    @net.minecraftforge.eventbus.api.SubscribeEvent
    public static void onRenderLivingPre(RenderLivingEvent.Pre<?, ?> event) {
        if (shouldCull(event.getEntity())) {
            event.setCanceled(true);
        }
    }

    @net.minecraftforge.eventbus.api.SubscribeEvent
    public static void onRenderNameTag(RenderNameTagEvent event) {
        if (UltraBoostConfig.CULL_FAR_NAME_TAGS.get()
                && event.getEntity() instanceof LivingEntity living
                && shouldCull(living)) {
            event.setCanceled(true);
        }
    }
}
