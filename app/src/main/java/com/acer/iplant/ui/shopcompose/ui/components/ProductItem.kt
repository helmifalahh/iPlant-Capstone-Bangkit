package com.acer.iplant.ui.shopcompose.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import com.acer.iplant.R
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.acer.iplant.ui.shopcompose.ui.theme.IPlantTheme
import com.acer.iplant.ui.shopcompose.ui.theme.Shapes

@Composable
fun ProductItem(
    image: Int,
    title: String,
    price: Int,
    modifier: Modifier = Modifier
){
    Card(
        modifier = modifier,
        elevation = 5.dp,
        shape = RoundedCornerShape(corner = CornerSize(10.dp))
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp)
        ) {
            Image(
                painter = painterResource(image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(170.dp)
                    .padding(bottom = 5.dp)
                    .clip(Shapes.medium)
            )
            Text(
                modifier = Modifier.padding(bottom = 5.dp),
                text = title,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.subtitle1.copy(
                    fontWeight = FontWeight.ExtraBold
                )
            )
            Text(
                text = stringResource(R.string.required_price, price),
                style = MaterialTheme.typography.subtitle2,
                color = Color.Gray
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ProductItemPreview() {
    IPlantTheme() {
        ProductItem(R.drawable.ic_launcher_background, "Sepatu dengan \nKualitas Mahal", 400000)
    }
}