
var status = 0;
var jobName;
var job;

function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == -1) {
        cm.sendOk("�����ܺ�Ŷ~~�����ı��뷨�ǵ���ʱ�����ҡ�ף����ˣ�");
        cm.dispose();
    } else {
        if (mode == 1)
            status++;
        else
            status--;
        if (status == 0) {
            cm.sendNext("�ˣ����� #bս��תְ��#k �ҿ��԰��������תְŶ~~��");
        } else if (status == 1) {
            if(cm.getJob() >= 2000){
                cm.sendNext("��~~ս��ս��������ְҵŶ~�Һܸ���Ϊ�����Ŷ������");
                status = 163;
                return;
            }
              else {
				cm.sendNext("ֻΪսͯ����Ŷ������");
                cm.dispose();
            }

        }     else if (status == 154) {
            
            cm.sendSimple("ֻ��ս��ſ���ʹ�ÿ���תְ ");


      
            cm.sendYesNo("�����ѡ��Ŷ��ȷ��Ҫ��Ϊһ�� #b[" + jobName + "] #k��"); 
        } else if (status == 156) {
                cm.changeJob(job);
                cm.sendOk("תְ�ɹ������Ͷ�����������ǿ���ʱ��ǵ�������Ŷ��");
				if (job == 2100) {
				cm.teachSkill(21000000,0,10); //ì����ǿ��
				cm.teachSkill(21001001,0,15); //ս������
				cm.teachSkill(21000002,0,20); //˫���ػ�
				cm.teachSkill(21001003,0,20); //����ì
				}
                cm.serverNotice("[תְϵͳ]: ��ϲ [" + cm.getPlayer().getName() + "] ��NPC������Ա ����תְ�ɹ���");
                cm.dispose();
            
        } else if (status == 161) {
            if(cm.getJob() == 1000 && cm.getPlayer().getLevel()>=10){
                cm.sendSimple("�������㻹��һ��������,��ѡ��һ���ʺ��Լ���ְҵ��!#b\r\n#L0#����ʿ#l #L1#����ʿ#l #L2#����ʹ��#l #L3#ҹ����#l #L4#��Ϯ��#l#k");
            }else if(parseInt(cm.getJob() / 100) >10 && cm.getPlayer().getLevel()>=30 && cm.getJob()%100 == 0){
                cm.sendYesNo("�����ȷ��Ҫ���еڶ���תְ����");
            }else if(parseInt(cm.getJob() / 100) >10 && cm.getPlayer().getLevel()>=70 && cm.getJob()%10 == 0){
                cm.sendYesNo("�����ȷ��Ҫ���е�����תְ����");
            }else{
                cm.sendOk("��Ŀǰ����������ʹ���ҵķ���Ŷ!");
                cm.dispose();
            }
        }  else if (status == 164) {
            if(cm.getJob() == 2000 && cm.getPlayer().getLevel() >=10){
                cm.sendYesNo("ս��ս������\r\n�������㻹��һ��սͯ,��ȷ��Ҫ���е�һ��תְ��");
            } else if(cm.getJob() == 2100 && cm.getPlayer().getLevel() >=30) {
                cm.sendYesNo("ս��ս�������Ƿ����500����,�ҿ��԰������еڶ���תְŶ��");
            } else if(cm.getJob() == 2110 && cm.getPlayer().getLevel() >=70){
                cm.sendYesNo("ս��ս�������Ƿ����1000����,�ҿ��԰������е�����תְŶ��");
            } else if(cm.getJob() == 2111 && cm.getPlayer().getLevel() >=120) {
                cm.sendYesNo("ս��ս�������Ƿ����2000����,�ҿ��԰������е��Ĵ�תְŶ��");
            } else if(cm.getJob() == 2112 && cm.getPlayer().getLevel() >120) {
                cm.sendOk("���Ѿ���������е�תְ�������������Ͱɣ���");
            } else {
                cm.sendOk("������Ŀǰ���������һ�����Ϊ������Ŷ�����Ͱɣ�");
                cm.dispose();
            }
        } else if (status == 165) {
            if(cm.getJob() == 2000 && cm.getPlayer().getLevel() >=10){
                cm.changeJob(2100);
                //cm.gainItem(1142129,1);
                //cm.gainItem(1442077,1);
                //cm.gainItem(2000022,50);
                //cm.gainItem(2000023,50);
				cm.teachSkill(21000000,0,10); //ì����ǿ��
				cm.teachSkill(21001001,0,20); //ս������
				cm.teachSkill(21000002,0,20); //˫���ػ�
				cm.teachSkill(21001003,0,20); //����ì
                cm.serverNotice("[תְϵͳ]: ��ϲ [" + cm.getPlayer().getName() + "] ��NPC��ս��תְ�� ����תְΪս��ְҵ��");
                cm.sendOk("תְ�ɹ������Ͷ�����������ǿ���ʱ��ǵ�������Ŷ��");
            } else if(cm.getJob() == 2100 && cm.getPlayer().getLevel() >=30){
				
				if(cm.getMeso() < 5000000) {
                cm.sendOk("��Ǹ���Ľ�Ҳ���500���������������");
                cm.dispose();}
                else{
                cm.changeJob(2110);
                cm.gainMeso(-5000000);
                cm.gainItem(1442078,1);
				cm.teachSkill(21100000,0,20); //��׼ì
				cm.teachSkill(21100001,0,20); //�����ػ�
				cm.teachSkill(21100002,0,30); //ս��ͻ��
                                // cm.teachSkill(21101003,0,20); //��ѹ
				cm.teachSkill(21100004,0,20); //��������
				cm.teachSkill(21100005,0,20); //������Ѫ
                cm.serverNotice("[תְϵͳ]: ��ϲ [" + cm.getPlayer().getName() + "] ��NPC��ս��תְ�ٻ���500���� �������ս��2��תְ��");
				
                cm.sendOk("תְ�ɹ������Ͷ�����������ǿ���ʱ��ǵ�������Ŷ��");
				}
            } else if(cm.getJob() == 2110 && cm.getPlayer().getLevel() >=70){
				
				if(cm.getMeso() < 10000000) {
                cm.sendOk("��Ǹ���Ľ�Ҳ���1000���������������");
                cm.dispose();}
                else{
                cm.gainMeso(-10000000);
                cm.getPlayer().gainAp(5);
                cm.changeJob(2111);
				cm.teachSkill(21110000,0,20); //����ǿ��
				cm.teachSkill(21111001,0,20); //���ɻ���
				cm.teachSkill(21110002,0,20); //ȫ���ӻ�
				cm.teachSkill(21110003,0,30); //�ռ�Ͷ��
				cm.teachSkill(21110004,0,30); //��Ӱ����
				cm.teachSkill(21111005,0,20); //��ѩì
				cm.teachSkill(21110006,0,20); //����
				//cm.teachSkill(21110007,0,0); //ȫ���ӻ�
				//cm.teachSkill(21110008,0,0); //ȫ���ӻ�
               // cm.teachSkill(21120009,0,0); //ս��֮��
               // cm.teachSkill(21120010,0,0); //ս��֮��

                cm.sendOk("תְ�ɹ������Ͷ�����������ǿ���ʱ��ǵ�������Ŷ��");
            }
            }else if(cm.getJob() == 2111 && cm.getPlayer().getLevel() >=120){
				
				if(cm.getMeso() < 20000000) {
                cm.sendOk("��Ǹ���Ľ�Ҳ���2000���������������");
                cm.dispose();}
                else{
		        cm.gainMeso(-20000000);
                //cm.gainItem(1142132,1);
                cm.getPlayer().gainAp(5);
				cm.teachSkill(21121000,0,10); //ð�յ���ʿ
				cm.teachSkill(21120001,0,10); //��������
				cm.teachSkill(21120002,0,10); //ս��֮��
               // cm.teachSkill(21120009,0,0); //ս��֮��
               // cm.teachSkill(21120010,0,0); //ս��֮��
				cm.teachSkill(21121003,0,10); //ս�����־
				//cm.teachSkill(21120004,0,10); //���ز���
				//cm.teachSkill(21120005,0,10); //��������
                                // cm.teachSkill(21120006,0,10); //��ʯ�ǳ�
				// cm.teachSkill(21120007,0,10); //ս��֮��
				cm.teachSkill(21121008,0,5); //��ʿ����־
                cm.changeJob(2112);
				
				
				
                cm.sendOk("תְ�ɹ���ϣ�����Ժ��ð��֮·˳����");
            }
			}
            cm.dispose();
        } else {
            cm.dispose();
        }  

    }
}
