

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_D4_4261_빠른휴대전화키패드_강민기 {
	static int N, ans;
	static String input;
	static String[] word;
	static int[] alpha = {2,2,2,3,3,3,4,4,4,5,5,5,6,6,6,7,7,7,7,8,8,8,9,9,9,9};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		int cnt = 0;
		int num = 2;
		
		
		for(int t=1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			input = st.nextToken();
			N = Integer.parseInt(st.nextToken());
			word = new String[N];
			ans = 0;
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++)
				word[i] = st.nextToken();
			
			func();
			
			System.out.println("#"+t+" "+ans);
		}
	}
	private static void func() {
		//a,b,c = 2
		//d e f = 3
		boolean flag = false;
		for(int i=0; i<N; i++) {
			flag = true;
			for(int j=0; j<word[i].length(); j++) {
				if(word[i].length() != input.length()) {
					flag = false;
					break;
				}
				if(alpha[word[i].charAt(j)-'a'] != input.charAt(j)-'0') {
					flag = false;
					break;
				}
			}
			if(flag)
				ans++;
		}
	}
}