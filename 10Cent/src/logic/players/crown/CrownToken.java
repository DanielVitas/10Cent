package logic.players.crown;

import display.frame.misc.Dimension;
import display.images.Animation;
import display.images.Images;
import logic.boards.Move;
import logic.game.GameController;
import logic.players.Player;
import logic.players.Token;

import java.nio.file.Paths;

public class CrownToken extends Token {

    public CrownToken(Player player, Move move, GameController gameController, Dimension dimension) {
        super(player, move, gameController, dimension, Paths.get(Images.RESOURCES_PATH,"images", "tokens", "crown").toString());
    }

}
