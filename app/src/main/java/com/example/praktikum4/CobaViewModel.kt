package com.example.praktikum4

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.praktikum4.Data.Dataform
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CobaViewModel : ViewModel() {
    var namaUsr: String by mutableStateOf("")
        private set
    var noTlp: String by mutableStateOf("")
        private  set
    var emailUsr: String by mutableStateOf("")
        private set
    var alamat: String by mutableStateOf("")
        private  set
    var jenisKL: String by mutableStateOf("")
        private set
    var statusM: String by mutableStateOf("")
        private set
    private val _uiState = MutableStateFlow(Dataform())
    val uiState: StateFlow<Dataform> = _uiState.asStateFlow()

    fun insertData(nm: String, tlp: String , almt: String, eml:String, jk: String){
        namaUsr = nm;
        noTlp = tlp;
        emailUsr = eml;
        alamat = almt;
        jenisKL = jk;

    }

    fun setJenisK(pilihJK: String){
        _uiState.update { currentState -> currentState.copy(sex = pilihJK) }
    }
    fun setStatus(pilihST: String){
        _uiState.update { currentState -> currentState.copy(status = pilihST)}
    }
}