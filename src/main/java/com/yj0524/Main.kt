package com.yj0524

import io.github.monun.kommand.*
import org.bukkit.Sound
import org.bukkit.plugin.java.JavaPlugin

class Main : JavaPlugin() {

    override fun onEnable() {
        logger.info("Plugin Enabled")

        kommandLoad()
    }

    override fun onDisable() {
        logger.info("Plugin Disabled")
    }

    fun kommandLoad() {
        kommand {
            register("gamble") {
                then("start") {
                    requires {
                        isPlayer
                    }
                    executes {
                        val item = player.inventory.itemInHand

                        if (item.type.isAir) {
                            player.sendMessage("손에 아이템을 들고 명령어를 사용해주세요.")
                        } else {
                            val random = (0..1).random()
                            if (random == 0) {
                                player.inventory.remove(item)
                                player.playSound(player.location, Sound.BLOCK_ANVIL_LAND, 1f, 1f)
                                player.sendMessage("§c도박에 실패했습니다... 아이템이 모두 사라졌습니다.")
                            } else {
                                item.amount *= 2
                                player.playSound(player.location, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f)
                                player.sendMessage("§a도박에 성공했습니다! 아이템이 2배가 되었습니다!")
                            }
                        }
                    }
                }
                then("info") {
                    requires {
                        isOp
                    }
                    executes {
                        sender.sendMessage("Plugin Name : " + pluginMeta.name)
                        sender.sendMessage("Plugin Version : " + pluginMeta.version)
                        sender.sendMessage("Plugin API Version : " + pluginMeta.apiVersion)
                    }
                }
            }
        }
    }
}
