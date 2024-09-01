import java.util.*;
import java.io.*;

public class Main {

	static int row = 9;
	static int col = 9;
	static boolean answer = false;
	static boolean[][] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] sdoku = new int[row][col];
		visited = new boolean[row][col];
		// 9x9 배열 받기
		for (int i = 0; i < 9; i++) {
			String input_data = br.readLine();
			for (int j = 0; j < input_data.length(); j++) {
				sdoku[i][j] = input_data.charAt(j) - '0';
				if (sdoku[i][j] != 0) {
					visited[i][j] = true;
				}
			}
		}
		int[][] cloneArr = new int[row][col];
		for (int i = 0; i < row; i++) {
			cloneArr[i] = sdoku[i].clone();
		}
		boolean[][] cloneVisit = new boolean[row][col];
		for (int i = 0; i < row; i++) {
			cloneVisit[i] = visited[i].clone();
		}
		solution(0, 0, cloneArr, cloneVisit);
//		System.out.println(sdoku);
//		printSdoku(sdoku);
	}

	private static void solution(int x, int y, int[][] arr, boolean[][] visit) {
		if (answer) {
			return;
		} else {
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {

					if (visit[i][j])
						continue;

					int[][] cloneArr = new int[9][9];
					boolean[][] cloneVisit = new boolean[9][9];
					for (int k = 1; k <= 9; k++) {
						// 들어가도 되는 숫자이면
						if (checkOne(i, j, k, arr)) {
							visit[i][j] = true;
							arr[i][j] = k;
							for (int clone = 0; clone < 9; clone++) {
								cloneArr[clone] = arr[clone].clone();
								cloneVisit[clone] = visit[clone].clone();
							}
							solution(i, j, cloneArr, cloneVisit);
							visit[i][j] = false;
						}
					}
					return;
				}
			}
			if(checkAll(arr)) {
				printSdoku(arr);
				answer=true;
			}
		}
	}

	private static boolean checkOne(int x, int y, int num, int[][] arr) {
		if (checkOneGaro(x, y, num, arr) && checkOneSero(x, y, num, arr) && checkOneNine(x, y, num, arr)) {
			return true;
		}
		return false;
	}

	private static boolean checkAll(int[][] arr) {
		if (checkAllGaro(arr) && checkAllSero(arr) && checkAllNine(arr)) {
			return true;
		}
		return false;
	}

	private static boolean checkOneGaro(int x, int y, int num, int[][] arr) {
		for (int i = 0; i < 9; i++) {
			if (arr[x][i] == num) {
				return false;
			}
		}
		return true;
	}

	private static boolean checkOneSero(int x, int y, int num, int[][] arr) {
		for (int i = 0; i < 9; i++) {
			if (arr[i][y] == num) {
				return false;
			}
		}
		return true;
	}

	private static boolean checkOneNine(int x, int y, int num, int[][] arr) {
		int newX = x / 3;
		int newY = y / 3;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (arr[newX * 3 + i][newY * 3 + j] == num) {
					return false;
				}
			}
		}
		return true;
	}

	private static boolean checkAllGaro(int[][] arr) {
		for (int i = 0; i < 9; i++) {
			int count = 0;
			for (int j = 0; j < 9; j++) {
				count += arr[i][j];
			}
			if (count != 45) {
				return false;
			}
		}
		return true;
	}

	private static boolean checkAllSero(int[][] arr) {
		for (int i = 0; i < 9; i++) {
			int count = 0;
			for (int j = 0; j < 9; j++) {
				count += arr[j][i];
			}
			if (count != 45) {
				return false;
			}
		}
		return true;
	}

	private static boolean checkAllNine(int[][] arr) {
		int newX = 0;
		int newY = 0;

		// 00 02~ 20 22
		for (int k = 0; k < 9; k++) {
			newX = k/3;
			newY = k%3;
			int count = 0;
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					count += arr[newX*3+i][newY*3+j];
				}
			}
			if (count != 45) {
				return false;
			}
		}

		return true;
	}

	// 1부터 9를 넣을때 검사를해서 검사 안에 해당 숫자가 있으면 패스.
	// 하나 넣고 visited true로 바꾸고

	private static void printSdoku(int[][] sdoku) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(sdoku[i][j]);
			}
			System.out.println();
		}
	}

}