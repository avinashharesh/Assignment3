package com.example.assignment3.pages

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.assignment3.ui.theme.TopAppBarBackground
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import androidx.compose.material3.*


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsScreen(navController: NavController) {
    var newsList by remember { mutableStateOf<List<NewsArticle>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    Scaffold (
        topBar = {
            CenterAlignedTopAppBar(
                title = { androidx.compose.material3.Text(text = "News") },
                modifier = Modifier.padding(top = 20.dp),
                colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = TopAppBarBackground)
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding)
            ) {
                LaunchedEffect(Unit) {
                    isLoading = true
                    try {
                        val fetchedNews = withContext(Dispatchers.IO) {
                            RetrofitInstance.newsService.getFinancialNews().articles
                        }
                        newsList = fetchedNews
                    } catch (e: Exception) {
                        // Handle errors
                    } finally {
                        isLoading = false
                    }
                }

                if (isLoading) {
                    CircularProgressIndicator() // Show loading indicator
                } else {
                    NewsList(newsList = newsList)
                }
            }
        }

    )
}

@Composable
fun NewsList(newsList: List<NewsArticle>) {
    LazyColumn {
        items(newsList) { article ->
            NewsItem(article = article)
        }
    }
}

@Composable
fun NewsItem(article: NewsArticle, onItemClick: (String) -> Unit = {}) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable { onItemClick(article.url) }
    ) {
        Text(
            text = article.title,
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.padding(16.dp)
        )
    }



}

data class NewsArticle(
    val title: String,
    val description: String,
    val url: String
)

data class NewsResponse(
    val articles: List<NewsArticle>
)

interface NewsService {
    @GET("v2/top-headlines?category=business&language=en&apiKey=055bc8323cb74327a25189cd429187c1")
    suspend fun getFinancialNews(): NewsResponse
}

object RetrofitInstance {
    private const val BASE_URL = "https://newsapi.org/"

    val newsService: NewsService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsService::class.java)
    }
}
