 import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_4014_활주로건설_강민기 {
	static int N, X, ans;
	static int[][] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			arr = new int[N][N];
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			ans = 0;
			func();
			
			System.out.println("#"+t+" "+ans);
		}
	}
	private static void func() {//올라가거나 내려올 수 있음
		//가로
		for(int i=0;i<N; i++) {
			int temp = arr[i][0];
			boolean[] visit = new boolean[N];//경사로 세워둔 곳 체크
			Arrays.fill(visit, false);
			c:for(int j=0; j<N; j++) {
				if(arr[i][j] != temp) { 	//다른 수
					if(arr[i][j] > temp) {	//클 경우
						if(j - X >= 0) {
							for(int k = 1; k<=X; k++) {
								if(visit[j-k] || arr[i][j-k] != arr[i][j-1])
									break c;
								visit[j-k] = true;
							}
							 temp = arr[i][j];
						}else {
							break;
						}
					}else {					// 작을 경우
						if(j + X-1 < N) {	// arr[i][j] 도 포함이니까 X-1만 체크
							visit[j] = true;
							for(int k=1; k<X; k++) {
								if(visit[j+k] || arr[i][j+k] != arr[i][j])
									break c;
								visit[j+k] = true;
							}
							temp = arr[i][j];
						}else
							break;
					}
				}//if 다른 수
				if(j == N-1) {
//					System.out.println("#가로 i:"+i+" j:"+j);
					ans++;
				}
			}
		}
		//세로
		for(int i=0;i<N; i++) {							//col
			int temp = arr[0][i];
			boolean[] visit = new boolean[N];
			Arrays.fill(visit, false);
			c:for(int j=0; j<N; j++) {					//row
				if(arr[j][i] != temp) { 				//다른 수
					if(arr[j][i] == temp + 1) {			//클 경우
						if(j - X >= 0) {
							for(int k = 1; k<=X; k++) {
								if(visit[j-k] || arr[j-k][i] != arr[j-1][i])
									break c;
								visit[j-k] = true;
							}
							 temp = arr[j][i];
						}else {
							break;
						}
					}else if(arr[j][i] == temp - 1) {	// 작을 경우
						if(j + X-1 < N) {
							visit[j] = true;
							for(int k=1; k<X; k++) {
								if(visit[j+k] || arr[j+k][i] != arr[j][i])
									break c;
								visit[j+k] = true;
							}
							temp = arr[j][i];
						}else
							break;
					}else
						break c;
				}//if 다른 수
				if(j == N-1) {
//					System.out.println("#세로 i:"+i+" j:"+j);
					ans++;
				}
			}
		}//세로
	}
}//2 3 4 9