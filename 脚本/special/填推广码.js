function start() {
    status = -1;

    action(1, 0, 0);
}
function action(mode, type, selection) {
    if (mode == -1) {
        cm.dispose();
    }
    else {
        if (status >= 0 && mode == 0) {

            cm.sendOk("��лʹ���ƹ�ϵͳ��");
            cm.dispose();
            return;
        }
        if (mode == 1) {
            status++;
        }
        else {
            status--;
        }
        if (status == 0) {
           cm.sendGetNumber("����д�ƹ��롣\r\n���ƹ���Ϊ�ƹ��˵��ƹ��롣����㲻֪���������������㣩\r\n#b\r\n������д��ȷ���������ȷ�����޷�����ʹ���ƹ㹦�ܣ���",0,1,214755885);
           
            }
            else if(status == 1){
                yqm = selection;
                cm.sendYesNo("����д���ƹ���Ϊ��#r#e"+yqm+"#n  #k\r\n\r\n#e�������Ч�����룬�˺Ž������޷�ʹ�������빦�ܣ�\r\n\r\n#n------------------------------------------#r�Ƿ�ȷ�ϣ�");
            }else if(status == 2){
                if(yqm == cm.getPlayer().getAccountID()){
                    cm.sendOk("�벻Ҫ�������Լ��������룡");
                    cm.dispose();
                }else{
                cm.sendOk("�ƹ��룺"+yqm+"���Ѿ�д��");
                cm.д���ƹ���ID(""+yqm+"");
                cm.dispose();
          
                }
                }    
}
}


