package com.example.rockpaperscissors;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView textViewWinLoseMessage;
    ImageView imageViewComputer;
    Button playButton;
    TextView textViewPlayerMessages;
    ImageButton imageButtonRock;
    ImageButton imageButtonPaper;
    ImageButton imageButtonScissors;
    enum Choice {ROCK, PAPER, SCISSORS, NONE};
    Choice playerChoiceId = Choice.NONE;
    Choice computerChoiceId = Choice.NONE;
    Random random;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SetupControls();
        DisableImageButtons();
        CrossAllImageButtons();

    }

    public void SetupControls() {

        textViewWinLoseMessage = findViewById(R.id.textViewWinLoseMessage);
        imageViewComputer = findViewById(R.id.imageViewComputer);
        playButton = findViewById(R.id.playButton);
        textViewPlayerMessages = findViewById(R.id.textViewPlayerMessages);
        imageButtonRock = findViewById(R.id.imageButtonRock);
        imageButtonPaper = findViewById(R.id.imageButtonPaper);
        imageButtonScissors = findViewById(R.id.imageButtonScissors);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SetImageViewComputer(R.mipmap.ic_launcher);
                SetWinLoseMessage(R.string.Computer_Waiting);
                EnableImageButtons();
                ResetImageButtonImages(); // non cross , non tick - images
                SetPlayButtonText(R.string.Waiting_For_Player);
            }
        });
        imageButtonRock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playerChoiceId = Choice.ROCK;
                SetImageButtonRock(R.drawable.rock_tick);
                SetImageButtonPaper(R.drawable.paper_cross);
                SetImageButtonScissors(R.drawable.scissors_cross);
                ComputersChoice();
            }
         });
        imageButtonPaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playerChoiceId = Choice.PAPER;
                SetImageButtonRock(R.drawable.rock_cross);
                SetImageButtonPaper(R.drawable.paper_tick);
                SetImageButtonScissors(R.drawable.scissors_cross);
                ComputersChoice();
            }
        });
        imageButtonScissors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playerChoiceId = Choice.SCISSORS;
                SetImageButtonRock(R.drawable.rock_cross);
                SetImageButtonPaper(R.drawable.paper_cross);
                SetImageButtonScissors(R.drawable.scissors_tick);
                ComputersChoice();
            }
        });
    }

    public void DisableImageButtons() {
        imageButtonRock.setEnabled(false);
        imageButtonPaper.setEnabled(false);
        imageButtonScissors.setEnabled(false);
    }

    public void EnableImageButtons() {
        imageButtonRock.setEnabled(true);
        imageButtonPaper.setEnabled(true);
        imageButtonScissors.setEnabled(true);
    }

    public void ResetImageButtonImages() {
        imageButtonRock.setImageResource(R.drawable.rock);
        imageButtonPaper.setImageResource(R.drawable.paper);
        imageButtonScissors.setImageResource(R.drawable.scissors);
    }

    public void CrossAllImageButtons() {
        imageButtonRock.setImageResource(R.drawable.rock_cross);
        imageButtonPaper.setImageResource(R.drawable.paper_cross);
        imageButtonScissors.setImageResource(R.drawable.scissors_cross);
    }

    public void SetPlayButtonText(int textId) {
        playButton.setText(textId);
    }

    public void SetImageButtonRock(int imageId) {
        imageButtonRock.setImageResource(imageId);
    }

    public void SetImageButtonPaper(int imageId) {
        imageButtonPaper.setImageResource(imageId);
    }

    public void SetImageButtonScissors(int imageId) {
        imageButtonScissors.setImageResource(imageId);
    }

    public int GenerateNumber() {
        random = new Random();
        return random.nextInt(3); // 0, 1, 2
    }

    public void ComputersChoice() {
        int choice = 0;
        choice = GenerateNumber();
        if (choice == 0) {
            computerChoiceId = Choice.ROCK;
            SetImageForComputer(R.drawable.rock_tick);
        } else if (choice == 1) {
            computerChoiceId = Choice.PAPER;
            SetImageForComputer(R.drawable.paper_tick);
        } else if (choice == 2) {
            computerChoiceId = Choice.SCISSORS;
            SetImageForComputer(R.drawable.scissors_tick);
        }
        WhoWon();
        DisableImageButtons();
        SetPlayButtonText(R.string.Click_to_Play);
    }

    public void SetImageForComputer(int imageId) {
        imageViewComputer.setImageResource(imageId);
    }
    public void WhoWon() {
        if (playerChoiceId == computerChoiceId) {
            // Draw
            SetWinLoseMessage(R.string.Draw);
        } else if (playerChoiceId == Choice.ROCK) {
            if (computerChoiceId == Choice.PAPER) {
                // Computer Win
                SetWinLoseMessage(R.string.Computer_Wins);
            } else if (computerChoiceId == Choice.SCISSORS) {
                // Player Wins
                SetWinLoseMessage(R.string.Computer_Loses);
            }
        } else if (playerChoiceId == Choice.PAPER) {
            if (computerChoiceId == Choice.ROCK) {
                // Player Win
                SetWinLoseMessage(R.string.Computer_Loses);
            } else if (computerChoiceId == Choice.SCISSORS) {
                // Computer Wins
                SetWinLoseMessage(R.string.Computer_Wins);
            }
        } else if (playerChoiceId == Choice.SCISSORS) {
            if (computerChoiceId == Choice.ROCK) {
                // Computer Win
                SetWinLoseMessage(R.string.Computer_Wins);
            } else if (computerChoiceId == Choice.PAPER) {
                // Player Wins
                SetWinLoseMessage(R.string.Computer_Loses);
            }
        }
    }

    public void SetWinLoseMessage(int imageId) {
        textViewWinLoseMessage.setText( imageId );
    } // public void SetWinLoseMessage(int imageId)
    public void SetImageViewComputer(int imageId) {
        imageViewComputer.setImageResource( imageId );
    }
}