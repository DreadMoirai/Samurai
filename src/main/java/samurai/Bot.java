package samurai;

import com.sun.management.OperatingSystemMXBean;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.exceptions.RateLimitedException;

import javax.security.auth.login.LoginException;
import java.lang.management.ManagementFactory;

/**
 * Main Class
 * Initializes Samurai bot
 */
public class Bot {

    private static final String BOT_TOKEN = "MjcwMDQ0MjE4MTY3MTMyMTcw.C1yJ0Q.oyQMo7ZGXdaq2K3P43NMwOO8diM";
    public static User self;

    public static void main(String[] args) {

        OperatingSystemMXBean operatingSystemMXBean =
                (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

        try {
            JDABuilder jdaBuilder = new JDABuilder(AccountType.BOT);
            SamuraiListener listener = new SamuraiListener(operatingSystemMXBean);
            JDA jda = jdaBuilder
                    .addListener(listener)
                    .setToken(BOT_TOKEN)
                    .buildBlocking();
            listener.setJDA(jda);
            self = jda.getSelfUser();
        } catch (LoginException | RateLimitedException | InterruptedException e) {
            e.printStackTrace();
        }

    }
}
