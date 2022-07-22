function start() {
    //p = cm.getChar();
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == -1) { //ExitChat
        cm.dispose();
        return;
    } else if (mode == 0) { //No
        cm.sendOk("好的, 若是你准备好要转新手了再来找我吧！");
        cm.dispose();
    }
    if (mode == 1) {
        status++;
    } else {
        status--;
    }
    if (status == 0) {
        cm.sendYesNo("哈哈，你真的是万中无一的天才，你确定要转成24K纯新手（1级）回到解放前吗？请仔细阅读以下注意事项：\r\n\r\n#r\r\n" +
            "1、转新手后仅还原等级、能力点、技能点，其它信息保留。\r\n" +
            "2、转新手后请务必重新登录，否则数据混乱，后果自负！！#k#l");

    }else if(status == 1){
        if(cm.getPlayer().getNX() < 5000){
            cm.sendOk("您囊中羞涩，请确定有5000大洋后再说吧");
            cm.dispose();
            return;
        }
        cm.喇叭(2,"恭喜[" + cm.getPlayer().getName() + "]在拍卖中使用转新手功能放弃了所有荣华富贵回到了解放前，可喜可贺，勇气可嘉！！");
        //cm.gainNX(-5000);
        cm.unequipEverything(); //脱装备语句
        cm.getPlayer().clearSkills(); //清理技能
        cm.getPlayer().changeJob(0);//新手职业
        //cm.gainAp(5);
        cm.getPlayer().setLevel(1);
        cm.getPlayer().exp = 0;//经验值0
        cm.getPlayer().getStat().setDex(5);
        cm.getPlayer().getStat().setStr(5);
        cm.getPlayer().getStat().setInt(5);
        cm.getPlayer().getStat().setLuk(5);
        cm.getPlayer().getStat().setMaxHp(50);
        cm.getPlayer().getStat().setMaxMp(50);
        cm.getPlayer().resetAPSP();
        cm.getPlayer().levelUp();
        cm.getPlayer().saveToDB(false, false);
        cm.sendOk("哈哈，恭喜你年轻人，你已经完成了#r转新手#k！\r\n\r\n#e#d");
        cm.getPlayer().fakeRelog();
        cm.dispose();
    }
}