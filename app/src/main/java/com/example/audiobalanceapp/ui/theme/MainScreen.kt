package com.example.audiobalanceapp.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

@Composable
fun MainScreen(){

    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
        ){

        Button(
            onClick = {
//        TODO the actions
            }) {
            Text(text = "Button 1")
        }

        Button(
            onClick = {
//        TODO the actions
            }) {
            Text(text = "Button 2")
        }

        Button(
            onClick = {
//        TODO the actions
            }) {
            Text(text = "Button 3")
        }

    }


}