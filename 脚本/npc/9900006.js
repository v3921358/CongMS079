
var status = 0;
var jobName;
var job;

function start() {
 //cm.givePartyExp(611111500);//�����������˾���
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
            cm.sendNext("�ˣ����� #b��ʿ��תְ��#k �ҿ��԰��������תְŶ~~��");
        } else if (status == 1) {
            if(cm.getJob() >= 1000 && cm.getJob() <= 1520){
                cm.sendNext("�ۣ�������ʿ�ŵ�һԱ���Һܸ���Ϊ�����Ŷ������");
                status = 160;
                return;
            }else {
				
				cm.sendNext("��������ʿ�ţ���ֻΪ��ʿ�ŷ���Ŷ������");
				cm.dispose();
			}
            
            

        }     else if (status == 154) {
           
            cm.sendSimple("��ô��~~������ѡ��һ������ϲ����ְҵ�ɣ�#b\r\n#L6#����ʿ#l #L7#����ʿ#l #L8#����ʹ��#l #L9#ҹ����#l ");


        } else if (status == 155) {
            if (selection == 6) {
                jobName = "����ʿ";
                job = 1100;
            }if (selection == 7) {
                jobName = "����ʿ";
                job = 1200;
            }if (selection == 8) {
                jobName = "����ʹ��";
                job = 1300;
            }if (selection == 9) {
                jobName = "ҹ����";
                job = 1400;
            }if (selection == 10) {
                jobName = "��Ϯ��";
                job = 1500;
            }
            cm.sendYesNo("�����ѡ��Ŷ��ȷ��Ҫ��Ϊһ�� #b[" + jobName + "] #k��"); 
        }  else if (status == 161) {
            if(cm.getJob() == 1000 && cm.getPlayer().getLevel()>=10){
                cm.sendSimple("�������㻹��һ��������,��ѡ��һ���ʺ��Լ���ְҵ��!#b\r\n#L0#����ʿ#l #L1#����ʿ#l #L2#����ʹ��#l #L3#ҹ����#l #L4#��Ϯ��#l#k");
            }
			else if(parseInt(cm.getJob() / 100) >10 && cm.getPlayer().getLevel()>=30 && cm.getJob()%100 == 0){
                cm.sendYesNo("�����ȷ��Ҫ����500���ң����еڶ���תְ����");
            }else if(parseInt(cm.getJob() / 100) >10 && cm.getPlayer().getLevel()>=70 && cm.getJob()%10 == 0){
                cm.sendYesNo("�����ȷ��Ҫ����3000���ң����е�����תְ����");
            }else{
                cm.sendOk("��Ŀǰ����������ʹ���ҵķ���Ŷ!\r\n��ʿ��תְ�ȼ��ֱ��ǣ�10��-30��-70��\r\n��ʿ���������ת��û����תŶ��");
                cm.dispose();
            }
        } else if (status == 162) {
            if(cm.getJob() == 1000 && cm.getPlayer().getLevel()>=10){
                if (selection == 0) {
                    job = 1100;
                } else if (selection == 1) {
                    job = 1200;
                } else if (selection == 2) {
                    job = 1300;
                } else if (selection == 3) {
                    job = 1400;
                } else if (selection == 4) {
                    job = 1500;
                }
                cm.changeJob(job);
                
                
                cm.sendOk("תְ�ɹ������Ͷ�����������ǿ���ʱ��ǵ�������Ŷ��");
            } else if(parseInt(cm.getJob() / 100) >10 && cm.getPlayer().getLevel()>=30 && cm.getJob()%100 == 0){
				
				if(cm.getMeso() < 5000000) {
                cm.sendOk("��Ǹ���Ľ�Ҳ���500���������������");
                cm.dispose();}
                else{
				cm.gainMeso(-5000000);
                cm.changeJob(cm.getJob()+10);
                cm.sendOk("תְ�ɹ������Ͷ�����������ǿ���ʱ��ǵ�������Ŷ��");
				}
            } else if(parseInt(cm.getJob() / 100) >10 && cm.getPlayer().getLevel()>=70 && cm.getJob()%10 == 0){
               if(cm.getMeso() < 30000000) {
                cm.sendOk("��Ǹ���Ľ�Ҳ���3000���������������");
                cm.dispose();}
                else{ 
				cm.gainMeso(-30000000);
                cm.getPlayer().gainAp(5);
                cm.changeJob(cm.getJob()+1);
                cm.sendOk("תְ�ɹ���ϣ�����Ժ��ð��֮·˳��!");
				}
            }
            cm.dispose();
        }    else {
            cm.dispose();
        }  

    }
}
