package io.github.jaxvanyang.chess;

import io.github.jaxvanyang.chess.block.*;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(Chess.MODID)
public class Chess {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "chess";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();
    // Create a Deferred Register to hold Blocks which will all be registered under the "chess" namespace
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);
    // Create a Deferred Register to hold Items which will all be registered under the "chess" namespace
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);
    // Create a Deferred Register to hold CreativeModeTabs which will all be registered under the "chess" namespace
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static final DeferredBlock<Block> BISHOP = BLOCKS.registerBlock("bishop", Bishop::new, p -> p.mapColor(MapColor.QUARTZ).strength(0.8F, 0.8F).requiresCorrectToolForDrops());
    public static final DeferredBlock<Block> KING = BLOCKS.registerBlock("king", King::new, p -> p.mapColor(MapColor.QUARTZ).strength(0.8F, 0.8F).requiresCorrectToolForDrops());
    public static final DeferredBlock<Block> KNIGHT = BLOCKS.registerBlock("knight", Knight::new, p -> p.mapColor(MapColor.QUARTZ).strength(0.8F, 0.8F).requiresCorrectToolForDrops());
    public static final DeferredBlock<Block> PAWN = BLOCKS.registerBlock("pawn", Pawn::new, p -> p.mapColor(MapColor.QUARTZ).strength(0.8F, 0.8F).requiresCorrectToolForDrops());
    public static final DeferredBlock<Block> QUEEN = BLOCKS.registerBlock("queen", Queen::new, p -> p.mapColor(MapColor.QUARTZ).strength(0.8F, 0.8F).requiresCorrectToolForDrops());
    public static final DeferredBlock<Block> ROOK = BLOCKS.registerBlock("rook", Rook::new, p -> p.mapColor(MapColor.QUARTZ).strength(0.8F, 0.8F).requiresCorrectToolForDrops());
    public static final DeferredItem<BlockItem> BISHOP_ITEM = ITEMS.registerSimpleBlockItem("bishop", BISHOP);
    public static final DeferredItem<BlockItem> KING_ITEM = ITEMS.registerSimpleBlockItem("king", KING);
    public static final DeferredItem<BlockItem> KNIGHT_ITEM = ITEMS.registerSimpleBlockItem("knight", KNIGHT);
    public static final DeferredItem<BlockItem> PAWN_ITEM = ITEMS.registerSimpleBlockItem("pawn", PAWN);
    public static final DeferredItem<BlockItem> QUEEN_ITEM = ITEMS.registerSimpleBlockItem("queen", QUEEN);
    public static final DeferredItem<BlockItem> ROOK_ITEM = ITEMS.registerSimpleBlockItem("rook", ROOK);

    // Creates a new food item with the id "chess:example_id", nutrition 1 and saturation 2
//    public static final DeferredItem<Item> EXAMPLE_ITEM = ITEMS.registerSimpleItem("example_item", p -> p.food(new FoodProperties.Builder()
//            .alwaysEdible().nutrition(1).saturationModifier(2f).build()));

    // Creates a creative tab with the id "chess:example_tab" for the example item, that is placed after the combat tab
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> EXAMPLE_TAB = CREATIVE_MODE_TABS.register("chess_tab", () -> CreativeModeTab.builder().title(Component.translatable("itemGroup.chess")) //The language key for the title of your CreativeModeTab
            .withTabsBefore(CreativeModeTabs.COMBAT).icon(() -> PAWN_ITEM.get().getDefaultInstance()).displayItems((parameters, output) -> {
                // sort by piece value
                output.accept(PAWN_ITEM.get());
                output.accept(KNIGHT_ITEM.get());
                output.accept(BISHOP_ITEM.get());
                output.accept(ROOK_ITEM.get());
                output.accept(QUEEN_ITEM.get());
                output.accept(KING_ITEM.get());
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

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(PAWN_ITEM);
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }
}
