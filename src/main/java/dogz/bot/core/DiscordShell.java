package dogz.bot.core;

import discord4j.core.event.domain.Event;
import discord4j.core.event.domain.lifecycle.ConnectEvent;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.event.domain.InviteCreateEvent;
import discord4j.core.object.entity.channel.GuildMessageChannel;
import dogz.bot.commands.Command;
import dogz.bot.commands.EventAdventiser;
import dogz.bot.commands.Hello;
import dogz.bot.commands.ICommand;
import dogz.bot.commands.pattern.MessageEmbedInformationPattern;
import dogz.bot.events.DiscordEvent;
import dogz.bot.events.EvenementChatMessage;
import dogz.bot.events.IEventEvenementCreation;
import dogz.bot.persistance.IDataBase;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class DiscordShell {
    private static DiscordShell shell;

    private final Map<String, ICommand> commands = new HashMap<>();
    private final HashSet<IEventEvenementCreation> events = new HashSet<>();
    private IDataBase db;

    private DiscordShell(){
        loadCommand();
        loadEvent();
    }

    public static DiscordShell getDiscordShell(){
        if(shell == null)
            shell = new DiscordShell();
        return shell;
    }

    public void addCommand(Command command){
        if(command != null && !commands.containsKey(command.getName())){
            commands.put(command.getName(),command);
        }
    }

    public void addEvent(IEventEvenementCreation event){
        if(event != null && !events.contains(event)){
            events.add(event);
        }
    }

    private void loadCommand(){
        addCommand(new Hello());
        addCommand(new EventAdventiser());
    }

    private void loadEvent(){
        addEvent(new EvenementChatMessage());
    }

    public void setDb(IDataBase dataBase){
        db = dataBase;
    }

    public void read(Event event) {
        //Gestion des messages utilisateurs
        if (event instanceof MessageCreateEvent && ((MessageCreateEvent) event).getMessage().getContent().startsWith("!",0)) {
            for(final Map.Entry<String, ICommand> command : commands.entrySet()){
                if(((MessageCreateEvent) event).getMessage().getContent().startsWith("!"+command.getKey())){
                    command.getValue().execute((MessageCreateEvent) event);
                    return;
                }
            }
            ((MessageCreateEvent)event).getMessage().getChannel()
                    .ofType(GuildMessageChannel.class)
                    .flatMap(channel->channel.createMessage(MessageEmbedInformationPattern.InvalideCommand(((MessageCreateEvent)event).getMessage().getContent())))
                    .subscribe();
            return;
        }
        //Gestion de la création des liens d'invitations
        if(event instanceof InviteCreateEvent){
            for (final IEventEvenementCreation ievent : events){
                ievent.execute((InviteCreateEvent) event);
                return;
            }
            return;
        }
        //Message de connection au serveur
        if(event instanceof ConnectEvent){
            DiscordEvent.BotStartup((ConnectEvent) event);
        }
    }
}
