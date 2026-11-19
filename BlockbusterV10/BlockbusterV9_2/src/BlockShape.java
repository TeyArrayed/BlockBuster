import java.awt.*;
import java.util.Random;

public class BlockShape {
    private boolean[][] shape;
    private Color color;

    public BlockShape(boolean[][] shape, Color color) {
        this.shape = shape;
        this.color = color;
    }

    public boolean[][] getShape() {
        return shape;
    }

    public Color getColor() {
        return color;
    }

    // Predefined shapes:
    public static BlockShape L_SHAPE() {
        return new BlockShape(new boolean[][] {
                {true, false},
                {true, false},
                {true, true}
        }, Color.ORANGE);
    }

    public static BlockShape SQUARE() {
        return new BlockShape(new boolean[][] {
                {true, true},
                {true, true}
        }, Color.YELLOW);
    }

    public static BlockShape LINE() {
        return new BlockShape(new boolean[][] {
                {true, true, true, true}
        }, Color.CYAN);
    }

    public static BlockShape LINE2() {
        return new BlockShape(new boolean[][] {
                {true},
                {true},
                {true},
                {true}
        }, Color.MAGENTA);
    }

    public static BlockShape L_SHAPE2() {
        return new BlockShape(new boolean[][] {
                {true, true},
                {true, false},
                {true, false}
        }, Color.ORANGE);
    }

    public static BlockShape L_SHAPE3() {
        return new BlockShape(new boolean[][] {
                {false, true},
                {false, true},
                {true, true}
        }, Color.ORANGE);
    }

    public static BlockShape L_SHAPE4() {
        return new BlockShape(new boolean[][] {
                {true, true},
                {false, true},
                {false, true}
        }, Color.ORANGE);
    }

    public static BlockShape VAPE_SHAPE() {
        return new BlockShape(new boolean[][] {
                {true, false},
                {true, true},
                {true, true}
        }, Color.DARK_GRAY);
    }

    public static BlockShape T_SHAPE() {
        return new BlockShape(new boolean[][] {
                {true, true, true},
                {false, true, false}
        }, Color.BLUE);
    }

    public static BlockShape T_SHAPE2() {
        return new BlockShape(new boolean[][] {
                {false, true, false},
                {true, true, true}
        }, Color.BLUE);
    }

    public static BlockShape T_SHAPE3() {
        return new BlockShape(new boolean[][] {
                {false, true},
                {true, true},
                {false, true}
        }, Color.BLUE);
    }

    public static BlockShape T_SHAPE4() {
        return new BlockShape(new boolean[][] {
                {true, false},
                {true, true},
                {true, false}
        }, Color.BLUE);
    }

    public static BlockShape one_SHAPE() {
        return new BlockShape(new boolean[][] {
                {true}
        }, Color.GREEN);
    }

    public static BlockShape Three_SHAPE() {
        return new BlockShape(new boolean[][] {
                {true, false},
                {true, true}
        }, Color.RED);
    }

    public static BlockShape Three_SHAPE1() {
        return new BlockShape(new boolean[][] {
                {true, true},
                {true, false}
        }, Color.RED);
    }

    public static BlockShape Three_SHAPE2() {
        return new BlockShape(new boolean[][] {
                {false, true},
                {true, true}
        }, Color.RED);
    }

    public static BlockShape Three_SHAPE3() {
        return new BlockShape(new boolean[][] {
                {true, true},
                {false, true}
        }, Color.RED);
    }


    //Looked up online how to code this
    // Returns a random shape chosen from the predefined ones.
    public static BlockShape getRandomShape() {
        Random rand = new Random();
        int choice = rand.nextInt(17);
        switch(choice) {
            case 0: return L_SHAPE();
            case 1: return SQUARE();
            case 2: return LINE();
            case 3: return LINE2();
            case 4: return L_SHAPE2();
            case 5: return L_SHAPE3();
            case 6: return L_SHAPE4();
            case 7: return VAPE_SHAPE();
            case 8: return T_SHAPE();
            case 9: return T_SHAPE2();
            case 10: return T_SHAPE3();
            case 11: return T_SHAPE4();
            case 12: return one_SHAPE();
            case 13: return Three_SHAPE();
            case 14: return Three_SHAPE1();
            case 15: return Three_SHAPE2();
            case 16: return Three_SHAPE3();
            default: return SQUARE();
        }
    }

    @Override
    public String toString() {
        return "BlockShape[color=" + color + "]";
    }
}