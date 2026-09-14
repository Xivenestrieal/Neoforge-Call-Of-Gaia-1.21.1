package net.xivenestriealsilver.call_of_gaia.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.xivenestriealsilver.call_of_gaia.CallOfGaia;
import net.xivenestriealsilver.call_of_gaia.entity.custom.zaara.ZaaraEntity;

public class ZaaraRenderer extends MobRenderer<ZaaraEntity, ZaaraModel<ZaaraEntity>> {
    public ZaaraRenderer(EntityRendererProvider.Context context) {
        super(context, new ZaaraModel<>(context.bakeLayer(ZaaraModel.LAYER_LOCATION)), 0.25f);
    }

    @Override
    public ResourceLocation getTextureLocation(ZaaraEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(CallOfGaia.MOD_ID, "textures/entity/zaara/zaara_blue.png");
    }

    @Override
    public void render(ZaaraEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        if(entity.isBaby()) {
            poseStack.scale(0.45f, 0.45f, 0.45f);
        } else {
            poseStack.scale(1f, 1f, 1f);
        }

        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }
}