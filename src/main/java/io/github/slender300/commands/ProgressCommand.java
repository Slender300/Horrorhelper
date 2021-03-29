package io.github.slender300.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import io.github.slender300.commands.pogresscommand.AddProgress;
import io.github.slender300.commands.pogresscommand.ResetProgress;
import io.github.slender300.commands.pogresscommand.SetProgress;
import net.dv8tion.jda.api.EmbedBuilder;

import java.util.HashMap;


public class ProgressCommand extends Command {


    public static HashMap<String, Long> channels = new HashMap<>();

    HashMap<String, Long> owners = new HashMap<>();

    public ProgressCommand() {
        this.name = "progress";
        this.aliases = new String[]{"p", "progress"};
        this.guildOnly = true;
        this.arguments = "[operación] [canal] [cantidad(opcional)]";
        this.help = "Modifica el progreso";
    }


    @Override
    protected void execute(CommandEvent event) {
        channels.put("1", 816020212302348328L);
        channels.put("2", 816022995902529566L);
        channels.put("3", 816023227662204928L);

        event.getChannel().deleteMessageById(event.getChannel().getLatestMessageId()).queue();

        if(!event.getArgs().equalsIgnoreCase(""))
        {
            String[] args = event.getArgs().split("\\s+");
            checkArguments(event, args);
        }
        else
        {
            argsNotFound(event);
        }
    }

    private void checkArguments(CommandEvent event, String[] args) {
        switch (args[0]) {
            case "add":
                checkIsChannelOwner(event);
                AddProgress.checkLengthAdd(event, args);
                break;
            case "set":
                checkIsChannelOwner(event);
                SetProgress.checkLengthSet(event, args);
                break;
            case "reset":
                checkIsChannelOwner(event);
                ResetProgress.checkLengthReset(event, args);
                break;
            default:
                opNotFound(event);
        }
    }

    private void checkIsChannelOwner(CommandEvent event) {
        owners.put("Slender", 395646613973893121L);
        owners.put("ShadowLink", 467172524434784257L);

        if(ownerExist(event))
        {
            isSlender(event);
            isShadowLink(event);
        }
        else
        {
            notOwner();
        }
    }

    private void isSlender(CommandEvent event) {
        if(event.getAuthor().getIdLong() == owners.get("Slender"))
        {
            channels.remove("3");
        }
    }

    private void isShadowLink(CommandEvent event) {
        if(event.getAuthor().getIdLong() == owners.get("ShadowLink"))
        {
            channels.remove("1");
        }
    }


    private boolean ownerExist(CommandEvent event) {
        return owners.containsKey(event.getAuthor().getName());
    }


    private void notOwner() {

        channels.remove("1");
        channels.remove("2");
        channels.remove("3");
    }


    private void opNotFound(CommandEvent event) {
        EmbedBuilder embed = new EmbedBuilder();

        embed.setTitle("La operación que ingreso no existe");
        embed.setDescription(event.getAuthor().getAsMention());
        embed.setColor(0xff0606);

        embed.addField("Documentación:",
                "https://github.com/Slender300/Horrorhelper",
                false
        );

        event.reply(embed.build());

    }

    /*
    *  No args error
    */
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
