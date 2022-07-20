package models;


import base.User;


public class Cashier  extends User {

    public Cashier(String id, String name, String age) {
        super(id, name, age);
    }

    public Cashier() {
        super();
    }

    @Override
    public void setAge(String age) {
        super.setAge(age);
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public void setId(String id) {
        super.setId(id);
    }
}
