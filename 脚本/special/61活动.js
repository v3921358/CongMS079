/*���������qq1500663066��327321366*/



var ����è ="#fUI/ChatBalloon/37/n#";
var è�� =  "#fUI/ChatBalloon/37/ne#";
var è�� =  "#fUI/ChatBalloon/37/nw#";
var �� =    "#fUI/ChatBalloon/37/e#";
var �� =    "#fUI/ChatBalloon/37/w#";
var ����è ="#fUI/ChatBalloon/37/s#";
var è���� ="#fUI/ChatBalloon/37/se#";
var è���� ="#fUI/ChatBalloon/37/sw#";
var �ʹڰ� ="#fUI/GuildMark/Mark/Etc/00009004/16#";
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
            var tex2 = "";
            var text = "";
            for (i = 0; i < 10; i++) {
                text += "";
            }
			text += ""
			text += "                  #k"+�ʹڰ�+" #r#e#w �� һ �� �� #n#k "+�ʹڰ�+"\r\n\r\n";
			text += "  "+è��+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+è��+"\r\n";
            text += "        #dͯ��̣ܶ�δ����Զ �����Ҳ�ã�����Ҳ��\r\n";
			text += "             ������һ�����ռ�Сʱ�����߰�#k\r\n\r\n";
			text += "\t   [#v4000109##r#c4000109##k/601]   [#v4000476##r#c4000476##k/601]   [#v4000101##r#c4000101##k/601]\r\n\r\n"
			text += "\t         [#v4000127##r#c4000127##k/601]      [#v4000100##r#c4000100##k/6]\r\n"
            
			text += "\t  #L66##k��ȡ[#i1102354:#]#l   #L666##k����[#v1102354#]Ϊȫ���Թ���40#l\r\n\r\n"
			text += "\t            �����ʱ�䵽6��3�Ž���\r\n"
            cm.sendSimple(text);
		}
		
		else if (selection == 66) {
			if(cm.haveItem(1102354,3)){
				cm.sendOk("\t���Ѿ�������һ�����ˣ�һ��ֻ����������");
				cm.dispose();
				return;
				}else{
			if(!cm.haveItem(4000109,601) ){
			cm.sendOk("\t#v4000109#����601������ȥ�ռ�Ӵ��");
			cm.dispose();
			return;
			}
			if(!cm.haveItem(4000476,601) ){
			cm.sendOk("\t#v4000476#����601������ȥ�ռ�Ӵ��");
			cm.dispose();
			return;
			}
			if(!cm.haveItem(4000101,601) ){
			cm.sendOk("\t#v4000101#����601������ȥ�ռ�Ӵ��");
			cm.dispose();
			return;
			}
			if(!cm.haveItem(4000127,601) ){
			cm.sendOk("\t#v4000127#����601������ȥ�ռ�Ӵ��");
			cm.dispose();
			return;
			}
			if(!cm.haveItem(4000100,6) ){
			cm.sendOk("\t#v4000100#����6������ȥ�ռ�Ӵ������Ӵ��");
			cm.dispose();
			return;
			}
			if (!cm.checkNumSpace(1, 1)) {
			cm.sendOk("����װ�����ռ䲻��1��");
			cm.dispose();
			return;
		    }
			cm.gainItem(4000109,-601);
			cm.gainItem(4000476,-601);
			cm.gainItem(4000101,-601);
			cm.gainItem(4000127,-601);
			cm.gainItem(4000100,-6);
			cm.gainItem(1102354,1);
			Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "����һ���" + " : " + "[" + cm.getChar().getName() + "]ͨ���ռ�ȫ��ߣ�����˾�����һ���磡")); 
			cm.dispose();
			}	
			
			}else if (selection == 666) {
			if(!cm.haveItem(1102354,3)){
				cm.sendOk("\t����һ�������粻����������ȥ���룬�������ס�");
				cm.dispose();
				return;
				}else{
			
			if (!cm.checkNumSpace(1, 1)) {
			cm.sendOk("����װ�����ռ䲻��1��");
			cm.dispose();
			return;
		    }
			cm.gainItem(1102354,-3);
			cm.gainItem(1102354, 40, 40, 40, 40, 0, 0, 40, 40, 0, 0, 0, 0, 0, 0);
			Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "����һ���" + " : " + "[" + cm.getChar().getName() + "]ͨ���ռ�������һ���磬����˽��׾�����һ���磡")); 
			cm.dispose();
			}	
			
			}
		
		
		

		   else if (status == 2) {
            if (beauty == 1) {
				var zliang = cm.getPlayer().getItemQuantity(4001126, false);
                if (zliang > 0){
					cm.removeAll(4001126);
					cm.gainMeso(8000*zliang);					
							
                    cm.sendOk("�һ��ɹ������һ���:[#r"+(zliang)+"#k] ����");
					cm.worldMessage(6,"[���һ�]����� "+cm.getName()+" Ŭ����ש,���������һ��ˣ�"+(zliang*8000)+" ��ҡ�");
					cm.dispose();
                } else {
                    cm.sendOk("������Ʒ���㣬�޷��һ���");
                    cm.dispose()
                }            		
            }
			
        }
    }
}