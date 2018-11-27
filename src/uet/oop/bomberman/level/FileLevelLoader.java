package uet.oop.bomberman.level;


import uet.oop.bomberman.Board;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.LayeredEntity;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.entities.character.enemy.Balloon;
import uet.oop.bomberman.entities.character.enemy.Oneal;
import uet.oop.bomberman.entities.tile.Grass;
import uet.oop.bomberman.entities.tile.Portal;
import uet.oop.bomberman.entities.tile.Wall;
import uet.oop.bomberman.entities.tile.destroyable.Brick;
import uet.oop.bomberman.entities.tile.item.BombItem;
import uet.oop.bomberman.entities.tile.item.FlameItem;
import uet.oop.bomberman.entities.tile.item.SpeedItem;
import uet.oop.bomberman.exceptions.LoadLevelException;
import uet.oop.bomberman.graphics.Screen;
import uet.oop.bomberman.graphics.Sprite;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

public class FileLevelLoader extends LevelLoader {

	/**
	 * Ma trận chứa thông tin bản đồ, mỗi phần tử lưu giá trị kí tự đọc được
	 * từ ma trận bản đồ trong tệp cấu hình
	 */
	private static char[][] _map;
	
	public FileLevelLoader(Board board, int level) throws LoadLevelException {
		super(board, level);
	}
	
	@Override
	public void loadLevel(int level) throws LoadLevelException {
		// TODO: đọc dữ liệu từ tệp cấu hình /levels/Level{level}.txt
		// TODO: cập nhật các giá trị đọc được vào _width, _height, _level, _map
		try {
			URL url = FileLevelLoader.class.getResource("/levels/Level"+level+".txt");
			String path = url.getPath();
			FileReader fr = new FileReader(path);
			BufferedReader in = new BufferedReader(fr);

			String data = in.readLine();
			String value[] = data.split(" ");
			_level =  Integer.parseInt(value[0]);
			_height = Integer.parseInt(value[1]);
			_width = Integer.parseInt(value[2]);
			_map = new char[_width][_height];

			_lineTiles = new String[_height];

			for(int i = 0; i < _height; ++i) {
				_lineTiles[i] = in.readLine().substring(0, _width);
			}
			for(int y=0; y<_height; y++){
				for(int x=0; x<_width; x++){
					_map[x][y] = _lineTiles[y].charAt(x);
				}
			}
			in.close();
		} catch (IOException e) {
			throw new LoadLevelException("Error loading level " + level, e);
		}
	}

	@Override
	public void createEntities() {
		// TODO: tạo các Entity của màn chơi
		// TODO: sau khi tạo xong, gọi _board.addEntity() để thêm Entity vào game

		// TODO: phần code mẫu ở dưới để hướng dẫn cách thêm các loại Entity vào game
		// TODO: hãy xóa nó khi hoàn thành chức năng load màn chơi từ tệp cấu hình

		for (int x = 0; x < getWidth(); x++) {
			for (int y = 0; y < getHeight(); y++) {
				int pos = x + y * _width;
				/**
				 * lẤP ĐẦY MAP BẰNG NHỮNG ENTITY
				 */
				switch (_map[x][y]) {

					// Thêm Wall
					case '#':
					{
						_board.addEntity(pos, new Wall(x, y, Sprite.wall));
						break;
					}
					// thêm Bomber
					case 'p':
					{
						_board.addCharacter(new Bomber(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board));
						Screen.setOffset(0, 0);
						_board.addEntity(pos, new Grass(x, y, Sprite.grass));
						break;
					}
					// thêm Enemy
					case '1':
					{
						_board.addCharacter(new Balloon(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board));
						_board.addEntity(pos, new Grass(x, y, Sprite.grass));
						break;
					}
					// thêm oneal
					case '2':
					{
						_board.addCharacter(new Oneal(Coordinates.tileToPixel(x),Coordinates.tileToPixel(y)+Game.TILES_SIZE,_board));
						_board.addEntity(pos,new Grass(x,y,Sprite.grass));
						break;
					}
					// thêm Brick
					case'*': {
						_board.addEntity(pos,
								new LayeredEntity(x, y,
										new Grass(x, y, Sprite.grass),
										new Brick(x, y, Sprite.brick)) );
						break;
					}
					// thêm portal
					case 'x':
					{
						_board.addEntity(pos,
								new LayeredEntity(x, y,
								new Grass(x ,y, Sprite.grass),
								new Portal(x,y,Sprite.portal,_board),
								new Brick(x ,y, Sprite.brick)) );
						break;
					}
					/**
					 * thêm Item kèm Brick che phủ ở trên
					 */
					case 's':
					{
						_board.addEntity(pos,
								new LayeredEntity(x, y,
										new Grass(x, y, Sprite.grass),
										new SpeedItem(x, y, Sprite.powerup_speed),
										new Brick(x, y, Sprite.brick)) );
						break;
					}
					// flame Item
					case 'f':
					{
						_board.addEntity(pos,
								new LayeredEntity(x, y,
										new Grass(x, y, Sprite.grass),
										new FlameItem(x, y, Sprite.powerup_flames),
										new Brick(x, y, Sprite.brick)
								)
						);
						break;
					}
					// bomb Item
					case 'b':
					{
						_board.addEntity(pos,
								new LayeredEntity(x, y,
										new Grass(x, y, Sprite.grass),
										new BombItem(x, y, Sprite.powerup_bombs),
										new Brick(x, y, Sprite.brick)
								)
						);
						break;
					}
					default:
					{
						_board.addEntity(x + y * _width, new Grass(x, y, Sprite.grass));
						break;
					}
				}
			}
		}

	}

}
