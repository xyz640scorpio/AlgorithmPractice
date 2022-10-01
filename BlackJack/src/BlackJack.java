/*
Summary:
52 cards (no jokers)
2 - 10: number as points
J, Q, K: 10 points
A: 1 or 11 points
total score > 21: busted
winning rule: who reach 21 points first, who win (这个有好多可以讨论的点)
 */


import java.util.*;
public class BlackJack {

}

enum Suit {Club, Spade, Diamond, Heart}
enum Action {Hit, Stand}

class Card implements Comparable<Card>{
    int value; // 1 represents Ace, 2 - 10 represents normal points, 11 Jack, 12 Queen, 13 King
    String suit;

    // whether Ace
    public boolean isAce() {
        return value == 1;
    }

    // whether Jack, Queen, King
    public boolean isFace() {
        return value > 10;
    }

    @Override
    public int compareTo(Card o) {
        return Integer.compare(this.value, o.value);
    }

}

class Deck {
    Card[] cards;
    Random random;
    int curCard;


    public void shuffle() {
        for (int i = cards.length - 1; i >= 0; i--) {
            int j = random.nextInt(i + 1);
            Card temp = cards[i];
            cards[i] = cards[j];
            cards[j] = temp;
        }
    }

    public Card dealCard() {
        return cards[curCard++];
    }
}
class Player {
    String name;
    List<Card> cards;

    public boolean isBlackJack() {
        return score() == 21;
    }

    public boolean isBusted() {
        return score() > 21;
    }

    public void hit(Deck d) {
        cards.add(d.dealCard());
    }

    public void action(Action act, Deck d) {
        switch(act) {
            case Hit:
                hit(d);
                break;
            case Stand:
                break;
        }
    }

    public int score() {
        int score = 0;
        Collections.sort(cards, Collections.reverseOrder());
        int i = 0;
        for (; i < cards.size(); i++) {
            Card card = cards.get(i);
            if (card.isFace()) {
                score += 10;
            } else if (!card.isAce()) {
                score += card.value;
            } else {
                break;
            }
        }
        // can only at most 1 Ace count as 11
        int numOfAces = cards.size() - i;
        int min = score + numOfAces; // all Aces = 1
        int max = score + numOfAces + 10; // 1 Ace = 11 and all other Aces = 1
        if (max + score <= 21) {
            return max + score;
        }
        return min + score;
    }

}
class DealerPlayer extends Player {
    private final static int MIN_DEALER_SCORE = 17;

    @Override
    public void action(Action act, Deck d) {
        if (score() < MIN_DEALER_SCORE) {
            hit(d);
            return;
        }
        switch (act) {
            case Hit:
                hit(d);
                break;
            case Stand:
                break;
        }
    }
}