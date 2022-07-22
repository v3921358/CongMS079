/*
Stage 3: Exit Door - Guild Quest

@Author Lerk
*/

function enter(pi) {
    if (pi.getMap().getReactorByName("watergate").getState() == 1 || (pi.getPlayer().getEventInstance() != null && pi.getPlayer().getEventInstance().getProperty("stage3clear") != null && pi.getPlayer().getEventInstance().getProperty("stage3clear").equals("true"))) {
        pi.warp(990000600);
        return true;
    } else {
        pi.playerMessage("門尚未開啟。");
        return false;
    }
}