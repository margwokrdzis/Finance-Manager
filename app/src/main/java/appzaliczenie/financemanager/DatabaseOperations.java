package appzaliczenie.financemanager;


public interface DatabaseOperations {
    String LOGIN = "login";
    String CREATE_ACCOUNT = "createAccount";
    String CREATE_COMPANY = "createCompany";
    String CREATE_CLIENT = "createClient";
    String UPDATE_COMPANY_DATA = "changeCompany";
    String ADD_OUTGOING = "addOutgoing";
    String ADD_INCOME = "addIncome";
    String DELETE_CLIENT = "deleteClient";

    String LOGIN_SERVICE = "http://v-ie.uek.krakow.pl/~s174753/finance/login.php";
    String CREATE_ACCOUNT_SERVICE = "http://v-ie.uek.krakow.pl/~s174753/finance/createAccount.php";
    String CREATE_COMPANY_SERVICE = "http://v-ie.uek.krakow.pl/~s174753/finance/createCompany.php";
    String CREATE_CLIENT_SERVICE = "http://v-ie.uek.krakow.pl/~s174753/finance/createClient.php";
    String GET_CLIENT_LIST_SERVICE = "http://v-ie.uek.krakow.pl/~s174753/finance/getClientList.php";
    String UPDATE_COMPANY_DATA_SERVICE = "http://v-ie.uek.krakow.pl/~s174753/finance/changeCompany.php";
    String ADD_OUTGOING_SERVICE = "http://v-ie.uek.krakow.pl/~s174753/finance/addOutgoing.php";
    String ADD_INCOME_SERVICE = "http://v-ie.uek.krakow.pl/~s174753/finance/addIncome.php";
    String DELETE_CLIENT_SERVICE = "http://v-ie.uek.krakow.pl/~s174753/finance/deleteClient.php";
    String GET_COMPANY_ID_SERVICE = "http://v-ie.uek.krakow.pl/~s174753/finance/getCompanyID.php";
    String GET_INCOME_LIST_SERVICE = "http://v-ie.uek.krakow.pl/~s174753/finance/getIncomes.php";
    String GET_OUTGOINGS_LIST_SERVICE = "http://v-ie.uek.krakow.pl/~s174753/finance/getOutgoings.php";
    String GET_COMPANY_INFO_SERVICE = "http://v-ie.uek.krakow.pl/~s174753/finance/getCompanyInfo.php";
    String GET_CLIENT_INFO_SERVICE = "http://v-ie.uek.krakow.pl/~s174753/finance/getClientInfo.php";

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

    //client tags
    String TAG_CLIENT = "client";
    String TAG_ID_CLIENT = "id_client";
    String TAG_EMAIL = "mail";
    String TAG_PHONE_NUMBER = "phone_number";
    String TAG_NIP = "nip";
    String TAG_CITY = "city";
    String TAG_POSTAL_CODE = "postal_code";
    String TAG_STREET = "street";
    String TAG_BUILDING_NUMBER = "building_number";
    String TAG_DOOR_NUMBER = "door_number";


}
