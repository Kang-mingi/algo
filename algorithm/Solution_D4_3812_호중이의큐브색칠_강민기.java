import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_D4_3812_호중이의큐브색칠_강민기 {
	static int N;
	static int[] arr;
	static long[][] base;
	static long[] color,color2;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			arr=new int[6];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=0; i<6; i++) 
				arr[i] = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			
			base = new long[3][N];
			for(int i=0; i<arr[0]; i++) base[0][Math.abs(arr[3]-i) % N]++;	//x축 기준으로  
			for(int i=0; i<arr[1]; i++) base[1][Math.abs(arr[4]-i) % N]++;	//x축 기준으로  
			for(int i=0; i<arr[2]; i++) base[2][Math.abs(arr[5]-i) % N]++;	//x축 기준으로  
			
			//(x,y,z) 는 |x-A|+|y-B|+|z-C| = i(mod N)
			//z없이  x,y만으로 사용한 면기준으로 색상 카운팅
			color = new long[N];
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					color[(i+j)%N] += base[0][i] * base[1][j];
				}
			}
			//후 z축과 카운팅
			color2 = new long[N];
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					color2[(i+j)%N] += color[i] * base[2][j];
				}
			}
			
			sb.append("#"+t+" ");
			for(int i=0; i<N; i++)
				sb.append(color2[i]+" ");
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
}
