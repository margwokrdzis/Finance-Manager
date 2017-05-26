package appzaliczenie.financemanager;


public interface DatabaseOperations {
    String LOGIN = "login";
    String CREATE_ACCOUNT = "createAccount";
    String CREATE_COMPANY = "cateCompany";

    String CREATE_CLIENT = "createClient";
    String GET_CLIENT_LIST = "getClients";

    String CHANGE_CLIENT_DATA = "changeClient";
    String CHANGE_COMPANY_DATA = "changeCompany";

    String ADD_OUTGOING = "addOutgoing";
    String ADD_INCOME = "addIncome";

    String DELETE_CLIENT = "deleteClient";
    String DELETE_OUTGOING = "deleteOutgoing";
    String DELETE_INCOME = "deleteIncome";

    String LOGIN_SERVICE = "http://v-ie.uek.krakow.pl/~s174753/finance/login.php";
    String CREATE_ACCOUNT_SERVICE = "http://v-ie.uek.krakow.pl/~s174753/finance/createAccount.php";
    String CREATE_COMPANY_SERVICE = "http://v-ie.uek.krakow.pl/~s174753/finance/createCompany.php";
    String CREATE_CLIENT_SERVICE = "http://v-ie.uek.krakow.pl/~s174753/finance/createClient.php";
    String GET_CLIENT_LIST_SERVICE = "http://v-ie.uek.krakow.pl/~s174753/finance/getClientList.php";
    String CHANGE_CLIENT_DATA_SERVICE = "http://v-ie.uek.krakow.pl/~s174753/finance/changeClient.php";
    String CHANGE_COMPANY_DATA_SERVICE = "http://v-ie.uek.krakow.pl/~s174753/finance/changeCompany.php";
    String ADD_OUTGOING_SERVICE = "http://v-ie.uek.krakow.pl/~s174753/finance/addOutgoing.php";
    String ADD_INCOME_SERVICE = "http://v-ie.uek.krakow.pl/~s174753/finance/addIncome.php";
    String DELETE_CLIENT_SERVICE = "http://v-ie.uek.krakow.pl/~s174753/finance/deleteClient.php";
    String DELETE_OUTGOING_SERVICE = "http://v-ie.uek.krakow.pl/~s174753/finance/deleteOutgoing.php";
    String DELETE_INCOME_SERVICE = "http://v-ie.uek.krakow.pl/~s174753/finance/deleteIncome.php";


    String SUCCESS = "success";
    String FAILED = "fail";
}
