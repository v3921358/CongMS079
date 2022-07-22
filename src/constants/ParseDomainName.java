package constants;

import java.net.UnknownHostException;
import java.net.InetAddress;

public class ParseDomainName
{
    InetAddress myServer;
    String domainName;
    
    public ParseDomainName(final String domainName) {
        this.myServer = null;
        this.domainName = null;
        this.domainName = domainName;
    }
    
    public InetAddress getServerIP() {
        try {
            this.myServer = InetAddress.getByName(this.domainName);
        }
        catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return this.myServer;
    }
}
