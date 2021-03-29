package io.github.slender300.commands.pogresscommand;

import com.jagrosh.jdautilities.command.CommandEvent;
import io.github.slender300.commands.ProgressCommand;
import net.dv8tion.jda.api.EmbedBuilder;

import java.util.Objects;

public class AddProgress {


    public static void checkLengthAdd(CommandEvent event , String[] args) {
        switch(args.length) {
            case 2:
                defaultAddProgress(event, args);
                break;
            case 3:
                addProgress(event, args);
                break;
            default:
                errorArgs(event);
        }
    }

    /*
     * Default add operation if quantity is not provided
     */
    private static void defaultAddProgress(CommandEvent event, String[] args) {
        try
        {   // Name of the voice channel
            String name = Objects.requireNonNull(event.getJDA().getVoiceChannelById(ProgressCommand
                    .channels.get(args[1])))
                    .getName();

            // Number of the voice channel progress
            int number = Integer.parseInt(name.replaceAll("[^0-9]", ""));
            int result = number + 1; // Adding 1 to that progress

            // Parts of the name of the voice channel
            String[] parts = name.split(" ");

            // Update name of the voice channel
            Objects.requireNonNull(event.getJDA().getVoiceChannelById(ProgressCommand
                    .channels.get(args[1]))).getManager()
                    .setName(String.format("%s %s %s %d%%",parts[0], parts[1], parts[2], result))
                    .queue();

            // Success message
            EmbedBuilder embed = new EmbedBuilder();

            embed.setTitle("Se ha añadido 1 al canal de progreso");
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
                    "null: Referido a que no existe tal objeto, " +
                            "esto ocurre al dar un canal de voz inexistente o por no ser propietario del canal\n"
                            + "For input String: \"argumento ingresado\": Referido a que el argumento " +
                            "ingresado es texto, esto ocurre por dar " +
                            "un argumento que es texto en vez de dar un número entero",
                    false
            );

            event.reply(embed.build());

            System.err.printf(format, e.getMessage());
        }
    }

    /*
     * Adds args[2] to the progress
     */

    private static void addProgress(CommandEvent event, String[] args) {
        try
        {
            // Name of the voice channel
            String name = Objects.requireNonNull(event.getJDA().getVoiceChannelById(ProgressCommand
                    .channels.get(args[1])))
                    .getName();

            // Number of the progress , quantity and result
            int number, quantity, result;
            number = Integer.parseInt(name.replaceAll("[^0-9]", ""));
            quantity = Integer.parseInt(args[2]);
            result = number + quantity;

            // Parts of the name of the voice channel
            String[] parts = name.split(" ");

            // Update the name of voice channel
            Objects.requireNonNull(event.getJDA().getVoiceChannelById(ProgressCommand
                    .channels.get(args[1]))).getManager()
                    .setName(String.format("%s %s %s %d%%",parts[0], parts[1], parts[2], result))
                    .queue();


            // Success message
            EmbedBuilder embed = new EmbedBuilder();

            embed.setTitle(String.format("Se ha añadido %d al canal de progreso", quantity));
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
                    "null: Referido a que no existe tal objeto, " +
                            "esto ocurre al dar un canal de voz inexistente o por no ser propietario del canal\n"
                    + "For input String: \"argumento ingresado\": Referido a que el argumento ingresado es texto, " +
                            "esto ocurre por dar un argumento que es texto en vez de dar un número entero",
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
