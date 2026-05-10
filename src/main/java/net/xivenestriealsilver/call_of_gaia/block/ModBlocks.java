package net.xivenestriealsilver.call_of_gaia.block;

import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.xivenestriealsilver.call_of_gaia.call_of_gaia;
import net.xivenestriealsilver.call_of_gaia.block.custom.LazuriteLampBlock;
import net.xivenestriealsilver.call_of_gaia.block.custom.SeekingBlock;
import net.xivenestriealsilver.call_of_gaia.item.ModItems;

import java.util.function.Supplier;

import static net.minecraft.world.item.Items.registerBlock;
import static net.xivenestriealsilver.call_of_gaia.call_of_gaia.MOD_ID;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(call_of_gaia.MOD_ID);

    public static final DeferredBlock<Block> LAZURITE_BLOCK = registerBlock("lazurite_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops().sound(SoundType.STONE)));

    public static final DeferredBlock<Block> LAZURITE_ORE = registerBlock("lazurite_ore",
            () -> new DropExperienceBlock(UniformInt.of(3, 6),
                    BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops().sound(SoundType.STONE)));

public static final DeferredBlock<Block> DEEPSLATE_LAZURITE_ORE = registerBlock("deepslate_lazurite_ore",
            () -> new DropExperienceBlock(UniformInt.of(4, 8),
                    BlockBehaviour.Properties.of().strength(5f).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE)));

    public static final DeferredBlock<Block> SEEKING_BLOCK = registerBlock("seeking_block",
            () -> new SeekingBlock(BlockBehaviour.Properties.of().strength(5f).requiresCorrectToolForDrops().sound(SoundType.SCULK_SHRIEKER)));


    public static final DeferredBlock<Block> LAZURITE_LAMP = registerBlock("lazurite_lamp",
            () -> new LazuriteLampBlock(BlockBehaviour.Properties.of().strength(2f)
                    .requiresCorrectToolForDrops().lightLevel(state -> state.getValue(LazuriteLampBlock.CLICKED) ? 15 : 0)));



    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}