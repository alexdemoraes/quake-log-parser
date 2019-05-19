package com.llabs.quakeparser.service;

import com.llabs.quakeparser.web.model.KillViewModel;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service("lineProcessor")
public class LineProcessorImpl implements ILineProcessor {

    public static String TIME_PATTERN_MMMM_SS = "\\s*(\\d{1,4}:\\d{2}) ";
    public static Pattern START_GAME = Pattern.compile(TIME_PATTERN_MMMM_SS + "InitGame:.+");
    public static Pattern FINISH_GAME = Pattern.compile(TIME_PATTERN_MMMM_SS + "ShutdownGame:");
    public static Pattern KILL = Pattern.compile(".+Kill: .*: (.+) killed (.+) by (.+)");

    public String getStart(String line) {
        Matcher matcher = START_GAME.matcher(line);

        if (matcher.matches()) {
            return matcher.group(1);
        }
        return null;
    }

    public KillViewModel getKill(String line) {
        Matcher matcher = KILL.matcher(line);
        if (matcher.matches()) {
            String player = matcher.group(1);
            String killed = matcher.group(2);
            String mode = matcher.group(3);
            return new KillViewModel(player, killed, mode);
        }
        return null;
    }

    public String getFinish(String line) {
        Matcher matcher = FINISH_GAME.matcher(line);
        if (matcher.matches()) {
            return matcher.group(1);
        }
        return null;
    }

}
