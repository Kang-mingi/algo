import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_1953_탈주범검거_강민기 {
	static int N, M, ans, L;
	static Point manhole;
	static int[][] arr;
	static int[][] dir = {{-1,0},{0,1},{1,0},{0,-1}};
	static int[][] direction = {{},{0,1,2,3},{0,2},{1,3},{0,1},{1,2},{2,3},{0,3}};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());		//R
			M = Integer.parseInt(st.nextToken());		//C
			manhole = new Point(Integer.parseInt(st.nextToken())
								,Integer.parseInt(st.nextToken()));
			L = Integer.parseInt(st.nextToken());		//탈출 후 소요된 시간
			
			arr = new int[N][M];
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<M; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			ans = 1;
			if(L != 1)
				func();
			
			System.out.println("#"+t+" "+ans);
		}
	}
	
	private static void func() {
		Queue<Point> q = new LinkedList<>();
		boolean[][] visit = new boolean[N][M];
		q.add(manhole);
		visit[manhole.r][manhole.c] = true;
		int level = 1;
		
		while(!q.isEmpty()) {
			level++;
			int size = q.size();
			for(int i=0; i<size; i++) {
				Point cur = q.poll();
				int temp = arr[cur.r][cur.c];
				int leng = direction[temp].length;				//파이프 종류에 따른 이동 방향
				for(int j=0; j<leng; j++) {
					int nr = cur.r + dir[direction[temp][j]][0];
					int nc = cur.c + dir[direction[temp][j]][1];
					if(nr>=0&&nr<N&&nc>=0&&nc<M&&!visit[nr][nc]&&arr[nr][nc] != 0) {
						//1-7
						if(direction[temp][j] == 0) {						//상 1 2 5 6
							if(arr[nr][nc] == 3 || arr[nr][nc] == 4 ||	arr[nr][nc] == 7)
								continue;
						}else if(direction[temp][j] == 1) {					//우 1 3 6 7
							if(arr[nr][nc] == 2 || arr[nr][nc] == 4 ||  arr[nr][nc] == 5)
								continue;
						}else if(direction[temp][j] == 2) {					//하 1 2 4 7
							if(arr[nr][nc] == 3 || arr[nr][nc] == 5 ||  arr[nr][nc] == 6)
								continue;
						}else if(direction[temp][j] == 3) {					//좌 1 3 4 5
							if(arr[nr][nc] == 2 || arr[nr][nc] == 6 ||  arr[nr][nc] == 7)
								continue;
						}
						ans++;
						visit[nr][nc] = true;
						q.add(new Point(nr,nc));
					}
				}
			}
			if(level == L)	break;								//L sec 끝
			
		}//while
	}

	static class Point{
		int r;
		int c;
		Point(int r, int c){this.r=r; this.c=c;}
	}
}

//상 1 2 5 6
//우 1 3 6 7
//하 1 2 4 7
//좌 1 3 4 5

//1 상하좌우
//2 상하
//3 좌우
//4 상우
//5 하우
//6 하좌
//7 상좌
