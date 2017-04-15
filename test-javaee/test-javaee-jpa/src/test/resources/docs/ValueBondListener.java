package org.harmony.test.javaee.jpa.query;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ValueBondListener implements BondListener{

    @Override
    public void bondAdded(Bond bond) {
        if (((AbstractBond) bond).isDate) {
            Date dateValue;
            if (bond.getValue() instanceof Calendar) {
                dateValue = ((Calendar) bond.getValue()).getTime();
            } else {
                dateValue = (Date) bond.getValue();
            }
            String dateStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dateValue);
            /*
             * mysql
             */
            //((AbstractBond) bond).sqlValue = "str_to_date('" + dateStr + "','%Y-%m-%d %k:%i:%s')";
            /*
             * oracle
             */
            ((AbstractBond) bond).sqlValue = "to_date('" + dateStr + "','yyyy-mm-dd hh24:mi:ss')";
        }
        
    }

}
