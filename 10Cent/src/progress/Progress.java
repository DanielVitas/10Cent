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

public final class Progress {

    /*
    Keeps track of current progress - tokens unlocked, stages beaten,...
     */

    public static final String PROGRESS_FILE = Paths.get(Images.RESOURCES_PATH, "progress", "progress.txt").toString();
    public static String DEFAULT_PLAYER = Cross.NAME;

    public static boolean campaignDialog;  // campaign dialog will display
    // currently selected mode - easy goes first, hard second
    public static boolean easy;
    public static int newestStage;
    // currently selected player
    public static String selectedPlayer;

    // remembers which games player has player - for displaying rules
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

    public static void defaults() {
        campaignDialog = true;
        easy = true;
        newestStage = Stage.STAGE1;
        players = new ArrayList<>();
        addPlayer(DEFAULT_PLAYER);
        oldPlayers = new ArrayList<>();
        selectedPlayer = DEFAULT_PLAYER;
        gamesPlayed = new ArrayList<>();
    }

    public static void load() {
        defaults();
        read();
    }

    public static void save() {
        write();
    }

    private static void read() {
        try{
            BufferedReader in = new BufferedReader(new FileReader(PROGRESS_FILE));

            while (in.ready()) {
                String line = in.readLine().trim().replaceAll("\\s", "");

                if (line.equals(""))
                    continue;
                if (line.charAt(0) == '#')
                    continue;

                String[] splitLine = line.split("=");

                if (splitLine.length != 2)
                    continue;

                switch (splitLine[0]){
                    case ("campaignDialog"):
                        campaignDialog = Boolean.parseBoolean(splitLine[1]);
                        break;
                    case ("easy"):
                        easy = Boolean.parseBoolean(splitLine[1]);
                        break;
                    case ("newestStage"):
                        newestStage = Integer.parseInt(splitLine[1]);
                        break;
                    case ("players"):
                        String[] playerArray = splitLine[1].split(",");
                        for (String player : playerArray)
                            addPlayer(player);
                        break;
                    case ("oldPlayers"):
                        String[] oldPlayerArray = splitLine[1].split(",");
                        for (String player : oldPlayerArray)
                            addOldPlayer(player);
                        break;
                    case ("selectedPlayer"):
                        selectedPlayer = splitLine[1];
                        break;
                    case ("gamesPlayed"):
                        String[] gameArray = splitLine[1].split(",");
                        for (String game : gameArray)
                            addGame(game);
                        break;
                }

            }

            in.close();
        } catch (Exception e) {
            e.printStackTrace(); // exceptions can arise from user messing with settings file
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

}
