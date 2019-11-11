import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_5427_불 {
	static int R,C, ans;
	static char[][] arr;
	static int[][] dir = {{-1,0},{0,1},{1,0},{0,-1}};
	
	static Pos user,home;
	static ArrayList<Pos> fire; 
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			C = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			arr = new char[R][C];
			fire = new ArrayList<>();
			ans = 0;
			for(int i=0; i<R; i++) {
				String s = br.readLine();
				for(int j=0; j<C; j++) {
					arr[i][j] = s.charAt(j);
					if(arr[i][j] == 'D') {
//						home = new Pos(i, j);
					}else if(arr[i][j] == '@') {
						user = new Pos(i, j);
					}else if(arr[i][j] == '*') {
						Pos temp = new Pos(i, j);
						fire.add(temp);
					}
				}
			}
			
			func();		//user먼저 bfs 하고, 불 번지고
			
			if(ans == -1)
				System.out.println("IMPOSSIBLE");
			else
				System.out.println(ans);
		}//test case
	}
	private static void func() {
		Queue<Pos> quser = new LinkedList<Pos>();
		Queue<Pos> qfire= new LinkedList<Pos>();

		//user
		quser.add(user);
		//fire
		for(int i=0; i<fire.size(); i++)
			qfire.add(fire.get(i));
		
		while(!quser.isEmpty()) {
			ans++;//level
			
			//user
			int size = quser.size();
			for(int i=0; i<size; i++) {
				Pos temp = quser.poll();
				if(arr[temp.r][temp.c] == '*') continue;
				for(int j=0; j<4; j++) {//dir
					int nr = temp.r;
					int nc = temp.c;
					nr += dir[j][0];
					nc += dir[j][1];
					if(nr<R && nc < C && nr>= 0 && nc>=0 && arr[nr][nc] == '.') {
						arr[nr][nc] = 'S';
						quser.add(new Pos(nr, nc));
					}else if(nr>=R || nc >= C || nr<0 || nc<0){
						return;
					}
				}
			}//for q.size
			
			//fire
			int size2 = qfire.size();
			for(int i=0; i<size2; i++) {
				Pos temp = qfire.poll();
				for(int j=0; j<4; j++) {//dir
					int nr = temp.r;
					int nc = temp.c;
					nr += dir[j][0];
					nc += dir[j][1];
					if(nr< R && nc< C && nr>= 0 && nc>=0 
							&& arr[nr][nc] != '#' && arr[nr][nc] != '*') {
						arr[nr][nc] = '*';
						qfire.add(new Pos(nr, nc));
					}
				}
			}//for q.size
		}//while
		ans = -1;
	}
	
	static class Pos{
		int r;
		int c;
		Pos(int r, int c){this.r=r; this.c=c;}
	}
}
