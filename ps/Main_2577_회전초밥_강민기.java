import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2577_회전초밥_강민기 {
	static int N, D, K, C, ans;
	static int[] arr, d, visit;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());		//벨트에 높이 접시의 수
		D = Integer.parseInt(st.nextToken());		//초밥의 가짓 수
		K = Integer.parseInt(st.nextToken());		//연속해서 먹는 접시 수
		C = Integer.parseInt(st.nextToken());		//쿠폰 번호
		arr = new int[N];
		d = new int[N];
		visit = new int[D+1];
		
		for(int i=0; i<N; i++)
			arr[i] = Integer.parseInt(br.readLine());
		
//		for(int i=0; i<N; i++) {
//			func(i);
//		}
		
		func2();
		
		System.out.println(ans);
	}
	private static void func2() {
		int temp = 0;
		for(int i=0; i<K; i++) {
			if(visit[arr[i]] == 0)
				temp++;
			visit[arr[i]]++;
		}
		if(visit[C] == 0)
			d[0] = temp+1;
		else
			d[0] = temp;
		ans = d[0];
		
		for(int i=1; i<N; i++) {
			int idx = i+K-1;
			if(idx >= N)
				idx -= N;
			
			visit[arr[i-1]]--;
			if(visit[arr[i-1]] == 0) {	//이전 초밥 계산
				temp--;
			}
			
			visit[arr[idx]]++;
			if(visit[arr[idx]] == 1) {	//다음 초밥이 아직 안먹은 종류
				temp++;
			}
			
			if(visit[C] == 0)
				d[i] = temp+1;
			else
				d[i] = temp;
			ans = Math.max(ans, d[i]);
		}
	}
	private static void func(int start) {
		int temp = 0;
		boolean[] select = new boolean[D+1];
		boolean included = false;
		
		for(int i=start,idx=0; idx<K; i++,idx++) {
			if(i == N)
				i -= N;
			if(arr[i] == C)
				included = true;
			if(!select[arr[i]]) {
				select[arr[i]] = true;
				temp++;
			}
			if(temp == K)
				break;
		}
		if(!included)
			temp++;
		ans = Math.max(ans, temp);
	}
}
