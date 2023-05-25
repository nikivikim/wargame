package com.example.w23comp1008s1w5memorygame;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.Deque;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class WarGameController implements Initializable {

    @FXML
    private Label p1CardCountLabel;
    @FXML
    private Label name1;
    @FXML
    private Label name2;
    @FXML
    private TextField nameText1;
    @FXML
    private TextField nameText2;


    public void inputName(ActionEvent actionEvent) {
        String text = nameText1.getText();
        name1.setText(text);

        String text1 = nameText2.getText();
        name2.setText(text1);
        nameText1.setVisible(false);
        nameText2.setVisible(false);
    }

    @FXML
    private ImageView p1ImageView;

    @FXML
    private Label p2CardCountLabel;

    @FXML
    private Label winnerLabel;

    @FXML
    private ImageView p2ImageView;

    @FXML
    private Button playButton;
    @FXML
    private Label pullCardCountLabel;

    private Deque<Card> p1Hand; //Deque объект для реализации интерфейса deque

    private Deque<Card> p2Hand; //Deque объект для реализации интерфейса deque

    private Deque<Card> cardPile; //Deque объект для реализации интерфейса deque

    @FXML
    private void playHand() {
        playButton.setText("Выложить");
        p1ImageView.setVisible(true);
        p2ImageView.setVisible(true);

        //Есть ли карты у первого игрока
        if (p1Hand.size()==0)
            declareWinner(name2.getText());
            //Есть ли карты у второго игрока
        else if (p2Hand.size()==0)
            declareWinner(name1.getText());

        else {
// Первый игрок выкладывает верхнюю карту на поле
            Card p1Card = p1Hand.pollFirst();
            cardPile.addLast(p1Card);
            p1ImageView.setImage(p1Card.getImage());

            // Второй игрок выкладывает верхнюю карту на поле
            Card p2Card = p2Hand.pollFirst();
            cardPile.addLast(p2Card);
            p2ImageView.setImage(p2Card.getImage());

            if (cardPile.size()>=0){
                pullCardCountLabel.setText(cardPile.size() + " Карт");
                updateLabels();
            }
            // Если карты равны
            if (p1Card.getCardValue() == p2Card.getCardValue()) {
                updateLabels();
                playWarHand();
                System.out.println("Both");
            }

            else if (p1Card.getCardValue()>p2Card.getCardValue())//Если карта первого больше карты второго
            {
                p1Hand.addAll(cardPile);
                updateLabels();
                //Очистка поля
                cardPile.clear();
                System.out.println("First");
            }
            else if (p1Card.getCardValue()<p2Card.getCardValue()) //Карта второго больше карты первого
            {
                p2Hand.addAll(cardPile);
                updateLabels();
                cardPile.clear();
                System.out.println("Sec");

            }
            updateLabels();
        }
    }

    @FXML
    private void newGame()
    {
        playAgainButton.setDisable(true);
        playButton.setDisable(false);
        winnerLabel.setVisible(false);
        p1ImageView.setVisible(false);
        p2ImageView.setVisible(false);

        p1Hand = new LinkedList<>();
        p2Hand = new LinkedList<>();
        cardPile = new LinkedList<>();

        //Перемешиваем карты в колоде
        DeckOfCards deck = new DeckOfCards();
        deck.shuffle();

        //уменьшение размера колоды для теста
        //for (int i=1; i<=46; i++)
        //   deck.dealTopCard();

        //все ли карты сданы
        while (deck.getNumOfCardsInDeck()>=2)
        {
            p1Hand.add(deck.dealTopCard());
            p2Hand.add(deck.dealTopCard());
        } //Тест для проверки в случае одинаковых карт
        //Card ace = new Card("14","spades");
        //Card ace2 = new Card("14","diamonds");
        //p1Hand.add(0,ace);
        //p2Hand.add(0,ace2);

        updateLabels();
    }

    @FXML
    private Button playAgainButton;

    //Обновление всех текстовых полей
    private void updateLabels()
    {


        if (p1Hand.size()==1)
            p1CardCountLabel.setText(p1Hand.size() + " Карт");
        else
            p1CardCountLabel.setText(p1Hand.size() + " Карт");

        p2CardCountLabel.setText(String.format("%d %s",p2Hand.size(),
                p2Hand.size()==1?"Карт":"Карт"));
    }

    //Метод на случай когда у игроков одинаковые карты
    private void playWarHand()
    {
        //Проверяем достаточно ли у игрока 1 карт
        if (p1Hand.size()<4)
            declareWinner(name2.getText());
            //Достаточно ли карт у 2 игрока
        else if (p2Hand.size()<4)
            declareWinner(name1.getText());
        else //Если карт достаточно
        {
            // Игроки выкладывают в закрытую три карты и вскрывают еще две
            for (int i = 1; i <= 3; i++) {
                cardPile.addLast(p1Hand.pollFirst());
                cardPile.addLast(p2Hand.pollFirst());
            }

        }
        playHand();
    }

    private void declareWinner(String winnerName)
    {
        playButton.setDisable(true);
        playAgainButton.setDisable(false);

        p1ImageView.setVisible(false);

        p2ImageView.setVisible(false);

        winnerLabel.setVisible(true);
        winnerLabel.setText(winnerName + " Победил!!");

        updateLabels();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        newGame();

    }
}