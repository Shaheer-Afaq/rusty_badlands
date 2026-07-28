package rusty_badlands.client.data_generation;

import net.fabricmc.fabric.api.client.datagen.v1.builder.SoundTypeBuilder;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricSoundsProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;
import rusty_badlands.ModSounds;
import rusty_badlands.RustyBadlands;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

public class ModSoundProvider extends FabricSoundsProvider {
    public ModSoundProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture){
        super(output, registriesFuture);
    }

    @Override
    protected void configure(HolderLookup.Provider registryLookup, SoundExporter exporter) {
        register(exporter, ModSounds.GEYSER_ERUPT, "geyser_erupt");
    }

    void register(SoundExporter exporter, SoundEvent sound, String... files) {
        SoundTypeBuilder builder = SoundTypeBuilder.of().subtitle(sound.location().toLanguageKey("sound"));

        Arrays.stream(files).forEach(file -> builder.sound(
                SoundTypeBuilder.RegistrationBuilder.create(
                        SoundTypeBuilder.RegistrationType.FILE,
                        Identifier.fromNamespaceAndPath(RustyBadlands.MOD_ID, file)
                )
        ));
        exporter.add(sound, builder);
    }

    @Override
    public String getName() {
        return "SoundProvider";
    }
}
