package rusty_badlands.client;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class DataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(ModEnglishLangProvider::new);
        pack.addProvider(ModModelProvider::new);
//        pack.addProvider(ModSoundProvider::new);
//        pack.addProvider(ModRecipeProvider::new);
    }
}