import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import javax.tools.ForwardingJavaFileManager;

public class Solution_D3_5607_Professional조합_강민기 {
	static int N, R, P;
	static long[] factorial;
	
	public static void main(String[] args) throws IOException{
		P = 1234567891;
		factorial = new long[1000001];
		factorial[0] = factorial[1] = 1;
		for(int i=2; i<=1000000; i++) {
			factorial[i] = (factorial[i-1] * i) %P;	//fac미리 계산해두기
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			
			System.out.println("#"+t+" "+combination());
		}
	}
	static long power(long a, int b) {
		if(b==1) return a;
		long r = power(a, b/2);
		r = (r*r) % P;
		if(b%2>0) r = (r * a) %P;	//홀수
		return r%P;
	}
	static long combination() {
		return (factorial[N] * power(factorial[N-R], P-2)%P * power(factorial[R],P-2)%P ) % P;
	}
}
/*
나머지 공식은 (+, - , *)에서만 적용되고 나누기에서는 적용되지 않음
따라서 페르마의 소정리를 이용해야한다. 

nCk % p = n! / (n-k)!k! % p
= ( n! * (n-k)^p-2 * k^p-2 ) % p
= ( n! % p *(n-k)^p-2 % p * k^p-2 %p ) % p		//여기서 factorial을 구해준다 (%p하면서), test case 밖에서 한번만


//페르마의 소정리
a^p-1 = 1 mod p
a * a^p-2 = 1/a mod p


*/
