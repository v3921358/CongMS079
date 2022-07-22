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

            cm.sendOk("感谢使用推广系统！");
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
           cm.sendGetNumber("请填写推广码。\r\n（推广码为推广人的推广码。如果你不知道，请让他告诉你）\r\n#b\r\n↓请填写正确，如果不正确。将无法继续使用推广功能！↓",0,1,214755885);
           
            }
            else if(status == 1){
                yqm = selection;
                cm.sendYesNo("你填写的推广码为：#r#e"+yqm+"#n  #k\r\n\r\n#e如果是无效邀请码，账号将进行无法使用邀请码功能！\r\n\r\n#n------------------------------------------#r是否确认？");
            }else if(status == 2){
                if(yqm == cm.getPlayer().getAccountID()){
                    cm.sendOk("请不要输入你自己的邀请码！");
                    cm.dispose();
                }else{
                cm.sendOk("推广码："+yqm+"。已经写入");
                cm.写入推广人ID(""+yqm+"");
                cm.dispose();
          
                }
                }    
}
}


