import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1912_연속합 {
	static int N, ans;
	static int[] arr;
	static int[] d;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		d = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		d[0] = arr[0];
		ans = arr[0];
		for(int i=1; i<N; i++) {
			d[i] = d[i-1] + arr[i];
			d[i] = Math.max(d[i], arr[i]);
//			if(d[i] < 0) {
//				d[i] = 0;
//			}
			
			ans = Math.max(d[i], ans);
		}
		
		print();
		System.out.println(ans);
	}
	
	private static void print() {
		System.out.println("# print #");
		for(int i=0; i<N; i++) {
			System.out.print(d[i]+" ");
		}System.out.println("\n");
	}
}

//새로 시작하는 것
//이어서 사용하는 것