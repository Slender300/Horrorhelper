package io.github.slender300.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;

import java.util.Objects;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class HugCommand extends Command {

    private final EventWaiter waiter;

    final String[] hugGifs = {
            "https://acegif.com/wp-content/gif/anime-hug-38.gif", // gifs[0]
            "https://acegif.com/wp-content/gif/anime-hug-27.gif", // gifs[1]
            "https://acegif.com/wp-content/gif/anime-hug-86.gif", // gifs[2]
            "https://acegif.com/wp-content/gif/anime-hug-79.gif", // gifs[3]
            "https://acegif.com/wp-content/gif/anime-hug-83.gif", // gifs[4]
            "https://acegif.com/wp-content/gif/anime-hug-14.gif", // gifs[5]
            "https://acegif.com/wp-content/gif/anime-hug-63.gif", // gifs[6]
            "https://acegif.com/wp-content/gif/anime-hug-19.gif", // gifs[7]
            "https://acegif.com/wp-content/gif/anime-hug-2.gif", // gifs[8]
            "https://acegif.com/wp-content/gif/anime-hug-6.gif" // gifs[9]

    };

    final String[] madGifs = {
            "https://www.infoidiomas.com/wp-content/uploads/espa%C3%B1ol-japones.gif", // gifs[0]
            "https://i.pinimg.com/originals/24/46/fc/2446fcf5ccc0449f1d2fd1d7a0c3398b.gif",
            "https://static.vix.com/es/sites/default/files/t/toradora-taiga-gif.gif",
            "https://i.pinimg.com/originals/b7/c4/c0/b7c4c0be88d27484e0145ab0d822f1d4.gif",
            "http://i.imgur.com/3kyTd.gif",
            "http://k32.kn3.net/taringa/3/2/D/5/D/5/BetoMoy/CB9.gif",
            "https://i.gifer.com/BLs3.gif",
            "https://i.pinimg.com/originals/2d/72/3e/2d723e76a32cda4219dd589deafc8777.gif",
            "https://i.pinimg.com/originals/bb/8d/19/bb8d192411c61b960af876a86813aef7.gif",
            "https://acegif.com/wp-content/gif/angry-46.gif"
    };

    Random r = new Random();

    public HugCommand(EventWaiter waiter){
        this.waiter = waiter;
        this.name = "Hug";
        this.aliases = new String[] {"h", "hug"};
        this.guildOnly = true;
        this.arguments = "[usuario]";
        this.help = "Manda una solicitud a abrazo a alguien";
    }


    @Override
    protected void execute(CommandEvent event){

        event.getTextChannel().deleteMessageById(event.getTextChannel().getLatestMessageId()).queue();

        sendHugRequest(event);
    }

    private void sendHugRequest(CommandEvent event) {
        try {
            Member user = event.getMessage().getMentionedMembers().get(0);

            EmbedBuilder embed = new EmbedBuilder();

            embed.setTitle("Se ha enviado una solicitud de abrazo");
            embed.setDescription(String.format("%s quiere abrazar a %s",
                    event.getAuthor().getAsMention(), user));
            embed.setColor(0xee6767);

            TextChannel channel = event.getTextChannel();

            channel.sendMessage(embed.build()).queue(m ->
                    {
                        m.addReaction("\u2705").queue();
                        m.addReaction("\u274C").queue();
                        m.delete().queueAfter(8, TimeUnit.SECONDS);
                    }
            );

            waiter.waitForEvent(MessageReactionAddEvent.class,
                    e -> isCheck(e, event) || isCross(e, event) || notUser(e, event),
                    e -> {
                            if(isCheck(e, event))
                            {
                                acceptedHugRequest(event, user);
                            }
                            else if(isCross(e, event))
                            {
                                rejectedHugRequest(event, user);
                            }
                            else if(notUser(e, event))
                            {
                                notSendedHugRequest(event, event.getMember());
                            }
                    }
            );
        }
        catch (Exception e)
        {
            argsNotFound(event);
        }
    }

    private boolean isCheck(MessageReactionAddEvent e, CommandEvent event) {
        Member user = event.getMessage().getMentionedMembers().get(0);

        return !(Objects.requireNonNull(e.getUser()).isBot())
                && Objects.equals(e.getMember(), user)
                && e.getReactionEmote().getAsReactionCode().equals("\u2705")
                && e.getChannel().equals(event.getChannel());
    }

    private boolean isCross(MessageReactionAddEvent e, CommandEvent event) {
        Member user = event.getMessage().getMentionedMembers().get(0);

        return !(Objects.requireNonNull(e.getUser()).isBot())
                && Objects.equals(e.getMember(), user)
                && e.getReactionEmote().getAsReactionCode().equals("\u274C")
                && e.getChannel().equals(event.getChannel());
    }

    private boolean notUser(MessageReactionAddEvent e, CommandEvent event) {
        Member user = event.getMessage().getMentionedMembers().get(0);

        return !(Objects.requireNonNull(e.getUser()).isBot())
                && !(Objects.equals(e.getMember(), user))
                && e.getReactionEmote().getAsReactionCode().equals("\u2705")
                && e.getChannel().equals(event.getChannel())
                || !(Objects.requireNonNull(e.getUser()).isBot())
                && !(Objects.equals(e.getMember(), user))
                && e.getReactionEmote().getAsReactionCode().equals("\u274C")
                && e.getChannel().equals(event.getChannel());
    }

    private void acceptedHugRequest(CommandEvent event, Member name) {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle("Han aceptado la solicitud de abrazo");
        embed.setDescription(String.format("%s ha abrazado a %s",
                name, event.getAuthor().getAsMention()));
        embed.setColor(0x02ff02);

        int randNumber = r.nextInt(10);

        embed.setImage(hugGifs[randNumber]);

        event.reply(embed.build());
    }

    private void rejectedHugRequest(CommandEvent event, Member name) {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle("Han rechazado la solicitud de abrazo");
        embed.setDescription(String.format("%s no ha abrazado a %s",
                name, event.getAuthor().getAsMention()));
        embed.setColor(0xff0606);

        int randNumber = r.nextInt(10);

        embed.setImage(madGifs[randNumber]);

        event.reply(embed.build());
    }

    private void notSendedHugRequest(CommandEvent event, Member member) {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle("No eres a quien le han pedido el abrazo");
        embed.setDescription(String.format("%s no quiere abrazarte a ti %s",
                event.getAuthor().getAsMention() , member.getAsMention()));
        embed.setColor(0xff0606);

        event.reply(embed.build());

    }

    private void argsNotFound(CommandEvent event) {

        EmbedBuilder embed = new EmbedBuilder();

        embed.setTitle("No ingreso ningún argumento");
        embed.setDescription(event.getAuthor().getAsMention());
        embed.setColor(0xff0606);

        embed.addField("Documentación:",
                "https://github.com/Slender300/Horrorhelper",
                false
        );

        event.reply(embed.build());

    }

}
