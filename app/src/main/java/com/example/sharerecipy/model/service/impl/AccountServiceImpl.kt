package com.example.sharerecipy.model.service.impl

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.sharerecipy.*
import com.example.sharerecipy.model.service.AccountService
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import javax.inject.Inject
import com.example.sharerecipy.R.string as AppText

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
                            AppText.sign_in,
                            Toast.LENGTH_SHORT).show()
                        openAndPopUp(HOME_SCREEN, LOGIN_SCREEN)
                    }else { // 실패 시 진행바 중지 & 에러 토스트
                        loginValid.value = false
                        try{
                            throw task.exception!!
                        }catch (e : FirebaseAuthInvalidUserException){
                            Toast.makeText(context, AppText.non_existent_member, Toast.LENGTH_SHORT).show()
                        }catch (e: FirebaseAuthInvalidCredentialsException){
                            Toast.makeText(context, AppText.wrong_format, Toast.LENGTH_SHORT).show()
                        }catch (e: FirebaseAuthException){
                            Toast.makeText(context, e.errorCode, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
        }else {
            loginValid.value = false
            Toast.makeText(
                context,
                AppText.empty_id_or_pw,
                Toast.LENGTH_SHORT).show()
        }
    }

    // 계정 생성
    override fun createAccount(
        email: String,
        name: String,
        password: String,
        confirmPw: String,
        context: Context,
        openAndPopUp: (String, String) -> Unit
    ) {
        val db = Firebase.firestore
        val users = db.collection("user")
        if (confirmPw!=password){
            Toast.makeText(context, AppText.password_match_error, Toast.LENGTH_SHORT).show()
        } else {
            if (email.isNotEmpty() && password.isNotEmpty()){
                Firebase.auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) { // 성공 시 회원가입 완료
                            val user = hashMapOf(
                                "email" to email,
                                "nickname" to name,
                                "password" to password
                            )
                            // user 객체를 파이어스토어의 user 컬렉션에 email 이라는 document 로 저장
                            users.document(email).set(user)
                                .addOnSuccessListener { it ->
                                    Log.i(TAG, "DocumentSnapshot added with ID: $it")
                                }.addOnFailureListener { e ->
                                    Log.w(TAG, "Error adding document", e)
                                }
                            Toast.makeText(context, AppText.complete_creation, Toast.LENGTH_SHORT).show()
                            openAndPopUp(LOGIN_SCREEN, SIGNUP_SCREEN) // 로그인 화면으로 이동
                        }else { // 실패 시 에러 토스트
                            try {
                                throw task.exception!!
                            }catch (e: FirebaseAuthUserCollisionException){
                                Toast.makeText(context, AppText.already_exist_email, Toast.LENGTH_SHORT).show()
                            }catch (e: FirebaseAuthWeakPasswordException){
                                Toast.makeText(context, AppText.password_error, Toast.LENGTH_SHORT).show()
                            } catch (e: FirebaseAuthInvalidCredentialsException){
                                Toast.makeText(context, AppText.invalid_email, Toast.LENGTH_SHORT).show()
                            }catch (e: FirebaseAuthException){
                                Toast.makeText(context, e.errorCode, Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
            }else {
                Toast.makeText(context, AppText.fill_in_all_fields, Toast.LENGTH_SHORT).show()
            }
        }
    }

    // 회원 탈퇴
    override fun deleteAccount(context: Context, openAndPopUp: (String, String) -> Unit) {
        Firebase.auth.currentUser!!.delete()
            .addOnCompleteListener {
                Toast.makeText(context, AppText.withdrawal, Toast.LENGTH_SHORT).show()
                openAndPopUp(LOGIN_SCREEN, HOME_SCREEN)
            }
    }

    override fun signOut(context: Context, openAndPopUp: (String, String) -> Unit) { // 메인화면으로 이동
        Firebase.auth.signOut()
        Toast.makeText(context, AppText.logout, Toast.LENGTH_SHORT).show()
        openAndPopUp(LOGIN_SCREEN, HOME_SCREEN) // 로그인 화면으로 이동
    }

    override fun autoLogin(openAndPopUp: (String, String) -> Unit) {
        val currentUser = Firebase.auth.currentUser
        if (currentUser!=null)
            openAndPopUp(HOME_SCREEN, LOGIN_SCREEN)
    }

    companion object{
        const val TAG = "AccountServiceImpl"
    }
}