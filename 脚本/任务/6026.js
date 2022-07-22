/* ==================
 脚本类型:  任务	    
 脚本版权：游戏创意
 联系扣扣：12384161
 =====================
 */
 var status = -1;

function start(mode, type, selection) {
	qm.forceStartQuest();//开始任务
	qm.dispose();
}

function end(mode, type, selection) {
	qm.forceCompleteQuest(6026);
	qm.forceCompleteQuest();//完成任务
	var jobid=qm.getPlayer().getJob();
	if(jobid==1000||jobid==1111||jobid==1100||jobid==1110||jobid==1211||jobid==1210||jobid==1200||jobid==1311||jobid==1310||jobid==1300||jobid==1411||jobid==1410||jobid==1400||jobid==1511||jobid==1510||jobid==1500||jobid==2000||jobid==2100||jobid==2110||jobid==2111||jobid==2112){
			 
	if(jobid==2000||jobid==2100||jobid==2110||jobid==2111||jobid==2112){
			qm.teachSkill(20001007,3,3);//战神
		    qm.dispose();
		}else{
            qm.teachSkill(10001007,3,3);//骑士团
		    qm.dispose();
		}
		
		}else{
            qm.teachSkill(1007,3,3);//冒险家
		    qm.dispose();
		
		}
	
	qm.dispose();
}