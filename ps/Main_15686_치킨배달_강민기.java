import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_15686_치킨배달_강민기 {
	static int N, M, ans, chickenSize, homeSize;
	static int[][] arr;
	static List<Point> chicken, home;
	static int[][] dir = {{-1,0},{0,1},{1,0},{0,-1}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());			//N*N
		M = Integer.parseInt(st.nextToken());			//치킨집의 최대 개수
		arr = new int[N][N];
		chicken = new ArrayList<>();
		home = new ArrayList<>();
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if(arr[i][j] == 1)
					home.add(new Point(i,j));
				else if(arr[i][j] == 2) {
					chicken.add(new Point(i,j));
					arr[i][j] = 0;
				}
			}
		}
		
		chickenSize = chicken.size();
		homeSize = home.size();
		ans = 987654321;
		
		combi(0, -1, 0);
		
		System.out.println(ans);
	}
	private static void combi(int m, int prev, int bit) {
		if(m == M) {
			func(bit);
			return;
		}
		
		for(int i=prev+1; i<chickenSize; i++)
			combi(m+1, i, bit | 1<<i);
	}
	private static void func(int bit) {
		//M개의 조합을 정해서 각 집에서 치킨집으로 bfs
		//0 1 2 3 4
		//012 013 014 023 024 034 123 124 134 234
		for(int i=0; i<chickenSize; i++) {
			if((bit & 1<<i) != 0) {
				arr[chicken.get(i).r][chicken.get(i).c] = 2;
			}
		}
		
		//도시에서 bfs해서 값을 찾아 출력
		int distance = 0;
		hom:for(int i=0; i<homeSize; i++) {
			Queue<Point> q = new LinkedList<>();//home q
			boolean[][] visit = new boolean[N][N];
			q.add(home.get(i));
			visit[q.peek().r][q.peek().c] = true;
			
			int d = 1;							//distance(level)
			chick:while(!q.isEmpty()) {
				int qsize = q.size();
				for(int j=0; j<qsize; j++) {
					Point cur = q.poll();
					for(int k=0; k<4; k++) {
						int nr = cur.r + dir[k][0];
						int nc = cur.c + dir[k][1];
						if(nr>=0&&nr<N&&nc>=0&&nc<N&&!visit[nr][nc]) {
							if(arr[nr][nc] == 2) {
								distance+=d;
								if(distance > ans)
									break hom;
								break chick;
							}
							visit[nr][nc] = true;
							q.add(new Point(nr,nc));
						}
					}
				}//qsize
				d++;
			}//while
		}//for home
		ans = Math.min(ans, distance);
		
		for(int i=0; i<chickenSize; i++) {
			if((bit & 1<<i) != 0) {
				arr[chicken.get(i).r][chicken.get(i).c] = 0;
			}
		}
	}
	static class Point{
		int r;
		int c;
		Point(int r, int c){this.r=r; this.c=c;}
	}
}


//1은 집, 2는 치킨집