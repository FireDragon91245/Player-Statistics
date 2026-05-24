package io.github.kr8gz.playerstatistics.extensions

import net.minecraft.item.ItemStack
import net.minecraft.text.ClickEvent
import net.minecraft.text.HoverEvent
import net.minecraft.text.Text

object TextEvents {
    fun showText(text: Text): HoverEvent {
        //? if <1.21.11 {
        /*return HoverEvent(HoverEvent.Action.SHOW_TEXT, text)
        *///?} else
        return HoverEvent.ShowText(text)
    }

    fun runCommand(command: String): ClickEvent {
        //? if <1.21.11 {
        /*return ClickEvent(ClickEvent.Action.RUN_COMMAND, command)
        *///?} else
        return ClickEvent.RunCommand(command)
    }

    fun suggestCommand(command: String): ClickEvent {
        //? if <1.21.11 {
        /*return ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, command)
        *///?} else
        return ClickEvent.SuggestCommand(command)
    }

    fun showItem(stack: ItemStack): HoverEvent {
        //? if <1.21.11 {
        /*return HoverEvent(HoverEvent.Action.SHOW_ITEM, HoverEvent.ItemStackContent(stack))
        *///?} else
        return HoverEvent.ShowItem(stack)
    }
}


