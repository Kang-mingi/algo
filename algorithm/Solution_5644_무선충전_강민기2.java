import java.io.*;
import java.util.*;

public class Solution_5644_무선충전_강민기2 {
	
	private static class Node implements Comparable<Node>{
		int no, per;
		Node (int no, int per) {
			this.no = no;
			this.per = per;
		}
		@Override
		public int compareTo(Node o) {
			return -1 * (this.per - o.per);
		}
	}

	static int second, apNum, aMove[], bMove[], energy;
	static ArrayList<Node>[][] map;
	//이동x 상 우 하 좌
	static int[] dx = {0, -1, 0, 1, 0};
	static int[] dy = {0, 0, 1, 0, -1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			energy = 0;

			st = new StringTokenizer(br.readLine());
			second = Integer.parseInt(st.nextToken());
			apNum = Integer.parseInt(st.nextToken());
			
			aMove = new int[second];
			bMove = new int[second];
			map = new ArrayList[10][10];
			for (int i = 0; i < 10; i++)
				for (int j = 0; j < 10; j++)
					map[i][j] = new ArrayList<>();
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < second; i++)
				aMove[i] = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < second; i++)
				bMove[i] = Integer.parseInt(st.nextToken());
			
			int x, y, c, p;
			for (int i = 0; i < apNum; i++) {
				st = new StringTokenizer(br.readLine());
				y = Integer.parseInt(st.nextToken()) - 1;
				x = Integer.parseInt(st.nextToken()) - 1;
				c = Integer.parseInt(st.nextToken());
				p = Integer.parseInt(st.nextToken());
				draw(x, y, c, p, i);
			}
			move();
			System.out.printf("#%d %d\n", t, energy);
		}
	}
	
	private static void move() {
		int ax = 0, ay = 0, bx = 9, by = 9;	//ac, bc는 몇번충전기인지, 즉 인덱스임
		for (int t = 0; t <= second; t++) {
			Collections.sort(map[ax][ay]);
			Collections.sort(map[bx][by]);
			
			//둘다 들어있는 값이 없으면 아무것도 안함
			if (map[ax][ay].size() == 0 && map[bx][by].size() == 0) {
			
			//a만 0이면
			} else if (map[ax][ay].size() == 0) {
				energy += map[bx][by].get(0).per;
				
			//b만 0이면
			} else if (map[bx][by].size() == 0) {
				energy += map[ax][ay].get(0).per;
				
			//둘다 1이면
			} else if (map[ax][ay].size() == 1 && map[bx][by].size() == 1) {
				if (map[ax][ay].get(0).no == map[bx][by].get(0).no)	//충전기 번호가 같으면
					energy += map[ax][ay].get(0).per;				//어차피 반반 더할거니까
				else
					energy += (map[ax][ay].get(0).per + map[bx][by].get(0).per);
				
			//a만 1이면
			} else if (map[ax][ay].size() == 1) {
				if (map[ax][ay].get(0).no == map[bx][by].get(0).no)	//ac랑 bc랑 같으면 bc 다음꺼로 더함
					energy += (map[ax][ay].get(0).per + map[bx][by].get(1).per);
				else	//다르면 걍 더함
					energy += (map[ax][ay].get(0).per + map[bx][by].get(0).per);
				
			//b만 1이면
			} else if (map[bx][by].size() == 1) {
				if (map[bx][by].get(0).no == map[ax][ay].get(0).no)	//ac랑 bc랑 같으면 ac 다음꺼로 더함
					energy += (map[bx][by].get(0).per + map[ax][ay].get(1).per);
				else	//다르면 걍 더함
					energy += (map[bx][by].get(0).per + map[ax][ay].get(0).per);
			
			//둘다 사이즈 1 아닐때
			} else {	
				if (map[ax][ay].get(0).per < map[bx][by].get(0).per)
					energy += (map[bx][by].get(0).per + map[ax][ay].get(0).per);
				else if (map[ax][ay].get(0).per > map[bx][by].get(0).per)
					energy += (map[bx][by].get(0).per + map[ax][ay].get(0).per);
				else {
					//perform은 같지만 번호가 다르면 걍 더하면 됨
					if (map[ax][ay].get(0).no != map[bx][by].get(0).no) {
						energy += (map[ax][ay].get(0).per + map[bx][by].get(0).per);
					//perform은 같지만 번호도 같다면
					} else {
						energy += map[ax][ay].get(0).per;
						energy += Math.max(map[ax][ay].get(1).per, map[bx][by].get(1).per);
					}
				}
			}
			if (t == second) break;
			ax += dx[aMove[t]];	ay += dy[aMove[t]];
			bx += dx[bMove[t]];	by += dy[bMove[t]];
		}	
	}

	private static void draw(int x, int y, int c, int p, int no) {
		int tx, ty;
		for (int i = -1 * c; i <= c; i++) {
			for (int j = -1 * c; j <= c; j++) {
				
				if ((Math.abs(i) + Math.abs(j)) > c) continue;
				
				tx = x + i;
				ty = y + j;
				
				if (tx < 0 || tx >= 10 || ty < 0 || ty >= 10) continue;
				
				map[tx][ty].add(new Node(no, p));
			}
		}
	}
}