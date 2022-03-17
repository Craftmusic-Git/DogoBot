package dogz.bot.commands.pattern;

import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.core.object.entity.channel.TextChannel;
import discord4j.core.spec.EmbedCreateSpec;
import discord4j.rest.util.Color;
import dogz.bot.commands.Command;
import dogz.bot.utils.BotMessages;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.HashSet;

public abstract class MessageEmbedInformationPattern {
    public static EmbedCreateSpec Error(String msg){
        EmbedCreateSpec embed = EmbedCreateSpec.builder()
                .color(Color.RED)
                .title(BotMessages.INFORMATION_ERROR)
                .thumbnail(BotMessages.BOT_ICONE_THUMBNAIL)
                .description(msg)
                .timestamp(Instant.now())
                .build();
        return embed;
    }

    public static EmbedCreateSpec InvalideArgument(String arg, Command command){
        EmbedCreateSpec embed = EmbedCreateSpec.builder()
                .color(Color.RED)
                .title(BotMessages.INFORMATION_INVALIDE_ARGUMENT)
                .thumbnail(BotMessages.BOT_ICONE_THUMBNAIL)
                .description("Argument : "+arg+" is invalide\n" +
                        "From : "+command.getName())
                .build();
        return embed;
    }

    public static EmbedCreateSpec InvalideCommand(String arg){
        EmbedCreateSpec embed = EmbedCreateSpec.builder()
                .color(Color.RED)
                .title(BotMessages.INFORMATION_ERROR)
                .thumbnail(BotMessages.BOT_ICONE_THUMBNAIL)
                .description("Argument : "+arg+" is not a command\n")
                .build();
        return embed;
    }

    public static EmbedCreateSpec standartInformation(String msg){
        EmbedCreateSpec embed = EmbedCreateSpec.builder()
                .color(Color.SUBMARINE)
                .thumbnail(BotMessages.BOT_ICONE_THUMBNAIL)
                .description(msg)
                .timestamp(Instant.now())
                .build();
        return embed;
    }

    public static EmbedCreateSpec channelList(HashSet<Mono<MessageChannel>> textChannel){
        StringBuilder Desc = new StringBuilder();
        for(Mono<MessageChannel> channel : textChannel){
            Desc.append("- ").append(((TextChannel)channel.block()).getName()).append("\n");
        }

        EmbedCreateSpec embed = EmbedCreateSpec.builder()
                .color(Color.SUBMARINE)
                .thumbnail(BotMessages.BOT_ICONE_THUMBNAIL)
                .description("Liste du/des channel.s abonnés : \n"+Desc)
                .timestamp(Instant.now())
                .build();
        return embed;
    }

}
