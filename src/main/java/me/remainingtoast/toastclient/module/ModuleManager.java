package me.remainingtoast.toastclient.module;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import me.remainingtoast.toastclient.ToastClient;
import me.remainingtoast.toastclient.command.CommandManager;
import me.remainingtoast.toastclient.managers.HashMapManager;
import me.remainingtoast.toastclient.module.modules.combat.AutoTotem;
import me.remainingtoast.toastclient.module.modules.combat.CrystalAura;
import me.remainingtoast.toastclient.module.modules.combat.KillAura;
import me.remainingtoast.toastclient.module.modules.exploits.AntiHunger;
import me.remainingtoast.toastclient.module.modules.gui.ClickGui;
import me.remainingtoast.toastclient.module.modules.gui.Console;
import me.remainingtoast.toastclient.module.modules.gui.HUD;
import me.remainingtoast.toastclient.module.modules.misc.DiscordRPC;
import me.remainingtoast.toastclient.module.modules.movement.ElytraFlight;
import me.remainingtoast.toastclient.module.modules.movement.Flight;
import me.remainingtoast.toastclient.module.modules.movement.Sprint;
import me.remainingtoast.toastclient.module.modules.player.AutoReconnect;
import me.remainingtoast.toastclient.module.modules.player.AutoRespawn;
import me.remainingtoast.toastclient.module.modules.player.DeathCoords;
import me.remainingtoast.toastclient.module.modules.player.NoFall;
import me.remainingtoast.toastclient.module.modules.render.BlockHighlight;
import me.remainingtoast.toastclient.module.modules.render.FullBright;
import me.remainingtoast.toastclient.module.modules.render.PlayerESP;
import me.remainingtoast.toastclient.util.MessageUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;

import java.io.*;
import java.util.HashSet;

public class ModuleManager extends HashMapManager<String, Module> {

    public final HashSet<Module> modulesSet = new HashSet<>();
    private final HashSet<Integer> unReleasedKeys = new HashSet<>();
    private File directory;

    public ModuleManager(File directory) {
        this.directory = directory;
        if (!directory.exists())
            directory.mkdir();
    }

    @Override
    public void load() {
        super.load();
        modulesSet.clear();
        register(new ClickGui(), new NoFall(), new HUD(), new FullBright(), new Flight(), new AntiHunger(), new ElytraFlight(), new DiscordRPC(), new KillAura(),
                new Sprint(), new AutoTotem(), new PlayerESP(), new CrystalAura(), new AutoRespawn(), new AutoReconnect(), new DeathCoords(),
                new BlockHighlight(), new Console());
        getRegistry().values().forEach(Module::init);
        this.loadCheats();
    }

    @Override
    public void unload() {
        this.saveCheats();
    }

    public void saveCheats() {
        if (getRegistry().values().isEmpty()) {
            directory.delete();
        }
        File[] files = directory.listFiles();
        if (!directory.exists()) {
            directory.mkdir();
        } else if (files != null) {
            for (File file : files) {
                file.delete();
            }
        }
        getRegistry().values().forEach(module -> {
            File file = new File(directory, module.getName() + ".json");
            JsonObject node = new JsonObject();
            module.save(node);
            if (node.entrySet().isEmpty()) {
                return;
            }
            try {
                file.createNewFile();
            } catch (IOException e) {
                return;
            }
            try (Writer writer = new FileWriter(file)) {
                writer.write(new GsonBuilder().setPrettyPrinting().create().toJson(node));
            } catch (IOException e) {
                file.delete();
            }
        });
        files = directory.listFiles();
        if (files == null || files.length == 0) {
            directory.delete();
        }
    }

    public void loadCheats() {
        getRegistry().values().forEach(module -> {
            final File file = new File(directory, module.getName() + ".json");
            if (!file.exists()) {
                return;
            }
            try (Reader reader = new FileReader(file)) {
                JsonElement node = new JsonParser().parse(reader);
                if (!node.isJsonObject()) {
                    return;
                }
                module.load(node.getAsJsonObject());
            } catch (IOException e) {
            }
        });
    }

    public void register(Module... modules) {
        for (Module cheat : modules) {
            if (cheat.getName() != null)
                include(cheat.getName().toLowerCase(), cheat);
            modulesSet.add(cheat);
        }
    }

    // there is a check in the method for whether or not it is the correct class
    @SuppressWarnings("unchecked")
    public <T extends Module> T getModuleByClass(Class<T> clazz) {
        for (Module current : modulesSet) {
            if (current.getClass() == clazz) return (T) current;
        }
        return null;
    }

    public Module getModuleByName(String name) {
        for (Module current : modulesSet) {
            if (current.getName().equalsIgnoreCase(name)) return current;
        }
        return null;
    }

    public HashSet<Module> getModulesInCat(Module.Category category) {
        HashSet<Module> modulesInCategory = new HashSet<>();
        for (Module current : modulesSet) {
            if (current.getCategory() == category) modulesInCategory.add(current);
        }
        return modulesInCategory;
    }

    @SubscribeEvent
    public void inputEvent(InputEvent.KeyInputEvent event) {
        ToastClient.INSTANCE.getModuleManager().getRegistry().values().forEach(module -> {
            if(module.getKey() == Keyboard.getEventKey()) module.toggle();
        });
        if (("" + Keyboard.getEventCharacter()).equalsIgnoreCase(CommandManager.getCommandPrefix()) && !(Minecraft.getMinecraft().player.isSneaking())) {
            Minecraft.getMinecraft().displayGuiScreen(new GuiChat(CommandManager.getCommandPrefix()));
            MessageUtil.sendMessage("Opened chat using command prefix! "+"\"" + CommandManager.getCommandPrefix() + "\"", MessageUtil.Color.GREEN);
        }
    }

    public HashSet<Module> getModules() {
        return modulesSet;
    }

    @SubscribeEvent
    public void onTick(TickEvent e) {
        if (Minecraft.getMinecraft().player != null) {
            for (Module module : modulesSet) {
                if (module.isEnabled()) module.onTick();
            }
        }
    }
}
