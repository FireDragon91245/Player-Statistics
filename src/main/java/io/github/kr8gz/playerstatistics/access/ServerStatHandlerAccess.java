package io.github.kr8gz.playerstatistics.access;

import net.minecraft.stat.Stat;

import java.util.Map;

public interface ServerStatHandlerAccess {
    String getStatsFileName();

    Map<Stat<?>, Integer> takeChangedStats();
}
