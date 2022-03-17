package dogz.bot.commands;

import discord4j.common.util.Snowflake;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.channel.GuildMessageChannel;
import discord4j.core.spec.EmbedCreateSpec;
import discord4j.rest.util.Color;
import dogz.bot.utils.CommandsName;
import dogz.bot.utils.ConsoleMessages;

public class Hello extends Command implements ICommand {

    public Hello(){
        super(CommandsName.HELLO);
    }

    @Override
    public void execute(MessageCreateEvent messageCreateEvent) {

        EmbedCreateSpec embed = EmbedCreateSpec.builder()
                .color(Color.BLUE)
                .description("Hello "+messageCreateEvent.getMessage().getUserData().username())
                .build();

        messageCreateEvent.getClient().getChannelById(Snowflake.of("948560291149525032"))
                .ofType(GuildMessageChannel.class)
                .flatMap(channel->channel.createMessage(embed))
                .subscribe();
    }
}
