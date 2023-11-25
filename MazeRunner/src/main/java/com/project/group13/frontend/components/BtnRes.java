package com.project.group13.frontend.components;

import com.project.group13.backend.ResLoader;
import java.awt.*;

/**
 * Holds styled button component resource
 */
public class BtnRes {

    public static Image IDLE = ResLoader.loadUI("button_idle.png");
    public static Image HOVER = ResLoader.loadUI("button_hover.png");
    public static Image DOWN = ResLoader.loadUI("button_clicked.png");

}