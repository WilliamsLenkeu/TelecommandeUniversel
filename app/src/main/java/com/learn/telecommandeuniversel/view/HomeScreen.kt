package com.learn.telecommandeuniversel.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.learn.telecommandeuniversel.ui.theme.background

@Composable
fun HomeScreen(){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(background)
            .padding(10.dp)
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clip(RoundedCornerShape(15.dp))
                .border(border = BorderStroke(8.dp, Color.White))
                .background(Color.Magenta)
        ){
            Column (
                modifier = Modifier
                    .background(Color.Red)
                    .fillMaxWidth()
            ){
                Text(text = ".")
            }
            Column (
                modifier = Modifier
                    .background(Color.Yellow)
                    .fillMaxWidth()
            ){
                Text(text = ".")
            }
        }
    }
}
