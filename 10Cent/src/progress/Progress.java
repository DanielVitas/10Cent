package progress;

import logic.players.Player;
import logic.players.cross.Cross;
import logic.players.nought.Nought;

import java.util.ArrayList;
import java.util.List;

public final class Progress {

    public static boolean campaignDialog;  // campaign dialog will display
    public static String selectedPlayer;
    public static int selectedStage;
    public static boolean easy;
    public static int newestStage;

    private static List<String> gamesPlayed = new ArrayList<>();
    private static List<String> players = new ArrayList<>();
    private static List<String> oldPlayers = new ArrayList<>();
    private final static String[] allPlayers = new String[]{
        Cross.NAME,
        Nought.NAME
    };

    public static void load() {
        campaignDialog = true;
        addPlayer(Cross.NAME);
        newestStage = Stage.STAGE1;
        selectedPlayer = players.get(0);
        easy = true;
    }

    public static boolean isPlayed(String game) {
        return gamesPlayed.contains(game);
    }

    public static void addGame(String game) {
        gamesPlayed.add(game);
    }

    public static Player[] getPlayers() {
        Player[] players = new Player[Progress.players.size()];
        for (int i = 0; i < players.length; i++)
            players[i] = Player.parseString((String) Progress.players.toArray()[i], null);
        return players;
    }

    public static List<String> getOldPlayers() {
        return oldPlayers;
    }

    public static Player[] getAllPlayers() {
        Player[] players = new Player[Progress.allPlayers.length];
        for (int i = 0; i < players.length; i++)
            players[i] = Player.parseString(Progress.allPlayers[i], null);
        return players;
    }

    public static void addPlayer(String playerName) {
        players.add(playerName);
    }

    public static void addOldPlayer(String playerName) {
        oldPlayers.add(playerName);
    }


}