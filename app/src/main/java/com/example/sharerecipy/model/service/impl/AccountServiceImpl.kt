package com.example.sharerecipy.model.service.impl

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.sharerecipy.model.service.AccountService
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import javax.inject.Inject

class AccountServiceImpl @Inject constructor() : AccountService {

    var loginValid : MutableLiveData<Boolean> = MutableLiveData()

    // 로그인
    override fun authenticate(
        email: String,
        password: String,
        context: Context,
        openAndPopUp: (String, String) -> Unit
    ) {
        if (email.isNotEmpty() && password.isNotEmpty()){
            Firebase.auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful){ // 성공 시 메인화면으로 이동
                        loginValid.value = true // 하기 위해 라이브데이터 검사
                        Toast.makeText(
                            context,
                            "로그인 완료",
                            Toast.LENGTH_SHORT).show()
                        openAndPopUp("home", "login")
                    }else { // 실패 시 진행바 중지 & 에러 토스트
                        loginValid.value = false
                        try{
                            throw task.exception!!
                        }catch (e : FirebaseAuthInvalidUserException){
                            Toast.makeText(context, "존재하지 않는 회원입니다.", Toast.LENGTH_SHORT).show()
                        }catch (e: FirebaseAuthInvalidCredentialsException){
                            Toast.makeText(context, "이메일 또는 비밀번호가 잘못되었습니다.", Toast.LENGTH_SHORT).show()
                        }catch (e: FirebaseAuthException){
                            Toast.makeText(context, e.errorCode, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
        }else {
            loginValid.value = false
            Toast.makeText(
                context,
                "아이디 또는 비밀번호를 입력해주세요",
                Toast.LENGTH_SHORT).show()
        }
    }

    // 계정 생성
    override fun createAccount(
        email: String,
        password: String,
        confirmPw: String,
        context: Context,
        openAndPopUp: (String, String) -> Unit
    ) {
        val db = Firebase.firestore
        val users = db.collection("user")
        if (confirmPw!=password){
            Toast.makeText(context, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
        } else {
            if (email.isNotEmpty() && password.isNotEmpty()){
                Firebase.auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) { // 성공 시 회원가입 완료
                            val user = hashMapOf(
                                "email" to email,
                                "nickname" to "신규회원", // default nickname
                                "password" to password
                            )
                            // user 객체를 파이어스토어의 user 컬렉션에 email 이라는 document 로 저장
                            users.document(email).set(user)
                                .addOnSuccessListener { it ->
                                    Log.i(TAG, "DocumentSnapshot added with ID: $it")
                                }.addOnFailureListener { e ->
                                    Log.w(TAG, "Error adding document", e)
                                }
                            Toast.makeText(context, "가입 완료", Toast.LENGTH_SHORT).show()
                            openAndPopUp("home", "login") // 로그인 화면으로 이동
                        }else { // 실패 시 에러 토스트
                            try {
                                throw task.exception!!
                            }catch (e: FirebaseAuthUserCollisionException){
                                Toast.makeText(context, "이미 존재하는 아이디입니다.", Toast.LENGTH_SHORT).show()
                            }catch (e: FirebaseAuthWeakPasswordException){
                                Toast.makeText(context, "비밀번호는 최소 6자리 이상이어야 합니다.", Toast.LENGTH_SHORT).show()
                            } catch (e: FirebaseAuthInvalidCredentialsException){
                                Toast.makeText(context, "유효하지 않은 이메일 형식입니다.", Toast.LENGTH_SHORT).show()
                            }catch (e: FirebaseAuthException){
                                Toast.makeText(context, e.errorCode, Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
            }else {
                Toast.makeText(context, "모든 입력란을 채워주세요.", Toast.LENGTH_SHORT).show()
            }
        }
    }
//    override fun authenticate(email: String, password: String, onResult: (Throwable?) -> Unit) {
//        Firebase.auth.signInWithEmailAndPassword(email, password)
//            .addOnCompleteListener { onResult(it.exception) }
//    }

    override fun deleteAccount(onResult: (Throwable?) -> Unit) {
        Firebase.auth.currentUser!!.delete()
            .addOnCompleteListener { onResult(it.exception) }
    }

    override fun signOut() {
        Firebase.auth.signOut()
    }

    companion object{
        const val TAG = "AccountServiceImpl"
    }
}