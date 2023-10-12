package de.telekom.theaifight

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activities = listOf(
            de.telekom.theaifight.chatgpt.TicTacToeActivity::class.java,
            de.telekom.theaifight.chatgpt4.TicTacToeActivity::class.java,
            de.telekom.theaifight.chatgpt4.FactorialActivity::class.java,
            de.telekom.theaifight.chatgpt4.FibonacciActivity::class.java,
            de.telekom.theaifight.studiobot.TicTacToeActivity::class.java,
        )


        setContent {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text(stringResource(id = R.string.app_name)) }
                    )
                },
                modifier = Modifier.fillMaxSize(),
            ) { padding ->
                Surface(modifier = Modifier.padding(padding)) {
                    LazyColumn {
                        item {
                            Text(text = "Launch")
                        }
                        items(activities) {
                            Button(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 8.dp),
                                onClick = { it.launch() }) {
                                val aiName =
                                    it.`package`?.name?.split(".")?.last()
                                        ?: "Unknown"
                                Text(
                                    text = "${aiName.capitalize()} ${it.simpleName}",
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    private fun Class<out ComponentActivity>.launch() {
        startActivity(Intent(this@MainActivity, this))
    }
}
