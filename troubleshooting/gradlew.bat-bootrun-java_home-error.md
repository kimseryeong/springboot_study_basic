# 🛠 [gradlew.bat bootRun 실행 시 JAVA_HOME 오류]

### 🤬 error

```
ERROR: JAVA_HOME is set to an invalide directory: `C:\Program Files\Java\jdk-17\jdk-17.0.10_windows-x64_bin.exe`

Please set the JAVA_HOME variable in your environment to match the location of your Java installation.
```

<br>

### 🔍 cause

```
(window 노트북 사용 중)
JAVA_HOME 의 경로가 잘못 설정되어 있음
```

<br>

### ✅ solution

1. `C:\Program Files\Java\jdk-17` 로 변경해야 함
2. where java 명령어 실행 시 `C:\Program Files\Common Files\Oracle\Java\javapath\java.exe` 로 Java 가 이중으로 설정되어 있음 → 삭제
3. cmd 적용 안되길래 끄고 다시 켰더니 변경됨..
