package appzaliczenie.financemanager;


public interface DatabaseOperations {
    String LOGIN = "login";
    String CREATE_ACCOUNT = "createAccount";
    String CREATE_COMPANY = "createCompany";
    String CREATE_CLIENT = "createClient";
    String GET_CLIENT_LIST = "getClients";
    String GET_INCOME_LIST = "getIncomeList";
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
    String GET_COMPANY_ID_SERVICE = "http://v-ie.uek.krakow.pl/~s174753/finance/getCompanyID.php";
    String GET_INCOME_LIST_SERVICE = "http://v-ie.uek.krakow.pl/~s174753/finance/getIncomes.php";
    String GET_OUTGOINGS_LIST_SERVICE = "http://v-ie.uek.krakow.pl/~s174753/finance/getOutgoings.php";
    String GET_COMPANY_INFO_SERVICE = "http://v-ie.uek.krakow.pl/~s174753/finance/getCompanyInfo.php";

    String SUCCESS_TAG = "success";
    String ID_COMPANY_TAG = "id_company";
    String INCOMING_TAG = "incomings";
    String OUTGOING_TAG = "outgoings";
    String ID_INCOMING_TAG = "id_incomings";
    String ID_OUTGOINGS_TAG = "id_outgoings";
    String TAG_NAME = "name";
    String TAG_DATE = "date";
    String TAG_AMMOUNT = "ammount";

    //company info
    String TAG_COMPANY = "company";
    String TAG_COMPANY_NAME = "companyName";
    String TAG_COMPANY_EMAIL = "companyEmail";
    String TAG_COMPANY_PHONE_NUMBER = "companyPhoneNumber";
    String TAG_COMPANY_NIP = "companyNIP";
    String TAG_COMPANY_CITY = "companyCity";
    String TAG_COMPANY_POSTAL_CODE = "companyPostalCode";
    String TAG_COMPANY_STREET = "street";
    String TAG_COMPANY_BUILDING_NUMBER = "building_number";
    String TAG_COMPANY_DOOR_NUMBER = "door_number";


}
