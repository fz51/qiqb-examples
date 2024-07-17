package net.qiqb.usr.customer.domain.type;

import lombok.Getter;

@Getter
public class Email {
    private String val;

    public Email() {

    }

    public Email(String val) {
        this.val = val;
        check();
    }


    public void setVal(String val) {
        this.val = val;
        check();
    }

    /**
     * 这里简单的做一个邮件验证。非空且包含@符号
     */
    private void check() {
        if (this.val == null || this.val.isEmpty()) {
            throw new IllegalArgumentException("邮件不能为空");
        }
        if (!this.val.contains("@")) {
            throw new IllegalArgumentException("邮件格式不合法");
        }
    }

    @Override
    public String toString() {
        return val;
    }
}
