package io.github.kr8gz.playerstatistics.mixin;

import com.google.common.collect.ImmutableMap;
import io.github.kr8gz.playerstatistics.access.ServerStatHandlerAccess;
import io.github.kr8gz.playerstatistics.database.Statistics;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.stat.ServerStatHandler;
import net.minecraft.stat.Stat;
import net.minecraft.stat.StatHandler;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(ServerStatHandler.class)
public abstract class ServerStatHandlerMixin extends StatHandler implements ServerStatHandlerAccess {
    //? if >=1.21.11
    @Shadow @Final private java.nio.file.Path path;
    //? if <1.21.11
    // @Shadow @Final private java.io.File file;

    @Unique private final Map<Stat<?>, Integer> changedStats = new Object2IntOpenHashMap<>();

    @Inject(method = "setStat", at = @At("TAIL"))
    private void setStat(PlayerEntity player, Stat<?> stat, int value, CallbackInfo ci) {
        changedStats.put(stat, value);
    }

    @Inject(method = "save()V", at = @At("HEAD"))
    private void save(CallbackInfo ci) {
        Statistics.launchStatsUpdate((ServerStatHandler) (Object) this);
    }

    @Override
    public String getStatsFileName() {
        //? if >=1.21.11
        return path.getFileName().toString();
        //? if <1.21.11
        // return file.getName();
    }

    @Override
    public Map<Stat<?>, Integer> takeChangedStats() {
        var map = ImmutableMap.copyOf(changedStats);
        changedStats.clear();
        return map;
    }
}
