import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_17069_파이프옮기기2_강민기 {
	static int N;
	static long ans;
	static long[][] horizon, vertical, diagonal;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		horizon = new long[N][N];					//가로
		vertical = new long[N][N];					//세로
		diagonal = new long[N][N];					//대각선
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				long input = Long.parseLong(st.nextToken());
				if(input == 1) input = -1;			//벽 -1로 변경
				horizon[i][j] = input;
				vertical[i][j] = input;
				diagonal[i][j] = input;
			}
		}
		
		//초기값 파이프
		horizon[0][1] = 1;
		
		func();
		
		ans = horizon[N-1][N-1] + vertical[N-1][N-1] + diagonal[N-1][N-1];
		if(ans < 0)
			System.out.println("0");
		else
			System.out.println(ans);
	}

	private static void func() {
		for(int i=0; i<N; i++) {				//row
			for(int j=0; j<N; j++) {			//col
				if(horizon[i][j] == -1) continue;	//벽일 경우 패스
				int ni = i-1;
				int nj = j-1;
				
				//가로	왼쪽 가로 + 왼쪽 대각선
				if(nj>=0) {
					if(horizon[i][nj]>0)
						horizon[i][j] += horizon[i][nj];
					if(diagonal[i][nj]>0)
						horizon[i][j] += diagonal[i][nj];
				}
					
				//세로	위쪽 세로 + 위 대각선
				if(ni>=0) {
					if(vertical[ni][j]>0)
						vertical[i][j] += vertical[ni][j];
					if(diagonal[ni][j]>0)
						vertical[i][j] += diagonal[ni][j];
				}
				
				//대각선	왼쪽 위 가로 + 왼쪽 위 세로 + 왼쪽 위 대각선
				if(ni>=0 && nj>=0) {
					//대각선일경우는 3칸 모두 -1이 아닌지 확인
					if(horizon[i][nj]<0 || horizon[ni][j]<0) continue;
					if(horizon[ni][nj]>0)
						diagonal[i][j] += horizon[ni][nj];
					if(vertical[ni][nj]>0)
						diagonal[i][j] += vertical[ni][nj];
					if(diagonal[ni][nj]>0)
						diagonal[i][j] += diagonal[ni][nj];
				}
			}
		}
		
	}
}

//벽은 1
//가로	왼쪽 가로 + 왼쪽 대각선
//세로	위쪽 세로 + 위 대각선
//대각선	왼쪽 위 가로 + 왼쪽 위 세로 + 왼쪽 위 대각선
