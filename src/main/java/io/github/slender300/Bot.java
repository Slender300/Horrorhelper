package io.github.slender300;

import com.jagrosh.jdautilities.command.CommandClientBuilder;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import io.github.slender300.commands.HugCommand;
import io.github.slender300.commands.ProgressCommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;


public class Bot extends ListenerAdapter {

    public Bot(JDABuilder builder) {

        builder.disableCache(CacheFlag.ACTIVITY);
        builder.setChunkingFilter(ChunkingFilter.NONE);

        builder.setLargeThreshold(40);
    }

    public static void main(String[] args) throws Exception {

        EventWaiter waiter = new EventWaiter();

        CommandClientBuilder client = new CommandClientBuilder();

        client.setPrefix("%");

        client.setActivity(Activity.listening("Los problemas del server"));

        client.setOwnerId("395646613973893121");

        client.addCommands(
                new ProgressCommand(),
                new HugCommand(waiter)
        );

        JDABuilder builder = JDABuilder.createDefault(System.getenv("DISOCRD_TOKEN"));
        builder.addEventListeners(new Bot(builder),waiter, client.build());
        builder.build();
    }

    @Override
    public void onReady(@NotNull ReadyEvent event) {
        System.out.printf("%s#%s is ready\n", event.getJDA().getSelfUser().getName(),
                event.getJDA().getSelfUser().getDiscriminator());

        EmbedBuilder embed = new EmbedBuilder();

        embed.setTitle(String.format("%s#%s ha sido activado", event.getJDA().getSelfUser().getName(),
                event.getJDA().getSelfUser().getDiscriminator()));

        embed.setDescription("@everyone");

        embed.setColor(0x3b704e);

        try
        {
            Objects.requireNonNull(event.getJDA().getTextChannelById(810353245415407646L))
                    .sendMessage(embed.build()).queue();
        }
        catch (Exception e)
        {
            System.err.println("Channel not found");
        }
    }
}
