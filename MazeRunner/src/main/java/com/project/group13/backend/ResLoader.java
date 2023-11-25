package com.project.group13.backend;

import com.project.group13.frontend.view.GameView;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/**
 * Loads resource
 */
public class ResLoader {

    /**
     * Loads sprite
     * @param filename name of the sprite image file
     * @return sprite image
     */
    public static Image loadSprite(String filename) {
        return new ImageIcon(Objects.requireNonNull(GameView.class.getResource("/sprites/" + filename))).getImage();
    }

    /**
     * Loads ui resource
     * @param filename name of the ui image file
     * @return ui image
     */
    public static Image loadUI(String filename) {
        return new ImageIcon(Objects.requireNonNull(GameView.class.getResource("/ui/" + filename))).getImage();
    }

    /**
     * Loads fonts resource
     * @param filename name of the font file
     * @param FONT_STYLE style of the font
     * @param FONT_SIZE size of the font
     * @return font
     */
    public static Font loadFont(String filename, int FONT_STYLE, int FONT_SIZE) {
        InputStream fontStream = Objects.requireNonNull(ResLoader.class.getResourceAsStream("/fonts/" + filename));
        Font customFont;
        try {
            customFont = Font.createFont(Font.TRUETYPE_FONT, fontStream);
        } catch (FontFormatException | IOException e) {
            return null;
        }
        return customFont.deriveFont(FONT_STYLE, FONT_SIZE);
    }

}