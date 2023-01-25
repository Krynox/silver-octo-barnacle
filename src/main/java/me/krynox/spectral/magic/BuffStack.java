package me.krynox.spectral.magic;

public class BuffStack {
    private final AbstractBuff buff;
    private int ticksRemaining;

    public BuffStack(AbstractBuff buff, int duration) {
        this.buff = buff;
        this.ticksRemaining = duration;
    }

    public AbstractBuff getBuff() {
        return buff;
    }

    public int getTicksRemaining() {
        return ticksRemaining;
    }

    public void decTicksRemaining() {
        ticksRemaining--;
    }
}
