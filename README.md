# auto-machine
玩游戏自动机

目前实现了弹弹堂玩游戏自动机。

弹弹堂状态机：

就绪->准备发射->发射中->发射完毕->就绪

程序检测到ready变为就绪状态。

程序在就绪状态按下空格，是准备发射状态。

准备发射状态蓄力，释放空格，变为发射中状态。

发射中状态会变为发射完毕状态。

...检测到ready变为就绪状态。

用java注解来描述：
@States(
    {
        @State(name = "READY"),
        @State(name = "PRE_SHOOT"),
        @State(name = "SHOOTING"),
        @State(name = "SUF_SHOOT")
    })
@Transitions({
    //就绪 -> 准备发射 on 按下空格
    @Transit(from = "READY", to = "PRE_SHOOT", on = "PRE_SHOOT", callMethod = "fromReadyToPreShootOnPreShoot"),
    @Transit(from = "READY", to = "READY", on = "READY", callMethod = "fromReadyToReadyOnReady"),

    //准备发射 -> 发射中 on 释放空格
    @Transit(from = "PRE_SHOOT", to = "SHOOTING", on = "SHOOT", callMethod = "fromPreShootToShootingOnShoot"),

    //发射中 -> 发射完成
    @Transit(from = "SHOOTING", to = "SUF_SHOOT", on = "SHOOT_DONE_BY_MAN", callMethod = "fromShootingToSufShootOnShootDoneByMan"),
    @Transit(from = "SHOOTING", to = "SUF_SHOOT", on = "SHOOT_DONE", callMethod = "fromShootingToSufShootOnShootDone"),

    //发射完成 -> 就绪
    @Transit(from = "SUF_SHOOT", to = "READY", on = "READY_BY_MAN", callMethod = "fromSufShootToReadyOnReadyByMan"),
    @Transit(from = "SUF_SHOOT", to = "READY", on = "READY", callMethod = "fromSufShootToReadyOnReady")
})
@StateMachineParameters(stateType = DandanTangState.class, eventType = DandanTangEvent.class, contextType = Context.class)

