package com.acer.iplant.ui.shopcompose.ui.screen.home

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.acer.iplant.di.Injection
import com.acer.iplant.ui.shopcompose.model.Order
import com.acer.iplant.ui.shopcompose.ui.ViewModelFactory
import com.acer.iplant.ui.shopcompose.ui.common.UiState
import com.acer.iplant.ui.shopcompose.ui.components.*
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeScreen (
    modifier: Modifier = Modifier,
    carouselState: PagerState,
    configuration: Configuration,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateToDetail: (Long) -> Unit,
){
    Scaffold(
        topBar = { ActionBar() },
        modifier = Modifier,
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
        ) {
            viewModel.resultCarousel.collectAsState(initial = UiState.Loading).value.let { resultCarousel ->
                when (resultCarousel) {
                    is UiState.Loading -> {
                        viewModel.getAllCarousel()
                    }
                    is UiState.Success -> {
                        Box {
                            when (configuration.orientation) {
                                Configuration.ORIENTATION_LANDSCAPE -> {
                                    Carousel(resultCarousel.data, carouselState, width = 550)
                                }
                                else -> {
                                    Carousel(resultCarousel.data, carouselState)
                                }
                            }
                            Box(
                                modifier = Modifier
                                    .align(Alignment.BottomCenter)
                                    .padding(bottom = 15.dp)
                            ) {
                                DotsIndicator(
                                    totalDots = resultCarousel.data.size,
                                    selectedIndex = carouselState.currentPage,
                                )
                            }
                        }
                    }
                    is UiState.Error -> {}
                }
            }

            TitleSection(
                title = "Our Favorites",
                desc = "You've never met comfort like this!")

            viewModel.resultCare.collectAsState(initial = UiState.Loading).value.let { resultCare ->
                when (resultCare) {
                    is UiState.Loading -> {
                        viewModel.getAllCare()
                    }
                    is UiState.Success -> {
                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            contentPadding = PaddingValues(horizontal = 16.dp),
                            modifier = modifier
                        ){
                            items(resultCare.data){data ->
                                FavoriteItem(
                                    image = data.image,
                                    title = data.title,
                                    price = data.price,
                                    modifier = Modifier.clickable {
                                        navigateToDetail(data.id)
                                    }
                                )
                            }
                        }
                    }
                    is UiState.Error -> {}
                }
            }

            TitleSection(
                title = "New Arrival",
                desc = "Looking for an everyday shoes? Your hunt for the comfort pair is over")

            viewModel.result.collectAsState(initial = UiState.Loading).value.let { result ->
                when (result) {
                    is UiState.Loading -> {
                        viewModel.getAllShoes()
                    }
                    is UiState.Success -> {
                        HomeContent(
                            orderProduct = result.data,
                            modifier = modifier,
                            navigateToDetail = navigateToDetail
                        )
                    }
                    is UiState.Error -> {}
                }
            }
        }
    }
}

@Composable
fun HomeContent(
    orderProduct: List<Order>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Long) -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(160.dp),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.height(1000.dp)
    ) {
        items(orderProduct) { data ->
            ProductItem(
                image = data.product.image,
                title = data.product.title,
                price = data.product.price,
                modifier = Modifier.clickable {
                    navigateToDetail(data.product.id)
                }
            )
        }
    }
}