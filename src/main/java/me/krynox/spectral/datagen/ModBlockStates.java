package me.krynox.spectral.datagen;

import me.krynox.spectral.Spectral;
import me.krynox.spectral.setup.Registration;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockStates extends BlockStateProvider {
    public ModBlockStates(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Spectral.MODID, exFileHelper);
    }

    private void simpleBlockWithItem(Block block) {
        simpleBlockWithItem(block, cubeAll(block));
    }

    @Override
    protected void registerStatesAndModels() {
       simpleBlockWithItem(Registration.LEY_CONDUIT_BLOCK.get());
       simpleBlockWithItem(Registration.SOUL_MIRROR_BLOCK.get());
       simpleBlockWithItem(Registration.SPECTRAL_FORGE_BLOCK.get());
       simpleBlockWithItem(Registration.SPIRIT_LOCUS_BLOCK.get());

    }
}
