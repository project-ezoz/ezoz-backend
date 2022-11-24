package ezoz.backend_ezoz.domain.journal.entity;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum JournalType {
    EZ("EZ"), OZ("OZ");

    private final String value;

    JournalType(String value) {
        this.value = value;
    }

    public static boolean isJournalType(String type) {
        List<JournalType> collect = Arrays.stream(JournalType.values())
                .filter(journalType -> journalType.name().equals(type))
                .collect(Collectors.toList());

        return collect.size() != 0;
    }

    public static JournalType from(String type) {
        return JournalType.valueOf(type.toUpperCase());
    }


}
