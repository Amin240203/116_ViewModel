package com.example.praktikum4

import android.os.Bundle
import android.widget.RadioButton
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.praktikum4.Data.DataSource.jenis
import com.example.praktikum4.Data.DataSource.status
import com.example.praktikum4.Data.Dataform
import com.example.praktikum4.ui.theme.Praktikum4Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Praktikum4Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TampilLayout()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Praktikum4Theme {
        TampilLayout()
    }
}

@Composable
fun TampilLayout(modifier: Modifier = Modifier){
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
    )
    {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(20.dp)
        ) {
            TampilForm()
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TampilForm(cobaViewModel: CobaViewModel = viewModel()){
    var textNama by remember { mutableStateOf("")}
    var textTlp by remember { mutableStateOf("")}
    var txtemail by remember { mutableStateOf("")}
    var textalamat by remember { mutableStateOf("")}

    val context = LocalContext.current
    val dataForm: Dataform
    val uiState by cobaViewModel.uiState.collectAsState()
    dataForm = uiState
    Text(text = "Create Your Account")
    OutlinedTextField(
        value = textNama,
        singleLine = true,
        shape = MaterialTheme.shapes.large,
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = "Nama Lengkap")},
        onValueChange = {
            textNama = it
        }
    )
    OutlinedTextField(
        value = textTlp,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        shape = MaterialTheme.shapes.large,
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = "Telpon")},
        onValueChange = {
            textTlp = it
        }
    )
    OutlinedTextField(
        value = textalamat,
        singleLine = true,
        shape = MaterialTheme.shapes.large,
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = "Email")},
        onValueChange = {
            textalamat = it
        } )
    SelectJK(
        options = jenis.map{id -> context.resources.getString(id)},
        onSelectionChanged = {cobaViewModel.setJenisK(it)})
    SelectST(
        options = status.map{id -> context.resources.getString(id)},
        onSelectionChanged = {cobaViewModel.setStatus(it)})
    OutlinedTextField(
        value = textalamat,
        singleLine = true,
        shape = MaterialTheme.shapes.large,
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = "Alamat")},
        onValueChange = {
            textalamat = it
        } )
    Button(
        modifier = Modifier
            .fillMaxWidth(),
        onClick = {
            cobaViewModel.insertData(textNama, textTlp, txtemail, textalamat, dataForm.sex)
        },
        colors = ButtonDefaults.buttonColors(containerColor = Color.Black, contentColor = Color.White)
    ) {
        Text(
            text = stringResource(R.string.submit),
            fontSize = 16.sp
        )
    }
    Spacer(modifier = Modifier.height(80.dp))
    TextHasil(
        namanya = cobaViewModel.namaUsr,
        telponnya = cobaViewModel.noTlp,
        emailnya = cobaViewModel.emailUsr,
        alamatnya = cobaViewModel.alamat,
        jenisnya = cobaViewModel.jenisKL)
}

@Composable
fun TextHasil(namanya: String, telponnya: String, emailnya: String, alamatnya: String, jenisnya: String){
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = "Nama : " + namanya,
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 4.dp)
        )
        Text(
            text = "Telepon : " + telponnya,
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 5.dp)
        )
        Text(
            text = "Alamat : " + alamatnya,
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 4.dp)
        )
        Text(
            text = "Jenis Kelamin : " + jenisnya,
            modifier =  Modifier
                .padding(horizontal = 10.dp, vertical = 5.dp)
        )

    }
}

@Composable
fun SelectJK(
    options: List<String>,
    onSelectionChanged: (String) -> Unit = {}
)
{
    var selectedValue by rememberSaveable { mutableStateOf("")}

    Column{
        Text(text = "Jenis Kelamin", textAlign = TextAlign.Start)
    }
    Row(modifier = Modifier.padding(16.dp)) {
        options.forEach{ item ->
            Row (
                modifier = Modifier.selectable(
                    selected = selectedValue == item,
                    onClick = {
                        selectedValue = item
                        onSelectionChanged(item)
                    }
                ),
                verticalAlignment = Alignment.CenterVertically
            ){
                RadioButton(
                    selected = selectedValue == item,
                    onClick = {
                        selectedValue = item
                        onSelectionChanged(item)
                    }
                )
                Text(item)
            }
        }

    }

}
@Composable
fun SelectST(
    options: List<String>,
    onSelectionChanged: (String) -> Unit = {}
)
{
    var selectedValue by rememberSaveable { mutableStateOf("")}

    Column{
        Text(text = "Status", textAlign = TextAlign.Start)
    }
    Row(modifier = Modifier.padding(16.dp)) {
        options.forEach{ item ->
            Row (
                modifier = Modifier.selectable(
                    selected = selectedValue == item,
                    onClick = {
                        selectedValue = item
                        onSelectionChanged(item)
                    }
                ),
                verticalAlignment = Alignment.CenterVertically
            ){
                RadioButton(
                    selected = selectedValue == item,
                    onClick = {
                        selectedValue = item
                        onSelectionChanged(item)
                    }
                )
                Text(item)
            }
        }

    }

}
