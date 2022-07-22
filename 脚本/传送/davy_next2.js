function enter(pi) {
    if (pi.getMap().getAllMonstersThreadsafe().size() == 0) {
	pi.warp(925100300,0); //next
    } else {
	pi.playerMessage(5, "你需要杀掉这里所有的怪物，才可以通关.");
    }
}