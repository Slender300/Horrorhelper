package io.github.slender300.commands.pogresscommand;

import com.jagrosh.jdautilities.command.CommandEvent;
import io.github.slender300.commands.ProgressCommand;
import net.dv8tion.jda.api.EmbedBuilder;

import java.util.Objects;

public class ResetProgress {

    public static void checkLengthReset(CommandEvent event, String[] args) {
        if (args.length == 2)
        {
            resetProgress(event, args);
        }
        else
        {
            errorArgs(event);
        }
    }

    private static void resetProgress(CommandEvent event, String[] args) {
        try
        {
            // Name of the voice channel
            String name = Objects.requireNonNull(event.getJDA().getVoiceChannelById(ProgressCommand
                    .channels.get(args[1])))
                    .getName();

            // Parts of the name of the voice channel
            String[] parts = name.split(" ");

            // Update the name of voice channel
            Objects.requireNonNull(event.getJDA().getVoiceChannelById(ProgressCommand
                    .channels.get(args[1]))).getManager()
                    .setName(String.format("%s %s %s 0%%",parts[0], parts[1], parts[2]))
                    .queue();


            // Success message
            EmbedBuilder embed = new EmbedBuilder();

            embed.setTitle("Se ha reseteado el canal de progreso");
            embed.setDescription(event.getAuthor().getAsMention());
            embed.setColor(0x02ff02);

            event.reply(embed.build());

        }
        catch (Exception e)
        {

            String format = "Error causado por: %s\n";

            EmbedBuilder embed = new EmbedBuilder();

            embed.setTitle("Ha ocurrido un error");
            embed.setDescription(event.getAuthor().getAsMention());
            embed.setColor(0xff0606);

            embed.addField("Detalles:",
                    String.format(format, e.getMessage()),
                    false
            );

            embed.addField("Documentación:",
                    "https://github.com/Slender300/Horrorhelper",
                    false
            );

            embed.addField("Errores comunes:",
                    "null: Referido a que no existe tal objeto, esto ocurre al dar un canal " +
                            "de voz inexistente o por no ser propietario del canal",
                    false
            );

            event.reply(embed.build());

            System.err.printf(format, e.getMessage());
        }
    }

    private static void errorArgs(CommandEvent event) {
        EmbedBuilder embed = new EmbedBuilder();

        embed.setTitle("La cantidad de argumentos es insuficiente o execede lo establecido");
        embed.setDescription(event.getAuthor().getAsMention());
        embed.setColor(0xff0606);

        embed.addField("Documentación:",
                "https://github.com/Slender300/Horrorhelper",
                false
        );

        event.reply(embed.build());

    }
}
