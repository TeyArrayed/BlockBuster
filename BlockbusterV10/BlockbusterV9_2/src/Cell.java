import javax.swing.*;
import java.awt.*;

public class Cell extends JPanel {
    private boolean occupied = false;

    public Cell() {
        setBackground(Colors.BackgroundColor);
        setBorder(BorderFactory.createLineBorder(Colors.BorderColor));
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occ, String name, Color color) {
        this.occupied = occ;
        setBackground(occ ? color.brighter() : Colors.BorderColor);
        repaint();
    }

    public void clear() {
        occupied = false;
        setBackground(Colors.BackgroundColor);
        repaint();
    }



}