/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.k33ptoo;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.TableCell;
import javax.swing.JFrame;
import javax.swing.JTable;

/**
 *
 * @author KeepToo
 */
public class Konfig {

    private static FrameKonfig frameKonfig;
    private static TableKonfig tableKonfig;
    private static boolean isClearKonfig = false;

    /**
     * Saves the config of the component/frame specified when window closes and
     * restores state when window opens Saves - Size and Location
     *
     * @param components define the window to save size and location.
     */
    public static void saveKonfigurations(Component... components) {

        for (Component component : components) {
//            if (component instanceof JTable) {
//                JTable table = (JTable) component;
//                List<Component> ls = new ArrayList<>();
//                ls.add(component);
//                tableKonfig = new TableKonfig(table.getClass(), ls, table);
//            }

//          save frame configurations
            if (component instanceof JFrame) {
                Frame f = (Frame) component;
                frameKonfig = new FrameKonfig(f.getClass().getName(), f);
                f.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        if (isClearKonfig == false) {
                            frameKonfig.setSize(f.getSize());
                            frameKonfig.setLocation(f.getLocation());
                            frameKonfig.setTitle(f.getTitle());
                           }
                     }

                    @Override
                    public void windowOpened(WindowEvent e) {
                        frameKonfig.applySettings();
                    }

                });
            }
        }

    }

    /**
     * Clears all saved preferences for this app
     */
    public static void clearKonfigurations() {
        KonfigPref.clearPref();
        isClearKonfig = true;
    }

}
