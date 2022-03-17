package dogz.bot.core;

import discord4j.core.DiscordClient;
import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.GatewayResources;
import discord4j.core.retriever.EntityRetrievalStrategy;
import discord4j.gateway.GatewayClientGroup;
import discord4j.voice.VoiceConnectionFactory;
import reactor.core.publisher.Mono;

import java.util.Set;

public class MasterDiscordClient extends GatewayDiscordClient{

    private static MasterDiscordClient master;

    private MasterDiscordClient(DiscordClient discordClient, GatewayResources gatewayResources, Mono<Void> onDisconnect, GatewayClientGroup gatewayClientGroup, VoiceConnectionFactory voiceConnectionFactory, EntityRetrievalStrategy entityRetrievalStrategy, Set<String> completingChunkNonces) {
        super(discordClient, gatewayResources, onDisconnect, gatewayClientGroup, voiceConnectionFactory, entityRetrievalStrategy, completingChunkNonces);
    }

    public static GatewayDiscordClient getMasterDiscordClient(){
        return (GatewayDiscordClient)master;
    }

    public static GatewayDiscordClient getMasterDiscordClient(String key){
        if(master == null){
            master = (MasterDiscordClient) DiscordClientBuilder.create(key).build()
                    .login()
                    .block();
        }
        return (GatewayDiscordClient)master;
    }
}
