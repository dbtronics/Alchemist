package hk.ust.cse.comp107x.cryptospinner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    String player1, player2;
    int correctQuestionPlayer1, correctQuestionPlayer2;
    int correctSectionPlayer1, correctSectionPlayer2;
    SpinnerWheel spinnerWheel;
    int section, random;
    Random randomQuestion = new Random(4);
    String question[][] = new String[5][4];
    HashMap<String, String> mcqAnswer = new HashMap<String, String>();
    HashMap<String, HashMap<String, String>> mcq = new
            HashMap<String, HashMap<String, String>>();
    boolean firstTimeSpin = true;
    boolean player1Question, player2Question;

    Button spinButton;
    Button response1A, response1B, response1C, response1D;
    Button response2A, response2B, response2C, response2D;
    EditText editPlayer1, editPlayer2;
    EditText editDepositPlayer1, editDepositPlayer2;
    TextView questionPlayer1, questionPlayer2;
    TextView sectionPlayer1, sectionPlayer2;
    ImageView cryptoSpinner;

    public static final int SCIENCE = 0;
    public static final int BUSINESS = 1;
    public static final int GEOGRAPHY = 2;
    public static final int ENTERTAINMENT = 3;
    public static final int SPORTS = 4;


    public void MainActivity(String player1, String player2){
        this.player1 = player1;
        this.player2 = player2;
        correctQuestionPlayer1=0;
        correctQuestionPlayer2=0;
        correctSectionPlayer1=0;
        correctSectionPlayer2=0;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editPlayer1 = (EditText) findViewById(R.id.editPlayer1);
        editPlayer2 = (EditText) findViewById(R.id.editPlayer2);

        editDepositPlayer1 = (EditText) findViewById(R.id.editDepositPlayer1);
        editDepositPlayer2 = (EditText) findViewById(R.id.editDepositPlayer2);

        questionPlayer1 = (TextView) findViewById(R.id.questionPlayer1);
        questionPlayer2 = (TextView) findViewById(R.id.questionPlayer2);

        sectionPlayer1 = (TextView) findViewById(R.id.sectionPlayer1);
        sectionPlayer2 = (TextView) findViewById(R.id.sectionPlayer2);

        cryptoSpinner = (ImageView) findViewById(R.id.imageView);

        spinButton = (Button) findViewById(R.id.buttonSpin);
        spinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFirstTimeSpin()){
                    MainActivity(editPlayer1.getText().toString(), editPlayer2.getText().toString());
                    spinnerWheel = new SpinnerWheel(View v);
                    spinnerWheel.spinWheel();
                    section = spinnerWheel.getSection();
                    setSection(section);
                    question(section);
                } else{
                    section = spinnerWheel.getSection();
                    setSection(section);
                    Truffle.deposit (Integer.parseInt(editDepositPlayer1.getText().toString()),
                            Integer.parseInt(editDepositPlayer2.getText().toString())); //LINK TO SOLIDITY
                    question(section);
                }
            }
        });
    }

    public void setRandomQuestion(int random){
        this.random = random;
    }

    public int getRandomQuestion(){
        return random;
    }

    public void setSection(int section){
        this.section = section;
    }

    public int getSection(){
        return section;
    }


    public void question(int section){
        while(correctQuestionPlayer1 == 3 || correctQuestionPlayer2 == 3){
            random = randomQuestion.nextInt(4);
            setRandomQuestion(random);
            switch(section) {
                case SCIENCE:
                    sectionPlayer1.setText("Science");
                    sectionPlayer2.setText("Science");
                    break;
                case BUSINESS:
                    sectionPlayer1.setText("Business");
                    sectionPlayer2.setText("Business");
                    break;
                case GEOGRAPHY:
                    sectionPlayer1.setText("GEOGRAPHY");
                    sectionPlayer2.setText("GEOGRAPHY");
                    break;
                case ENTERTAINMENT:
                    sectionPlayer1.setText("ENTERTAINMENT");
                    sectionPlayer2.setText("ENTERTAINMENT");
                    break;
                case SPORTS:
                    sectionPlayer1.setText("SPORTS");
                    sectionPlayer2.setText("SPORTS");
                default:
                    break;
            }

            questionPlayer1.setText(question[section][random]);
            questionPlayer2.setText(question[section][random);

            response1A.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) { //////////

                        player1Question = "A" == mcqAnswer.get(Integer.toString(getSection()*10 + random));


                }
            });

            response1B.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) { //////////

                    player1Question = "B" == mcqAnswer.get(Integer.toString(getSection()*10 + random));


                }
            });

            response1C.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) { //////////

                    player1Question = "C" == mcqAnswer.get(Integer.toString(getSection()*10 + random));


                }
            });

            response1D.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) { //////////

                    player1Question = "D" == mcqAnswer.get(Integer.toString(getSection()*10 + random));


                }
            });

            response2A.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) { //////////

                    player2Question = "A" == mcqAnswer.get(Integer.toString(getSection()*10 + random));


                }
            });

            response2B.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) { //////////

                    player2Question = "B" == mcqAnswer.get(Integer.toString(getSection()*10 + random));


                }
            });

            response2C.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) { //////////

                    player2Question = "C" == mcqAnswer.get(Integer.toString(getSection()*10 + random));


                }
            });

            response2D.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) { //////////

                    player2Question = "D" == mcqAnswer.get(Integer.toString(getSection()*10 + random));


                }
            });

            if (player1Question && !player2Question){
                correctQuestionPlayer1 += 1;
            }

            else if (!player1Question && player2Question) {
                correctQuestionPlayer2 += 1;
            } else {

            }

        }
        if (correctQuestionPlayer1 == 3) {
            correctSectionPlayer1 += 1;

            if (correctSectionPlayer1 == 3) {
                System.out.println("Player1 wins this game!!");
                endGame(player1, Integer.parseInt(editDepositPlayer1.getText().toString()));
            } else {
                System.out.println("Player1 wins this round");
            }

        } else {
            if (correctSectionPlayer2 == 3) {
                System.out.println("Player2 wins this game!!");
                endGame(player2, Integer.parseInt(editDepositPlayer2.getText().toString()));

            } else {
                System.out.println("Player2 wins this round");
                correctQuestionPlayer1 =0;
                correctQuestionPlayer2 =0;
            }
        }
    }

    public void endGame(String winnerAddress, int loserDeposit){
        Truffle.endOfRound(winnerAddress, loserDeposit); //LINK TO SOLIDITY
        System.out.println("Thank you for playing!");
    }

    public boolean isFirstTimeSpin(){
        return firstTimeSpin;
    }
}
