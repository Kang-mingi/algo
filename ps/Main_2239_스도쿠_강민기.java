import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_2239_스도쿠_강민기 {
	static int N, ans;
	static int[][] arr;
	static boolean isClear;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = 9;
		arr = new int[N][N];
		for(int i=0; i<N; i++) {
			String s = br.readLine();
			for(int j=0; j<N; j++) {
				arr[i][j] = s.charAt(j)-'0';
			}
		}
		
		func(0,0);
		
	}
	
	private static void func(int r, int c) {
		if(isClear)
			return;
		if(r == N-1 && c == N) {
			print();
			isClear = true;
			return;
		}
		
		if(c==N) {
			func(r+1,0);
			return;
		}
		
		if(arr[r][c] != 0) {
			func(r,c+1);
			return;
		}
		
		for(int k=1; k<=N; k++) {
			if(check(r,c,k)) {
				arr[r][c] = k;
				func(r,c+1);
				arr[r][c] = 0;
			}
		}
		
	}

	private static boolean allClear() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(arr[i][j] == 0)
					return false;
			}
		}
		return true;
	}

	private static boolean check(int r, int c, int n) {
		for(int i=0; i<N; i++) {
			if(arr[r][i] == n)
				return false;
		}
		
		for(int i=0; i<N; i++) {
			if(arr[i][c] == n)
				return false;
		}
		int idx1=0;
		int idx2=0;
		if(r == 0) {
			idx1 = 0;
		}else {
			idx1 = r/3;
			idx1 *= 3;
		}
		if(c == 0) {
			idx2 = 0;
		}else {
			idx2 = c/3;
			idx2 *= 3;
		}
		
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				if(arr[idx1+i][idx2+j] == n)
					return false;
			}
		}
		return true;
	}

	private static void print() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				System.out.print(arr[i][j]);
			}
			System.out.println();
		}
	}
}


//빈칸 리스트 생성
//행체크 boolean 배열
//열체크 boolean 배열
//3x3  boolean 배열