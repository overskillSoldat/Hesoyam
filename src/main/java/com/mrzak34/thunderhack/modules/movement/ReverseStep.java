package com.mrzak34.thunderhack.modules.movement;

import com.mrzak34.thunderhack.Thunderhack;
import com.mrzak34.thunderhack.events.EventPreMotion;
import com.mrzak34.thunderhack.modules.Module;
import com.mrzak34.thunderhack.setting.Setting;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ReverseStep extends Module {
    public ReverseStep() {
        super("ReverseStep", "ReverseStep", Category.MOVEMENT);
    }


    private boolean Field292 = true;
    private boolean Field293 = false;
    


    private Setting<Mode> mode = this.register (new Setting<>("Mode", Mode.Motion));
    
    public enum Mode {
        Timer, Motion
    }
    public Setting<Float> timer = register(new Setting("Timer", 3.0F, 1F, 10.0F));
    public Setting< Boolean > anyblock = this.register ( new Setting <> ( "AnyBlock" , false) );


    @SubscribeEvent
    public void onEntitySync(EventPreMotion eventPlayerUpdateWalking) {
        if (Thunderhack.moduleManager.getModuleByClass(PacketFly.class).isEnabled() || Thunderhack.moduleManager.getModuleByClass(PacketFly2.class).isEnabled()) {
            return;
        }
        IBlockState iBlockState = mc.world.getBlockState(new BlockPos(mc.player.posX, mc.player.posY, mc.player.posZ).down(2));
        IBlockState iBlockState2 = mc.world.getBlockState(new BlockPos(mc.player.posX, mc.player.posY, mc.player.posZ).down(3));
        IBlockState iBlockState3 = mc.world.getBlockState(new BlockPos(mc.player.posX, mc.player.posY, mc.player.posZ).down(4));
        if (!(iBlockState.getBlock() != Blocks.BEDROCK && iBlockState.getBlock() != Blocks.OBSIDIAN && !(this.anyblock.getValue()) || mc.player.isInLava() || mc.player.isInWater() || mc.player.isInWeb || mc.player.isElytraFlying() || mc.player.capabilities.isFlying)) {
            if (mc.player.onGround && this.mode.getValue() == Mode.Motion) {
                mc.player.motionY -= 1.0;
            }
            if (!(this.mode.getValue() != Mode.Timer || !this.Field293 || mc.player.onGround || !(mc.player.motionY < -0.1) || this.Field292 || mc.player.isInLava() || mc.player.isInWater() || mc.player.isInWeb || mc.player.isElytraFlying() || mc.player.capabilities.isFlying)) {
                Thunderhack.TICK_TIMER = ((this.timer.getValue()));
                this.Field292 = true;
            }
        } else if (!(iBlockState2.getBlock() != Blocks.BEDROCK && iBlockState2.getBlock() != Blocks.OBSIDIAN && !this.anyblock.getValue() || mc.player.isInLava() || mc.player.isInWater() || mc.player.isInWeb || mc.player.isElytraFlying() || mc.player.capabilities.isFlying)) {
            if (mc.player.onGround && this.mode.getValue() == Mode.Motion) {
                mc.player.motionY -= 1.0;
            }
            if (!(this.mode.getValue() != Mode.Timer || !this.Field293 || mc.player.onGround || !(mc.player.motionY < -0.1) || this.Field292 || mc.player.isInLava() || mc.player.isInWater() || mc.player.isInWeb || mc.player.isElytraFlying() || mc.player.capabilities.isFlying)) {
                Thunderhack.TICK_TIMER = this.timer.getValue();
                this.Field292 = true;
            }
        } else if (!(iBlockState3.getBlock() != Blocks.BEDROCK && iBlockState3.getBlock() != Blocks.OBSIDIAN && !this.anyblock.getValue() || mc.player.isInLava() || mc.player.isInWater() || mc.player.isInWeb || mc.player.isElytraFlying() || mc.player.capabilities.isFlying)) {
            if (mc.player.onGround && this.mode.getValue() == Mode.Motion) {
                mc.player.motionY -= 1.0;
            }
            if (!(this.mode.getValue() != Mode.Timer || !this.Field293 || mc.player.onGround || !(mc.player.motionY < -0.1) || this.Field292 || mc.player.isInLava() || mc.player.isInWater() || mc.player.isInWeb || mc.player.isElytraFlying() || mc.player.capabilities.isFlying)) {
                Thunderhack.TICK_TIMER = this.timer.getValue();
                this.Field292 = true;
            }
        }
        if (this.Field292 && (mc.player.onGround || mc.player.isInLava() || mc.player.isInWater() || mc.player.isInWeb || mc.player.isElytraFlying() || mc.player.capabilities.isFlying)) {
            this.Field292 = false;
            Thunderhack.TICK_TIMER = (1.0f);
        }
        this.Field293 = mc.player.onGround;
    }

}
