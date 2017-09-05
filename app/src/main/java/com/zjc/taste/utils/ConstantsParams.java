package com.zjc.taste.utils;

/**
 * Created by Administrator on 2015/6/12.
 */
public class ConstantsParams {
    public static final String SYSTEM_NOT_TXT = "暂无";



    /**
     * 系统代表 1
     */
    public static final int APP_YES = 1;

    /**
     * 起始页数
     */
    public static final int PAGE_START = 0;
    /**
     * 分页加载条数
     */
    public static final int PAGE_SIZE = 20;

    /*** ########### 推送 ############*/
    /**
     * 消息_已读
     */
    public static final int NOTIFICATION_YES = 1;

    /**
     * 消息_未读
     */
    public static final int NOTIFICATION_NO = 0;

    /**
     * 推送_系统消息
     */
    public static final int NOTIFICATION_SYS = 1;
    /**
     * 推送_上转单消息
     */
    public static final int NOTIFICATION__REFERRAL_UP = 2;
    /**
     * 推送_下转单消息
     */
    public static final int NOTIFICATION__REFERRAL_DOWN = 3;


    /**
     * 推送内容类型：6.健康宣教邀请
     */
    public static final int PUSH_CONTENT_TYPE_HEALTH_PROPAGANDA = 6;

    /*** ########### 是否可用 ############*/
    /**
     * 1 是
     */
    public static final int SYSTEM_YES = 1;

    /**
     * 0 否
     */
    public static final int SYSTEM_NO = 0;


    /*** ########### 登录系统 ############*/
    /**
     * 0：注册
     */
    public static final int USER_REGISTER = 0;

    /**
     * 1：重置密码
     */
    public static final int USER_FORGET = 1;

    /**
     * 0：本平台
     */
    public static final int USER_LOGIN_PLATFORM = 0;

    /**
     * 1：外关联
     */
    public static final int USER_LOGIN_EXTERNAL = 1;

    /*** ########### 性别 ############*/
    /**
     * 0：女
     */
    public static final int USER_SEX_WOMEN = 0;

    /**
     * 1：男
     */
    public static final int USER_SEX_MEN = 1;


    /*** ########### 活动类型 ############*/
    /**
     * 1：促销
     */
    public static final int GOODS_SALES = 1;

    /**
     * 2：广告
     */
    public static final int GOODS_ADVERTISEMENT = 2;

    /**
     * 3：婚宴
     */
    public static final int GOODS_WEDDING = 3;
    /**
     * 4：商品
     */
    public static final int GOODS_SELF = 4;




    /*** ########### 转诊单状态 ############*/
    /**
     * 0发起转诊
     */
    public static final int REFERRAL_STATUS_START = 0;
    public static final String REFERRAL_STATUS_START_TXT = "发起转诊";

    /**
     * 1转诊绑定
     */
    public static final int REFERRAL_STATUS_BANDING = 1;
    public static final String REFERRAL_STATUS_BANDING_TXT = "已报到";

    /**
     * 2结束转诊
     */
    public static final int REFERRAL_STATUS_END = 2;
    public static final String REFERRAL_STATUS_END_TXT = "已接诊";

    /**
     * 3已下转
     */
    public static final int REFERRAL_STATUS_END_DOWN = 3;
    public static final String REFERRAL_STATUS_END_DOWN_TXT = "已下转";

    /**
     * 4拒收转诊
     */
    public static final int REFERRAL_STATUS_REFUSE = 4;
    public static final String REFERRAL_STATUS_REFUSE_TXT = "未接收";

    /**
     * 2 流失，作废
     */
    public static final int REFERRAL_STATUS_CANCEL = 5;
    public static final String REFERRAL_STATUS_CANCEL_TXT = "流失/作废";


    /*** ########### 作废类型 ############*/
    /**
     * 1超时
     */
    public static final int REFERRAL_CANCEL_OVERDUE = 1;
    public static final String REFERRAL_CANCEL_OVERDUE_TXT = "超时";
    /**
     * 2填错
     */
    public static final int REFERRAL_CANCEL_ERROR = 2;
    public static final String REFERRAL_CANCEL_ERROR_TXT = "填错";
    /**
     * 3病源流失
     */
    public static final int REFERRAL_CANCEL_LOSS = 3;
    public static final String REFERRAL_CANCEL_LOSS_TXT = "病源流失";
    /**
     * 10其他
     */
    public static final int REFERRAL_CANCEL_OTHER = 10;
    public static final String REFERRAL_CANCEL_OTHER_TXT = "其他";

