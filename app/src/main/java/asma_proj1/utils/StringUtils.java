package asma_proj1.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import jade.core.Agent;

import asma_proj1.card.Card;
import asma_proj1.card.CardInstance;

public class StringUtils {
    public static final String RED = "\033[1;31m",
        GREEN = "\033[1;32m",
        YELLOW = "\033[1;33m",
        BLUE = "\033[1;34m";

    private static final String RESET = "\u001B[0m";

    private StringUtils() {}

    public static String colorize(String str, String color) {
        return color + str + RESET;
    }

    public static void logError(String str) {
        System.err.println(colorize(str, RED));
    }

    public static void logAgentMessage(Agent agent, String str) {
        System.out.println(colorize("Agent '" + agent.getLocalName() + "': ", BLUE) + str);
    }

    public static List<String> cardIds(List<?> cards) {
        if (cards.isEmpty()) return new ArrayList<>();
        Class<?> elemClass = cards.get(0).getClass();

        if (elemClass == Card.class) {
            return cards.stream()
                .map(e -> {
                    Card c = (Card) e;
                    return c.getId() + " " + c.getRarity().symbol;
                }).collect(Collectors.toList());
        }
        else if (elemClass == CardInstance.class) {
            return cards.stream()
                .map(e -> {
                    Card c = ((CardInstance) e).getCard();
                    return c.getId() + " " + c.getRarity().symbol;
                }).collect(Collectors.toList());
        }

        return new ArrayList<>();
    }
}
