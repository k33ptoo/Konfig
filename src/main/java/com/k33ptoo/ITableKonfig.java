/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.k33ptoo;

import java.awt.Component;
import java.util.List;
import javax.swing.JTable;

/**
 *
 * @author Too
 */
 interface ITableKonfig {

    void configureTables(TableKonfig props, JTable... tables);
    
    void configureComponents(TableKonfig props, List<Component> components);

    void applySettings();
}
