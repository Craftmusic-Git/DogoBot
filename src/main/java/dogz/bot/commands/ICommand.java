package dogz.bot.commands;

import discord4j.core.event.domain.message.MessageCreateEvent;

public interface ICommand {
    void execute(MessageCreateEvent messageCreateEvent);
}
