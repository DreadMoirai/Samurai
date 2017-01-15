package dreadmoirai.samurais;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.util.HashMap;
import java.util.Random;

/**
 * Created by TonTL on 1/14/2017.
 *
 */
public class BotListener extends ListenerAdapter {

    private static HashMap<String, String> roleResponses;
    private static Random rand;
    private static JDA jda;

    public BotListener() { //constructor


        roleResponses = new HashMap<>(); //hashmap for responses to who am i?
        roleResponses.put("[R:Shogun(267924909752188928)]", "a coding god");
        roleResponses.put("[R:Daimyo(268458081225146369)]", "ur ok");
        roleResponses.put("[R:Samurai(267925162991681547)]", "pretty cool");
        roleResponses.put("[R:Peasant(267924616574533634)]", "meh");

        //Random object for rolls
        rand = new Random();
    }

    public BotListener setParent(JDA jda) {
        BotListener.jda = jda;
        return this;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {  //when a message is sent in the channel
        if (simpleResponse(event)) {
        } else if (exitProtocol(event)) {
        } else if (duel(event)) {
        }
    }

    private boolean simpleResponse(MessageReceivedEvent event) {
        String messageRecieved = event.getMessage().getRawContent().toLowerCase();
        String messageSent = "";
        if (messageRecieved.equals("who am i?")) {
            messageSent = " " + roleResponses.get(event.getMember().getRoles().toString());
        } else if (messageRecieved.contains("!roll")) {
            if (messageRecieved.length() > 6) {
                try {
                    int x = Integer.parseInt(messageRecieved.substring(6));
                    messageSent = " rolled " + (rand.nextInt(x) + 1) + "!";
                } catch (NumberFormatException e) {
                    messageSent = " what?";
                }
            } else {
                messageSent = " rolled " + (rand.nextInt(100) + 1) + "!";
            }
        }
        if (messageSent.length() > 0) {
            event.getChannel().sendMessage(event.getAuthor().getAsMention() + messageSent).queue();
            return true;
        }
        return false;
    }

    private boolean exitProtocol(MessageReceivedEvent event) {
        if (event.getMessage().getRawContent().equalsIgnoreCase("!kys")) {
            event.getChannel().sendMessage("ok :(").queue();
            event.getJDA().shutdown();
            return true;
        } else if (event.getMessage().getRawContent().equalsIgnoreCase("!shutdown")) {
            event.getChannel().sendMessage("sad boop").queue();
            event.getJDA().shutdown();
            return true;
        }
        return false;
    }

    private boolean duel(MessageReceivedEvent event) {

        return false;
    }


}
