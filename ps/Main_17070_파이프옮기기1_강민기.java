import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_17070_파이프옮기기1_강민기 {
	static int N, ans;
	static int[][] arr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//초기값 가로 파이프
		arr[0][0] = 1; arr[0][1] = 1;
		
		func(0,1,2);
		
		System.out.println(ans);
	}

	private static void func(int r, int c, int prev) {
		if(r == N-1 && c == N-1) {
			ans++;
			return;
		}
		
		//가로
		if(c+1 < N && arr[r][c+1] != 1 && prev != 3) {
			func(r,c+1,2);
		}
		//세로
		if(r+1 < N && arr[r+1][c] != 1 && prev != 2) {
			func(r+1,c,3);
		}
		//대각선
		if(r+1 < N && c+1 < N
				&& arr[r][c+1] != 1 && arr[r+1][c] != 1 && arr[r+1][c+1] != 1) {
			func(r+1,c+1,4);
		}
	}
}
//r,c 인덱스 1부터 시작
//파이프는 회전할 수 있으며 3가지 방향 가능 →, ↘, ↓ 대각선(4칸다먹음)
//벽은 1
//가로 2 세로 3 대각선 4
//
