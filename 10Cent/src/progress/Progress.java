package progress;

import display.images.Images;
import logic.players.Player;
import logic.players.bird.Bird;
import logic.players.cross.Cross;
import logic.players.scratch.Scratch;
import logic.players.crown.Crown;
import logic.players.heart.Heart;
import logic.players.nought.Nought;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static display.screens.Games.allGames;

public final class Progress {

    /*
    Keeps track of current progress - tokens unlocked, stages beaten,... Also manages saving and reading it.
    NOTE: player is kind of token - tokens are objects actually placed on board
     */

    private static final String PROGRESS_FILE = Paths.get(Images.RESOURCES_PATH, "progress", "progress.txt").toString();
    private static String DEFAULT_PLAYER = Cross.NAME;

    public static boolean campaignDialog;  // true if campaign dialog will display
    public static boolean easy;  // currently selected mode - easy goes first, hard second
    public static int newestStage;  // newest stage unlocked
    public static String selectedPlayer;  // currently selected player

    // remembers which games player has played - for displaying rules
    private static List<String> gamesPlayed;
    // tokens player has unlocked
    private static List<String> players;
    // tokens player has already seen - others have "new" tag
    private static List<String> oldPlayers;
    private final static String[] allPlayers = new String[]{
            Cross.NAME,
            Nought.NAME,
            Bird.NAME,
            Crown.NAME,
            Scratch.NAME,
            Heart.NAME
    };

    // checks whether the game was already played
    public static boolean isPlayed(String game) {
        return gamesPlayed.contains(game);
    }

    // adds game to games played
    public static void addGame(String game) {
        if (!gamesPlayed.contains(game) && contained(game, allGames))
            gamesPlayed.add(game);
    }

    // returns currently selected player - parsed of course
    public static Player[] getPlayers() {
        Player[] players = new Player[Progress.players.size()];
        for (int i = 0; i < players.length; i++)
            players[i] = Player.parsePlayer((String) Progress.players.toArray()[i], null);
        return players;
    }

    // returns old players
    public static List<String> getOldPlayers() {
        return oldPlayers;
    }

    // returns array of all players - parsed
    public static Player[] getAllPlayers() {
        Player[] players = new Player[Progress.allPlayers.length];
        for (int i = 0; i < players.length; i++)
            players[i] = Player.parsePlayer(Progress.allPlayers[i], null);
        return players;
    }

    // adds player (unlocks)
    public static void addPlayer(String playerName) {
        if (!players.contains(playerName) && contained(playerName, allPlayers))
            players.add(playerName);
    }

    // adds player to seen players
    public static void addOldPlayer(String playerName) {
        if (!oldPlayers.contains(playerName) && contained(playerName, allPlayers))
            oldPlayers.add(playerName);
    }

    // default progress - minimal
    private static void defaults() {
        campaignDialog = true;
        easy = true;
        newestStage = Stage.STAGE1;
        players = new ArrayList<>();
        addPlayer(DEFAULT_PLAYER);
        oldPlayers = new ArrayList<>();
        selectedPlayer = DEFAULT_PLAYER;
        gamesPlayed = new ArrayList<>();
    }

    // called once at the start of the program
    public static void load() {
        defaults();
        read();
    }

    public static void save() {
        write();
    }

    // read is identical in structure to one from settings
    private static void read() {
        try{
            BufferedReader in = new BufferedReader(new FileReader(PROGRESS_FILE));

            while (in.ready()) {
                String line = in.readLine();  // don't remove spaces, because game names are space sensitive

                if (line.equals(""))
                    continue;
                if (line.charAt(0) == '#')
                    continue;

                String[] splitLine = line.split("=");

                if (splitLine.length != 2)
                    continue;

                switch (splitLine[0].trim()){
                    case ("campaignDialog"):
                        campaignDialog = Boolean.parseBoolean(splitLine[1].trim());
                        break;
                    case ("easy"):
                        easy = Boolean.parseBoolean(splitLine[1].trim());
                        break;
                    case ("newestStage"):
                        newestStage = Integer.parseInt(splitLine[1].trim());
                        break;
                    case ("players"):
                        String[] playerArray = splitLine[1].split(",");
                        for (String player : playerArray)
                            addPlayer(player.trim());
                        break;
                    case ("oldPlayers"):
                        String[] oldPlayerArray = splitLine[1].split(",");
                        for (String player : oldPlayerArray)
                            addOldPlayer(player.trim());
                        break;
                    case ("selectedPlayer"):
                        selectedPlayer = splitLine[1].trim();
                        break;
                    case ("gamesPlayed"):
                        String[] gameArray = splitLine[1].split(",");
                        for (String game : gameArray)
                            addGame(game.trim());
                        break;
                }
            }

            in.close();
        } catch (Exception e) {
            e.printStackTrace(); // catches all exceptions - IO, ones that araise from parsing
        }
    }

    private static void write() {
        try {
            PrintWriter out = new PrintWriter(new FileWriter(PROGRESS_FILE));

            out.println("# do NOT temper with this file, unless you know what you are doing");
            out.println();

            out.println("campaignDialog = " + campaignDialog);
            out.println("easy = " + easy);
            out.println("newestStage = " + newestStage);

            boolean first = true;
            out.print("players = ");
            for (String player : players)
                if (!player.equals(DEFAULT_PLAYER)) {
                    if (first)
                        first = false;
                    else
                        out.print(", ");
                    out.print(player);
                }
            out.println();

            out.print("oldPlayers = ");
            writeList(oldPlayers, out);
            out.println();

            out.println("selectedPlayer = " + selectedPlayer);

            out.print("gamesPlayed = ");
            writeList(gamesPlayed, out);
            out.println();

            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeList(List<String> stringList, PrintWriter out) {
        boolean first = true;
        for (String s : stringList) {
            if (first)
                first = false;
            else
                out.print(", ");
            out.print(s);
        }
    }

    // function isn't called to often so there is no need for more appropriate data type
    private static boolean contained(String item, String[] stringArray) {
        for (String s : stringArray)
            if (s.equals(item))
                return true;
        return false;
    }

}
