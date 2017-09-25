package ir.moke.realm.basic.model.to;

import java.util.Date;

public class OnlineUser {
    private String username ;
    private String remoteAddress ;
    private Date date ;

    public OnlineUser(String username, String remoteAddress, Date date) {
        this.username = username;
        this.remoteAddress = remoteAddress;
        this.date = date;
    }

    public OnlineUser() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRemoteAddress() {
        return remoteAddress;
    }

    public void setRemoteAddress(String remoteAddress) {
        this.remoteAddress = remoteAddress;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "OnlineUser{" +
                "username='" + username + '\'' +
                ", remoteAddress='" + remoteAddress + '\'' +
                ", date=" + date +
                '}';
    }
}
