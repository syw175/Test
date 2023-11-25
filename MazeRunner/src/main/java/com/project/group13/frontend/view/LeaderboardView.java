
package com.project.group13.frontend.view;

import com.project.group13.backend.LeaderboardManager;
import com.project.group13.backend.ResLoader;
import com.project.group13.backend.SoundManager;
import com.project.group13.backend.ctrl.CtrlFactory;
import com.project.group13.backend.handler.InputHandler;
import com.project.group13.frontend.components.StyledBtn;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * LeaderboardView extends BaseView to show the leaderboard. It displays a list of high scores
 * and allows navigation back to the main menu. It initializes its own UI components upon creation
 * and handles the user interactions through button clicks and mouse events.
 * The background and buttons are styled and responsive to mouse events.
 * 
 * @author Ali Nikan
 * @author Kelvin Lu
 * @author Steven Wong
 * @author Shaima El Masry
 * @version 1.0
 */
public class LeaderboardView extends BaseView {

    private Image bg; // Background image for the leaderboard view.
    private StyledBtn returnBtn;
    private Font samurai, samuraiHighlight, nort, regular, toonNumbers;

    /**
     * Constructor for LeaderboardView that takes an InputHandler.
     * It initializes the view's components and makes the view visible.
     * 
     * @param inputHandler The handler for user input.
     */
    public LeaderboardView(InputHandler inputHandler) {
        super(inputHandler);
        init(); // Initialize the components.
        showView(); // Display the view.
    }

    /**
     * Constructor for LeaderboardView that takes a JFrame and an InputHandler.
     * It uses the given JFrame and initializes the view's components.
     * 
     * @param jFrame The frame in which the view is displayed.
     * @param inputHandler The handler for user input.
     */
    public LeaderboardView(JFrame jFrame, InputHandler inputHandler) {
        super(jFrame, inputHandler);
        init(); // Initialize the components.
        revalidateView(); // Revalidate the view.
    }

    /********************** Getter/Setter ***********************/

    public StyledBtn[] getButtonList() {
        return new StyledBtn[]{returnBtn};
    }

    /********************** Drawing methods ***********************/

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // Call superclass method to ensure proper JPanel painting.
        g.drawImage(bg, 0, 0, getWidth(), getHeight(), this); // Draw the background image.
        drawScreenTitle(g);
        drawBtn(g, returnBtn);
        drawScores(g);
    }

    /**
     * Draws screen title
     * 
     * @param g graphics object
     */
    public void drawScreenTitle(Graphics g) {

        // Center the text horizontally and vertically within the button
        FontMetrics fm = g.getFontMetrics(samurai);
        int textX = (BaseView.GAME_WIDTH - fm.stringWidth("Leaderboard")) / 2;

        g.setFont(samuraiHighlight);
        g.setColor(Color.WHITE);
        g.drawString("Leaderboard", textX-7, 148);
        g.setFont(samurai);
        g.setColor(Color.RED);
        g.drawString("Leaderboard", textX, 150);
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
     * Draws leaderboard scores
     * @param g graphics object
     */
    public void drawScores(Graphics g) {
        g.setFont(nort);
        g.setColor(Color.DARK_GRAY);
        g.drawString("Position", 265, 230);
        g.drawString("Score", 465, 230);

        List<Integer> scores = LeaderboardManager.getTopScores();
        final int scoresSize = Math.min(5, scores.size());

        if (scoresSize <= 0) {
            g.setColor(Color.RED);
            g.setFont(nort);
            g.drawString("No records found", 305, 350);
            return;
        }

        g.setColor(Color.RED);
        g.setFont(toonNumbers);
        for (int i = 0; i < scoresSize; i++) {
            g.drawString(String.valueOf(i+1), 295, 280+(i*45));
        }

        g.setColor(Color.BLACK);
        g.setFont(regular);
        for (int i = 0; i < scoresSize; i++) {
            g.drawString(String.valueOf(scores.get(i)), 480, 275+(i*46));
        }
    }

    /********************** Internal Methods ***********************/

    /**
     * Initializes the components of the leaderboard view.
     * It sets up the layout, background, title, leaderboard list, scroll pane, and home button.
     * The method configures the alignment and spacing of components and adds the necessary action listeners.
     */
    private void init() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // Use BoxLayout for vertical stacking of components.
        setBackground(Color.BLACK); // Set the background color of the panel.

        // Load the background image.
        bg = ResLoader.loadSprite("menuBg1.png");

        // initialize fonts
        this.samuraiHighlight = ResLoader.loadFont("SamuraiBlast.otf", Font.BOLD, 90);
        this.samurai = ResLoader.loadFont("SamuraiBlast.otf", Font.BOLD, 88);
        this.nort = ResLoader.loadFont("NortRight.otf", Font.BOLD, 24);
        this.regular = ResLoader.loadFont("Regular.otf", Font.BOLD, 18);
        this.toonNumbers = ResLoader.loadFont("ToonNumbers.ttf", Font.BOLD, 44);

        // button dimensions
        int btnWidth = (int)((double)BaseView.GAME_WIDTH/4);
        int btnHeight = (int)((double)BaseView.GAME_HEIGHT/10);
        int btnStartY = BaseView.GAME_HEIGHT - btnHeight*2 + btnHeight/2;
        int btnStartX = (BaseView.GAME_WIDTH/2)-(btnWidth/2);

        Rectangle returnBtnBounds = new Rectangle(btnStartX, btnStartY, btnWidth, btnHeight);
        this.returnBtn = new StyledBtn("Return", returnBtnBounds);
        this.returnBtn.setActionListener(e -> {
            SoundManager.playClickSound(); // Play a click sound when the button is pressed.
            hideJFrame(); // Hide the current frame.
            CtrlFactory.createMenuCtrlInstance(getFrame()); // Navigate to the menu control.
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