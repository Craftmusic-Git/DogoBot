package dogz.bot.events;

import discord4j.common.util.Snowflake;
import discord4j.core.event.domain.lifecycle.ConnectEvent;
import discord4j.core.object.entity.channel.GuildMessageChannel;
import discord4j.core.spec.EmbedCreateSpec;
import discord4j.rest.util.Color;
import dogz.bot.utils.BotMessages;

import java.time.Instant;

public abstract class DiscordEvent {
    public static void BotStartup(ConnectEvent connectEvent){
        EmbedCreateSpec embed = EmbedCreateSpec.builder()
                .color(Color.GREEN)
                .title(BotMessages.Emoji.GREEN_CIRCLE + " " +BotMessages.INFORMATION_TITLE)
                .thumbnail(BotMessages.BOT_ICONE_THUMBNAIL)
                .description(BotMessages.BOT_STATUS_ONLINE)
                .timestamp(Instant.now())
                .build();

        connectEvent.getClient().getChannelById(Snowflake.of("948564345581895710"))
                .ofType(GuildMessageChannel.class)
                .flatMap(channel->channel.createMessage(embed))
                .subscribe();
    }
}
