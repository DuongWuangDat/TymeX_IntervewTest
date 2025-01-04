package com.example.tymex_currencyconverter_test.ui.screens.MainScreens

import android.annotation.SuppressLint
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tymex_currencyconverter_test.R
import kotlin.math.sin

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
@Preview
fun MainScreen (

){
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val mainViewModel : MainViewModel = hiltViewModel()
    var rotateFromAngle = remember {
        mutableStateOf(180f)
    }
    var rotateToAngle = remember {
        mutableStateOf(180f)
    }
    val rotationFrom by animateFloatAsState(targetValue = rotateFromAngle.value)
    val rotationTo by animateFloatAsState(targetValue = rotateToAngle.value)

    var snackbarHostState = mainViewModel.snackbarHostState

    //Handle rotate animation at drop down menu (from)
    LaunchedEffect(key1 = mainViewModel.isFromExpanded.value) {
        rotateFromAngle.value= if(rotateFromAngle.value == 0f) 180f else 0f
    }

    //Handle rotate animation at drop down menu (to)
    LaunchedEffect(key1 = mainViewModel.isToExpanded.value) {
        rotateToAngle.value= if(rotateToAngle.value == 0f) 180f else 0f
    }

    Scaffold(modifier = Modifier
        .fillMaxSize(),
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) {
        Column(modifier = Modifier
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Color.Cyan, Color.White),
                    start = Offset(0f, 0f),
                    end = Offset(1000f, 1000f)
                )
            )
            .padding(40.dp)
            .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,


        ) {
            Spacer(modifier = Modifier.fillMaxHeight(0.2f))

            //Result of currency
            if(mainViewModel.isFetchDataLoading.value){
                CircularProgressIndicator(modifier = Modifier.width(60.dp), color = Color.DarkGray)
            }else{
                Text(text = mainViewModel.conversionResult.value, style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 70.sp, color = Color.Black))
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = mainViewModel.rateResult.value, style = TextStyle(color = Color.Red,fontSize = 30.sp))

            }

            Spacer(modifier = Modifier.weight(1f))
            //Drop menu for currency want to convert from
            Text(text = "From", style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.Black), modifier = Modifier
                .align(Alignment.Start)
                .padding(bottom = 6.dp))

            Box{

                Row( modifier = Modifier
                    .fillMaxWidth()
                    .border(width = 1.dp, Color.Black, RoundedCornerShape(12.dp))
                    .height(50.dp)
                    .padding(10.dp)
                    .clickable {
                        //Function to open drop down menu
                        mainViewModel.fromExpanded()
                    },
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically) {
                    Text(mainViewModel.selectedFromString.value, style = TextStyle(color = Color.Black))
                    Image(painter = painterResource(id = R.drawable.expandedmenu),
                        contentDescription = "",
                        modifier = Modifier.rotate(rotationFrom)
                    )
                }

                DropdownMenu(
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .heightIn(max = (screenHeight * 0.3f)),
                    expanded = mainViewModel.isFromExpanded.value,

                    onDismissRequest = {
                        mainViewModel.isFromExpanded.value = false
                    },

                    ) {
                    
                    //Handle choosing item action
                    if(mainViewModel.isLoading.value){
                        CircularProgressIndicator(modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .width(30.dp))
                    }else{
                        mainViewModel.listOfCode.forEachIndexed { _, s ->
                            DropdownMenuItem(text = {Text(text = s)},
                                onClick = {
                                    mainViewModel.isFromExpanded.value = false
                                    mainViewModel.selectBaseCode(s)
                                })
                        }
                    }
                }

            }
            Spacer(modifier = Modifier.height(20.dp))


            //Drop menu for currency want to convert to
            Text(text = "To", style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.Black), modifier = Modifier
                .align(Alignment.Start)
                .padding(bottom = 6.dp))
            Box{

                Row( modifier = Modifier
                    .fillMaxWidth()
                    .border(width = 1.dp, Color.Black, RoundedCornerShape(12.dp))
                    .height(50.dp)
                    .padding(10.dp)
                    .clickable {
                        //Function to open drop down menu
                        mainViewModel.toExpanded()
                    },
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically) {
                    Text(mainViewModel.selectedToString.value, style = TextStyle(color = Color.Black) )
                    Image(painter = painterResource(id = R.drawable.expandedmenu),
                        contentDescription = "",
                        modifier = Modifier.rotate(rotationTo))
                }

                DropdownMenu(
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .heightIn(max = (screenHeight * 0.3f)),
                    expanded = mainViewModel.isToExpanded.value,
                    onDismissRequest = {mainViewModel.isToExpanded.value = false
                    },

                    ) {
                    //Handle choosing item action
                    if(mainViewModel.isLoading.value){
                        CircularProgressIndicator(modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .width(30.dp))
                    }else{
                        mainViewModel.listOfCode.forEachIndexed { _, s ->
                            DropdownMenuItem(text = {Text(text = s)},
                                onClick = {
                                    mainViewModel.isToExpanded.value = false
                                    mainViewModel.selectTargetCode(s)
                                })
                        }
                    }
                }

            }
            Spacer(modifier = Modifier.height(20.dp))

            //Amount TextField
            Text(text = "Amount", style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.Black), modifier = Modifier
                .align(Alignment.Start)
                .padding(bottom = 6.dp))
            TextField(value = mainViewModel.amountValue.value,
                onValueChange = {it-> mainViewModel.amountValue.value = it},
                label = {Text(text = "Input your amount value")},
                colors = TextFieldDefaults.colors(
                   unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    cursorColor = Color.Black,
                    unfocusedLabelColor = Color.Gray,
                    focusedLabelColor = Color.Gray,
                    focusedTextColor = Color.Black,
                    unfocusedPrefixColor = Color.Black
                ),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                ),
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, Color.Black, RoundedCornerShape(12.dp))

            )

            //Button to fetch data
            Button(onClick = { mainViewModel.getData() }, modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(android.graphics.Color.parseColor("#9E62D0")),
                    contentColor = Color.White

                )
                ) {
                Text(text = "Get converter result", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }

        }
    }

}