    public static String getInvalidType(int type) {
        if (type == REFERRAL_CANCEL_OVERDUE) {
            return REFERRAL_CANCEL_OVERDUE_TXT;
        } else if (type == REFERRAL_CANCEL_ERROR) {
            return REFERRAL_CANCEL_ERROR_TXT;
        } else if (type == REFERRAL_CANCEL_LOSS) {
            return REFERRAL_CANCEL_LOSS_TXT;
        } else {
            return REFERRAL_CANCEL_OTHER_TXT;
        }
    }


    /*** ########### 加入购物车方式 ############*/
    /**
     * 覆盖
     */
    public static final int SHOP_CART_COVER = 0;

    /**
     * 累加Cumulative
     */
    public static final int SHOP_CART_CUMULATIVE = 1;




    /*** ########### 拍照图片类型 ############*/
    /**
     * 照片通知类型 头像修改
     */
    public static final int PHOTO_TYPE_USER = 1;

    /**
     * 照片通知类型 评价图片提交
     */
    public static final int PHOTO_TYPE_COMMENT = 2;


    /*** ########### 订单状态 ############*/
    /**
     * 0 未送
     */
    public static final int APPLY_RECIPE_NO = 0;
    public static final String APPLY_RECIPE_NO_TXT = "未送";

    /**
     * 1 已送
     */
    public static final int APPLY_RECIPE_YES = 1;
    public static final String APPLY_RECIPE_YES_TXT = "订单已送达";

    /**
     * 2 取消
     */
    public static final int APPLY_RECIPE_CANCEL = 2;
    public static final String APPLY_RECIPE_CANCEL_TXT = "已取消";

    /*** ########### 会诊类型 ############*/
    /**
     * 1 紧急
     */
    public static final int CONSULT_TYPE_SPECIAL = 1;
    public static final String CONSULT_TYPE_SPECIAL_TXT = "紧急";

    /**
     * 2 普通
     */
    public static final int CONSULT_TYPE_COMMON = 2;
    public static final String CONSULT_TYPE_COMMON_TXT = "普通";

    /*** ########### 会诊状态 ############*/

    /**
     * 0：全部
     */
    public static final int CONSULT_STATUS_ALL = -1;
    public static final String CONSULT_STATUS_ALL_TXT = "全部";

    /**
     * 0：待接受
     */
    public static final int CONSULT_STATUS_START = 0;
    public static final String CONSULT_STATUS_START_TXT = "待接受";

    /**
     * 1:待会诊
     */
    public static final int CONSULT_STATUS_RECEIVE = 1;
    public static final String CONSULT_STATUS_RECEIVE_TXT = "待会诊";

    /**
     * 2：已会诊
     */
    public static final int CONSULT_STATUS_FINISH = 2;
    public static final String CONSULT_STATUS_FINISH_TXT = "已会诊";

    /**
     * 3：不接受
     */
    public static final int CONSULT_STATUS_REFUSE = 3;
    public static final String CONSULT_STATUS_REFUSE_TXT = "不接受";

    /**
     * 3：取消
     */
    public static final int CONSULT_STATUS_CANCEL = 4;
    public static final String CONSULT_STATUS_CANCEL_TXT = "取消";

    public static String getConsultTextByStatus(int type) {
        if (type == CONSULT_STATUS_START) {
            return CONSULT_STATUS_START_TXT;
        } else if (type == CONSULT_STATUS_RECEIVE) {
            return CONSULT_STATUS_RECEIVE_TXT;
        } else if (type == CONSULT_STATUS_FINISH) {
            return CONSULT_STATUS_FINISH_TXT;
        } else if (type == CONSULT_STATUS_CANCEL) {
            return CONSULT_STATUS_CANCEL_TXT;
        } else {
            return "";
        }
    }

