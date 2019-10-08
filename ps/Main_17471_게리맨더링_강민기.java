import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_17471_게리맨더링_강민기 {

	static int N, ans;
	static int[] arr, parent;
	static ArrayList<Integer>[] list;
	static int[] selec1, selec2;
	static boolean isOkay;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		arr = new int[N];
		selec1 = new int[N];
		selec2 = new int[N];
		list = new ArrayList[N];
		
		for (int i = 0; i < N; i++)
			list[i] = new ArrayList<Integer>();

		StringTokenizer st = new StringTokenizer(in.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken()); // 인원 수
		}
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			int size = Integer.parseInt(st.nextToken());
			for (int j = 0; j < size; j++) {
				int input = Integer.parseInt(st.nextToken()) - 1; // 인접한 구역
				list[i].add(input);
			}
		}

		if(N == 2) {
			System.out.println(Math.abs(arr[0] - arr[1]));
			return;
		}
		
		ans = Integer.MAX_VALUE;
		func(0, 0, 0);
		System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
	}

	private static void func(int idx, int left, int right) {
		if (idx == N) {
			check(left, right);
			return;
		}
		selec1[left] = idx;
		func(idx + 1, left + 1, right);
		selec2[right] = idx;
		func(idx + 1, left, right + 1);
	}

	private static boolean check(int left, int right) {
		parent = new int[N];
		Arrays.fill(parent, -1);
		
		//왼쪽 선거구 가능한 지 확인
		checkSet(selec1, left);
		//오른쪽 선거구 가능한 지 확인
		checkSet(selec2, right);
		
		//cnt : -1인 지역(루트), 2개일 경우만 성립
		int cnt = 0;
		for(int i=0; i<N; i++) {
			if(parent[i] == -1) {	
				cnt++;
			}
		}
		
		if(cnt > 2)
			return false;
		else {
			//왼쪽과 오른쪽 선거구 합 차이 구하기 
			makeAns(left, right);
			return true;
		}
	}

	private static void makeAns(int left, int right) {
		int leftSum = 0;
		int rightSum = 0;
		
		for(int i=0; i<left; i++) {
			leftSum += arr[selec1[i]];
		}
		for(int i=0; i<right; i++) {
			rightSum += arr[selec2[i]];
		}
		
		ans = Math.min(ans,  Math.abs(leftSum - rightSum));
	}

	private static void checkSet(int[] selec, int idx) {
		ArrayList<Integer> newList = new ArrayList<>();
		
		for(int i=0; i<idx;i++)
			newList.add(selec[i]);
		
		for(int i=0; i<idx-1; i++) {	//adj list
			for(int j=i+1; j<idx; j++) {//newList
				if(list[newList.get(i)].contains(newList.get(j))) {
					union(newList.get(i), newList.get(j));
				}
			}
		}
	}
	
	private static int find(int a) {
		if (parent[a] == -1) {
			return a;
		}
		return parent[a] = find(parent[a]);
	}

	private static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);

		if (aRoot != bRoot) {
			parent[bRoot] = aRoot;
			return true;
		} else {
			return false;
		}
	}
}
// 하나씩 선택하고
// 가능한 방법인지 확인 (어떻게? union-find로 ? 낮은 숫자로 union 후 find해봐서 ? )
// 가능한 방법이라면 인구의 차이 구함
