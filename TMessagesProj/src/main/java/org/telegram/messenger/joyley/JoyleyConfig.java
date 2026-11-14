package org.telegram.messenger.joyley;

import android.content.Context;
import android.content.SharedPreferences;
import org.telegram.messenger.ApplicationLoader;

public class JoyleyConfig {

    private static final String PREF_NAME = "joyleyconfig";

    public static boolean ghostDontSendReadState = false;
    public static boolean ghostDontSendTyping = false;
    public static boolean allowScreenshots = true;
    public static boolean hidePhoneNumber = false;
    public static boolean unlimitedPinnedDialogs = false;

    private static boolean loaded = false;

    static {
        loadConfig();
    }

    public static void loadConfig() {
        if (loaded) return;
        Context context = ApplicationLoader.applicationContext;
        if (context == null) return;
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        ghostDontSendReadState = preferences.getBoolean("ghostDontSendReadState", false);
        ghostDontSendTyping = preferences.getBoolean("ghostDontSendTyping", false);
        allowScreenshots = preferences.getBoolean("allowScreenshots", true);
        hidePhoneNumber = preferences.getBoolean("hidePhoneNumber", false);
        unlimitedPinnedDialogs = preferences.getBoolean("unlimitedPinnedDialogs", false);
        loaded = true;
    }

    public static void saveConfig() {
        Context context = ApplicationLoader.applicationContext;
        if (context == null) return;
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        preferences.edit()
                .putBoolean("ghostDontSendReadState", ghostDontSendReadState)
                .putBoolean("ghostDontSendTyping", ghostDontSendTyping)
                .putBoolean("allowScreenshots", allowScreenshots)
                .putBoolean("hidePhoneNumber", hidePhoneNumber)
                .putBoolean("unlimitedPinnedDialogs", unlimitedPinnedDialogs)
                .apply();
    }

    public static void toggleGhostRead() {
        ghostDontSendReadState = !ghostDontSendReadState;
        saveConfig();
    }

    public static void toggleGhostTyping() {
        ghostDontSendTyping = !ghostDontSendTyping;
        saveConfig();
    }

    public static void toggleAllowScreenshots() {
        allowScreenshots = !allowScreenshots;
        saveConfig();
    }

    public static void toggleHidePhoneNumber() {
        hidePhoneNumber = !hidePhoneNumber;
        saveConfig();
    }

    public static void toggleUnlimitedPinned() {
        unlimitedPinnedDialogs = !unlimitedPinnedDialogs;
        saveConfig();
    }
}
