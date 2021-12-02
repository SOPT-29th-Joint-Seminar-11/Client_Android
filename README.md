# 🚩 합동 세미나 11조 

## 📌 시연 영상

https://user-images.githubusercontent.com/61824695/142553648-51a2f73d-d64d-48bb-9931-156693e9a0a6.mp4

<br>

## 📌 git branch

* 이슈 번호로 branch 생성
  * ex) feature/#1 
  * 이슈는 '[type] 기능 설명'으로 작성

<br>

## 📌 git commit message

# Commit

------

### Basic Struture

```
[type] #{issue_number} - subject
```

### Type

- feat: 새로운 기능
- fix: 버그 수정
- docs: 문서 수정
- style: 스타일 변경(코드 변경 X)
- refactor: 리팩토링
- test: 테스트 코드 추가/테스트 리팩토링
- chore: updating build tasks, package manager 설정

### Subject

- Subject는 50글자를 넘어가면 안된다
- 첫 시작은 대문자로 해야한다
- 마지막에 마침표(.)를 찍으면 안된다
- 어떤 변경점이 있는지 설명한다
- 명령조를 사용한다

<br>

## 📌 git flow

- Master
- Develop
- Feature
- Release
- Hotfix

<br>

## 📌 프로젝트 폴더링

+ network
  + service
  + model

+ ui
  + calendar
  + detail
  + home
    + bestreview
    + recommendplace 
  + mypage
  + wish
+ util

<br>

## 📌 coding convention

kotlin style guide 따르기

### Class Layout

다음과 같은 순서 클래스를 구성합니다

- Property 선언과 초기화 블럭(intializer blocks)
- 추가적인 생성자
- 메소드 정의
- 컴패니언 오브젝트(Companion object)

### Naming Rule

### Package

- 패키지의 이름은 항상 소문자로 하고, 밑줄을 사용하지 않습니다
- 두 개 이상의 단어를 한 번에 사용하는 것을 금지합니다

### Class/Object

- Pascal Case

```
open class SampleName { /* ... */ }
object MoreSampleName : SampleName() { /* ... */ }
```

### Function/Parameter/Variable

- Camel Case

```
val initList = mutableList<SampleData>()
fun getList: List<SampleData>() { /* ... */ }
```

### Constant

- Upper Snake Case
- 상수는 companion objet에 넣어 보관합니다

```
companion object {
    const val MAX_COUNT = 8
}
```

### Formatting

### Indenting

Tab 키를 써서 Indenting 합니다

### 중괄호

괄호 뒤에 한 칸을 띄우고 사용합니다

```
if (elements != null) {
    for (element in elements) {
        // ...
    }
}
```

### 괄호

- Control문(if/while/for)
  - 한 칸 띄어씁니다
- 생성자/Method
  - 붙여씁니다

```
if (isSpacing == true) { /*...*/ }
fun isSpacing() { /*...*/ }
```

### Colon(:)

- 변수의 타입/함수 리턴 타입 결정
  - 콜론을 앞에 붙인다
- 상속받은 클래스/인터페이스 구분
  - 한 칸 띄어쓴다

```
fun isSpacing(): Boolean { /*...*/ }
class MainActivity : AppCompatActivity()
```


### Resource naming


<img width="1091" alt="스크린샷 2021-08-13 오후 2 39 37" src="https://user-images.githubusercontent.com/61824695/142552864-9375be39-1b2c-4216-807c-fd43a6770184.png">

