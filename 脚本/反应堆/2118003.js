function act() {
    rm.getReactor().forceTrigger();
    rm.getReactor().delayedDestroyReactor(1000);
    rm.mapMessage("雷克斯被召喚了出來。");
    rm.spawnMonster(9300281);
}