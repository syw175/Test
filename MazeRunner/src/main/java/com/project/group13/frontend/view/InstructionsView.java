package com.project.group13.frontend.view;

import com.project.group13.backend.ResLoader;
import com.project.group13.backend.SoundManager;
import com.project.group13.backend.ctrl.CtrlFactory;
import com.project.group13.backend.handler.InputHandler;
import com.project.group13.frontend.components.StyledBtn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

/**
 * Draws the instructions view
 */
public class InstructionsView extends BaseView {

    private Image bg;
    private StyledBtn returnBtn;
    private Font samurai, samuraiHighlight, nort;

    /**
     * Constructs leaderboard view
     * @param inputHandler InputHandler object
     */
    public InstructionsView(InputHandler inputHandler) {
        super(inputHandler);
        init();
        showView();
    }

    /**
     * Constructs menu view
     * @param jFrame JFrame object
     * @param inputHandler InputHandler object
     */
    public InstructionsView(JFrame jFrame, InputHandler inputHandler) {
        super(jFrame, inputHandler);
        init();
        revalidateView();
    }

    /********************** Getter/Setter ***********************/

    public StyledBtn[] getButtonList() {
        return new StyledBtn[]{returnBtn};
    }

    /********************** Drawing methods ***********************/

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the background image
        g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
        drawScreenTitle(g);
        drawBtn(g, returnBtn);
        drawInstructions(g);
    }

    /**
     * Draws screen title
     * @param g graphics object
     */
    public void drawScreenTitle(Graphics g) {

        // Center the text horizontally and vertically within the button
        FontMetrics fm = g.getFontMetrics(samurai);
        int textX = (BaseView.GAME_WIDTH - fm.stringWidth("Instructions")) / 2;

        g.setFont(samuraiHighlight);
        g.setColor(Color.WHITE);
        g.drawString("Instructions", textX-7, 148);
        g.setFont(samurai);
        g.setColor(Color.RED);
        g.drawString("Instructions", textX, 150);
    }

    /**
     * Draw button
     * @param g graphics object
     * @param button button object
     */
    public void drawBtn(Graphics g, StyledBtn button) {
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

    /**
     * Draw instructions
     * @param g graphics object
     */
    public void drawInstructions(Graphics g) {
        String text = "Welcome to the Maze Game";
        String text2 = "Navigate the maze - Collect rewards - Find the exit door for the next level";
        String text3 = "Use arrow keys to move and gather life and health for better survival";
        String text4 = "Aim for a quick finish for higher scores";
        String text5 = "Avoid enemies and dodge obstacles and use power-ups against foes";
        String text6 = "Press ESCAPE to return to the main menu";
        String text7 = "Enjoy your adventure in the game";

        g.setFont(nort);
        g.setColor(Color.DARK_GRAY);
        g.drawString(text, 50, 250);
        g.drawString(text2, 50, 290);
        g.drawString(text3, 50, 315);
        g.drawString(text4, 50, 340);
        g.drawString(text5, 50, 365);
        g.drawString(text6, 50, 390);
        g.drawString(text7, 50, 420);
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
        this.nort = ResLoader.loadFont("NortRight.otf", Font.PLAIN, 18);

        // button dimensions
        int btnWidth = (int)((double)BaseView.GAME_WIDTH/4);
        int btnHeight = (int)((double)BaseView.GAME_HEIGHT/10);
        int btnStartY = BaseView.GAME_HEIGHT - btnHeight*2 + btnHeight/2;
        int btnStartX = (BaseView.GAME_WIDTH/2)-(btnWidth/2);

        Rectangle returnBtnBounds = new Rectangle(btnStartX, btnStartY, btnWidth, btnHeight);
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