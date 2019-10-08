import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_17472_다리만들기2_강민기 {
	
	static int N, M, ans;
	static int[][] island, arr;
	static boolean[][] bIsland;
	static int nIsland;
	static ArrayList<Edge> edges;
	static int[] parent;
	static int[][] dir = {{-1,0},{0,1},{1,0},{0,-1}};
	static boolean isComplete;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		island = new int[N][M];
		bIsland = new boolean[N][M];
		arr = new int[N][M];
		edges = new ArrayList<>();
		isComplete = false;
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				island[i][j] = Integer.parseInt(st.nextToken());
				arr[i][j] = island[i][j];
			}
		}
		ans = 0;
		
		func();
		
		if(isComplete)
			System.out.println(ans);
		else
			System.out.println(-1);
	}


	private static void func() {
		//1. ij 탐색하면서 섬 넘버링 
		nIsland = 1;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(island[i][j] != 0 && !bIsland[i][j]) {
					numbering(i,j);
					nIsland++;
				}
			}
		}
		
		//2. 섬과 섬사이의 거리 측정(간선 정보)
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(island[i][j] != 0) {
					checkEdge(i,j);
				}
			}
		}
		
		
		//3. 간선들을 이어서 최소 신장 트리 만들기
		Collections.sort(edges);
		parent = new int[nIsland];
		Arrays.fill(parent, -1);
		
		int cnt = 0;
		for(Edge e : edges) {
			if(union(e.from, e.to)) {
				ans += e.v;
				cnt++;
			}
			if(cnt == nIsland-2) {
				isComplete = true;
				break;
			}
		}
	}


	private static void checkEdge(int r, int c) {
		for(int i=0; i<4; i++) {
			int to = 0;
			int distance = 0;
			int nr = r + dir[i][0];
			int nc = c + dir[i][1];
			boolean find = false;
			while(nr >= 0 && nr < N && nc >= 0 && nc < M
					&& island[nr][nc] == 0) {
				distance++;
				nr += dir[i][0];
				nc += dir[i][1];
				if(nr >= 0 && nr < N && nc >= 0 && nc < M
						&& island[nr][nc] != 0) {
					to = island[nr][nc];
					find = true;
					break;
				}
			}//while
			if(find) {
				if(distance >= 2 && island[r][c] < to)
					edges.add(new Edge(island[r][c], to, distance));
			}
		}
	}


	private static void numbering(int r, int c) {
		Queue<Point> q = new LinkedList<>();
		
		bIsland[r][c] = true;
		island[r][c] = nIsland;
		q.add(new Point(r,c));
		
		while(!q.isEmpty()) {
			Point temp = q.poll();
			for(int i=0; i<4; i++) {
				int nr = temp.r+dir[i][0];
				int nc = temp.c+dir[i][1];
				if(nr >=0&&nr<N&&nc>=0&&nc<M
						&&island[nr][nc] == 1 && !bIsland[nr][nc]) {
					bIsland[nr][nc] = true;
					q.add(new Point(nr, nc));
					island[nr][nc] = nIsland;
				}
			}
		}
	}

	private static void print(int[][] arr) {
		System.out.println("#######################");
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println("#######################");
	}
	
	static class Point{
		int r;
		int c;
		Point(int r, int c){this.r=r; this.c=c;}
	}
	static class Edge implements Comparable<Edge>{
		int from;
		int to;
		int v;
		Edge(int from, int to, int v){ this.from=from; this.to=to; this.v=v;}
		public int compareTo(Edge e) {
			return Integer.compare(v,e.v);
		}
	}
	
	static int find(int a) {
		if(parent[a] == -1) {
			return a;
		}else
			return parent[a] = find(parent[a]);
	}
	
	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot != bRoot) {
			parent[bRoot] = aRoot;
			return true;
		}else
			return false;
	}
}


