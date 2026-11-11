package net.xivenestriealsilver.call_of_gaia.keymapping;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.neoforged.neoforge.common.util.Lazy;
import org.lwjgl.glfw.GLFW;

public class ModKeyMappings {
    private static final KeyMapping KEY_MAPPING_TAROT = new KeyMapping("key.call_of_gaia.tarot",
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_G, "key.call_of_gaia.category");
    public static final Lazy<KeyMapping> PRESS_TAROT = Lazy.of(() -> KEY_MAPPING_TAROT);

    public static void register() {
        // Register key mappings here if needed
    }

}
