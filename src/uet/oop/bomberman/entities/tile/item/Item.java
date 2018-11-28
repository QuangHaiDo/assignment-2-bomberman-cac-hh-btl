package uet.oop.bomberman.entities.tile.item;

import uet.oop.bomberman.entities.tile.Tile;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.sound.SoundEffect;

public abstract class Item extends Tile {

	SoundEffect soundEffect = new SoundEffect();
	public Item(int x, int y, Sprite sprite) {
		super(x, y, sprite);
	}
}
