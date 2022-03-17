package dogz.bot.commands;

import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.channel.GuildMessageChannel;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.core.object.entity.channel.TextChannel;
import discord4j.core.spec.EmbedCreateSpec;
import dogz.bot.commands.pattern.MessageEmbedInformationPattern;
import dogz.bot.persistance.IDataBase;
import dogz.bot.utils.BotMessages;
import dogz.bot.utils.CommandsName;
import reactor.core.publisher.Mono;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;

public class EventAdventiser extends Command implements ICommand{

    private HashSet<Mono<MessageChannel>> messageChannels;

    private class EventParameter{
        private String title;
        private String description;
        private String imageURL;


    }

    private HashSet<EventParameter> parameters;

    private void init(){
        messageChannels = new HashSet<>();
        parameters = new HashSet<>();
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
        while(!fifo.isEmpty()){
            String param = fifo.pop();
            switch (param){
                case "-add":
                    messageChannels.add(messageCreateEvent.getMessage().getChannel());
                    print(MessageEmbedInformationPattern.standartInformation(BotMessages.EventMessages.CHANNEL_ADDED),messageCreateEvent.getMessage().getChannel());
                    break;
                case "-print":
                    print();
                    break;
                case "-list":
                    print(MessageEmbedInformationPattern.channelList(messageChannels),messageCreateEvent.getMessage().getChannel());
                    break;
                default :
                    print(MessageEmbedInformationPattern.InvalideArgument(param,this),messageCreateEvent.getMessage().getChannel());
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

        for(Mono<MessageChannel> channel : messageChannels){
            print(embed,channel);
        }
    }
}
