package cs.apps.obg.domain;

/**
 * Created by d1jun on 2018-02-01.
 */

public class UserVO {
    private String e_mail;
    private String nick_name;

    public UserVO() {
    }

    public UserVO(String e_mail, String nick_name) {
        this.e_mail = e_mail;
        this.nick_name = nick_name;
    }

    public String getE_mail() {
        return e_mail;
    }

    public void setE_mail(String e_mail) {
        this.e_mail = e_mail;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }
}

