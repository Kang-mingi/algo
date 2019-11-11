import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1733_오목_강민기 {
	static int N, ans, ansR,ansC;
	static int[][] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		arr = new int[19][19];
		N = 19;
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++)
				arr[i][j] = Integer.parseInt(st.nextToken());
		}
		all:for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(arr[i][j] != 0) {
					if(func(i,j))
						break all;
				}
			}
		}
		
		System.out.println(ans);
		if(ans != 0)
			System.out.println(ansR + " " + ansC);
	}//검1 흰2
	private static boolean func(int r, int c) {
		int temp = arr[r][c];
		
		//오른쪽
		for(int i=1; i<5; i++) {
			if(c+4 < N) {
				if(arr[r][c+i] != temp) {
					break;
				}
				if(i == 4) {
					boolean flag = true;
					if(c+5 < N) {
						if(arr[r][c+5] == temp)
							flag = false;
					}
					if(c-1 >= 0) {
						if(arr[r][c-1] == temp)
							flag = false;
					}
					if(flag) {
						ans = temp;
						ansR = r+1;
						ansC = c+1;
						return flag;
					}
				}
			}else
				break;
		}
		
		//아래
		for(int i=1; i<5; i++) {
			if(r+4 < N) {
				if(arr[r+i][c] != temp) {
					break;
				}
				if(i == 4) {
					boolean flag = true;
					if(r+5 < N) {
						if(arr[r+5][c] == temp)
							flag = false;
					}
					if(r-1 >= 0) {
						if(arr[r-1][c] == temp)
							flag = false;
					}
					if(flag) {
						ans = temp;
						ansR = r+1;
						ansC = c+1;
						return flag;
					}
				}
			}else
				break;
		}
		
		//오른쪽 아래
		for(int i=1; i<5; i++) {
			if(r+4 < N && c+4 < N) {
				if(arr[r+i][c+i] != temp) {
					break;
				}
				if(i == 4) {
					boolean flag = true;
					if(r+5 < N && c+5 < N) {
						if(arr[r+5][c+5] == temp)
							flag = false;
					}
					if(r-1 >= 0 && c-1 >= 0) {
						if(arr[r-1][c-1] == temp)
							flag = false;
					}
					if(flag) {
						ans = temp;
						ansR = r+1;
						ansC = c+1;
						return flag;
					}
				}
			}else
				break;
		}
		
		//왼쪽 아래
		for(int i=1; i<5; i++) {
			if(c-4 >= 0 && r+4 < N) {
				if(arr[r+i][c-i] != temp) {
					break;
				}
				if(i == 4) {
					boolean flag = true;
					if(r+5 < N && c-5 >= 0) {
						if(arr[r+5][c-5] == temp)
							flag = false;
					}
					if(r-1 >= 0 && c+1 < N) {
						if(arr[r-1][c+1] == temp)
							flag = false;
					}
					if(flag) {
						ans = temp;
						ansR = r+1+4;
						ansC = c+1-4;
						return flag;
					}
				}
			}else
				break;
		}
		
		return false;
	}
}
