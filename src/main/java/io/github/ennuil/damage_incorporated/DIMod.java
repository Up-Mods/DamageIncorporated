package io.github.ennuil.damage_incorporated;

import io.github.ennuil.damage_incorporated.game_rules.DIGameRules;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;

public class DIMod implements ModInitializer {
    @Override
    public void onInitialize(ModContainer mod) {
        // Initialize the game rules
        new DIGameRules();
    }
}
