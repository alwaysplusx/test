package org.moon.test.model;

import java.util.Calendar;
import java.util.Date;

public class Model {

    private Calendar birthday;
    private Date submitDate;

    public Calendar getBirthday() {
        return birthday;
    }

    public void setBirthday(Calendar birthday) {
        this.birthday = birthday;
    }

    public Date getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(Date submitDate) {
        this.submitDate = submitDate;
    }

    @Override
    public String toString() {
        return birthday + ",\n" + submitDate + "";
    }
}
