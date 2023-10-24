package com.swp391.maid4uni.enums;

/**
 * The type Api params.
 */
public class API_PARAMS {
    /**
     * The constant API_VERSION.
     */
    public static final String API_VERSION = "/api/v1";


    /********************************************************
     *                     ACCOUNT APIs                     *
     ********************************************************/


    /**
     * The constant LOGIN.
     */
    public static final String LOGIN_BY_USERNAME = "/login";
    /**
     * The constant REGISTER.
     */
    public static final String REGISTER = "/register";

    /**
     * The constant GET_ACCOUNT_LIST.
     */
    public static final String GET_ACCOUNT_LIST = "/get-account-list";

    /**
     * The constant GET_MANAGER_LIST.
     */
    public static final String GET_MANAGER_LIST = "/get-manager-list";

    /**
     * The constant GET_STAFF_LIST.
     */
    public static final String GET_STAFF_LIST = "/get-staff-list";

    /**
     * The constant GET_CUSTOMER_LIST.
     */
    public static final String GET_CUSTOMER_LIST = "/get-customer-list";

    /**
     * The constant UPDATE_ACCOUNT_INFO.
     */
    public static final String UPDATE_ACCOUNT_INFO = "/update-account-info";

    /**
     * The constant UPDATE_ROLE.
     */
    public static final String UPDATE_ROLE = "/update-role";

    /********************************************************
     *                    FEEDBACK APIs                     *
     */
    public static final String GET_ALL_FEEDBACK_LIST = "/get-all-feedback-list";

    /**
     * The constant GET_FEEDBACK_BY_RECEIVER_ID.
     */
    public static final String GET_FEEDBACK_BY_RECEIVER_ID = "/get-feedback-by-receiver-id/{id}";

    /**
     * The constant GET_FEEDBACK_BY_SENDER_ID.
     */
    public static final String GET_FEEDBACK_BY_SENDER_ID = "/get-feedback-by-sender-id/{id}";

    /**
     * The constant GET_ALL_PACKAGE.
     */
    public static final String GET_ALL_PACKAGE = "/get-all-package";

    /**
     * The constant CREATE_PACKAGE.
     */
    public static final String CREATE_PACKAGE = "/create-package";

    /**
     * The constant DELETE_PACKAGE.
     */
    public static final String DELETE_PACKAGE = "/delete-package/{id}";

    /**
     * The constant UPDATE_PACKAGE.
     */
    public static final String UPDATE_PACKAGE = "/update-package/{id}";

    /**
     * The constant CREATE_SERVICE.
     */
    public static final String CREATE_SERVICE = "/create-service";
    public static final String GET_ALL_SERVICE = "/get-all-service";
}

