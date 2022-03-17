package dogz.bot.events;

import discord4j.common.util.Snowflake;
import discord4j.core.event.domain.InviteCreateEvent;
import discord4j.core.object.entity.channel.GuildMessageChannel;
import discord4j.core.spec.EmbedCreateSpec;
import discord4j.rest.util.Color;

public class EvenementChatMessage extends DiscordEvent implements IEventEvenementCreation {
    @Override
    public void execute(InviteCreateEvent inviteCreateEvent) {

        EmbedCreateSpec embed = EmbedCreateSpec.builder()
                .color(Color.MOON_YELLOW)
                .title(inviteCreateEvent.getShardInfo().format())
                .timestamp(inviteCreateEvent.getExpiration().get())
                .build();

        inviteCreateEvent.getClient().getChannelById(Snowflake.of("948564345581895710"))
                .ofType(GuildMessageChannel.class)
                .flatMap(channel -> channel.createMessage(embed))
                .subscribe();
    }
}
