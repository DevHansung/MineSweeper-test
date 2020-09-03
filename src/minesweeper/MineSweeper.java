package minesweeper;

import java.util.Arrays;
import java.util.Random;

public class MineSweeper {
	private static final String NULL_POINT = "0 ";
	private static final String MINE_POINT = "M ";
	private static final int BASE_POINT_RANGE = 8;
	private static final int TOTAL_MINE = 10;
	private static final int MAX_X = 10;
	private static final int MAX_Y = 10;
	private String[][] mineMap = new String[MAX_X][MAX_Y];
	private String[] mineLocation = new String[TOTAL_MINE];

	// mineMap 초기화
	public void initMineMap() {
		for (int x = 0; x < MAX_X; x++) {
			for (int y = 0; y < MAX_Y; y++) {
				mineMap[x][y] = NULL_POINT;
			}
		}
	}

	// mine 위치 설정
	public void setMineLocation() {
		Random random = new Random();
		for (int currentMineCount = 0; currentMineCount < TOTAL_MINE; currentMineCount++) {
			int randomX = random.nextInt(MAX_X);
			int randomY = random.nextInt(MAX_Y);
			if (!mineMap[randomX][randomY].equals(MINE_POINT)) {
				mineMap[randomX][randomY] = MINE_POINT;
				mineLocation[currentMineCount] = "(x:" + randomX + " y:" + randomY + ")";
			} else {
				currentMineCount--;
			}
		}
	}

	// 기준점부터 방사형으로 8개 mine 개수 count
	public void countMineMap() {
		for (int x = 0; x < MAX_X; x++) {
			for (int y = 0; y < MAX_Y; y++) {
				if (!checkMine(x, y)) {
					int count = 0;
					int nx[] = { x - 1, x - 1, x - 1, x, x, x + 1, x + 1, x + 1 };
					int ny[] = { y - 1, y, y + 1, y - 1, y + 1, y - 1, y, y + 1 };
					for (int i = 0; i < BASE_POINT_RANGE; i++) {
						if (checkMine(nx[i], ny[i])) {
							count++;
							mineMap[x][y] = "" + count + " ";
						}
					}
				}
			}
		}
	}

	// mine point check
	private boolean checkMine(int x, int y) {
		if (x >= 0 && x < MAX_X && y >= 0 && y < MAX_Y && mineMap[x][y].equals(MINE_POINT))
			return true;
		else
			return false;
	}

	public void printMap() {
		System.out.println("지뢰 위치: " + Arrays.toString(mineLocation));
		System.out.println("====================");
		for (int i = 0; i < MAX_X; i++) {
			for (int j = 0; j < MAX_Y; j++) {
				System.out.print(mineMap[i][j]);
			}
			System.out.println();
		}
		System.out.println("====================");
	}

	public static void main(String[] args) {
		MineSweeper mineSweeper = new MineSweeper();
		mineSweeper.initMineMap();
		mineSweeper.setMineLocation();
		mineSweeper.countMineMap();
		mineSweeper.printMap();
	}
}