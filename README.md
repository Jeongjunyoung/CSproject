#  국가, 수도 맞추기 퀴즈
간단한 국가, 수도 맞추기 퀴즈 

스크린샷
==========
>**로그인 화면**
<div>
  <img width="250" height="400" src="https://user-images.githubusercontent.com/18605138/46819265-42067580-cdbe-11e8-96c4-3764e0038b10.PNG">
</div>

#####   Google, E-mail 로그인 
#####   사용기능 : Firebase Authentication
Firebase 구글 로그인
```java
if (requestCode == RC_SIGN_IN) {
    GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
    if (result.isSuccess()) {
        GoogleSignInAccount account = result.getSignInAccount();
        firebaseAuthWithGoogle(account);
    }
}
```


>**순위표 화면**
<div>
  <img width="250" height="400" src="https://user-images.githubusercontent.com/18605138/46819600-1df76400-cdbf-11e8-9aef-7f6fed0ddd82.PNG">
</div>

#####   유저간 순위표 기능
#####   사용기능 : Firebase RealtimeDatabase
국기 퀴즈 점수 등록
```java
myRef.child(UserApplication.getInstance().getServiceInterface().getUID())
           .child("score").child("flag_store").child(getContinentString(continentNum)).setValue(updateScore);
```
수도 퀴즈 점수 등록
```java
myRef.child(UserApplication.getInstance().getServiceInterface().getUID())
           .child("score").child("capital_store").child(getContinentString(continentNum)).setValue(updateScore);
```
