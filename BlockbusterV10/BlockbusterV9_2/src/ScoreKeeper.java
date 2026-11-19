import java.io.*;
import java.util.ArrayList;

public class ScoreKeeper {
    private int score0;
    public int getScore0() {
        return score0;
    }
    public void setScore0(int score0) {
        this.score0 = score0;
    }

    private int score1;
    public int getScore1() {
        return score1;
    }
    public void setScore1(int score1) {
        this.score1 = score1;
    }

    private int score2;
    public int getScore2() {
        return score2;
    }
    public void setScore2(int score2) {
        this.score2 = score2;
    }

    private int score3;
    public int getScore3() {
        return score3;
    }
    public void setScore3(int score3) {
        this.score3 = score3;
    }

    private int score4;
    public int getScore4() {
        return score4;
    }
    public void setScore4(int score4) {
        this.score4 = score4;
    }

    private String filePath;
    // Constructor to initialize the file path
    public ScoreKeeper(String filePath) {
        this.filePath = filePath;
    }

    public void getScore() {
            ArrayList<String> scoresList = new ArrayList<>();
        try {
            System.out.println("file path is " + filePath);
            FileReader file = new FileReader(filePath);
            BufferedReader br = new BufferedReader(file);
            String line;
            while ((line = br.readLine()) != null) {
                scoresList.add(line);
            }
            br.close();
            file.close();

            System.out.println(scoresList);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        setScore0(Integer.valueOf(scoresList.get(0)));
        setScore1(Integer.valueOf(scoresList.get(1)));
        setScore2(Integer.valueOf(scoresList.get(2)));
        setScore3(Integer.valueOf(scoresList.get(3)));
        setScore4(Integer.valueOf(scoresList.get(4)));

    }

    public void saveScores() throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write(String.valueOf(BlockBuster.score0) + "\n");
            bw.write(String.valueOf(BlockBuster.score1) + "\n");
            bw.write(String.valueOf(BlockBuster.score2) + "\n");
            bw.write(String.valueOf(BlockBuster.score3) + "\n");
            bw.write(String.valueOf(BlockBuster.score4) + "\n");
        } catch (IOException e) {
            System.err.println("Error saving scores: " + e.getMessage());
        }
    }






}
