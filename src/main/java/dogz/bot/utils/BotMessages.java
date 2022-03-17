package dogz.bot.utils;

public abstract class BotMessages {
    public static final String INFORMATION_TITLE = "Information : ";
    public static final String INFORMATION_ERROR = "Erreur : ";
    public static final String BOT_STATUS_ONLINE = "Le DogoBot est opérationel.";
    public static final String BOT_ICONE_THUMBNAIL = "https://i.imgur.com/F9BhEoz.png";
    public static final String INFORMATION_INVALIDE_ARGUMENT = "Erreur : (INVALIDE_ARGUMENT)";

    public abstract class Emoji{
        public static final String GREEN_CIRCLE = ":green_circle:";
        public static final String YELLOW_CIRCLE = ":yellow_circle:";
        public static final String RED_CIRCLE = ":red_circle:";
    }

    public abstract class EventMessages{
        public static final String CHANNEL_ADDED = "Channel rajouté :ballot_box_with_check:";
    }
}
