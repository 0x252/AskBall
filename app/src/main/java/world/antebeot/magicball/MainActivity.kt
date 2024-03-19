package world.antebeot.magicball

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import world.antebeot.magicball.ui.theme.MagicBallTheme

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.yandex.mobile.ads.banner.BannerAdSize
import com.yandex.mobile.ads.banner.BannerAdView
import com.yandex.mobile.ads.common.MobileAds

//import com.yandex.android.MobileAds;

object Ball {
    val answers = listOf(
        "Может быть", "Да", "Нет", "Попробуйте ещё",
        "Определенно", "Абсолютно нет","Не знаю",
        "Возможно", "Конечно", "Абсолютно", "Сейчас не подскажу",
        "Это точно"
    )
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MobileAds.initialize(this) {
        } // ads yandex
        setContent {
                MagicBallTheme {
                    // A surface container using the 'background' color from the theme
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        val bannerAd = BannerAdView(this)
                        BannerAdSize.inlineSize(this, resources.displayMetrics.widthPixels, resources.displayMetrics.widthPixels)
                        BallAndText()
                    }
                }
           }
    }
}

@Preview
@Composable
fun BallAndText(modifier: Modifier = Modifier) {
    var answer by remember { mutableStateOf("Задайте вопрос") }

    Column (
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.size(200.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ball),
                contentDescription = "Magic ball"
            )
            Text(text = answer, color= Color.Green, modifier = Modifier
                .shadow(
                    elevation = 5.dp, // Размер тени
                    shape = MaterialTheme.shapes.medium, // Форма тени, можно выбрать другую форму
                ).padding(8.dp))
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            answer = Ball.answers.random()
        }) {
            Text("Получить ответ")
        }
    }
}

