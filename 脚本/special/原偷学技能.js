//���ܽű�   ���ߣ���Ҷ   QQ��1848350048
//�нӿ���һ����
var ���ͼ�� = "#fUI/CashShop/CashItem/0#";
var ����Ԫ��=888;
var MaxLevel=0;
var newskillId=Array( 
14101004,
14111000,
12101003,
2311003
);

function start() {
    status = -1;

    action(1, 0, 0);
}


function action(mode, type, selection) {
    if (status == 0 && mode == 0) {
        cm.dispose();
        return;
    }
    if (mode == 1) {
        status++;
    } else {
        status--;
    }
        if(status==0)
		{

				text="";
				text +=  "    \t"+�ʺ�+"  #e#d ͵ ѧ �� �� #k#n  #r  "+�ʺ�+"#b#k#n\r\r\n";
				text += "#d��ඣ���û����Ľ�����˵ļ����أ�����У�����888Ԫ���Ϳ���͵͵�Ľ���ѧϰ��Ŷ.ͬ�����ܵڶ�,����ѧϰ�����!#k\r\n"
				
				text+="#r���ѧϰ�ü��ܣ������ڼ����趨F12����ȡ����ʹ�ã�#k\r\n"
				text+="#rѧϰӰ����,���Ի��˫���˺�.#k\r\n"
				text+="#r"+���ͼ��+"��ǰԪ����"+cm.getmoneyb()+"  #k\r\n"
				for(i=0;i<newskillId.length;i++)//
				{
		        text+=" #L"+newskillId[i]+"##s"+newskillId[i]+"##q"+newskillId[i]+"#  [#rѧϰ#k] ��ǰ�ȼ�:"+cm.getPlayer().getSkillLevel(newskillId[i])+" - ��ߵȼ�:"+cm.getPlayer().getMasterLevel(newskillId[i])+"#l \r\n"
				
				}
				cm.sendSimple(text);
			
	    }
		else if(status ==1){
		  
	        var jobid=cm.getPlayer().getJob();
			if (jobid==412){
			
				    cm.sendOk("���ã����Ҫ�����ר��Ĳſ���Ŷ!");
					cm.dispose();
				    return;
			}
			
			if(selection>10000){
				var getLevelskill=0;
				var nowSkilLevel=cm.getPlayer().getSkillLevel(selection);
				var MaxLevel=cm.getPlayer().getMasterLevel(selection);
				var palyerid=cm.getPlayer().getId();
				
				if(nowSkilLevel==MaxLevel&&nowSkilLevel!=0){
					
					cm.�¼����ϼ���(palyerid,88,1,selection,nowSkilLevel);
					cm.sendOk("�ü����Ѿ�ѧ����!");
					cm.dispose();
				    return;
					}
					
					if(nowSkilLevel+10>MaxLevel&&MaxLevel!=0){
						getLevelskill=MaxLevel;
						
					}else{
						getLevelskill=nowSkilLevel+10;
					}
					if(MaxLevel!=0){
					����Ԫ�� =����Ԫ��/2;//�ڶ�����,���Ѱ��
					}
					
					if(cm.getmoneyb() >= ����Ԫ��){
						
					cm.setmoneyb(-����Ԫ��);
					MaxLevel=cm.getPlayer().getMasterLevel(selection);
					
					text="";
					
					cm.�¼����ϼ���(palyerid,88,1,selection,nowSkilLevel+10);
					text+="�ɹ�ѧϰ#s"+selection+"##q"+selection+"#  Max��"+MaxLevel
					cm.sendOk(text);
					cm.dispose();
					return;
					}
					else
					{
						cm.sendOk("Ԫ�������޷�ѧϰ�ü��ܣ�");
						oversend();
					}
				
			}
		}
	
}

function oversend()
{
	cm.dispose();
	return;
}
var �ʺ� ="#fEffect/ItemEff/1071085/effect/walk1/2#";
