function enter(pi) {
    var em = pi.getEventManager("HorntailBattle");
    var ema = pi.getEventManager("ChaosHorntail");
    if (em != null) {
        var prop = em.getProperty("preheadCheck");

        if (prop != null && prop.equals("0")) {
            //pi.mapMessage(6, "闇黑龍王吼了一聲，你必須殺死闇黑龍王的左頭顱，才能進入下一關。")
            em.setProperty("preheadCheck", "1");
        }
    }
    if (ema != null) {
        var prop = ema.getProperty("preheadCheck");

        if (prop != null && prop.equals("0")) {
            //pi.mapMessage(6, "混沌闇黑龍王吼了一聲，你必須殺死混沌闇黑龍王的左頭顱，才能進入下一關。")
            ema.setProperty("preheadCheck", "1");
        }
    }
}