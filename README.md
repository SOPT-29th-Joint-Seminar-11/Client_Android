# π© ν©λ μΈλ―Έλ 11μ‘° 

## π μμ° μμ
https://user-images.githubusercontent.com/61824695/144712918-cdf84bdf-01d4-462e-a1c1-146551514c6d.mp4



https://user-images.githubusercontent.com/61824695/144712897-e8aef281-aa94-477e-8d60-316018f8ea9b.mp4




<br>

## π git branch

* μ΄μ λ²νΈλ‘ branch μμ±
  * ex) feature/#1 
  * μ΄μλ '[type] κΈ°λ₯ μ€λͺ'μΌλ‘ μμ±

<br>

## π git commit message

# Commit

------

### Basic Struture

```
[type] #{issue_number} - subject
```

### Type

- feat: μλ‘μ΄ κΈ°λ₯
- fix: λ²κ·Έ μμ 
- docs: λ¬Έμ μμ 
- style: μ€νμΌ λ³κ²½(μ½λ λ³κ²½ X)
- refactor: λ¦¬ν©ν λ§
- test: νμ€νΈ μ½λ μΆκ°/νμ€νΈ λ¦¬ν©ν λ§
- chore: updating build tasks, package manager μ€μ 

### Subject

- Subjectλ 50κΈμλ₯Ό λμ΄κ°λ©΄ μλλ€
- μ²« μμμ λλ¬Έμλ‘ ν΄μΌνλ€
- λ§μ§λ§μ λ§μΉ¨ν(.)λ₯Ό μ°μΌλ©΄ μλλ€
- μ΄λ€ λ³κ²½μ μ΄ μλμ§ μ€λͺνλ€
- λͺλ Ήμ‘°λ₯Ό μ¬μ©νλ€

<br>

## π git flow

- Master
- Develop
- Feature
- Release
- Hotfix

<br>

## π νλ‘μ νΈ ν΄λλ§

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

## π coding convention

kotlin style guide λ°λ₯΄κΈ°

### Class Layout

λ€μκ³Ό κ°μ μμ ν΄λμ€λ₯Ό κ΅¬μ±ν©λλ€

- Property μ μΈκ³Ό μ΄κΈ°ν λΈλ­(intializer blocks)
- μΆκ°μ μΈ μμ±μ
- λ©μλ μ μ
- μ»΄ν¨λμΈ μ€λΈμ νΈ(Companion object)

### Naming Rule

### Package

- ν¨ν€μ§μ μ΄λ¦μ ν­μ μλ¬Έμλ‘ νκ³ , λ°μ€μ μ¬μ©νμ§ μμ΅λλ€
- λ κ° μ΄μμ λ¨μ΄λ₯Ό ν λ²μ μ¬μ©νλ κ²μ κΈμ§ν©λλ€

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
- μμλ companion objetμ λ£μ΄ λ³΄κ΄ν©λλ€

```
companion object {
    const val MAX_COUNT = 8
}
```

### Formatting

### Indenting

Tab ν€λ₯Ό μ¨μ Indenting ν©λλ€

### μ€κ΄νΈ

κ΄νΈ λ€μ ν μΉΈμ λμ°κ³  μ¬μ©ν©λλ€

```
if (elements != null) {
    for (element in elements) {
        // ...
    }
}
```

### κ΄νΈ

- Controlλ¬Έ(if/while/for)
  - ν μΉΈ λμ΄μλλ€
- μμ±μ/Method
  - λΆμ¬μλλ€

```
if (isSpacing == true) { /*...*/ }
fun isSpacing() { /*...*/ }
```

### Colon(:)

- λ³μμ νμ/ν¨μ λ¦¬ν΄ νμ κ²°μ 
  - μ½λ‘ μ μμ λΆμΈλ€
- μμλ°μ ν΄λμ€/μΈν°νμ΄μ€ κ΅¬λΆ
  - ν μΉΈ λμ΄μ΄λ€

```
fun isSpacing(): Boolean { /*...*/ }
class MainActivity : AppCompatActivity()
```


### Resource naming


<img width="1091" alt="αα³αα³αα΅α«αα£αΊ 2021-08-13 αα©αα? 2 39 37" src="https://user-images.githubusercontent.com/61824695/142552864-9375be39-1b2c-4216-807c-fd43a6770184.png">

