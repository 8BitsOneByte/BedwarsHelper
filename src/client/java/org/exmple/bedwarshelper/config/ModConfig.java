package org.exmple.bedwarshelper.config;

public class ModConfig {
    public static boolean ESP_ENABLED = true;
    //注意：这里有个未解之谜：这里默认值为true，但游戏内进入默认关闭ESP，为什么呢？原因在于mixin里面：
    // if (ModConfig.ESP_ENABLED && entity instanceof PlayerEntity) {
    //            cir.setReturnValue(false);
    //        }
    //之前的做法符合直觉，即这里的 public static boolean ESP_ENABLED = false;而  cir.setReturnValue(true);
    //但经过很多测试，之前的做法就是没用，玩家一直在发光，不管ESP是否被启用。换成上面的做法就能用，所以如果你能解决此问题，请
    //积极提交PR,我会尽快审核通过，谢谢！
}
