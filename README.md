

#  ⚡ 알고리즘 스터디 ⚡️

SSAFY 9기 18반 알고리즘 스터디

- [ ] 서울 18반 고범수
- [ ] 서울 18반 김예빈
- [ ] 서울 18반 김윤우
- [ ] 서울 18반 박지원
<br><br>

## 📌 Rule
화요일 22:00 온라인으로 미팅
* ❕❕ 문제 출제 : 매주 각자 한 문제씩 출제한다
* ❕❕ 문제 설명 : 본인이 풀이한 문제의 풀이방법을 설명한다
<br><br>

## 📌 Convention
<details>
<summary>1️⃣ Code Convention</summary>
<div markdown="1">
<br>
각 코드 별 목적을 주석으로 작성합니다.
<br>
변수와 함수 이름 또한 역할을 알 수 있도록 간단한 주석을 덧붙입니다.
</div>
</details>


<details>
<summary>2️⃣ Project Convention</summary>
<div markdown="1">
<br>
각 멤버별 프로젝트 구조는 다음과 같습니다<br>
**프로젝트이름/week번호/플랫폼_문제번호_레벨_문제이름/...**

    baekjoon/username/week15/BOJ_1051_S3_숫자정사각형/...

<br>
</div>
</details>


<details>
<summary>3️⃣ Commit Convention</summary>
<div markdown="1">
<br>한번에 모든 파일을 add하지 않고 type별로 분리하여 commit 합니다.

    docs : README.md 등 문서 작성 및 수정
    code : 코드 작성
    fix : 코드 수정
    add : 기존에 푼 문제에 대한 추가
    remove : 코드 및 문서 삭제
    merge : pr(pull request)을 통해 자신의 repo에서 원본 repo로 merge하기


적용 예시 ::
1. BOJ의 1051번 숫자 정사각형 (silver3) 문제를 풀었다면
   해당 코드를 하나의 commit으로 분리합니다.  
   이 때의 commit message는 다음과 같이 통일합니다

   	 git commit -m "code : BOJ 1051 S3 숫자정사각형"

   해당 코드를 수정할 때의 commit message는 다음과 같이 통일합니다.

   	 git commit -m "fix : BOJ 1051 S3 숫자정사각형"

2.  코드에 대한 설명을 작성하고
    해당 문서를 하나의 commit으로 분리합니다.  
    이 때의 commit message는 다음과 같습니다.

    	 git commit -m "docs : BOJ 1051 S3 숫자정사각형"
         
3. main README.md 파일에 주차 별 문제를 추가할 때의 commit message는 다음과 같습니다.

   	 git commit -m "docs : update week # problems"

4. main README.md 파일을 수정할 때의 commit message는 다음과 같습니다.

   	 git commit -m "docs : update README.md"

5. 파일을 삭제할 경우 commit message는 다음과 같습니다

   	 git commit -m "remove : 삭제파일"
<br>
</div>
</details>

<details>
<summary>4️⃣ Review Convention</summary>
<div markdown="1"><br>
1. Pull Request의 제목은 다음과 같이 통일합니다.
   **이름 : 문제플랫폼 문제번호 문제등급 문제제목**

   	 이름 : BOJ 1051 S3 숫자정사각형

2. Pull Request의 comment에는 본인이 작성한 README.md의 내용을 추가합니다.

3. 문제에 해당하는 유형을 선택하여 PR에 label을 attach하고,   
   자신의 PR의 assignee에 자신을 추가 후 문제풀이 week에 해당하는 Milestones을 선택합니다.

4. 기존에 PR을 작성 후 새로운 문제를 풀었을 경우, 새로운 문제에 대한 commit을 하기 전 다음 과정을 수행합니다.

    - ❓ 코드리뷰가 완료 되었을 경우  
      자신의 PR에서 merge 버튼을 눌러 merge 합니다.

    - ❓ 리뷰 완료 전 새로운 문제를 풀 경우
        1. 자신의 local에서 새로 푼 문제에 대한 branch를 생성합니다.  
           이 때 branch의 이름을 **문제플랫폼-문제번호**과 같이 생성하는 것을 권장합니다.

               boj-1051

        2. 새로운 문제에 대한 code와 README.md에 대한 commit을 추가 후 push합니다.   
           이 때의 commit message는 2️⃣ Commit Convention에서 언급한 규칙에 맞게 설정합니다.
        3. 이 때 반드시 (a)에서 생성한 branch로 push 되는지 확인합니다.
        4. 본인 계정의 fork된 repo에서 Pull Request을 작성할 때,   
           코드가 push된 브랜치(a에서 생성한 jodawoooon/boj-1051)에서   
           organization repo의 main 브랜치로 Pull Request를 보냅니다.

</div>
</details>
<br>

## 📌 Solved Problems
### 🚩 week 1
| Type | 문제 | 제목 | 유형 | rank |
| -- |--| -- |--|--|
| BOJ | 9935 | [문자열 폭발](https://www.acmicpc.net/problem/9935) | 스택, 문자열 | gold4 |
| BOJ | 21939 | [문제 추천 시스템 Version 1](https://www.acmicpc.net/problem/21939) | 우선순위 큐 | gold4 |
| BOJ | 3190 | [뱀](https://www.acmicpc.net/problem/3190) | 구현, 덱, 큐 | gold4 |
| BOJ | 1655 | [가운데를 말해요](https://www.acmicpc.net/problem/1655) | 우선순위 큐 | gold2 |

### 🚩 week 2
| Type | 문제 | 제목 | 유형 | rank |
| -- |--| -- |--|--|
| BOJ | 15683 | [감시](https://www.acmicpc.net/problem/15683) | 구현, 브루트포스 | gold4 |
| BOJ | 1941 | [소문난 칠공주](https://www.acmicpc.net/problem/1941) | 그래프 탐색, 조합론, 백트래킹 | gold3 |
| BOJ | 14725 | [개미굴](https://www.acmicpc.net/problem/14725) | 문자열, 트리, 트라이 | gold3 |
| BOJ | 1167 | [트리의 지름](https://www.acmicpc.net/problem/1167) | 트리, 깊이 우선 탐색 | gold2 |

### 🚩 week 3
| Type | 문제 | 제목 | 유형 | rank |
| -- |--| -- |--|--|
| BOJ | 5639 | [이진 검색 트리](https://www.acmicpc.net/problem/5639) | 트리, 재귀, 그래프 탐색 | gold5 |
| BOJ | 1600 | [말이 되고픈 원숭이](https://www.acmicpc.net/problem/1600) | 너비 우선 탐색 | gold3 |
| BOJ | 2206 | [벽 부수고 이동하기](https://www.acmicpc.net/problem/2206) | 너비 우선 탐색 | gold3 |
| BOJ | 2250 | [트리의 높이와 너비](https://www.acmicpc.net/problem/2250) | 트리, 깊이 우선 탐색 | gold2 |

### 🚩 week 3
| Type | 문제 | 제목 | 유형 | rank |
| -- |--| -- |--|--|
| BOJ | 2234 | [성곽](https://www.acmicpc.net/problem/2234) | 너비 우선 탐색, 비트마스킹 | gold3 |
| BOJ | 1493 | [박스 채우기](https://www.acmicpc.net/problem/1493) | 수학, 그리디, 분할 정복 | gold3 |
| BOJ | 2812 | [크게 만들기](https://www.acmicpc.net/problem/2812) | 그리디, 스택 | gold3 |
| BOJ | 10830 | [행렬 제곱](https://www.acmicpc.net/problem/10830) | 분할 정복, 선형대수학 | gold4 |

