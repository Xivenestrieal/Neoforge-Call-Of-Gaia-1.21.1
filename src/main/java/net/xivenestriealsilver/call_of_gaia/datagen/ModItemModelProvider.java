package net.xivenestriealsilver.call_of_gaia.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimMaterials;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.xivenestriealsilver.call_of_gaia.CallOfGaia;
import net.xivenestriealsilver.call_of_gaia.item.ModItems;

import java.util.LinkedHashMap;

public class ModItemModelProvider extends ItemModelProvider {

    private static final LinkedHashMap<ResourceKey<TrimMaterial>, Float> TRIM_MATERIALS = new LinkedHashMap<>();

    static {
        TRIM_MATERIALS.put(TrimMaterials.QUARTZ, 0.1F);
        TRIM_MATERIALS.put(TrimMaterials.IRON, 0.2F);
        TRIM_MATERIALS.put(TrimMaterials.NETHERITE, 0.3F);
        TRIM_MATERIALS.put(TrimMaterials.REDSTONE, 0.4F);
        TRIM_MATERIALS.put(TrimMaterials.COPPER, 0.5F);
        TRIM_MATERIALS.put(TrimMaterials.GOLD, 0.6F);
        TRIM_MATERIALS.put(TrimMaterials.EMERALD, 0.7F);
        TRIM_MATERIALS.put(TrimMaterials.DIAMOND, 0.8F);
        TRIM_MATERIALS.put(TrimMaterials.LAPIS, 0.9F);
        TRIM_MATERIALS.put(TrimMaterials.AMETHYST, 1.0F);
    }

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, CallOfGaia.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {

        // Basic Items
        basicItem(ModItems.LAZURITE_INGOT.get());
        basicItem(ModItems.RAW_LAZURITE.get());

        basicItem(ModItems.HOGYOKU_SOUL.get());
        basicItem(ModItems.CORRUPTED_SOUL.get());

        basicItem(ModItems.FROZEN_FLAME.get());
        basicItem(ModItems.ROOT_HEART.get());
        basicItem(ModItems.ENT_CHARCOAL.get());

        basicItem(ModItems.CHISEL.get());
        basicItem(ModItems.SEEKING_EYES.get());
        basicItem(ModItems.LAZURITE_KEY.get());

        // Tools
        handheldItem(ModItems.LAZURITE_SWORD);
        handheldItem(ModItems.LAZURITE_PICKAXE);
        handheldItem(ModItems.LAZURITE_SHOVEL);
        handheldItem(ModItems.LAZURITE_AXE);
        handheldItem(ModItems.LAZURITE_HOE);

        // Armor (CALL THIS OR TRIMS WON'T GENERATE)
        trimmedArmorItem(ModItems.LAZURITE_HELMET);
        trimmedArmorItem(ModItems.LAZURITE_CHESTPLATE);
        trimmedArmorItem(ModItems.LAZURITE_LEGGINGS);
        trimmedArmorItem(ModItems.LAZURITE_BOOTS);
    }

    /* -------------------- ARMOR TRIMS -------------------- */

    private void trimmedArmorItem(DeferredItem<ArmorItem> item) {

        ArmorItem armorItem = item.get();
        ResourceLocation itemId = item.getId();
        String itemPath = itemId.getPath();

        String armorType = switch (armorItem.getEquipmentSlot()) {
            case HEAD -> "helmet";
            case CHEST -> "chestplate";
            case LEGS -> "leggings";
            case FEET -> "boots";
            default -> "";
        };

        // Base (non-trimmed) model
        ItemModelBuilder baseModel = withExistingParent(itemPath, mcLoc("item/generated"))
                .texture("layer0",
                        ResourceLocation.fromNamespaceAndPath(CallOfGaia.MOD_ID, "item/" + itemPath));

        TRIM_MATERIALS.forEach((trimMaterial, trimValue) -> {

            String trimName = trimMaterial.location().getPath();
            String trimTexturePath = "trims/items/" + armorType + "_trim_" + trimName;
            ResourceLocation trimTexture = ResourceLocation.withDefaultNamespace(trimTexturePath);

            existingFileHelper.trackGenerated(trimTexture, PackType.CLIENT_RESOURCES, ".png", "textures");

            String trimmedModelName = itemPath + "_" + trimName + "_trim";

            // Trimmed model file
            getBuilder(trimmedModelName)
                    .parent(new ModelFile.UncheckedModelFile("item/generated"))
                    .texture("layer0", itemId.getNamespace() + ":item/" + itemPath)
                    .texture("layer1", trimTexture);

            // Override in base model
            baseModel.override()
                    .predicate(mcLoc("trim_type"), trimValue)
                    .model(new ModelFile.UncheckedModelFile(
                            itemId.getNamespace() + ":item/" + trimmedModelName))
                    .end();
        });
    }

    /* -------------------- TOOL MODEL -------------------- */

    private ItemModelBuilder handheldItem(DeferredItem<?> item) {
        return withExistingParent(item.getId().getPath(), mcLoc("item/handheld"))
                .texture("layer0",
                        ResourceLocation.fromNamespaceAndPath(CallOfGaia.MOD_ID,
                                "item/" + item.getId().getPath()));
    }

    /* -------------------- BLOCK ITEM HELPERS -------------------- */

    public void buttonItem(DeferredBlock<?> block, DeferredBlock<?> baseBlock) {
        withExistingParent(block.getId().getPath(), mcLoc("block/button_inventory"))
                .texture("texture",
                        ResourceLocation.fromNamespaceAndPath(CallOfGaia.MOD_ID,
                                "block/" + baseBlock.getId().getPath()));
    }

    public void fenceItem(DeferredBlock<?> block, DeferredBlock<?> baseBlock) {
        withExistingParent(block.getId().getPath(), mcLoc("block/fence_inventory"))
                .texture("texture",
                        ResourceLocation.fromNamespaceAndPath(CallOfGaia.MOD_ID,
                                "block/" + baseBlock.getId().getPath()));
    }

    public void wallItem(DeferredBlock<?> block, DeferredBlock<?> baseBlock) {
        withExistingParent(block.getId().getPath(), mcLoc("block/wall_inventory"))
                .texture("wall",
                        ResourceLocation.fromNamespaceAndPath(CallOfGaia.MOD_ID,
                                "block/" + baseBlock.getId().getPath()));
    }
}
