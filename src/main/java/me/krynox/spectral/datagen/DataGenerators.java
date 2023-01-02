package me.krynox.spectral.datagen;

import me.krynox.spectral.Spectral;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Spectral.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        generator.addProvider(event.includeServer(), (DataProvider.Factory<ModRecipes>) ModRecipes::new);
        generator.addProvider(event.includeServer(), (DataProvider.Factory<ModLootTables>) ModLootTables::new);
        generator.addProvider(event.includeServer(), (DataProvider.Factory<ModBlockTags>) ( o -> new ModBlockTags(o, event.getLookupProvider() , event.getExistingFileHelper())) );
//        generator.addProvider(event.includeServer(), (DataProvider.Factory<ModItemTags>) ( o -> new ModItemTags(o, event.getLookupProvider(), event.getExistingFileHelper())));
        generator.addProvider(event.includeClient(), (DataProvider.Factory<ModBlockStates>) ( o -> new ModBlockStates(o, event.getExistingFileHelper())));
        generator.addProvider(event.includeClient(), (DataProvider.Factory<ModItemModels>) ( o -> new ModItemModels(o, event.getExistingFileHelper())));
        generator.addProvider(event.includeClient(), (DataProvider.Factory<ModEnglishLanguageProvider>) ( o -> new ModEnglishLanguageProvider(o, "en_us")));
        generator.addProvider(event.includeClient(), (DataProvider.Factory<ModEnglishLanguageProvider>) ( o -> new ModEnglishLanguageProvider(o, "en_gb")));
        generator.addProvider(event.includeClient(), (DataProvider.Factory<ModEnglishLanguageProvider>) ( o -> new ModEnglishLanguageProvider(o, "en_ca")));
        generator.addProvider(event.includeClient(), (DataProvider.Factory<ModEnglishLanguageProvider>) ( o -> new ModEnglishLanguageProvider(o, "en_au")));
    }
}
