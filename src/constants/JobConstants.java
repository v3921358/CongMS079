package constants;

import server.ServerProperties;

public class JobConstants
{
    public static final boolean enableJobs = true;
    public static final int jobOrder = 184;
    
    public enum LoginJob
    {
        冒險家(0), 
        皇家騎士團(1), 
        狂狼勇士(2);
        
        private final int jobType;
        private final boolean enableCreate = true;
        
        private LoginJob(final int jobType) {
            this.jobType = jobType;
        }
        
        public int getJobType() {
            return this.jobType;
        }
        
        public boolean enableCreate() {
            return (boolean)Boolean.valueOf(ServerProperties.getProperty("JobEnableCreate" + this.jobType, String.valueOf(true)));
        }
        
        public void setEnableCreate(final boolean info) {
            if (info) {
                ServerProperties.removeProperty("JobEnableCreate" + this.jobType);
                return;
            }
            ServerProperties.setProperty("JobEnableCreate" + this.jobType, String.valueOf(info));
        }
    }
}
