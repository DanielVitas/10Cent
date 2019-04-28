package logic.players.empty;

import display.frame.misc.Dimension;
import display.images.Images;
import logic.players.Player;
import logic.players.Token;

import java.nio.file.Paths;

public class EmptyToken extends Token {

    public EmptyToken(Player player, Dimension dimension) {
        super(player, dimension, Paths.get(Images.resourcesPath,"images", "empty").toString());
    }

}
