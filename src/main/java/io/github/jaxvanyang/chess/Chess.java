package io.github.jaxvanyang.chess;

import com.mojang.logging.LogUtils;
import io.github.jaxvanyang.chess.block.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(Chess.MODID)
public class Chess {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "chess";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();
    // Create a Deferred Register to hold Blocks which will all be registered under the "chess" namespace
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);
    public static final DeferredBlock<Block> WHITE_BISHOP = BLOCKS.registerBlock("white_bishop", Bishop::new, BlockBehaviour.Properties.of().mapColor(MapColor.QUARTZ).strength(0.8F, 0.8F).requiresCorrectToolForDrops());
    public static final DeferredBlock<Block> WHITE_KING = BLOCKS.registerBlock("white_king", King::new, BlockBehaviour.Properties.of().mapColor(MapColor.QUARTZ).strength(0.8F, 0.8F).requiresCorrectToolForDrops());
    public static final DeferredBlock<Block> WHITE_KNIGHT = BLOCKS.registerBlock("white_knight", Knight::new, BlockBehaviour.Properties.of().mapColor(MapColor.QUARTZ).strength(0.8F, 0.8F).requiresCorrectToolForDrops());
    public static final DeferredBlock<Block> WHITE_PAWN = BLOCKS.registerBlock("white_pawn", Pawn::new, BlockBehaviour.Properties.of().mapColor(MapColor.QUARTZ).strength(0.8F, 0.8F).requiresCorrectToolForDrops());
    public static final DeferredBlock<Block> WHITE_QUEEN = BLOCKS.registerBlock("white_queen", Queen::new, BlockBehaviour.Properties.of().mapColor(MapColor.QUARTZ).strength(0.8F, 0.8F).requiresCorrectToolForDrops());
    public static final DeferredBlock<Block> WHITE_ROOK = BLOCKS.registerBlock("white_rook", Rook::new, BlockBehaviour.Properties.of().mapColor(MapColor.QUARTZ).strength(0.8F, 0.8F).requiresCorrectToolForDrops());
    public static final DeferredBlock<Block> BLACK_BISHOP = BLOCKS.registerBlock("black_bishop", Bishop::new, BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLACK).strength(1.5F, 6F).requiresCorrectToolForDrops());
    public static final DeferredBlock<Block> BLACK_KING = BLOCKS.registerBlock("black_king", King::new, BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLACK).strength(1.5F, 6F).requiresCorrectToolForDrops());
    public static final DeferredBlock<Block> BLACK_KNIGHT = BLOCKS.registerBlock("black_knight", Knight::new, BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLACK).strength(1.5F, 6F).requiresCorrectToolForDrops());
    public static final DeferredBlock<Block> BLACK_PAWN = BLOCKS.registerBlock("black_pawn", Pawn::new, BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLACK).strength(1.5F, 6F).requiresCorrectToolForDrops());
    public static final DeferredBlock<Block> BLACK_QUEEN = BLOCKS.registerBlock("black_queen", Queen::new, BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLACK).strength(1.5F, 6F).requiresCorrectToolForDrops());
    public static final DeferredBlock<Block> BLACK_ROOK = BLOCKS.registerBlock("black_rook", Rook::new, BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLACK).strength(1.5F, 6F).requiresCorrectToolForDrops());
    // Create a Deferred Register to hold Items which will all be registered under the "chess" namespace
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);
    public static final DeferredItem<BlockItem> WHITE_BISHOP_ITEM = ITEMS.registerSimpleBlockItem("white_bishop", WHITE_BISHOP);
    public static final DeferredItem<BlockItem> WHITE_KING_ITEM = ITEMS.registerSimpleBlockItem("white_king", WHITE_KING);
    public static final DeferredItem<BlockItem> WHITE_KNIGHT_ITEM = ITEMS.registerSimpleBlockItem("white_knight", WHITE_KNIGHT);
    public static final DeferredItem<BlockItem> WHITE_PAWN_ITEM = ITEMS.registerSimpleBlockItem("white_pawn", WHITE_PAWN);
    public static final DeferredItem<BlockItem> WHITE_QUEEN_ITEM = ITEMS.registerSimpleBlockItem("white_queen", WHITE_QUEEN);
    public static final DeferredItem<BlockItem> WHITE_ROOK_ITEM = ITEMS.registerSimpleBlockItem("white_rook", WHITE_ROOK);
    public static final DeferredItem<BlockItem> BLACK_BISHOP_ITEM = ITEMS.registerSimpleBlockItem("black_bishop", BLACK_BISHOP);
    public static final DeferredItem<BlockItem> BLACK_KING_ITEM = ITEMS.registerSimpleBlockItem("black_king", BLACK_KING);
    public static final DeferredItem<BlockItem> BLACK_KNIGHT_ITEM = ITEMS.registerSimpleBlockItem("black_knight", BLACK_KNIGHT);
    public static final DeferredItem<BlockItem> BLACK_PAWN_ITEM = ITEMS.registerSimpleBlockItem("black_pawn", BLACK_PAWN);
    public static final DeferredItem<BlockItem> BLACK_QUEEN_ITEM = ITEMS.registerSimpleBlockItem("black_queen", BLACK_QUEEN);
    public static final DeferredItem<BlockItem> BLACK_ROOK_ITEM = ITEMS.registerSimpleBlockItem("black_rook", BLACK_ROOK);
    // Create a Deferred Register to hold CreativeModeTabs which will all be registered under the "chess" namespace
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);
    // Creates a creative tab with the id "chess:example_tab" for the example item, that is placed after the combat tab
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> EXAMPLE_TAB = CREATIVE_MODE_TABS.register("chess_tab", () -> CreativeModeTab.builder().title(Component.translatable("itemGroup.chess")) //The language key for the title of your CreativeModeTab
            .withTabsBefore(CreativeModeTabs.COMBAT).icon(() -> WHITE_PAWN_ITEM.get().getDefaultInstance()).displayItems((parameters, output) -> {
                // sort by piece value
                output.accept(WHITE_PAWN_ITEM.get());
                output.accept(WHITE_KNIGHT_ITEM.get());
                output.accept(WHITE_BISHOP_ITEM.get());
                output.accept(WHITE_ROOK_ITEM.get());
                output.accept(WHITE_QUEEN_ITEM.get());
                output.accept(WHITE_KING_ITEM.get());
                output.accept(BLACK_PAWN_ITEM.get());
                output.accept(BLACK_KNIGHT_ITEM.get());
                output.accept(BLACK_BISHOP_ITEM.get());
                output.accept(BLACK_ROOK_ITEM.get());
                output.accept(BLACK_QUEEN_ITEM.get());
                output.accept(BLACK_KING_ITEM.get());
            }).build());

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public Chess(IEventBus modEventBus, ModContainer modContainer) {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register the Deferred Register to the mod event bus so blocks get registered
        BLOCKS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so items get registered
        ITEMS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so tabs get registered
        CREATIVE_MODE_TABS.register(modEventBus);

        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (Chess) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(this);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }
}
