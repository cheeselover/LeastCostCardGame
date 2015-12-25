package models;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import utils.Suit;
import utils.Value;

import java.util.ArrayList;

/**
 * Created by siva on 2015-12-24.
 */
public class HandTest {
    private Hand hand;
    private ArrayList<Integer> indexes;

    @Before
    public void setup() {
        hand = new Hand();
        indexes = new ArrayList<>();
    }

    @Test
    public void valid1() {
        hand.add(5, Suit.CLUBS);
        indexes.add(0);
        Assert.assertNotNull(hand.play(indexes));
    }

    @Test
    public void validSameValues1() {
        hand.add(4, Suit.SPADES);
        hand.add(4, Suit.DIAMONDS);
        indexes.add(1);
        indexes.add(0);
        Assert.assertNotNull(hand.play(indexes));

        hand.add(4, Suit.SPADES);
        hand.add(4, Suit.DIAMONDS);
        hand.add(4, Suit.HEARTS);
        indexes.add(2);
        Assert.assertNotNull(hand.play(indexes));

        hand.add(4, Suit.SPADES);
        hand.add(4, Suit.DIAMONDS);
        hand.add(4, Suit.HEARTS);
        hand.add(4, Suit.CLUBS);
        indexes.add(3);
        Assert.assertNotNull(hand.play(indexes));
    }

    @Test
    public void validConsecutive1() {
        hand.add(Value.JACK, Suit.DIAMONDS);
        hand.add(Value.QUEEN, Suit.DIAMONDS);
        hand.add(Value.KING, Suit.DIAMONDS);

        indexes.add(0);
        indexes.add(1);
        indexes.add(2);

        Assert.assertNotNull(hand.play(indexes));
    }

    @Test
    public void validConsecutive2() {
        hand.add(Value.JACK, Suit.DIAMONDS);
        indexes.add(0);
        Assert.assertNotNull(hand.play(indexes));

        hand.add(Value.JACK, Suit.DIAMONDS);
        hand.add(Value.JACK, Suit.HEARTS);
        indexes.add(1);
        Assert.assertNotNull(hand.play(indexes));

        hand.add(Value.JACK, Suit.DIAMONDS);
        hand.add(Value.JACK, Suit.HEARTS);
        hand.add(Value.JACK, Suit.SPADES);
        indexes.add(2);
        Assert.assertNotNull(hand.play(indexes));

        hand.add(Value.JACK, Suit.DIAMONDS);
        hand.add(Value.JACK, Suit.HEARTS);
        hand.add(Value.JACK, Suit.SPADES);
        hand.add(Value.JACK, Suit.CLUBS);
        indexes.add(3);
        Assert.assertNotNull(hand.play(indexes));
    }

    @Test
    public void invalid1() {
        hand.add(Value.ACE, Suit.HEARTS);
        hand.add(2, Suit.HEARTS);
        indexes.add(0);
        indexes.add(1);
        Assert.assertNull(hand.play(indexes));
    }

    @Test
    public void invalid2() {
        hand.add(2, Suit.HEARTS);
        hand.add(5, Suit.HEARTS);
        hand.add(3, Suit.HEARTS);
        indexes.add(1);
        indexes.add(2);
        indexes.add(0);
        Assert.assertNull(hand.play(indexes));
    }

    @Test
    public void invalid3() {
        hand.add(2, Suit.HEARTS);
        hand.add(5, Suit.HEARTS);
        hand.add(3, Suit.HEARTS);
        indexes.add(1);
        indexes.add(2);
        indexes.add(0);
        Assert.assertNull(hand.play(indexes));
    }

    @Test
    public void invalid4() {
        hand.add(2, Suit.HEARTS);
        hand.add(3, Suit.HEARTS);
        hand.add(4, Suit.DIAMONDS);
        indexes.add(2);
        indexes.add(0);
        indexes.add(1);
        Assert.assertNull(hand.play(indexes));
    }

    @Test
    public void invalid5() {
        hand.add(2, Suit.HEARTS);
        hand.add(2, Suit.CLUBS);
        hand.add(3, Suit.HEARTS);
        indexes.add(1);
        indexes.add(0);
        indexes.add(2);
        Assert.assertNull(hand.play(indexes));
    }
}
