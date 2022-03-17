package dogz.bot.core;

import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.Event;
import dogz.bot.utils.ConsoleMessages;
import dogz.bot.utils.States;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class Bot {

    private static String botKey;
    private static HashSet<String> musicBotsKeys;

    private static final Logger LOG = LogManager.getLogger("Startup");

    public static void main(String[] args){
        LOG.log(Level.INFO,"Bot start.");
        switch (args.length){
            case 0 :
                System.out.println("Le nombre d'arguments ne peut être nulle ! ");
                LOG.log(Level.ERROR,"Number of args = 0");
                throw new IllegalArgumentException("args.length = 0");
            case 1 :
                switch (args[0]){
                    case "-h":
                        System.out.println(ConsoleMessages.HELP);
                        break;
                    default:
                        LOG.log(Level.ERROR,"Bad argument");
                        throw new IllegalArgumentException("Bad argument");
                }
                break;
            default:
                ArrayDeque<String> fifo = new ArrayDeque<>(Arrays.asList(args));
                while(!fifo.isEmpty()){
                    String param = fifo.pop();
                    switch (param){
                        case "-h":
                            System.out.println(ConsoleMessages.HELP);
                            break;
                        case "-k":
                            botKey = fifo.pop();
                            break;
                        case "-m":
                            if(fifo.peekFirst() != null && fifo.peekFirst() != "-m" && fifo.peekFirst() != "-k" && fifo.peekFirst() != "-d")
                                musicBotsKeys.add(fifo.pop());
                            break;
                        case "-d":
                            System.out.println(ConsoleMessages.DEBUG);
                            States.__DEBUG_MODE__ = true;
                            break;
                        default:
                            break;
                    }
                }
                break;
        }
        if(Bot.botKey == null)
            return;

        LOG.info("Connection . . .");
        final GatewayDiscordClient client = DiscordClientBuilder.create(Bot.botKey).build()
                .login()
                .block();
        LOG.info("Connected !");

        assert client != null;
        client.getEventDispatcher().on(Event.class)
                .subscribe(event -> {
                    DiscordShell.getDiscordShell().read(event);
                });

        client.onDisconnect().block();
    }
}
