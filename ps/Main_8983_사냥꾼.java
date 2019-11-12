import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_8983_사냥꾼 {
	static int N, M, L, ans;
	static int[] arrM;
	static int[][] ani;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());			//사대의 수
		N = Integer.parseInt(st.nextToken());			//동물의 수
		L = Integer.parseInt(st.nextToken());			//사정거리
		arrM = new int[M];
		ani = new int[N][2];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<M; i++)
			arrM[i] = Integer.parseInt(st.nextToken());	//사대의 x좌표
		
		Arrays.sort(arrM);
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			ani[i][0] = Integer.parseInt(st.nextToken());	//동물의 x좌표
			ani[i][1] = Integer.parseInt(st.nextToken());	//동물의 y좌표
		}
		for(int i=0; i<N; i++)
			func(i);
		
		System.out.println(ans);
	}
	
	private static void func(int idx) {
		int left = 0;
		int right = M;
		while(left <= right) {
			int mid = (left + right) / 2;
			
			if(mid < 0 || mid >= M) return;
			//mid index의 사대의 사정거리에 속하는지 확인
			long rs = Math.abs(arrM[mid] - ani[idx][0]) + ani[idx][1];
			if(rs <= L) {
				ans++;
				return;
			}else {
				if(ani[idx][0] > arrM[mid]) {
					left = mid + 1;
				}else if(ani[idx][0] < arrM[mid]){
					right = mid - 1;
				}else
					return;
			}
		}
	}

}
