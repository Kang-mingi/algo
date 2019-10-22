package com.ssafy.graph;

import java.util.Arrays;

/*
Boyer-Moore  
 - Bad Character Shift 방법 
 */
public class BoyerMoore {
	public static void main(String[] args) {
		String text = "adndkfieiainddfdmaindlflidl";
//	    String text = "adndmaiamain";
		String P = "main";
		String text2 = "a pattern matching algorithm";
		String P2 = "rithm";
		String text3 = "accabcabc";
		String P3 = "abcabc";
		String text4 = "violindonotlearmalice";
		String P4 = "alice";
		String text5 = "ABC ABCDAB ABCDABCDABDE";
		String P5 = "ABCDABD";

//	    System.out.println(boyerMooreSearch(text,P));
//	    System.out.println(boyerMooreSearch(text2,P2));
		System.out.println(boyerMooreSearch(text3, P3));
		System.out.println(boyerMooreSearch(text4, P4));
		System.out.println(boyerMooreSearch(text5, P5));
	}

	public static int boyerMooreSearch(String text, String pattern) {
		int patLen = pattern.length(); // 패턴의 길이
		int textLen = text.length();

		// 텍스트와 패턴이 일치하지 않았을 때 텍스트 포인터 skip 크기 저장
		int[] skip = new int[128]; // 문자의 코드를 인덱스로 사용하므로 문자코드를 표현할수 있는 크기로 만듦.
		int i, j;

		// 표 skip작성 : 기본값은 패턴의 길이로한다.(패턴 길이 만큼 건너뛰기 위해)
		// =================================================
		// x=0 패턴마지막인덱스-x 4-1-0 : 3
		// x=1 패턴마지막인덱스-x 4-1-1 : 2
		// x=2 패턴마지막인덱스-x 4-1-2 : 1
		// x=3 패턴마지막인덱스-x 4-1-3 : 0
		Arrays.fill(skip, patLen); // 텍스트 포인터의 문자가 패턴에 존재하지 않으면 패턴길이 만큼 skip하므로.
		for (int x = 0; x < patLen - 1; x++) { // 텍스트 포인터와 패턴의 마지막 글자가 다를 경우
												// 패턴글자가 반복적인 패턴을 보이지 않으면 결국 마지막 글자가 틀리면 패턴 길이만큼 skip(채워진 기본값임)
												// 패턴글자가 반복적인 패턴을 보일 경우 해당위치에서 끝위치의 인덱스 차이 만큼 skip
			skip[pattern.charAt(x)] = patLen - 1 - x; //
		}

		// 포인터를 초기화한다. 패턴을 뒤에서부터 비교하기 때문에
		i = patLen - 1;
		// 텍스트의 가장 마지막에 도달할 때까지 반복한다
		while (i < textLen) {
			// 포인터 j가 패턴의 마지막 문자를 가리키도록 한다
			j = patLen - 1;
			// 텍스트와 패턴이 일치하는 동안 반복한다
			while (text.charAt(i) == pattern.charAt(j)) {
				// 처음 문자까지 일치했다면 탐색은 성공이다
				if (j == 0) {
					return i;
				}
				i--;
				j--;
			}
			// 텍스트와 패턴이 일치하지 않으면 텍스트포인터 i 뒤로 이동.
			// 일치하지 않는 i위치의 문자가 패턴에 존재하지 않으면 결국 패턴길이만큼 이동하게 됨
			// 일치하지 않는 i위치의 문자가 패턴에 존재하면 skip크기와 패턴에서 틀린위치를 비교해서 큰 값만큼 뒤로 이동.
			// patLen - j가 더 커서 이동하게 되는 경우는 i포인터를 비교시작위치에서 한칸 뒤로 이동하는 효과.
			i = i + Math.max(skip[text.charAt(i)], patLen - j);
		}
		// 결국 발견하지 못했을때
		return -1;
	}

}
