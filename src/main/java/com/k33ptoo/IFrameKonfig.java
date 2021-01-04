/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.k33ptoo;

import java.awt.Frame;

/**
 *
 * @author KeepToo
 */
 interface IFrameKonfig {
   public void configureFrame(FrameKonfig props, Frame... frames);
   public void applySettings();
}
