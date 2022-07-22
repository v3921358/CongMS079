
function enter(pi) {
    var em = pi.getEventManager("HorntailBattle");
    var ema = pi.getEventManager("ChaosHorntail");
    if (em != null) {
        var map = pi.getMapId();
        var d1 = pi.getMap(240060000);
        var d2 = pi.getMap(240060100);
        if (map == 240060000) {
            if (d1.getAllMonstersThreadsafe().size() <= 0 && em.getProperty("state") >= 2) {
                em.warpAllPlayer(240060000, 240060100);
                //		em.setProperty("state", "3");
            } else {
                pi.playerMessage("這個門還沒開起。");
            }
        } else if (map == 240060100) {
            if (d2.getAllMonstersThreadsafe().size() <= 0 && em.getProperty("state") == 3) {
                em.warpAllPlayer(240060100, 240060200);
                em.setProperty("state", "4");
            } else {
                pi.playerMessage("這個門還沒開起。");
            }
        }
    }
    if (ema != null) {
        var map = pi.getMapId();
        var d1 = pi.getMap(240060001);
        var d2 = pi.getMap(240060101);
        if (map == 240060001) {
            if (d1.getAllMonstersThreadsafe().size() <= 0 && ema.getProperty("state") >= 2) {
                ema.warpAllPlayer(240060001, 240060101);
                //		ema.setProperty("state", "3");
            } else {
                pi.playerMessage("這個門還沒開起。");
            }
        } else if (map == 240060101) {
            if (d2.getAllMonstersThreadsafe().size() <= 0 && ema.getProperty("state") == 3) {
                ema.warpAllPlayer(240060101, 240060201);
                ema.setProperty("state", "4");
            } else {
                pi.playerMessage("這個門還沒開起。");
            }
        }
    }
}
