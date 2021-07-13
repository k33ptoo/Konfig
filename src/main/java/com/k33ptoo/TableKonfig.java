/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.k33ptoo;

import java.awt.Component;
import java.awt.Font;
import java.util.List;
import java.util.prefs.PreferenceChangeEvent;
import java.util.prefs.PreferenceChangeListener;
import javax.swing.JTable;

/**
 *
 * @author KeepToo
 */
 class TableKonfig implements ITableKonfig, PreferenceChangeListener {

    private int defFontSize;
    private int defCellHeight;
    private final String key;
    private final JTable[] tables;
    private final List<Component> allComponents;

    public TableKonfig(Class cname, List<Component> components, JTable... tables) {
        this.key = cname.getName();
        this.tables = tables;
        this.allComponents = components;
        KonfigPref.addPreferenceChangeListener(TableKonfig.this);
    }

    public int getFontSize() {
        return KonfigPref.getInt(key + "fontsize", getDefFontSize());
    }

    public void setFontSize(int fontSize) {
        KonfigPref.putInt(key + "fontsize", fontSize);
    }

    public int getRowHeight() {
        return KonfigPref.getInt(key + "rowheight", getDefRowHeight());
    }

    public void setRowHeight(int rowHeight) {
        KonfigPref.putInt(key + "rowheight", rowHeight);
    }

    public int getDefFontSize() {
        return defFontSize;
    }

    public void setDefFontSize(int defFontSize) {
        this.defFontSize = defFontSize;
    }

    public int getDefRowHeight() {
        return defCellHeight;
    }

    public void setDefRowHeight(int defCellHeight) {
        this.defCellHeight = defCellHeight;
    }

    @Override
    public void configureTables(TableKonfig props, JTable... tables) {
        if (tables == null) {
            return;
        }
        for (JTable table : tables) {
            //customize tables
            //set deaults - to avoid nulls
            int defaultFontSize = table.getFont().getSize();
            int defaultRowHeight = table.getRowHeight();
            props.setDefRowHeight(defaultRowHeight);
            props.setDefFontSize(defaultFontSize);

            //set from saved data
            Font font = table.getFont();
            font = font.deriveFont(0, props.getFontSize());
            table.setFont(font);
            table.setRowHeight(props.getRowHeight());
        }
    }

    @Override
    public void preferenceChange(PreferenceChangeEvent arg0) {
        configureTables(this, this.tables);
        configureComponents(this, this.allComponents);
        System.out.println("--konfig saving --- Key---- " + arg0.getKey() + " ---Value--- " + arg0.getNewValue());

    }

    @Override
    public void applySettings() {
        configureTables(this, this.tables);
        configureComponents(this, allComponents);
        System.out.println(this.getClass().getName() +"  --konfig apply settings--");
    }

    @Override
    public void configureComponents(TableKonfig props, List<Component> components) {
        if (components == null) {
            return;
        }
        for (Component component : components) {
            Font font = component.getFont();
            font = font.deriveFont(0, props.getFontSize());
            component.setFont(font);
            component.invalidate();
            component.repaint();;
        }

    }

}
