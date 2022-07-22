function enter(pi) {
    if (pi.getMap().getAllMonstersThreadsafe().size() != 0) {
        pi.playerMessage(5, "請把憤怒的瑪哈給殺死");
        return false;
    } else {
        pi.warp(140000000, 0);
        return true;
    }
}