/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.k33ptoo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.BackingStoreException;
import java.util.prefs.PreferenceChangeListener;
import java.util.prefs.Preferences;

/**
 *
 * @author KeepToo
 */
class KonfigPref {

    private static final Preferences konfigPref;

    static {
        Preferences p = null;
        try {
            p = Preferences.userNodeForPackage(KonfigPref.class);
        } catch (Exception e) {
        }
        konfigPref = p;
    }

    public static int getInt(String key, int def) {
        return (konfigPref != null) ? konfigPref.getInt(key, def) : def;
    }

    public static boolean getBoolean(String key, boolean def) {
        return (konfigPref != null) ? konfigPref.getBoolean(key, def) : def;
    }

    public static String get(String key, String def) {
        return (konfigPref != null) ? konfigPref.get(key, def) : def;
    }

    public static void putInt(String key, int value) {
        if (konfigPref != null) {
            konfigPref.putInt(key, value);
        } else {
            System.err.println("Konfig: failed putInt - konfigPref is null");
        }
    }

    public static void putBoolean(String key, boolean value) {
        if (konfigPref != null) {
            konfigPref.putBoolean(key, value);
        } else {
            System.err.println("Konfig: failed putBoolean - konfigPref is null");
        }
    }

    public static void put(String key, String value) {
        if (konfigPref != null) {
            konfigPref.put(key, value);
        } else {
            System.err.println("Konfig: failed put - konfigPref is null");
        }
    }

    public static void addPreferenceChangeListener(PreferenceChangeListener pcl) {
        if (konfigPref != null) {
            konfigPref.addPreferenceChangeListener(pcl);
        } else {
            System.err.println("Konfig: failed addPreferenceChangeListener - konfigPref is null");
        }
    }

    public static void removeKey(String key) {
        if (konfigPref != null) {
            konfigPref.remove(key);
        } else {
            System.err.println("Konfig: failed removeKey - konfigPref is null");
        }
    }

    public static void removeKey(String... keys) {
        if (konfigPref != null) {
            for (String key : keys) {
                konfigPref.remove(key);
            }
        } else {
            System.err.println("Konfig: failed removeKey - konfigPref is null");
        }
    }

    public static void saveConfig(String dir) {
        try {
            //incase you want to a file
            File f = new File(dir + KonfigPref.class.getName() + ".props");
            OutputStream out = new FileOutputStream(f);
            //If you wish to make some comments 
            konfigPref.exportNode(out);
        } catch (IOException | BackingStoreException e) {
            System.err.println("saveKonfig() --- " + e.getMessage());
        }
    }

    public static boolean hasKey(String key) {
        try {
            for (String k : konfigPref.keys()) {
                if (key.equals(k)) {
                    return true;
                }
            }
        } catch (BackingStoreException e) {
        }
        return false;
    }

    public static void clearPref() {
        try {
            if (konfigPref != null) {
                konfigPref.clear();                
            } else {
                System.err.println("Konfig: failed to clear all - konfigPref is null");
            }
        } catch (BackingStoreException ex) {
            Logger.getLogger(KonfigPref.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
