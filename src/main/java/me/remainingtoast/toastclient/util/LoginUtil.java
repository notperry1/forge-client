package me.remainingtoast.toastclient.util;

import com.mojang.authlib.Agent;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import com.mojang.authlib.yggdrasil.YggdrasilUserAuthentication;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Session;

import java.lang.reflect.Field;
import java.net.Proxy;
import java.util.UUID;

public class LoginUtil {

    private static final Minecraft mc = Minecraft.getMinecraft();

    public static boolean setSession(Session newSession) {
        if (mc == null) return false;
        if (mc.getClass() == null) return false;
        if (mc.getClass().getDeclaredFields() == null) return false;
        Field newField = null;
        for (Field field : mc.getClass().getDeclaredFields()) {
            if (field.getName().equalsIgnoreCase("session") || field.getName().equalsIgnoreCase("field_1726")) {
                newField = field;
            }
        }
        try {
            newField.setAccessible(true);
            newField.set(mc, newSession);
            newField.setAccessible(false);
            return true;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean loginWithPass(String mail, String pass) {
        if (mail.isEmpty() || pass.isEmpty()) return false;
        YggdrasilUserAuthentication auth = (YggdrasilUserAuthentication) new YggdrasilAuthenticationService(Proxy.NO_PROXY, "").createUserAuthentication(Agent.MINECRAFT);
        auth.setUsername(mail);
        auth.setPassword(pass);
        try {
            auth.logIn();
        } catch (com.mojang.authlib.exceptions.AuthenticationException e) {
            e.printStackTrace();
            return false;
        }
        Session account = new Session(auth.getSelectedProfile().getName(), auth.getSelectedProfile().getId().toString(), auth.getAuthenticatedToken(), "mojang");
        return setSession(account);
    }

    public static boolean loginCracked(String name){
        if(name.isEmpty()) return false;
        Session session = new Session(name, UUID.randomUUID().toString(), "0", "legacy");
        return setSession(session);
    }

}
