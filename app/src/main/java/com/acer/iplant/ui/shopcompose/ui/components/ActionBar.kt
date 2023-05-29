package com.acer.iplant.ui.shopcompose.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.acer.iplant.R

@Composable
fun ActionBar() {
    TopAppBar(
        title = {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .height(80.dp)
                    .fillMaxWidth()
                    .background(color = Color.White)
            ) {
                Image(
                    painter = painterResource(R.drawable.logo_iplant),
                    contentDescription = null,
                    modifier = Modifier.height(20.dp)
                )
            }
        },

        backgroundColor = Color.White
    )
}