    /*** ########### 消息类型 ############*/
    /**
     * 1.转诊
     */
    public static final int PUSH_CONTENT_TYPE_REFERRAL = 1;
    public static final String PUSH_CONTENT_TYPE_REFERRAL_TEXT = "转诊消息提醒";
    /**
     * 2.会诊
     */
    public static final int PUSH_CONTENT_TYPE_CONSULT = 2;
    public static final String PUSH_CONTENT_TYPE_CONSULT_TEXT = "会诊消息提醒";

    /**
     * 3.健康教育邀请
     */
    public static final int PUSH_CONTENT_TYPE_EDUCATION = 3;
    public static final String PUSH_CONTENT_TYPE_EDUCATION_TEXT = "中医服务提醒";

    /**
     * 1.预约挂号提醒
     */
    public static final int PUSH_TYPE_APPOINTMENT_DOCTOR = 1;

    /**
     * 2.在线报名提醒
     */
    public static final int PUSH_TYPE_ON_LINE_REGIST = 2;

    /**
     * 3.双向转诊提醒
     */
    public static final int PUSH_TYPE_DOWN_UP_REFERRAL = 3;

    /**
     * 4.中医服务提醒
     */
    public static final int PUSH_TYPE_CHINESE_DOCTOR = 4;

    /**
     * 5体检报名提醒
     */
    public static final int PUSH_TYPE_CHECK_UP_REGIST = 5;

    /**
     * 11.会诊消息提醒
     */
    public static final int PUSH_TYPE_CONSULT = 11;


    /*** ########### 协议类型 ############*/
    /**
     * 0：家庭医生
     */
    public static final String FAMILY_XY_GENERAL = "0";
    /**
     * 1：居家养老
     */
    public static final String FAMILY_XY_ENDOWMENT = "1";
    /**
     * 2全部
     */
    public static final String FAMILY_XY_ALL = "2";

    public static final String FAMILY_TREATMENT_WAIT = "待服务";
    public static final String FAMILY_TREATMENT_FINISH = "已服务";


    public static final String PATIENT_CUOSHI_ADD = "1";//干预措施新建
    public static final String PATIENT_CUOSHI_DESC = "2";//干预测试详情


    /***
     * ########### 权限类型 ############
     */

    public static final String IS_DOWN_ADD_POWER = "isDownAddPower";//下转权限
    public static final String IS_DOWN_CONFIRM_POWER = "isDownConfirmPower";//下转确认权限
    public static final String IS_DOWN_CAUSE_POWER = "isDownCausePower";//下转拒收转诊权限
    public static final String IS_DOWN_GIVEN_POWER = "isDownGivenPower";//下转指定接收机构权限
    public static final String IS_DOWN_DEL_POWER = "isDownDelPower";//下转作废权限
    public static final String IS_DOWN_LOSS_POWER = "isDownLossPower";//下转流失权限

    public static final String IS_UP_ADD_POWER = "isUpAddPower";//上转权限
    public static final String IS_UP_DEL_POWER = "isUpDelPower";//上转作废权限
    public static final String IS_UP_LOSS_POWER = "isUpLossPower";//上转流失权限
    public static final String IS_UP_CONFIRM_POWER = "isUpConfirmPower";//上转确认权限
    public static final String IS_UP_BIND_POWER = "isUpBindPower";//上转报到权限

    public static final String IS_PATIENT_LIST_POWER = "isPatientListPower";//患者查询权限
    public static final String IS_PATIENT_ADD_POWER = "isPatientAddPower";//患者添加权限
    public static final String IS_ACCEPT_POWER = "isAcceptPower";//会诊接受权限
    public static final String IS_ADD_POWER = "isAddPower";//会诊发起权限
    public static final String IS_RECEIVE_FIND_POWER = "isReceiveFindPower";//接收查询权限

    public static final String IS_BIND_CONFIRM = "isBindConfirm";//转诊单是否必须先报道，再确认:0否;1是
    public static final String IS_SAVE_UPDATE = "isSaveUpdate";//保存后是否可修改：0:否;1是
    public static final String IS_PRINT_UPDATE = "isPrintUpdate";////打印后是否可修改：0:否;1是

}
