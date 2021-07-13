/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.k33ptoo;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Point;
import java.util.prefs.PreferenceChangeEvent;
import java.util.prefs.PreferenceChangeListener;

/**
 *
 * @author KeepToo
 *
 */
 class FrameKonfig implements IFrameKonfig, PreferenceChangeListener {

    private Dimension defSize;
    private Point defLocation;
    private String defTitle;
    private final String keyValue;
    private final Frame[] allFrames;

    public FrameKonfig(String key, Frame... frames) {
        this.allFrames = frames;
        this.keyValue = key;
        KonfigPref.addPreferenceChangeListener(FrameKonfig.this);
    }

    public Dimension getSize() {
        int h = KonfigPref.getInt(keyValue + "sizeh", getDefSize().height);
        int w = KonfigPref.getInt(keyValue + "sizew", getDefSize().width);
        return new Dimension(w, h);
    }

    public void setSize(Dimension size) {
        KonfigPref.putInt(keyValue + "sizew", size.width);
        KonfigPref.putInt(keyValue + "sizeh", size.height);
    }

    public Point getLocation() {
        int x = KonfigPref.getInt(keyValue + "locationx", getDefLocation().x);
        int y = KonfigPref.getInt(keyValue + "locationy", getDefLocation().y);
        return new Point(x, y);
    }

    public void setLocation(Point location) {
        KonfigPref.putInt(keyValue + "locationx", location.x);
        KonfigPref.putInt(keyValue + "locationy", location.y);
    }

    public String getTitle() {
        return KonfigPref.get(keyValue + "title", getDefTitle());
    }

    public void setTitle(String title) {
        KonfigPref.put(keyValue + "title", title);
    }

    public Dimension getDefSize() {
        return defSize;
    }

    public void setDefSize(Dimension defSize) {
        this.defSize = defSize;
    }

    public Point getDefLocation() {
        return defLocation;
    }

    public void setDefLocation(Point defLocation) {
        this.defLocation = defLocation;
    }

    public String getDefTitle() {
        return defTitle;
    }

    public void setDefTitle(String defTitle) {
        this.defTitle = defTitle;
    }

    @Override
    public void configureFrame(FrameKonfig konfig, Frame... frames) {

        for (Frame frame : frames) {
            //set defaults to avoid null;
            konfig.setDefLocation(frame.getLocation());
            konfig.setDefSize(frame.getSize());
            konfig.setDefTitle(frame.getTitle());

            //set from save configs
            frame.setTitle(konfig.getTitle());
            frame.setLocation(konfig.getLocation());
            frame.setSize(konfig.getSize());

        }
    }

    @Override
    public void preferenceChange(PreferenceChangeEvent arg0) {
        configureFrame(this, this.allFrames);
        System.out.println("--preference saving --- Key---- " + arg0.getKey() + " ---Value--- " + arg0.getNewValue());
    }

    @Override
    public void applySettings() {
        configureFrame(this, this.allFrames);
        System.out.println(this.getClass().getName() +"  --konfig apply settings--");
    }
}


