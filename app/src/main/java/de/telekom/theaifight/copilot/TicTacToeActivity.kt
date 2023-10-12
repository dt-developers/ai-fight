/**
 * An Android application that implements the TicTacToe game using jetpack compose, view models and a working AI oppponent.
 */

package de.telekom.theaifight.copilot

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.zIndex
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.*

class TicTacToeViewModel : ViewModel() {
//    private val board = listOf(
//        "","","",
//        "","","",
//        "","",""
//    )

    var player by mutableStateOf(Player.X)
        private set

    var winner by mutableStateOf<Player?>(null)
        private set

//    var board by mutableStateOf(board)
//        private set

    var isThinking by mutableStateOf(false)
        private set

    var isGameOver by mutableStateOf(false)
        private set

    init {
        reset()
    }

    fun reset() {
//        game.reset()
        player = Player.X
        winner = null
//        board = game.board
        isThinking = false
        isGameOver = false
    }

    fun move(position: Position) {
//        if (!isThinking && !isGameOver && game.move(player, position)) {
//            board = game.board
//            winner = game.winner
//            player = player.opponent
//        }
    }

    fun startThinking() {
        if (!isThinking && !isGameOver && winner == null) {
            isThinking = true
            CoroutineScope(Dispatchers.Main).launch {
                delay(1000)
//                move(game.getBestMove(player))
                isThinking = false
            }
        }
    }
}

// add player data class
data class Player(val name: String, val icon: ImageVector) {
    companion object {
        val X = Player("Player X", Icons.Default.Close)
        val O = Player("Player O", Icons.Default.Refresh)
    }

    val opponent: Player
        get() = when (this) {
            X -> O
            O -> X
            else -> this
        }
}

@Composable
fun TicTacToeScreen(viewModel: TicTacToeViewModel = viewModel()) {
//    val board = viewModel.board
    val winner = viewModel.winner
    val player = viewModel.player
    val isThinking = viewModel.isThinking
    val isGameOver = viewModel.isGameOver

    val density = LocalDensity.current
    val strokeWidth = with(density) { 2.dp.toPx() }
    val iconSize = with(density) { 32.dp.toPx() }
    val iconPadding = with(density) { 8.dp.toPx() }

    val buttonModifier = Modifier
        .padding(8.dp)
        .height(36.dp)

    val playerIcon = player.icon

    val playerText = when (player) {
        Player.X -> "Player X"
        Player.O -> "Player O"
        else -> ""
    }

    val winnerText = when (winner) {
        Player.X -> "Player X wins"
        Player.O -> "Player O wins"
        null -> "Draw"
        else -> ""
    }

    val dialogText = if (winner != null) {
        winnerText
    } else {
        "Thinking..."
    }

    val dialogIcon = playerIcon

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
//            board.forEachIndexed { y, row ->
//                Column(
//                    modifier = Modifier
//                        .weight(1f)
//                        .padding(2.dp)
//                ) {
//                    row.forEachIndexed { x, player ->
//                        Cell(
//                            player = player,
//                            strokeWidth = strokeWidth,
//                            onClick = { viewModel.move(Position(x, y)) },
//                            modifier = Modifier
//                                .weight(1f)
//                                .padding(2.dp)
//                        )
//                    }
//                }
//            }
        }
        Row(
            modifier = Modifier.weight(0.5f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = playerText,
                modifier = Modifier.padding(8.dp),
            )
            Icon(
                imageVector = playerIcon,
                contentDescription = null,
                modifier = Modifier.fillMaxSize(iconSize),
            )
            if (isThinking) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxSize(iconSize)
                )
            }
        }
        Row(
            modifier = Modifier.weight(0.5f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {
                    viewModel.reset()
                },
                modifier = buttonModifier,
                enabled = isGameOver
            ) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(iconSize)
                )
                Spacer(modifier = Modifier.fillMaxSize(iconPadding))
                Text(text = "Reset")
            }
            Button(
                onClick = {
                    viewModel.startThinking()
                },
                modifier = buttonModifier,
                enabled = !isThinking && !isGameOver && winner == null
            ) {
                Icon(
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(iconSize)
                )
                Spacer(modifier = Modifier.fillMaxSize(iconPadding))
                Text(text = "Play")
            }
        }
    }
    if (winner != null || isThinking) {
        Dialog(onDismissRequest = { }) {
            Surface(
                modifier = Modifier
                    .padding(8.dp)
                    .animateContentSize(
                        animationSpec = tween(durationMillis = 300)
                    )
                    .zIndex(10f),
                shape = MaterialTheme.shapes.medium,
            ) {
                Column(
                    modifier = Modifier
                        .padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = dialogIcon,
                        contentDescription = null,
                        modifier = Modifier.size(64.dp),
                    )
                    Spacer(modifier = Modifier.size(8.dp))
                    Text(
                        text = dialogText,
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
    }
}

data class Position(val x: Int, val y: Int)

// cell composable
@Composable
fun Cell(
    player: Player?,
    strokeWidth: Float,
    modifier: Modifier,
    onClick: () -> Unit
) {
    val density = LocalDensity.current
    val iconSize = with(density) { 32.dp.toPx() }
    val iconPadding = with(density) { 8.dp.toPx() }

    val icon = player?.icon

    Surface(
        modifier = modifier
            .clickable(onClick = onClick),
        shape = CircleShape
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .padding(strokeWidth.dp)
        ) {
            drawCircle(
                color = Color.White,
                radius = size.minDimension / 2
            )
        }
    }
}
