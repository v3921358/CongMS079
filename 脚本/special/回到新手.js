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
        cm.sendOk("�õ�, ������׼����Ҫת�������������Ұɣ�");
        cm.dispose();
    }
    if (mode == 1) {
        status++;
    } else {
        status--;
    }
    if (status == 0) {
        cm.sendYesNo("�������������������һ����ţ���ȷ��Ҫת��24K�����֣�1�����ص����ǰ������ϸ�Ķ�����ע�����\r\n\r\n#r\r\n" +
            "1��ת���ֺ����ԭ�ȼ��������㡢���ܵ㣬������Ϣ������\r\n" +
            "2��ת���ֺ���������µ�¼���������ݻ��ң�����Ը�����#k#l");

    }else if(status == 1){
        if(cm.getPlayer().getNX() < 5000){
            cm.sendOk("��������ɬ����ȷ����5000�������˵��");
            cm.dispose();
            return;
        }
        cm.����(2,"��ϲ[" + cm.getPlayer().getName() + "]��������ʹ��ת���ֹ��ܷ����������ٻ�����ص��˽��ǰ����ϲ�ɺأ������ɼΣ���");
        //cm.gainNX(-5000);
        cm.unequipEverything(); //��װ�����
        cm.getPlayer().clearSkills(); //������
        cm.getPlayer().changeJob(0);//����ְҵ
        //cm.gainAp(5);
        cm.getPlayer().setLevel(1);
        cm.getPlayer().exp = 0;//����ֵ0
        cm.getPlayer().getStat().setDex(5);
        cm.getPlayer().getStat().setStr(5);
        cm.getPlayer().getStat().setInt(5);
        cm.getPlayer().getStat().setLuk(5);
        cm.getPlayer().getStat().setMaxHp(50);
        cm.getPlayer().getStat().setMaxMp(50);
        cm.getPlayer().resetAPSP();
        cm.getPlayer().levelUp();
        cm.getPlayer().saveToDB(false, false);
        cm.sendOk("��������ϲ�������ˣ����Ѿ������#rת����#k��\r\n\r\n#e#d");
        cm.getPlayer().fakeRelog();
        cm.dispose();
    }
}