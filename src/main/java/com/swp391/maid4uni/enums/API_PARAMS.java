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
    public static final String GET_ACCOUNT_LIST = "/get-account-list/{page}";

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
    public static final String UPDATE_ACCOUNT_INFO = "/update-account-info/{accountId}";

    /**
     * The constant UPDATE_ROLE.
     */
    public static final String UPDATE_ROLE = "/update-role";

    /********************************************************
     *                    FEEDBACK APIs                     *
     ********************************************************/
    public static final String CREATE_FEEDBACK = "/create-feedback";

    /**
     * The constant GET_ALL_FEEDBACK_LIST.
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
    public static final String GET_ACCOUNT_RATING = "/get-account-rating/{id}";

    /********************************************************
     *                    PACKAGE APIs                      *
     ********************************************************/


    /**
     * The constant GET_ALL_PACKAGE.
     */
    public static final String GET_ALL_PACKAGE = "/get-all-package/{page}";

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

    /********************************************************
     *                     SERVICE APIs                     *
     ********************************************************/

    /**
     * The constant CREATE_SERVICE.
     */
    public static final String CREATE_SERVICE = "/create-service";

    /**
     * The constant GET_ALL_SERVICE.
     */
    public static final String GET_ALL_SERVICE = "/get-all-service/{page}";

    /**
     * The constant UPDATE_SERVICE.
     */
    public static final String UPDATE_SERVICE = "/update-service/{id}";

    /**
     * The constant DELETE_SERVICE.
     */
    public static final String DELETE_SERVICE = "/delete-service/{id}";

    /********************************************************
     *                      ORDER APIs                      *
     ********************************************************/

    public static final String GET_ORDER_LIST_BY_CUSTOMER = "/get-order-list-by-customer/{id}";

    public static final String CREATE_ORDER = "/create-order";

    /********************************************************
     *                     PAYMENT APIs                     *
     ********************************************************/
    public static final String CREATE_VNPAY_PAYMENT = "/create-vnpay-payment";
    public static final String GET_VNPAY_PAYMENT = "/get-vnpay-payment/{orderId}";
    public static final String VNPAY_PAYURL = "https://sandbox.vnpayment.vn/paymentv2/vpcpay.html";

    public static final String UPDATE_TASK = "/update-task/{id}";

    public static final String GET_BEST_REVIEWS = "/get-best-review";
    public static final String UPDATE_ORDER_STATUS = "/update-order-status";
    public static final String GET_A_PACKAGE = "/get-a-package/{id}";
    public static final String GET_PACKAGE_BY_CATEGORY = "/get-package-by-category/{id}&{page}";
    public static final String DELETE_ACCOUNT_BY_ID = "/delete-account-by-id/{id}";
    public static final String GET_MOST_POPULAR_PACKAGES = "/get-most-popular-packages";
    public static final String GET_ALL_ORDER = "/get-all-order/{page}";
    public static final String GET_TASK_BY_STAFF_ID = "/get-task-by-staff-id/{id}";
    public static final String UPDATE_ORDER_DETAIL = "/update-order-detail";
    public static final String GET_ORDER_DETAIL_BY_ORDERID = "/get-order-detail-by-orderId/{id}";
    public static final String GET_TOTAL_PRICE_BY_MONTH = "/get-total-price-by-month";
    public static final String GET_TOTAL_BY_MONTH_OF_PACKAGE = "/get-total-by-month-of-package";
    public static final String GET_ACCOUNT_INFO_BY_ID = "/get-account-info-by-id";
}

