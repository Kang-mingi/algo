package com.ssafy.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//input
//abcaeabcabcabdf
//abcab

// output
// 2


public class KMPTest {

	public static void main(String[] args) throws IOException {
//		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//
//		char[] T = in.readLine().toCharArray();
//		char[] P = in.readLine().toCharArray();
//
//		int tLength = T.length, pLength = P.length;
//		int[] fail = new int[pLength];
//
//		// 실패 함수 만들기: P에서 P를 찾는 형태로...
//		// 해당 부분문자열의 최대접미사의 길이
//		for (int i = 1, j = 0; i < pLength; i++) { // i: 접미사포인터, j: 접두사포인터
//			// j 위치에서 틀리면 j-1 위치까지는 맞았다는 이야기
//			// fai[j-1]: j-1 위치까지 일치하는 접미사의 길이 ==> 이동해야하는 다음 비교 j 위치
//			while (j > 0 && P[i] != P[j])
//				j = fail[j - 1];
//
//			if (P[i] == P[j])
//				fail[i] = ++j;
//		}
//
//		int cnt = 0;
//		// i: 텍스트포인터, j: 패턴포인터
//		// i는 계속 증가하는 형태
//		for (int i = 0, j = 0; i < tLength; i++) {
//			while (j > 0 && T[i] != P[j])
//				j = fail[j - 1];
//
//			if (T[i] == P[j]) {
//				if(j==pLength-1) { // 패턴의 끝까지 일치
//					cnt++;
//					j=fail[j]; // 주의!!!!! 끝까지 일치했다는 것은 j까지 맞았다는 의미이므로 => j-1아니고 j임에 주의!!!
//				}
//				else {
//					j++;
//				}
//			}
//		}
		System.out.println(KMP("신라면", ""));

	}
	static boolean KMP(String name, String keyword) {
		boolean result = false;
		char[] T = name.toCharArray();
		char[] P = keyword.toCharArray();

		int tLength = T.length, pLength = P.length;
		int[] fail = new int[pLength];

		// 실패 함수 만들기: P에서 P를 찾는 형태로...
		// 해당 부분문자열의 최대접미사의 길이
		for (int i = 1, j = 0; i < pLength; i++) { // i: 접미사포인터, j: 접두사포인터
			// j 위치에서 틀리면 j-1 위치까지는 맞았다는 이야기
			// fai[j-1]: j-1 위치까지 일치하는 접미사의 길이 ==> 이동해야하는 다음 비교 j 위치
			while (j > 0 && P[i] != P[j])
				j = fail[j - 1];

			if (P[i] == P[j])
				fail[i] = ++j;
		}

		int cnt = 0;
		// i: 텍스트포인터, j: 패턴포인터
		// i는 계속 증가하는 형태
		for (int i = 0, j = 0; i < tLength; i++) {
			while (j > 0 && T[i] != P[j])
				j = fail[j - 1];

			if (T[i] == P[j]) {
				if(j==pLength-1) { // 패턴의 끝까지 일치
					cnt++;
					j=fail[j]; // 주의!!!!! 끝까지 일치했다는 것은 j까지 맞았다는 의미이므로 => j-1아니고 j임에 주의!!!
				}
				else {
					j++;
				}
			}
			if(cnt>0) {
				result = true;
				break;
			}
		}
		return result;
	}

}
