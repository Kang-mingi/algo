import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1912_연속합2 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] nums = new int[N];
		int[] D = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		D[0] = nums[0];
		int ans = nums[0];
		for (int i = 1; i < N; i++) {
			D[i] = Math.max(D[i - 1] + nums[i], nums[i]);
			if (D[i] < 0)
				D[i] = nums[i];
			if (ans < D[i])
				ans = D[i];
		}
		System.out.println(ans);
	}
}