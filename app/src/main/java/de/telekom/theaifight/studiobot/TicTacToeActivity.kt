package de.telekom.theaifight.studiobot

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign

class TicTacToeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel = remember { TicTacToeViewModel() }
            TicTacToeScreen(viewModel)
        }
    }
}

@Composable
fun TicTacToeScreen(viewModel: TicTacToeViewModel) {
    val board = viewModel.board
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Tic Tac Toe", textAlign = TextAlign.Center)
        for (i in 0..2) {
            Row(horizontalArrangement = Arrangement.Center) {
                for (j in 0..2) {
                    Button(onClick = { viewModel.onCellClicked(i, j) }) {
                        Text(text = board[i][j])
                    }
                }
            }
        }
        Text(text = viewModel.winner)
    }
}

class TicTacToeViewModel {
    val board = Array(3) { Array(3) { "" } }
    var winner = ""

    fun onCellClicked(i: Int, j: Int) {
        if (board[i][j] == "") {
            board[i][j] = "X"
            checkWinner()
            if (winner == "") {
                val aiMove = getAiMove()
                board[aiMove.first][aiMove.second] = "O"
                checkWinner()
            }
        }
    }

    private fun checkWinner(): String {
        // check for a tic tac to win situation in board
        for (i in 0..2) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != "") {
                winner = board[i][0]
                return winner
            }
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] != "") {
                winner = board[0][i]
                return winner
            }
            if (board[i][0] == board[1][1] && board[1][1] == board[2][2] && board[i][0] != "") {
                winner = board[i][0]
                return winner
            }
            if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != "") {
                winner = board[0][2]
                return winner
            }
        }

        return ""
    }

    private fun getAiMove(): Pair<Int, Int> {
        // Check if there is a winning move
        for (i in 0..2) {
            for (j in 0..2) {
                if (board[i][j] == "") {
                    board[i][j] = "O"
                    if (checkWinner() == "O") {
                        return Pair(i, j)
                    }
                    board[i][j] = ""
                }
            }
        }

        // Check if there is a blocking move
        for (i in 0..2) {
            for (j in 0..2) {
                if (board[i][j] == "") {
                    board[i][j] = "X"
                    if (checkWinner() == "X") {
                        return Pair(i, j)
                    }
                    board[i][j] = ""
                }
            }
        }

        // Choose a random move
        while (true) {
            val i = (0..2).random()
            val j = (0..2).random()
            if (board[i][j] == "") {
                return Pair(i, j)
            }
        }
    }
}
