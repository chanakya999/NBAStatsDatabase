import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.io.FileInputStream;
import java.util.*;

public class NBAStatsDatabase {

    private static Comparator<PlayerStats> PointsComparator = Comparator.comparingDouble(PlayerStats::getPointsPerGame)
            .thenComparingDouble(PlayerStats::getAssistsPerGame)
            .thenComparingDouble(PlayerStats::getReboundsPerGame)
            .reversed();
    private static Comparator<PlayerStats> AssistsComparator = Comparator.comparingDouble(PlayerStats::getAssistsPerGame)
            .thenComparingDouble(PlayerStats::getPointsPerGame)
            .thenComparingDouble(PlayerStats::getReboundsPerGame)
            .reversed();
    private static Comparator<PlayerStats> ReboundsComparator = Comparator.comparingDouble(PlayerStats::getReboundsPerGame)
            .thenComparingDouble(PlayerStats::getPointsPerGame)
            .thenComparingDouble(PlayerStats::getAssistsPerGame)
            .reversed();

    private static Comparator<PlayerStats> StealsComparator = Comparator.comparingDouble(PlayerStats::getStealsPerGame)
            .thenComparingDouble(PlayerStats::getPointsPerGame)
            .thenComparingDouble(PlayerStats::getAssistsPerGame)
            .reversed();

    private static Comparator<PlayerStats> BlocksComparator = Comparator.comparingDouble(PlayerStats::getBlocksPerGame)
            .thenComparingDouble(PlayerStats::getPointsPerGame)
            .thenComparingDouble(PlayerStats::getAssistsPerGame)
            .reversed();

    private static Comparator<PlayerStats> TurnoversComparator = Comparator.comparingDouble(PlayerStats::getTurnoversPerGame)
            .thenComparingDouble(PlayerStats::getPointsPerGame)
            .thenComparingDouble(PlayerStats::getAssistsPerGame)
            .reversed();

    public static HashMap<PlayerStats, String> stats = new HashMap<>();
    public static TreeMap<PlayerStats, String> reducedStats = new TreeMap<>(PointsComparator);
    public static String fileName = "2018-2019 NBA Player Stats.xlsx";

    public static void main(String[] args) {
        readFile();
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to the NBA Stats Database.");
        String firstCriteria = "";
        boolean prompt = true;
        while (prompt) {
            System.out.print("Please type in a statistic you want to search by: ");
            firstCriteria = sc.nextLine();
            prompt = !readStatistic(firstCriteria);
            if (prompt) {
                System.out.println("Sorry, that is not a possible statistic to search by. Please try again.");
            }
        }
        adjustComparator(firstCriteria);
        double minAmount;
        prompt = true;
        while (prompt) {
            System.out.print("Please type in the minimum amount of " + firstCriteria.toLowerCase() + " you want to sort by: ");
            minAmount = sc.nextDouble();
            if (minAmount < 0) {
                System.out.println("That is not a valid number. Please try again.");
            } else {
                prompt = false;
            }
        }
        for (PlayerStats player : stats.keySet()) {
            reducedStats.put(player, stats.get(player));
//            System.out.println(stats.get(player));
        }
        for (PlayerStats player : reducedStats.keySet()) {
            System.out.println(player);
        }
    }

    public static void readFile() {
        try {
            FileInputStream file = new FileInputStream(fileName);
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            rowIterator.next();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                PlayerStats currentStat = new PlayerStats(row);
                String name = currentStat.getPlayerName() + " (" + currentStat.getPlayerTeam() + ")";
                stats.put(currentStat, name);
            }
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean readStatistic(String statistic) {
        String[] possibleStats = {"points", "assists", "rebounds", "steals", "blocks", "turnovers", "minutes",
                "field goal percentage", "free throw percentage", "three point percentage", "team"};
        for (int i = 0; i < possibleStats.length; i++) {
            if (possibleStats[i].equalsIgnoreCase(statistic)) {
                return true;
            }
        }
        return false;
    }

    public static void adjustComparator(String criteria) {
        if (criteria.equalsIgnoreCase("assists")) {
            reducedStats = new TreeMap<>(AssistsComparator);
        } else if (criteria.equalsIgnoreCase("rebounds")) {
            reducedStats = new TreeMap<>(ReboundsComparator);
        } else if (criteria.equalsIgnoreCase("steals")) {
            reducedStats = new TreeMap<>(StealsComparator);
        } else if (criteria.equalsIgnoreCase("blocks")) {
            reducedStats = new TreeMap<>(BlocksComparator);
        } else if (criteria.equalsIgnoreCase("turnovers")) {
            reducedStats = new TreeMap<>(TurnoversComparator);
        }
    }

}
