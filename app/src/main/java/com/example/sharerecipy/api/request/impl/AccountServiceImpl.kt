package com.example.sharerecipy.api.request.impl

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.collection.arrayMapOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.sharerecipy.*
import com.example.sharerecipy.api.request.AccountService
import com.firebase.ui.auth.data.model.User
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import javax.inject.Inject
import com.example.sharerecipy.R.string as AppText

class AccountServiceImpl @Inject constructor() : AccountService {

    // 로그인
    override fun login(
        email: String,
        password: String,
        context: Context,
        openAndPopUp: (String, String) -> Unit
    ) {
        if (email.isNotEmpty() && password.isNotEmpty()){
            Firebase.auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful){ // 로그인 성공
                        Toast.makeText(
                            context,
                            AppText.sign_in,
                            Toast.LENGTH_SHORT).show()
                        openAndPopUp(HOME_SCREEN, LOGIN_SCREEN)
                    }else { // 로그인 실패 실패
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
            Toast.makeText(
                context,
                AppText.empty_id_or_pw,
                Toast.LENGTH_SHORT).show()
        }
    }

    // 회원가입
    override fun createAccount(
        email: String,
        name: String,
        password: String,
        confirmPw: String,
        context: Context,
        openAndPopUp: (String, String) -> Unit
    ) {
        if (confirmPw!=password){
            Toast.makeText(context, AppText.password_match_error, Toast.LENGTH_SHORT).show()
        } else {
            if (email.isNotEmpty() && password.isNotEmpty()){
                Firebase.auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) { // 회원가입 성공
                            val db = Firebase.firestore
                            val users = db.collection("user").document(email)
                            // 하위 컬렉션(wish list) -> 문서(레시피명), 필드(재료, 레시피 종류)
                            // 하위 컬렉션에 sample 문서 저장
                            val docRef = users.collection("wish list").document("sample")
                            val data = hashMapOf(
                                "ingredients" to null,
                                "type" to null
                            )
                            docRef.set(data)
                                .addOnSuccessListener {
                                    Log.i(TAG, "DocumentSnapshot added with ID: $it")
                                }.addOnFailureListener { e ->
                                    Log.w(TAG, "Error adding document", e)
                                }
                            // 회원정보 업데이트
                            val newProfile = UserProfileChangeRequest.Builder().setDisplayName(name).build()
                            Firebase.auth.currentUser?.updateProfile(newProfile)
                            Toast.makeText(context, AppText.complete_creation, Toast.LENGTH_SHORT)
                                .show()
                            openAndPopUp(LOGIN_SCREEN, SIGNUP_SCREEN)
                        }else { // 회원가입 실패
                            try {
                                throw task.exception!!
                            }catch (e: FirebaseAuthUserCollisionException){
                                Toast.makeText(context, AppText.already_exist_email, Toast.LENGTH_SHORT).show()
                            }catch (e: FirebaseAuthWeakPasswordException){
                                Toast.makeText(context, AppText.password_error, Toast.LENGTH_SHORT).show()
                            }catch (e: FirebaseAuthInvalidCredentialsException){
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
        val currentUser = Firebase.auth.currentUser
        val email = currentUser?.email
        if (email != null){
            Firebase.auth.currentUser!!.delete()
                .addOnCompleteListener {
                    val db = Firebase.firestore
                    db.collection("user").document(email) // 관련 문서 삭제
                        .delete()
                        .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully deleted!") }
                        .addOnFailureListener { e -> Log.w(TAG, "Error deleting document", e) }
                    Toast.makeText(context, AppText.withdrawal, Toast.LENGTH_SHORT).show()
                    openAndPopUp(LOGIN_SCREEN, HOME_SCREEN)
                }
        }
    }

    // 로그아웃
    override fun logout(context: Context, openAndPopUp: (String, String) -> Unit) { // 메인화면으로 이동
        Firebase.auth.signOut()
        Toast.makeText(context, AppText.logout, Toast.LENGTH_SHORT).show()
        openAndPopUp(LOGIN_SCREEN, HOME_SCREEN) // 로그인 화면으로 이동
    }

    // 자동 로그인
    override fun autoLogin(openAndPopUp: (String, String) -> Unit) {
        val currentUser = Firebase.auth.currentUser
        if (currentUser!=null)
            openAndPopUp(HOME_SCREEN, LOGIN_SCREEN)
    }

    override fun editProfile( // 회원정보 변경
        context: Context,
        name: String,
        password: String,
        openAndPopUp: (String, String) -> Unit
    ) {
        val currentUser = Firebase.auth.currentUser
        if (currentUser != null){
            val newProfile = UserProfileChangeRequest.Builder().setDisplayName(name).build()
            currentUser.updateProfile(newProfile) // 닉네임 변경
            currentUser.updatePassword(password) // 비밀번호 변경
                .addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        Toast.makeText(context, AppText.complete_edit, Toast.LENGTH_SHORT)
                            .show()
                        openAndPopUp(LOGIN_SCREEN, EDIT_PROFILE_SCREEN)
                    }else { // 변경 실패
                        try {
                            throw task.exception!!
                        }catch (e: FirebaseAuthWeakPasswordException) {
                            Toast.makeText(context, AppText.password_error, Toast.LENGTH_SHORT).show()
                        }catch (e: FirebaseAuthException){
                            Toast.makeText(context, e.errorCode, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
        }
    }

    companion object{
        const val TAG = "AccountServiceImpl"
    }
}