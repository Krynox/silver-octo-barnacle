package me.krynox.spectral.networking;

import java.util.Optional;

public enum SpectralKeybindAction {
    TOGGLE_MAGIC_MODE(0),
    CAST_0(1),
    CAST_1(2),
    CAST_2(3),
    CAST_3(4),
    CAST_4(5),
    CAST_5(6);

    private final int id;
    SpectralKeybindAction(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    static Optional<SpectralKeybindAction> fromId(int id) {
        switch (id) {
            case 0 -> { return Optional.of(TOGGLE_MAGIC_MODE);}
            case 1 -> { return Optional.of(CAST_0);}
            case 2 -> { return Optional.of(CAST_1);}
            case 3 -> { return Optional.of(CAST_2);}
            case 4 -> { return Optional.of(CAST_3);}
            case 5 -> { return Optional.of(CAST_4);}
            case 6 -> { return Optional.of(CAST_5);}
            default -> { return Optional.empty();}
        }
    }
}
