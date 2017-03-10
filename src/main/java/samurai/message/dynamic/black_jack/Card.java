package samurai.message.dynamic.black_jack;

import java.text.ParseException;

/**
 * @author TonTL
 * @version 3/8/2017
 */
public class Card {
    private final int value;
    private final Suit suit;

    Card(int value, Suit suit) {
        this.value = value;
        this.suit = suit;
    }

    public static Card parseCard(String s) throws ParseException {
        if (s.length() == 2) {
            int value;
            Suit suit;
            switch (Character.toLowerCase(s.charAt(0))) {
                case '1':
                case 'a':
                    value = 1;
                    break;
                case '2':
                    value = 2;
                    break;
                case '3':
                    value = 3;
                    break;
                case '4':
                    value = 4;
                    break;
                case '5':
                    value = 5;
                    break;
                case '6':
                    value = 6;
                    break;
                case '7':
                    value = 7;
                    break;
                case '8':
                    value = 8;
                    break;
                case '9':
                    value = 9;
                    break;
                case 'j':
                    value = 11;
                    break;
                case 'q':
                    value = 12;
                    break;
                case 'k':
                    value = 13;
                    break;
                default:
                    throw new ParseException("Could not recognize " + s + " as a card", 0);
            }
            switch (Character.toLowerCase(s.charAt(1))) {
                case 's':
                    suit = Suit.SPADES;
                    break;
                case 'c':
                    suit = Suit.CLUBS;
                    break;
                case 'd':
                    suit = Suit.DIAMONDS;
                    break;
                case 'h':
                    suit = Suit.HEARTS;
                    break;
                default:
                    throw new ParseException("Could not recognize " + s + " as a card", 1);
            }
            return new Card(value, suit);
        } else if (s.length() == 3) {
            if (s.startsWith("10")) {
                Suit suit;
                switch (Character.toLowerCase(s.charAt(2))) {
                    case 's':
                        suit = Suit.SPADES;
                        break;
                    case 'c':
                        suit = Suit.CLUBS;
                        break;
                    case 'd':
                        suit = Suit.DIAMONDS;
                        break;
                    case 'h':
                        suit = Suit.HEARTS;
                        break;
                    default:
                        throw new ParseException("Could not recognize " + s + " as a card", 2);
                }
                return new Card(10, suit);
            }
        }
        throw new ParseException("Could not recognize " + s + " as a card", 2);
    }

    public int value() {
        return value;
    }

    public Suit suit() {
        return suit;
    }

    public String getSymbolSuit() {
        switch (suit) {
            case SPADES:
                return "♠";
            case CLUBS:
                return "♣";
            case DIAMONDS:
                return "♦";
            case HEARTS:
                return "♥";
        }
        return "";
    }

    public String getSymbolValue() {
        if (value != 1 && value <= 10) {
            return Integer.toString(value);
        } else switch (value) {
            case 1:
                return "A";
            case 11:
                return "J";
            case 12:
                return "Q";
            case 13:
                return "K";
        }
        return "";
    }

    public boolean isFace() {
        return value == 1 || value > 10;
    }

    public enum Suit {
        SPADES, CLUBS, DIAMONDS, HEARTS;
    }
}
