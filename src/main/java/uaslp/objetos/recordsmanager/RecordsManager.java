package uaslp.objetos.recordsmanager;
import java.util.List;
import java.io.*;
import java.nio.file.*;
import java.util.*;
public class RecordsManager {

    private final String filename;
    private final int maxRecords;

    public RecordsManager(String filename, int maxRecords) {
        this.filename = filename;
        this.maxRecords = maxRecords;
    }

    public void save(GameRecord record) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename, true))) {
            writer.println(record.getPlayerName() + "," + record.getScore());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<GameRecord> getRecords() {
        List<GameRecord> records = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String playerName = parts[0];
                    int score = Integer.parseInt(parts[1]);
                    GameRecord record = new GameRecord(playerName, score);
                    records.add(record);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return records;
    }
}
