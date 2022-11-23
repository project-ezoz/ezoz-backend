package ezoz.backend_ezoz.domain.journal.entity;

import lombok.Getter;

public enum JournalType {
    EZ("ez_story"),
    OZ("oz_story");

    @Getter
    private final String value;

    JournalType(String value) {
        this.value = value;
    }
}
