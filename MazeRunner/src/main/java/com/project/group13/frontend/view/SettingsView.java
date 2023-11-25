
package com.project.group13.frontend.view;

import com.project.group13.backend.LeaderboardManager;
import com.project.group13.backend.ResLoader;
import com.project.group13.backend.SoundManager;
import com.project.group13.backend.ctrl.CtrlFactory;
import com.project.group13.backend.handler.InputHandler;
import com.project.group13.frontend.components.SButton;
import com.project.group13.frontend.components.StyledBtn;
import com.project.group13.frontend.components.StyledToggleBtn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

/**
 * SettingsView class extends BaseView to create a settings panel for the game.
 * It allows the user to toggle sound and music settings and navigate back to the main menu.
 * The class uses JToggleButton to reflect and change the state of sound and music based on user interaction.
 * It also prepares the background and adjusts the layout for the settings components.
 *
 * @author Ali Nikan
 * @author Kelvin Lu
 * @author Steven Wong
 * @author Shaima El Masry
 * @version 1.0
 */
public class SettingsView extends BaseView {

    private Image bg;
    private SButton musicToggleBtn, soundToggleBtn, returnBtn;
    private Font samurai, samuraiHighlight, nort;

    /**
     * Constructs settings view
     * @param inputHandler InputHandler object
     */
    public SettingsView(InputHandler inputHandler) {
        super(inputHandler);
        init();
        showView();
    }

    /**
     * Constructs settings view
     * @param jFrame JFrame object
     * @param inputHandler InputHandler object
     */
    public SettingsView(JFrame jFrame, InputHandler inputHandler) {
        super(jFrame, inputHandler);
        init();
        revalidateView();
    }

    /********************** Getter/Setter ***********************/

    public SButton[] getButtonList() {
        return new SButton[]{musicToggleBtn, soundToggleBtn, returnBtn};
    }

    /********************** Drawing methods ***********************/

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the background image
        g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
        drawScreenTitle(g);
        drawBtn(g, soundToggleBtn);
        drawBtn(g, musicToggleBtn);
        drawBtn(g, returnBtn);
    }

    /**
     * Draws game title
     * @param g graphics object
     */
    public void drawScreenTitle(Graphics g) {

        // Center the text horizontally and vertically within the button
        FontMetrics fm = g.getFontMetrics(samurai);
        int textX = (BaseView.GAME_WIDTH - fm.stringWidth("Settings")) / 2;

        g.setFont(samuraiHighlight);
        g.setColor(Color.WHITE);
        g.drawString("Settings", textX-7, 148);
        g.setFont(samurai);
        g.setColor(Color.RED);
        g.drawString("Settings", textX, 150);
    }

    /**
     * Draw button
     * @param g graphics object
     * @param button button object
     */
    public void drawBtn(Graphics g, SButton button) {
        g.drawImage(
                button.getImage(),
                button.getBounds().x,
                button.getBounds().y,
                button.getBounds().width,
                button.getBounds().height,
                this
        );

        // Center the text horizontally and vertically within the button
        FontMetrics fm = g.getFontMetrics(nort);
        int textX = button.getBounds().x + (button.getBounds().width - fm.stringWidth(button.getText())) / 2;
        int textY = button.getBounds().y + ((button.getBounds().height - fm.getHeight()) / 2) + fm.getAscent();

        g.setFont(nort);
        g.setColor(Color.BLACK);
        g.drawString(button.getText(), textX, textY);
    }

    /********************** Internal Methods ***********************/

    /**
     * Initializes components of the leaderboard
     */
    private void init() {
        // Set the background to black color
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.BLACK);

        // initializes background image
        bg = ResLoader.loadSprite("menuBg1.png");

        // initialize fonts
        this.samuraiHighlight = ResLoader.loadFont("SamuraiBlast.otf", Font.BOLD, 90);
        this.samurai = ResLoader.loadFont("SamuraiBlast.otf", Font.BOLD, 88);
        this.nort = ResLoader.loadFont("NortRight.otf", Font.BOLD, 24);

        // button dimensions
        int btnWidth = (int)((double)BaseView.GAME_WIDTH/4);
        int btnHeight = (int)((double)BaseView.GAME_HEIGHT/10);
        int vBtnMargin = btnHeight/4;
        int btnStartY = (BaseView.GAME_HEIGHT/2)-btnHeight;
        int btnStartX = (BaseView.GAME_WIDTH/2)-(btnWidth/2);

        Rectangle soundToggleBtnBounds = new Rectangle(btnStartX, btnStartY, btnWidth, btnHeight);
        this.soundToggleBtn = new StyledToggleBtn("Sound " + (SoundManager.SOUND_ON ? "ON" : "OFF"), soundToggleBtnBounds, SoundManager.SOUND_ON);
        this.soundToggleBtn.setActionListener(e -> {
            SoundManager.playClickSound();
            SoundManager.toggleSound();
            soundToggleBtn.setText("Sound " + (SoundManager.SOUND_ON ? "ON" : "OFF"));
        });

        Rectangle musicToggleBtnBounds = new Rectangle(btnStartX, btnStartY+btnHeight+vBtnMargin, btnWidth, btnHeight);
        this.musicToggleBtn = new StyledToggleBtn("Music " + (SoundManager.MUSIC_ON ? "ON" : "OFF"), musicToggleBtnBounds, SoundManager.MUSIC_ON);
        this.musicToggleBtn.setActionListener(e -> {
            SoundManager.playClickSound();
            SoundManager.toggleMusic();
            musicToggleBtn.setText("Music " + (SoundManager.MUSIC_ON ? "ON" : "OFF"));
        });

        Rectangle returnBtnBounds = new Rectangle(btnStartX, btnStartY+btnHeight*2+vBtnMargin*2, btnWidth, btnHeight);
        this.returnBtn = new StyledBtn("Return", returnBtnBounds);
        this.returnBtn.setActionListener(e -> {
            SoundManager.playClickSound();
            hideJFrame();
            CtrlFactory.createMenuCtrlInstance(getFrame());
        });
    }

    /**
     * Shows the view,
     * after initializing the jFrame
     */
    private void showView() {
        initJFrame();
        showJFrame();
    }

    /**
     * Shows the view,
     * after revalidating the jFrame
     */
    private void revalidateView() {
        revalidateJFrame();
        showJFrame();
    }

}