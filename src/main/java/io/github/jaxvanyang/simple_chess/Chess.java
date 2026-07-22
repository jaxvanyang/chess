package io.github.jaxvanyang.simple_chess;

import io.github.jaxvanyang.simple_chess.block.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

@Mod(Chess.MODID)
public class Chess {
    public static final String MODID = "simple_chess";
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static final DeferredBlock<Block> WHITE_BISHOP = BLOCKS.registerBlock("white_bishop", Bishop::new, p -> p.mapColor(MapColor.QUARTZ).strength(0.8F, 0.8F).requiresCorrectToolForDrops());
    public static final DeferredBlock<Block> WHITE_KING = BLOCKS.registerBlock("white_king", King::new, p -> p.mapColor(MapColor.QUARTZ).strength(0.8F, 0.8F).requiresCorrectToolForDrops());
    public static final DeferredBlock<Block> WHITE_KNIGHT = BLOCKS.registerBlock("white_knight", Knight::new, p -> p.mapColor(MapColor.QUARTZ).strength(0.8F, 0.8F).requiresCorrectToolForDrops());
    public static final DeferredBlock<Block> WHITE_PAWN = BLOCKS.registerBlock("white_pawn", Pawn::new, p -> p.mapColor(MapColor.QUARTZ).strength(0.8F, 0.8F).requiresCorrectToolForDrops());
    public static final DeferredBlock<Block> WHITE_QUEEN = BLOCKS.registerBlock("white_queen", Queen::new, p -> p.mapColor(MapColor.QUARTZ).strength(0.8F, 0.8F).requiresCorrectToolForDrops());
    public static final DeferredBlock<Block> WHITE_ROOK = BLOCKS.registerBlock("white_rook", Rook::new, p -> p.mapColor(MapColor.QUARTZ).strength(0.8F, 0.8F).requiresCorrectToolForDrops());
    public static final DeferredBlock<Block> BLACK_BISHOP = BLOCKS.registerBlock("black_bishop", Bishop::new, p -> p.mapColor(MapColor.COLOR_BLACK).strength(1.5F, 6F).requiresCorrectToolForDrops());
    public static final DeferredBlock<Block> BLACK_KING = BLOCKS.registerBlock("black_king", King::new, p -> p.mapColor(MapColor.COLOR_BLACK).strength(1.5F, 6F).requiresCorrectToolForDrops());
    public static final DeferredBlock<Block> BLACK_KNIGHT = BLOCKS.registerBlock("black_knight", Knight::new, p -> p.mapColor(MapColor.COLOR_BLACK).strength(1.5F, 6F).requiresCorrectToolForDrops());
    public static final DeferredBlock<Block> BLACK_PAWN = BLOCKS.registerBlock("black_pawn", Pawn::new, p -> p.mapColor(MapColor.COLOR_BLACK).strength(1.5F, 6F).requiresCorrectToolForDrops());
    public static final DeferredBlock<Block> BLACK_QUEEN = BLOCKS.registerBlock("black_queen", Queen::new, p -> p.mapColor(MapColor.COLOR_BLACK).strength(1.5F, 6F).requiresCorrectToolForDrops());
    public static final DeferredBlock<Block> BLACK_ROOK = BLOCKS.registerBlock("black_rook", Rook::new, p -> p.mapColor(MapColor.COLOR_BLACK).strength(1.5F, 6F).requiresCorrectToolForDrops());

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

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> CHESS_TAB = CREATIVE_MODE_TABS.register("chess_tab", () -> CreativeModeTab.builder().title(Component.translatable("itemGroup.simple_chess")).withTabsBefore(CreativeModeTabs.COMBAT).icon(() -> WHITE_PAWN_ITEM.get().getDefaultInstance()).displayItems((parameters, output) -> {
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

    public Chess(IEventBus modEventBus, ModContainer modContainer) {
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);
    }
}
