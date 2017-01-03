package org.harmony.test.javaee.jpa.query;


public class LogicBondListener implements BondListener {

    @Override
    public void bondAdded(Bond bond) {
        if (bond instanceof Like) {
            ((Like) bond).sqlValue = "%" + bond.getValue() + "%";
            return ;
        }
        if(bond instanceof In){
            ((In)bond).sqlValue = toInSQL(bond.getValue());
            return ;
        }
    }

    protected String toInSQL(Object value) {
        if (value.getClass().isArray()) {
            Object[] vs = ((Object[]) value);
            StringBuffer sb = new StringBuffer();
            for (Object v : vs) {
                sb.append(",").append("'").append(v).append("'");
            }
            return sb.toString().length() > 0 ? sb.toString().substring(1) : "'" + value + "'";
        }
        return "'" + value + "'";
    }

}
