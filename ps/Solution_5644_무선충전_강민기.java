import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Solution_5644_무선충전_강민기 {
	static int N, M, A, ans, temp;
	static int[] move1, move2;
	static int[][] user;
	static BC[] bc;
	static boolean[] visit;
	static int[][] dir = {{0,0},{-1,0},{0,1},{1,0},{0,-1}};
	//0 1 2 3 4
	//X 상 우 하 좌
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			move1 = new int[M];
			move2 = new int[M];
			bc = new BC[A];
			user = new int[2][2];
			user[0][0] = user[0][1] = 1;
			user[1][0] = user[1][1] = 10;
			ans = 0;
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<M; i++) 
				move1[i] = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<M; i++) 
				move2[i] = Integer.parseInt(st.nextToken());
			
			for(int i=0; i<A; i++) {
				st = new StringTokenizer(br.readLine());
				bc[i] = new BC(Integer.parseInt(st.nextToken())
						,Integer.parseInt(st.nextToken())
						,Integer.parseInt(st.nextToken())
						,Integer.parseInt(st.nextToken()));
			}
//			Arrays.sort(bc);
			
			for(int i=0; i<2; i++) {
				for(int j=0; j<A; j++) {
					if(canCharge(i, j)) {
						ans += bc[j].perform;
					}
				}
			}
			
			for(int i=0; i<M; i++) {
				user[0][0] += dir[move1[i]][0];
				user[0][1] += dir[move1[i]][1];
				user[1][0] += dir[move2[i]][0];
				user[1][1] += dir[move2[i]][1];
				
				visit = new boolean[A];
				temp = 0;
				func(0, 0);
				ans += temp;
			}
			
			System.out.println("#"+t+" "+ans);
		}//test case
	}
	//n : user Num 
	private static void func(int n, int sum) {
		if(n == 2) {
			temp = Math.max(temp, sum);
			return;
		}
		
		for(int i=0; i<A; i++) {
			if(!visit[i] && canCharge(n, i)) {
				visit[i] = true;
				func(n+1, sum+bc[i].perform);
				visit[i] = false;
			}
		}
		func(n+1, sum);
	}
	
	private static boolean canCharge(int n, int idx) {
		int dis = Math.abs(user[n][0] - bc[idx].r) + Math.abs(user[n][1] - bc[idx].c);
		if(dis <= bc[idx].coverage)
			return true;
		else
			return false;
	}
	
	static class BC implements Comparable<BC>{
		int r;
		int c;
		int coverage;
		int perform;
		BC(int c, int r, int coverage, int perform){
			this.c=c; this.r=r; this.coverage=coverage; this.perform=perform;}
		@Override
		public int compareTo(BC o) {
			int rs = Integer.compare(perform, o.perform);
			return -rs;
		}
	}
}
