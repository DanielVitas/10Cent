package progress;

import logic.players.Player;
import logic.players.bird.Bird;
import logic.players.cross.Cross;
import logic.players.scratch.Scratch;
import logic.players.crossBloody.CrossBloody;
import logic.players.crown.Crown;
import logic.players.heart.Heart;
import logic.players.nought.Nought;
import logic.players.noughtBloody.NoughtBloody;

import java.util.ArrayList;
import java.util.List;

public final class Progress {

    public static boolean campaignDialog;  // campaign dialog will display
    public static String selectedPlayer;
    public static boolean easy;
    public static int newestStage;

    private static List<String> gamesPlayed = new ArrayList<>();
    private static List<String> players = new ArrayList<>();
    private static List<String> oldPlayers = new ArrayList<>();
    private final static String[] allPlayers = new String[]{
            Cross.NAME,
            Nought.NAME,
            Bird.NAME,
            Crown.NAME,
            Scratch.NAME,
            Heart.NAME
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
            players[i] = Player.parsePlayer((String) Progress.players.toArray()[i], null);
        return players;
    }

    public static List<String> getOldPlayers() {
        return oldPlayers;
    }

    public static Player[] getAllPlayers() {
        Player[] players = new Player[Progress.allPlayers.length];
        for (int i = 0; i < players.length; i++)
            players[i] = Player.parsePlayer(Progress.allPlayers[i], null);
        return players;
    }

    public static void addPlayer(String playerName) {
        players.add(playerName);
    }

    public static void addOldPlayer(String playerName) {
        oldPlayers.add(playerName);
    }


}
