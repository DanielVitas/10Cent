package logic.players.heart;

import display.frame.misc.Dimension;
import display.images.Images;
import logic.boards.Move;
import logic.game.GameController;
import logic.players.Player;
import logic.players.Token;

import java.nio.file.Paths;

public class HeartToken extends Token {

    public HeartToken(Player player, Move move, GameController gameController, Dimension dimension) {
        super(player, move, gameController, dimension, Paths.get(Images.RESOURCES_PATH,"images", "tokens", "heart").toString());
    }

}
