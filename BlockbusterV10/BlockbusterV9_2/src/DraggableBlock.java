import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DraggableBlock extends JPanel {
    private BlockBuster game;
    private BlockShape shape;
    private Point dragStart;
    private Rectangle initialBounds;

    public DraggableBlock(BlockBuster game, BlockShape shape) {
        this.game = game;
        this.shape = shape;
        setOpaque(false); // Transparent for custom drawing (Looked up because didn't know what to use)

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // Get the layered pane for reparenting.
                JLayeredPane layeredPane = game.getFrame().getLayeredPane();

                // Convert the mouse press location to the layered pane's coordinates.
                Point mousePtScreen = e.getLocationOnScreen();
                Point layeredPt = new Point(mousePtScreen);
                SwingUtilities.convertPointFromScreen(layeredPt, layeredPane);

                // Immediately set the block's location so that its top left corner is at the mouse location.
                setLocation(layeredPt);

                // Set dragStart to (0, 0) so dragging calculations always move from the top left.
                dragStart = new Point(0, 0);

                // Add the block to the layered pane for dragging.
                layeredPane.add(DraggableBlock.this, JLayeredPane.DRAG_LAYER);
                layeredPane.revalidate();
                layeredPane.repaint();
            }


            @Override
            public void mouseReleased(MouseEvent e) {
                // Convert drop point to gridPanel coordinates.
                Point dropPoint = SwingUtilities.convertPoint(DraggableBlock.this, e.getPoint(), game.getGridPanel());
                int gridRow = dropPoint.y / BlockBuster.CELL_SIZE;
                int gridCol = dropPoint.x / BlockBuster.CELL_SIZE;
                boolean succesfullyPlaced = false;

                // If drop is outside grid, snap back.
                if (dropPoint.x < 0 || dropPoint.x >= game.getGridPanel().getWidth() ||
                        dropPoint.y < 0 || dropPoint.y >= game.getGridPanel().getHeight()) {
                    setLocation(initialBounds.x, initialBounds.y);
                } else {
                    // Validate placement.
                    if (game.canPlaceBlock(shape, gridRow, gridCol)) {
                        game.placeBlock(shape, gridRow, gridCol);
                        game.piecePlaced();
                        succesfullyPlaced = true;
                        // Do not return the block to the bank.
                    } else {
                        // If invalid, snap back.
                        setLocation(initialBounds.x, initialBounds.y);
                    }
                }
                // Remove from layered pane.
                Container parent = getParent();
                if (parent != null) {
                    parent.remove(DraggableBlock.this);
                    parent.revalidate();
                    parent.repaint();
                }
                // Ensure block is in the bank if not placed.
                if (!succesfullyPlaced) {
                    if (!game.getBankPanel().isAncestorOf(DraggableBlock.this)) {
                        game.getBankPanel().add(DraggableBlock.this);
                        game.getBankPanel().revalidate();
                        game.getBankPanel().repaint();
                    }
                }
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                //Didn't know what to use so asked chat
                Point current = getLocation();
                current.translate(e.getX() - dragStart.x, e.getY() - dragStart.y);
                setLocation(current);
            }
        });
    }

    public void setInitialBounds(Rectangle bounds) {
        // Make a copy to avoid mutable references.
        initialBounds = new Rectangle(bounds);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        boolean[][] arr = shape.getShape();
        int drawCellSize = 20; // scale factor for drawing the shape
        for (int ii = 0; ii < arr.length; ii++) {
            for (int jj = 0; jj < arr[ii].length; jj++) {
                if (arr[ii][jj]) {
                    //Fill the cell with the block's color
                    g.setColor(shape.getColor());
                    g.fillRect(jj * drawCellSize, ii * drawCellSize, drawCellSize, drawCellSize);
                    //Draw a border around the cell
                    g.setColor(Color.BLACK);
                    g.drawRect(jj * drawCellSize, ii * drawCellSize, drawCellSize, drawCellSize);
                }
            }
        }
    }
}