import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_5656_벽돌깨기_강민기 {
	static int N,W,H, ans, all;
	static int[][] arr,copyArr;
	static int[][] dir = {{-1,0},{0,1},{1,0},{0,-1}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			arr = new int[H][W];
			copyArr = new int[H][W];
			ans = 4321;
			all = 0;
			
			for(int i=0; i<H; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<W; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					if(arr[i][j] != 0)
						all++;
				}
			}
			func(arr, 0, 0);
			
			System.out.println("#"+t+" "+ans);
		}
	}
	
	private static void func(int[][] arr, int n, int c) {	//func()를 N번 수행해보아야함
		if(n == N) {
			int rs = counting(arr);
			if(rs < ans) {
				ans = rs;
//				print(arr);
			}
			return;
		}
		
		for(int i=0; i<W; i++) {
			int[][] newArr = new int[H][W];	//복사할 새로운 배열
			copyArr(arr, newArr);			//기존 배열을 복사(from, to)
			
			//맨 위 벽돌 타격 + 연쇄 벽돌(있다면 계속)
			hit(newArr, -1, i);
			
			//벽돌내리기
			newArr = pushdown(newArr);
			
			func(newArr, n+1, i);	//다음 depth로 
		}
	}
	
	private static int counting(int[][] arr) {
		int cnt = 0;
		for(int i=0; i<H; i++) {
			for(int j=0; j<W; j++) {
				if(arr[i][j] != 0) {
					cnt++;
				}
			}
		}
		return cnt;
	}
	
	private static int[][] pushdown(int[][] arr) {
		int[][] newArr = new int[H][W];		//내려서 저장할 배열
		for(int i=0; i<W; i++) {
			Queue<Integer> q = new LinkedList<>();
			for(int j=H-1; j>=0; j--) {
				if(arr[j][i] != 0) {
					q.add(arr[j][i]);
				}
			}
			int index = H-1;
			while(!q.isEmpty()) {
				newArr[index--][i] = q.poll();
			}
		}
		arr = new int[H][W];
		copyArr(newArr, arr);				//리턴 대신 다시 복사
		return arr;
	}

	private static void hit(int[][] arr, int r, int c) {	//맨 위 벽돌 + 연쇄
		int sr = 0 ,sc = c;
		
		if(r == -1) {
			for(int i=0; i<H; i++) {
				if(arr[i][c] != 0) {
					sr = i;
					break;
				}
			}
		}else if(r != -1) {
			sr = r;
		}
		
		int count = arr[sr][sc];	//현재 벽돌 값
		arr[sr][sc] = 0;
		int cnt = 1;				//부순 벽돌 카운트
		
		for(int i=0; i<4; i++) {
			cnt = 1;
			int nr = sr;
			int nc = sc;
			for(int j=1; j<count; j++) {
				nr += dir[i][0];
				nc += dir[i][1];
				if(nr < H && nr >= 0 && nc >= 0 && nc < W) {
					if(arr[nr][nc] != 0) {
						hit(arr, nr, nc);
						arr[nr][nc] = 0;
					}
					cnt++;
				}
			}
		}
	}
	
	private static void print(int[][] arr) {
		System.out.println("## print ##");
		for(int i=0; i<H; i++) {
			for(int j=0; j<W; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	private static void copyArr(int[][] from, int[][] to) {
		for(int i=0; i<H; i++)
			to[i] = from[i].clone();
	}
}
