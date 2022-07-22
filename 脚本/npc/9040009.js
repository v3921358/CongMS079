/* 
 * @Author Lerk
 * 
 * Gatekeeper, Sharenian: Door to the Sharenian Castle (990000300)
 * 
 * Guild Quest - stage 1
 */


var status = -1;
var stage;

function start() {
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == 1) {
        status++;
    } else {
        status--;
    }
    var eim = cm.getEventInstance();
    if (eim == null) {
        cm.warp(990001100);
		cm.dispose();
    } else {
        if (eim.getProperty("leader").equals(cm.getName())) {
            if (cm.getMap().getReactorByName("statuegate").getState() > 0) {
                cm.sendOk("Proceed.");
                cm.safeDispose();
            } else {
                if (status == 0) {
                    if (eim.getProperty("stage1status") == null || eim.getProperty("stage1status").equals("waiting")) {
                        if (eim.getProperty("stage1phase") == null) {
                            stage = 1;
                            eim.setProperty("stage1phase", stage);
                        } else {
                            stage = parseInt(eim.getProperty("stage1phase"));
                        }
                        if (stage == 1) {
                            cm.sendOk("在這個挑戰，我將顯示在身邊的這些雕像的分身來考驗你。");
                        } else {
                            cm.sendOk("我現在提出一個更困難的題目考考你。\r\n祝你好運！！")
                        }
                    } else if (eim.getProperty("stage1status").equals("active")) {
                        stage = parseInt(eim.getProperty("stage1phase"));
                        if (eim.getProperty("stage1combo").equals(eim.getProperty("stage1guess"))) {
                            if (stage == 3) {
                                cm.getMap().getReactorByName("statuegate").hitReactor(cm.getClient());
                                cm.sendOk("好樣的，請繼續下一階段測驗。");
                                cm.showEffect(true, "quest/party/clear");
                                cm.playSound(true, "Party1/Clear");
                                var prev = eim.setProperty("stage1clear", "true", true);
                                if (prev == null) {
                                    cm.gainGP(45);
                                }
                            } else {
                                cm.sendOk("很好!你已經完成了一部分的測試，但是還有測試如果準備好隨時找我。");
                                eim.setProperty("stage1phase", stage + 1);
                                cm.mapMessage("你已經完成了一部分 " + stage + " 的測驗了。");
                            }

                        } else {
                            cm.sendOk("你已經失敗了這次的測驗。");
                            cm.mapMessage("你已經失敗了這次的測驗。");
                            eim.setProperty("stage1phase", "1")
                        }
                        eim.setProperty("stage1status", "waiting");
                        cm.safeDispose();
                    } else {
                        cm.sendOk("請稍等。");
                        cm.safeDispose();
                    }
                } else if (status == 1) {
                    var reactors = getReactors();
                    var combo = makeCombo(reactors);
                    /*/var reactorString = "Debug: Reactors in map: ";
                                                for (var i = 0; i < reactors.length; i++) {
                                                        reactorString += reactors[i] + " ";
                                                }
                                                cm.playerMessage(reactorString);
                                                reactorString = "Debug: Reactors in combo: ";
                                                for (var i = 0; i < combo.length; i++) {
                                                        reactorString += combo[i] + " ";
                                                }
                                                cm.playerMessage(reactorString);*/
                    cm.mapMessage("請等待完成。");

                    var delay = 5000;
                    for (var i = 0; i < combo.length; i++) {
                        cm.getMap().getReactorByOid(combo[i]).delayedHitReactor(cm.getClient(), delay + 3500 * i);
                    }
                    eim.setProperty("stage1status", "display");
                    eim.setProperty("stage1combo", "");
                    cm.dispose();
                }
            }

        } else {
            cm.sendOk("我需要你的領導者來和我談話。");
            cm.safeDispose();
        }
    }
}

//method for getting the statue reactors on the map by oid
function getReactors() {
    var reactors = new Array();

    var iter = cm.getPlayer().getMap().getAllReactorsThreadsafe().iterator();
    while (iter.hasNext()) {
        var mo = iter.next();
        if (!mo.getName().equals("statuegate")) {
            reactors.push(mo.getObjectId());
        }
    }

    return reactors;
}

function makeCombo(reactors) {
    var combo = new Array();

    while (combo.length < (stage + 3)) {
        var chosenReactor = reactors[Math.floor(Math.random() * reactors.length)];
        //cm.log("Debug: Chosen Reactor " + chosenReactor)
        var repeat = false;

        if (combo.length > 0) {
            for (var i = 0; i < combo.length; i++) {
                if (combo[i] == chosenReactor) {
                    repeat = true;
                    //cm.log("Debug: repeat reactor: " + chosenReactor);
                    break;
                }
            }
        }

        if (!repeat) {
            //cm.log("Debug: unique reactor: " + chosenReactor);
            combo.push(chosenReactor);
        }
    }

    return combo;
}