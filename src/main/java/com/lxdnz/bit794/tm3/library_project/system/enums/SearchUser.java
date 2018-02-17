package com.lxdnz.bit794.tm3.library_project.system.enums;

public enum SearchUser {

    ANY,NAME,USERNAME;

    private static String string;

    SearchUser() {}

    @Override
    public String toString() {
        switch(this) {
            case ANY: {
                string = "Any Match";
                break;
            }
            case NAME: {
                string = "Real Name";
                break;
            }
            case USERNAME: {
                string = "Username";
                break;
            }
        }
        return string;
    }
}
