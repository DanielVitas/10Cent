package logic.players.crossBloody;

import display.frame.misc.Dimension;
import display.images.Images;
import logic.boards.Move;
import logic.game.GameController;
import logic.players.Player;
import logic.players.Token;

import java.nio.file.Paths;

public class CrossBloodyToken extends Token {

    public CrossBloodyToken(Player player, Move move, GameController gameController, Dimension dimension) {
        super(player, move, gameController, dimension, Paths.get(Images.RESOURCES_PATH,"images", "tokens", "cross-bloody").toString());
    }

}