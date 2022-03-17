package dogz.bot.commands;

import discord4j.core.DiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.channel.GuildMessageChannel;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.core.object.entity.channel.TextChannel;
import discord4j.core.spec.EmbedCreateSpec;
import discord4j.rest.http.client.DiscordWebClient;
import dogz.bot.commands.pattern.MessageEmbedInformationPattern;
import dogz.bot.events.DiscordEvent;
import dogz.bot.persistance.IDataBase;
import dogz.bot.utils.BotMessages;
import dogz.bot.utils.CommandsName;
import reactor.core.publisher.Mono;

import java.util.*;

public class EventAdventiser extends Command implements ICommand{

    private HashSet<Mono<MessageChannel>> messageChannels;

    public static class EventParameter{
        private String title;
        private String description;
        private String imageURL;

        public EventParameter(String title, String description, String imageURL) {
            this.title = title;
            this.description = description;
            this.imageURL = imageURL;
        }
    }

    private List<EventParameter> parameters;

    private void init(){
        messageChannels = new HashSet<>();
        parameters = new LinkedList<>();

    }

    public EventAdventiser() {
        super(CommandsName.EVENT_ADVERTISER);
        init();
    }

    public EventAdventiser(IDataBase _db) {
        super(CommandsName.EVENT_ADVERTISER, _db);
        init();
    }

    @Override
    public synchronized void execute(MessageCreateEvent messageCreateEvent) {
        //Parse et définie une pile des arguments de la commande event
        ArrayDeque<String> fifo = new ArrayDeque<>(Arrays.asList(messageCreateEvent.getMessage().getContent().split(" ")));
        fifo.pop();
        Mono<MessageChannel> channel = messageCreateEvent.getMessage().getChannel();
        while(!fifo.isEmpty()){
            String param = fifo.pop();
            switch (param){
                case "-add":
                    messageChannels.add(channel);
                    db.saveEventChannel(channel.block().getId().asString());
                    print(MessageEmbedInformationPattern.standartInformation(BotMessages.EventMessages.CHANNEL_ADDED),channel);
                    break;
                case "-print":
                    print();
                    break;
                case "-list":
                    print(MessageEmbedInformationPattern.channelList(messageChannels),channel);
                    break;
                default :
                    print(MessageEmbedInformationPattern.InvalideArgument(param,this),channel);
                    break;
            }

        }
    }

    private void print(EmbedCreateSpec embedMsg, Mono<MessageChannel> chan){
        chan.ofType(GuildMessageChannel.class)
                .flatMap(guildMessageChannel -> guildMessageChannel.createMessage(embedMsg))
                .subscribe();
    }

    private void print(){
        EmbedCreateSpec embed = EmbedCreateSpec.builder()
                .build();

        HashSet<String> idChannels = (HashSet<String>) db.loadEventChannel();
        messageChannels = new HashSet<>();
        for (String id : db.loadEventChannel())
        {

        }

        for(Mono<MessageChannel> channel : messageChannels){
            print(embed,channel);
        }
    }
}
