import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.util.*;

public class PlayerStats {

    private String playerName;
    private String playerTeam;
    private double playerAge;
    private String playerPosition;
    private int gamesPlayed;
    private double pointsPerGame;
    private double assistsPerGame;
    private double reboundsPerGame;
    private double stealsPerGame;
    private double blocksPerGame;
    private double turnoversPerGame;
    private double minutesPerGame;
    private double fieldGoalPer;
    private double threePointPer;
    private double freeThrowPer;

    public PlayerStats(Row row) {
        Iterator<Cell> cellIterator = row.cellIterator();
        this.playerName = cellIterator.next().getStringCellValue();
        this.playerTeam = cellIterator.next().getStringCellValue();
        this.playerPosition = cellIterator.next().getStringCellValue();
        this.playerAge = cellIterator.next().getNumericCellValue();
        this.gamesPlayed = (int) cellIterator.next().getNumericCellValue();
        this.minutesPerGame = cellIterator.next().getNumericCellValue();
        this.freeThrowPer = cellIterator.next().getNumericCellValue();
        this.fieldGoalPer = cellIterator.next().getNumericCellValue();
        this.threePointPer = cellIterator.next().getNumericCellValue();
        this.pointsPerGame = cellIterator.next().getNumericCellValue();
        this.reboundsPerGame = cellIterator.next().getNumericCellValue();
        this.assistsPerGame = cellIterator.next().getNumericCellValue();
        this.stealsPerGame = cellIterator.next().getNumericCellValue();
        this.blocksPerGame = cellIterator.next().getNumericCellValue();
        this.turnoversPerGame = cellIterator.next().getNumericCellValue();
    }

    public String getPlayerName() {
        return this.playerName;
    }
    
    public String getPlayerTeam() {
        return this.playerTeam;
    }

    public int getPlayerAge() {
        return (int) this.playerAge;
    }

    public String getPlayerPosition() {
        return this.playerPosition;
    }

    public int getGamesPlayed() {
        return this.gamesPlayed;
    }

    public double getPointsPerGame() {
        return this.pointsPerGame;
    }

    public double getAssistsPerGame() {
        return this.assistsPerGame;
    }

    public double getReboundsPerGame() {
        return this.reboundsPerGame;
    }

    public double getStealsPerGame() {
        return this.stealsPerGame;
    }

    public double getBlocksPerGame() {
        return this.blocksPerGame;
    }

    public double getTurnoversPerGame() {
        return this.turnoversPerGame;
    }

    public double getMinutesPerGame() {
        return this.minutesPerGame;
    }

    public double getFieldGoalPer() {
        return this.fieldGoalPer;
    }

    public double getThreePointPer() {
        return this.threePointPer;
    }

    public double getFreeThrowPer() { return this.freeThrowPer; }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getPlayerName());
        sb.append(" Points: " + getPointsPerGame());
        sb.append(" Assists: " + getAssistsPerGame());
        sb.append(" Rebounds: " + getReboundsPerGame());
        return sb.toString();
    }
}
