package dogz.bot.events;

import discord4j.core.event.domain.InviteCreateEvent;

public interface IEventEvenementCreation {
    void execute(InviteCreateEvent inviteCreateEvent);
}
