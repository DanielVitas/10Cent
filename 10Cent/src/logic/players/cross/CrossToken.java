package logic.players.cross;

import display.images.Images;
import logic.players.Player;
import logic.players.Token;

import java.nio.file.Paths;

public class CrossToken extends Token {

    public CrossToken(Player player) {
        super(player, Paths.get(Images.resourcesPath,"images", "cross").toString());
    }

}
