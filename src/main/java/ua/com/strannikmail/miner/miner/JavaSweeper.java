package ua.com.strannikmail.miner.miner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Sergii Shcherbakov on 06.12.2017.
 */
public class JavaSweeper extends JFrame {
    private final int BOMBS;
    private final int COLS;
    private final int ROWS;
    private final int IMAGE_SIZE;
    private Game game;
    private JPanel panel;
    private JLabel label;

    public static void main(String[] args) {
        new JavaSweeper(args).setVisible(true);
    }

    private void initLabel(){
        label = new JLabel("Welcome!");
        add(label, BorderLayout.SOUTH);
    }

    private JavaSweeper(String[] params){
        this.COLS =  Integer.parseInt( params[0]);
        this.ROWS  = Integer.parseInt( params[1]);
        this.BOMBS = Integer.parseInt( params[2]);
        this.IMAGE_SIZE = Integer.parseInt( params[3]);
       game = new Game(COLS, ROWS, BOMBS);
       game.start();
        setImages();
        initLabel();
        initPanel();
        innitFrame();
    }


    private void initPanel(){
        panel = new JPanel()
        {
            @Override
            protected void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                for (Coord coord : Ranges.getAllCoords()){
                    g.drawImage( game.getBox(coord),
                            coord.getX() * IMAGE_SIZE, coord.getY() * IMAGE_SIZE,
                            this);
                }
            }
        };
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX() / IMAGE_SIZE;
                int y = e.getY() / IMAGE_SIZE;
                Coord mouseClickCoord = new Coord(x, y);
                if(e.getButton() == MouseEvent.BUTTON1 &&
                        game.getState() != GameState.BOMBED){
                    game.pressLeftButton(mouseClickCoord);
                } else if(e.getButton() == MouseEvent.BUTTON3&&
                        game.getState() != GameState.BOMBED){
                    game.pressRightButton(mouseClickCoord);
                } else if(e.getButton() == MouseEvent.BUTTON2){
                    game.start();
                }
                label.setText(getMessage());
                panel.repaint();
            }
        });
        panel.setPreferredSize(new Dimension( Ranges.getSize().getX() * IMAGE_SIZE,
                Ranges.getSize().getY() * IMAGE_SIZE));
        add(panel);
    }

    private String getMessage() {
        GameState state = game.getState();
        if(state == GameState.WINNER){
            return "You are Winner!!!!!";
        } else if(state == GameState.BOMBED){
            return "You loose:(";
        }
        return "Think Twice!!!";
    }

    private void innitFrame(){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Java Sweeper");
        setResizable(false);
        setVisible(true);
        setIconImage(getImage("icon"));
        pack();
        setLocationRelativeTo(null);
        setExtendedState(JFrame.NORMAL);
    }

    private void setImages(){
        for(BombIcon bombIcon : BombIcon.values()){
            bombIcon.image = getImage(bombIcon.name());
        }
        for(FlagIcon flagIcon : FlagIcon.values()){
            flagIcon.image = getImage(flagIcon.name());
        }
    }

    private Image getImage(String name){

        String fileName = "img/" + name.toLowerCase() + ".png";
        System.out.println(getClass().getClassLoader().getResource( fileName));
        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource( fileName) );

        return  icon.getImage();
    }
}