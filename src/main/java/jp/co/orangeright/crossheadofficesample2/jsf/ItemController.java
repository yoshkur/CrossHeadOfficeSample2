package jp.co.orangeright.crossheadofficesample2.jsf;

import jp.co.orangeright.crossheadofficesample2.entity.Item;
import jp.co.orangeright.crossheadofficesample2.jsf.util.JsfUtil;
import jp.co.orangeright.crossheadofficesample2.jsf.util.PaginationHelper;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import jp.co.orangeright.crossheadofficesample2.ejb.CustomerFacade;
import jp.co.orangeright.crossheadofficesample2.ejb.ItemFacade;
import jp.co.orangeright.crossheadofficesample2.ejb.UserMFacade;
import jp.co.orangeright.crossheadofficesample2.entity.Schedule;
import jp.co.orangeright.crossheadofficesample2.entity.Item_;
import jp.co.orangeright.crossheadofficesample2.entity.Schedule_;
import jp.co.orangeright.crossheadofficesample2.entity.UserM_;
import jp.co.orangeright.crossheadofficesample2.jsf.customer.CustomerSearchCondition;
import jp.co.orangeright.crossheadofficesample2.jsf.item.ItemSearchCondition;
import jp.co.orangeright.crossheadofficesample2.jsf.userm.UserMSearchCondition;

@Named("itemController")
@SessionScoped
public class ItemController implements Serializable {

    private Item current;
    private DataModel items = null;
    @EJB
    private ItemFacade ejbFacade;
    @EJB
    private UserMFacade userEjb;
    @EJB
    private CustomerFacade customerEjb;
    @Inject
    private ScheduleController scheduleController;
    private Schedule schedule4Create;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    private ItemSearchCondition condition;
    private List<Integer> pageSizeList;
    private Integer pageSize = 20;
    private String userSearchReturnPage;
    @Inject
    private LoginController loginController;

    public ItemController() {
    }

    public ItemSearchCondition getCondition() {
        if (this.condition == null) {
            this.condition = new ItemSearchCondition();
            this.condition.setWorked(Boolean.FALSE);
            this.condition.setFinished(Boolean.FALSE);
            this.condition.setCancel(Boolean.FALSE);
            this.condition.setCustomerList(this.customerEjb.findAll(new CustomerSearchCondition()));
//            UserMSearchCondition uCon = new UserMSearchCondition();
//            uCon.setAsc(Boolean.TRUE);
//            uCon.setOrderBy(UserM_.roworder);
//            this.condition.setUserList(this.userEjb.findAll(uCon));
        }
        return condition;
    }

    public void setCondition(ItemSearchCondition condition) {
        this.condition = condition;
    }

    public List<Integer> getPageSizeList() {
        if (this.pageSizeList == null) {
            this.pageSizeList = new ArrayList();
            this.pageSizeList.add(5);
            this.pageSizeList.add(10);
            this.pageSizeList.add(20);
            this.pageSizeList.add(50);
            this.pageSizeList.add(100);
            this.pageSizeList.add(10000);
        }
        return pageSizeList;
    }

    public void setPageSizeList(List<Integer> pageSizeList) {
        this.pageSizeList = pageSizeList;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Item getSelected() {
        if (current == null) {
            current = new Item();
            selectedItemIndex = -1;
        }
        return current;
    }

    private ItemFacade getFacade() {
        return ejbFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(getPageSize()) {

                @Override
                public int getItemsCount() {
                    return getFacade().count(getCondition());
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}, getCondition()));
                }
            };
        }
        return pagination;
    }

    public Schedule getSchedule4Create() {
        if (this.schedule4Create == null) {
            this.scheduleController.prepareCreate();
            this.schedule4Create = this.scheduleController.getSelected();
        }
        return schedule4Create;
    }

    public void setSchedule4Create(Schedule schedule4Create) {
        this.schedule4Create = schedule4Create;
    }

    public String getUserSearchReturnPage() {
        return userSearchReturnPage;
    }

    public void setUserSearchReturnPage(String userSearchReturnPage) {
        this.userSearchReturnPage = userSearchReturnPage;
    }

    public String searchUserOnItemCreate() {
        this.setUserSearchReturnPage("/item/Create?faces-redirect=true");
        return "/userM/Search?faces-redirect=true";
    }

    public String searchUserOnItemSearch() {
        this.setUserSearchReturnPage("/item/Search?faces-redirect=true");
        return "/userM/Search?faces-redirect=true";
    }

    public String prepareSearch() {
        return "Search";
    }

    public String searchList() {
        this.getPagination().setPage(0);
        recreateModel();
        return "List";
    }

    public String appointList() {
        this.getCondition().setKeyword(null);
        this.getCondition().setCustomer(null);
        this.getCondition().setUser(null);
        this.getCondition().setFinished(Boolean.FALSE);
        this.getCondition().setAccounting(Boolean.FALSE);
        this.getCondition().setItemcd(null);
        this.getCondition().setPayment(null);
        this.getCondition().setAppoint(Boolean.FALSE);
        this.getCondition().setWorked(Boolean.FALSE);
        this.getCondition().setTurnStart(null);
        this.getCondition().setTurnEnd(null);
        this.getCondition().setCancel(Boolean.FALSE);
        this.getCondition().setOnhold(Boolean.FALSE);
        this.getCondition().setAddDateStart(null);
        this.getCondition().setAddDateEnd(null);
        this.getCondition().setUpdateDateStart(null);
        this.getCondition().setUpdateDateEnd(null);
        this.getPagination().setPage(0);
        this.setPageSize(this.getPagination().getPageSize());
        recreateModel();
        return "/item/List?faces-redirect=true";
    }

    public String appointLateList() {
        this.getCondition().setKeyword(null);
        this.getCondition().setCustomer(null);
        this.getCondition().setUser(null);
        this.getCondition().setFinished(Boolean.FALSE);
        this.getCondition().setAccounting(Boolean.FALSE);
        this.getCondition().setItemcd(null);
        this.getCondition().setPayment(null);
        this.getCondition().setAppoint(Boolean.FALSE);
        this.getCondition().setWorked(Boolean.FALSE);
        this.getCondition().setTurnStart(null);
        this.getCondition().setTurnEnd(null);
        this.getCondition().setCancel(Boolean.FALSE);
        this.getCondition().setOnhold(Boolean.FALSE);
        Calendar cal = GregorianCalendar.getInstance();
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.add(Calendar.DAY_OF_YEAR, -7);
        this.getCondition().setAddDateStart(null);
        this.getCondition().setAddDateEnd(cal.getTime());
        this.getCondition().setUpdateDateStart(null);
        this.getCondition().setUpdateDateEnd(cal.getTime());
        this.getPagination().setPage(0);
        this.setPageSize(this.getPagination().getPageSize());
        recreateModel();
        return "/item/List?faces-redirect=true";
    }

    public String appointAfterDaysList(int days) {
        this.getCondition().setKeyword(null);
        this.getCondition().setCustomer(null);
        this.getCondition().setUser(null);
        this.getCondition().setFinished(Boolean.FALSE);
        this.getCondition().setAccounting(Boolean.FALSE);
        this.getCondition().setItemcd(null);
        this.getCondition().setPayment(null);
        this.getCondition().setAppoint(Boolean.FALSE);
        this.getCondition().setWorked(Boolean.FALSE);
        this.getCondition().setTurnStart(null);
        this.getCondition().setTurnEnd(null);
        this.getCondition().setCancel(Boolean.FALSE);
        this.getCondition().setOnhold(Boolean.FALSE);
        Calendar cal = GregorianCalendar.getInstance();
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.add(Calendar.DAY_OF_YEAR, -1 * days);
        this.getCondition().setAddDateStart(cal.getTime());
        this.getCondition().setAddDateEnd(cal.getTime());
        this.getCondition().setUpdateDateStart(null);
        this.getCondition().setUpdateDateEnd(null);
        this.getPagination().setPage(0);
        this.setPageSize(this.getPagination().getPageSize());
        recreateModel();
        return "/item/List?faces-redirect=true";
    }

    public String openCountExecutedItem() {
        Calendar cal = GregorianCalendar.getInstance();
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.add(Calendar.DAY_OF_YEAR, -1);
        this.getCondition().setTurnStart(cal.getTime());
        this.getCondition().setCustomer(null);
        return this.countExecutedItem();
    }

    public String countExecutedItem() {
        this.getCondition().setKeyword(null);
//        this.getCondition().setCustomer(null);
        this.getCondition().setUser(null);
        this.getCondition().setFinished(Boolean.FALSE);
        this.getCondition().setAccounting(Boolean.FALSE);
        this.getCondition().setItemcd(null);
        this.getCondition().setPayment(null);
        this.getCondition().setAppoint(null);
        this.getCondition().setWorked(null);
//        this.getCondition().setTurnStart(null);
        this.getCondition().setTurnEnd(this.getCondition().getTurnStart());
        this.getCondition().setCancel(null);
        this.getCondition().setOnhold(null);
        this.getCondition().setAddDateStart(null);
        this.getCondition().setAddDateEnd(null);
        this.getCondition().setUpdateDateStart(null);
        this.getCondition().setUpdateDateEnd(null);
        this.getPagination().setPage(0);
        this.getPagination().setPageSize(10000);
        this.setPageSize(this.getPagination().getPageSize());
        recreateModel();
        return "/item/CountExecutedItem?faces-redirect=true";
    }

    public String openCountRegisteredItem() {
        Calendar cal = GregorianCalendar.getInstance();
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.add(Calendar.DAY_OF_YEAR, -1);
        this.getCondition().setAddDateStart(cal.getTime());
        this.getCondition().setCustomer(null);
        return this.countRegisteredItem();
    }

    public String countRegisteredItem() {
        this.getCondition().setKeyword(null);
//        this.getCondition().setCustomer(null);
        this.getCondition().setUser(null);
        this.getCondition().setFinished(Boolean.FALSE);
        this.getCondition().setAccounting(Boolean.FALSE);
        this.getCondition().setItemcd(null);
        this.getCondition().setPayment(null);
        this.getCondition().setAppoint(null);
        this.getCondition().setWorked(null);
        this.getCondition().setTurnStart(null);
        this.getCondition().setTurnEnd(null);
        this.getCondition().setCancel(null);
        this.getCondition().setOnhold(null);
//        this.getCondition().setAddDateStart(null);
        this.getCondition().setAddDateEnd(this.getCondition().getAddDateStart());
        this.getCondition().setUpdateDateStart(null);
        this.getCondition().setUpdateDateEnd(null);
        this.getPagination().setPage(0);
        this.getPagination().setPageSize(10000);
        this.setPageSize(this.getPagination().getPageSize());
        recreateModel();
        return "/item/CountRegisteredItem?faces-redirect=true";
    }

    public String todaysWorkList() {
        this.getCondition().setKeyword(null);
        this.getCondition().setCustomer(null);
        this.getCondition().setUser(null);
        this.getCondition().setFinished(Boolean.FALSE);
        this.getCondition().setAccounting(Boolean.FALSE);
        this.getCondition().setItemcd(null);
        this.getCondition().setPayment(null);
        this.getCondition().setAppoint(Boolean.TRUE);
        this.getCondition().setWorked(Boolean.FALSE);
        Calendar cal = GregorianCalendar.getInstance();
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        this.getCondition().setTurnStart(cal.getTime());
        this.getCondition().setTurnEnd(cal.getTime());
        this.getCondition().setCancel(Boolean.FALSE);
        this.getCondition().setOnhold(Boolean.FALSE);
        this.getCondition().setAddDateStart(null);
        this.getCondition().setAddDateEnd(null);
        this.getCondition().setUpdateDateStart(null);
        this.getCondition().setUpdateDateEnd(null);
        this.getPagination().setPage(0);
        this.setPageSize(this.getPagination().getPageSize());
        recreateModel();
        return "/item/List?faces-redirect=true";
    }

    public String tomorrowsWorkList() {
        this.getCondition().setKeyword(null);
        this.getCondition().setCustomer(null);
        this.getCondition().setUser(null);
        this.getCondition().setFinished(Boolean.FALSE);
        this.getCondition().setAccounting(Boolean.FALSE);
        this.getCondition().setItemcd(null);
        this.getCondition().setPayment(null);
        this.getCondition().setAppoint(Boolean.TRUE);
        this.getCondition().setWorked(Boolean.FALSE);
        Calendar cal = GregorianCalendar.getInstance();
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.add(Calendar.DAY_OF_MONTH, 1);
        this.getCondition().setTurnStart(cal.getTime());
        this.getCondition().setTurnEnd(cal.getTime());
        this.getCondition().setCancel(Boolean.FALSE);
        this.getCondition().setOnhold(Boolean.FALSE);
        this.getCondition().setAddDateStart(null);
        this.getCondition().setAddDateEnd(null);
        this.getCondition().setUpdateDateStart(null);
        this.getCondition().setUpdateDateEnd(null);
        this.getPagination().setPage(0);
        this.setPageSize(this.getPagination().getPageSize());
        recreateModel();
        return "/item/List?faces-redirect=true";
    }

    public String twoDayslatersWorkList() {
        this.getCondition().setKeyword(null);
        this.getCondition().setCustomer(null);
        this.getCondition().setUser(null);
        this.getCondition().setFinished(Boolean.FALSE);
        this.getCondition().setAccounting(Boolean.FALSE);
        this.getCondition().setItemcd(null);
        this.getCondition().setPayment(null);
        this.getCondition().setAppoint(Boolean.TRUE);
        this.getCondition().setWorked(Boolean.FALSE);
        Calendar cal = GregorianCalendar.getInstance();
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.add(Calendar.DAY_OF_MONTH, 2);
        this.getCondition().setTurnStart(cal.getTime());
        this.getCondition().setTurnEnd(cal.getTime());
        this.getCondition().setCancel(Boolean.FALSE);
        this.getCondition().setOnhold(Boolean.FALSE);
        this.getCondition().setAddDateStart(null);
        this.getCondition().setAddDateEnd(null);
        this.getCondition().setUpdateDateStart(null);
        this.getCondition().setUpdateDateEnd(null);
        this.getPagination().setPage(0);
        this.setPageSize(this.getPagination().getPageSize());
        recreateModel();
        return "/item/List?faces-redirect=true";
    }

    public String willWorkList() {
        this.getCondition().setKeyword(null);
        this.getCondition().setCustomer(null);
        this.getCondition().setUser(null);
        this.getCondition().setFinished(Boolean.FALSE);
        this.getCondition().setAccounting(Boolean.FALSE);
        this.getCondition().setItemcd(null);
        this.getCondition().setPayment(null);
        this.getCondition().setAppoint(Boolean.TRUE);
        this.getCondition().setWorked(Boolean.FALSE);
        this.getCondition().setTurnStart(null);
        this.getCondition().setTurnEnd(null);
        this.getCondition().setCancel(Boolean.FALSE);
        this.getCondition().setOnhold(Boolean.FALSE);
        this.getCondition().setAddDateStart(null);
        this.getCondition().setAddDateEnd(null);
        this.getCondition().setUpdateDateStart(null);
        this.getCondition().setUpdateDateEnd(null);
        this.getPagination().setPage(0);
        this.setPageSize(this.getPagination().getPageSize());
        recreateModel();
        return "/item/List?faces-redirect=true";
    }

    public String workedList() {
        this.getCondition().setKeyword(null);
        this.getCondition().setCustomer(null);
        this.getCondition().setUser(null);
        this.getCondition().setFinished(Boolean.FALSE);
        this.getCondition().setAccounting(Boolean.FALSE);
        this.getCondition().setItemcd(null);
        this.getCondition().setPayment(null);
        this.getCondition().setAppoint(Boolean.TRUE);
        this.getCondition().setWorked(Boolean.TRUE);
        this.getCondition().setTurnStart(null);
        this.getCondition().setTurnEnd(null);
        this.getCondition().setCancel(Boolean.FALSE);
        this.getCondition().setOnhold(Boolean.FALSE);
        this.getCondition().setAddDateStart(null);
        this.getCondition().setAddDateEnd(null);
        this.getCondition().setUpdateDateStart(null);
        this.getCondition().setUpdateDateEnd(null);
        this.getPagination().setPage(0);
        this.setPageSize(this.getPagination().getPageSize());
        recreateModel();
        return "/item/List?faces-redirect=true";
    }

    public String notWorkReportList(int days) {
        this.getCondition().setKeyword(null);
        this.getCondition().setCustomer(null);
        this.getCondition().setUser(null);
        this.getCondition().setFinished(Boolean.FALSE);
        this.getCondition().setAccounting(Boolean.FALSE);
        this.getCondition().setItemcd(null);
        this.getCondition().setPayment(null);
        this.getCondition().setAppoint(Boolean.TRUE);
        this.getCondition().setWorked(Boolean.FALSE);
        Calendar cal = GregorianCalendar.getInstance();
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.add(Calendar.DAY_OF_MONTH, -1 * days);
        this.getCondition().setTurnStart(cal.getTime());
        this.getCondition().setTurnEnd(cal.getTime());
        this.getCondition().setCancel(Boolean.FALSE);
        this.getCondition().setOnhold(Boolean.FALSE);
        this.getCondition().setAddDateStart(null);
        this.getCondition().setAddDateEnd(null);
        this.getCondition().setUpdateDateStart(null);
        this.getCondition().setUpdateDateEnd(null);
        this.getPagination().setPage(0);
        this.setPageSize(this.getPagination().getPageSize());
        recreateModel();
        return "/item/List?faces-redirect=true";
    }

    public String notWorkReportAfter1WeekList() {
        this.getCondition().setKeyword(null);
        this.getCondition().setCustomer(null);
        this.getCondition().setUser(null);
        this.getCondition().setFinished(Boolean.FALSE);
        this.getCondition().setAccounting(Boolean.FALSE);
        this.getCondition().setItemcd(null);
        this.getCondition().setPayment(null);
        this.getCondition().setAppoint(Boolean.TRUE);
        this.getCondition().setWorked(Boolean.FALSE);
        Calendar cal = GregorianCalendar.getInstance();
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.add(Calendar.DAY_OF_MONTH, -7);
        this.getCondition().setTurnStart(null);
        this.getCondition().setTurnEnd(cal.getTime());
        this.getCondition().setCancel(Boolean.FALSE);
        this.getCondition().setOnhold(Boolean.FALSE);
        this.getCondition().setAddDateStart(null);
        this.getCondition().setAddDateEnd(null);
        this.getCondition().setUpdateDateStart(null);
        this.getCondition().setUpdateDateEnd(null);
        this.getPagination().setPage(0);
        this.setPageSize(this.getPagination().getPageSize());
        recreateModel();
        return "/item/List?faces-redirect=true";
    }

    public String accountingList() {
        this.getCondition().setKeyword(null);
        this.getCondition().setCustomer(null);
        this.getCondition().setUser(null);
        this.getCondition().setFinished(null);
        this.getCondition().setAccounting(Boolean.FALSE);
        this.getCondition().setItemcd(null);
        this.getCondition().setPayment(null);
        this.getCondition().setAppoint(null);
        this.getCondition().setWorked(null);
        this.getCondition().setTurnStart(null);
        this.getCondition().setTurnEnd(null);
        this.getCondition().setCancel(null);
        this.getCondition().setOnhold(null);
        this.getCondition().setAddDateStart(null);
        this.getCondition().setAddDateEnd(null);
        this.getCondition().setUpdateDateStart(null);
        this.getCondition().setUpdateDateEnd(null);
        this.getPagination().setPageSize(100);
        this.getPagination().setPage(0);
        this.setPageSize(this.getPageSizeList().get(this.getPageSizeList().size() - 1));
        recreateModel();
        return "/item/List?faces-redirect=true";
    }

    public String allList() {
        this.getCondition().setKeyword(null);
        this.getCondition().setCustomer(null);
        this.getCondition().setUser(null);
        this.getCondition().setFinished(null);
        this.getCondition().setAccounting(null);
        this.getCondition().setItemcd(null);
        this.getCondition().setPayment(null);
        this.getCondition().setAppoint(null);
        this.getCondition().setWorked(null);
        this.getCondition().setTurnStart(null);
        this.getCondition().setTurnEnd(null);
        this.getCondition().setCancel(null);
        this.getCondition().setOnhold(null);
        this.getCondition().setAddDateStart(null);
        this.getCondition().setAddDateEnd(null);
        this.getCondition().setUpdateDateStart(null);
        this.getCondition().setUpdateDateEnd(null);
        this.getPagination().setPage(0);
        this.setPageSize(this.getPagination().getPageSize());
        recreateModel();
        return "/item/List?faces-redirect=true";
    }

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareView() {
        current = (Item) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Item();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            this.current.setCancel(false);
            this.current.setDirectpayment(false);
            this.current.setFinished(false);
            this.current.setOnhold(false);
            this.current.setWorked(false);
            this.current.setAdddate(new Date());
            this.current.setAddcode(this.loginController.getLoginUser().getUserid());
            this.current.setAddprogram(this.getClass().getName());
            this.current.setUpdatedate(new Date());
            this.current.setUpdatecode(this.loginController.getLoginUser().getUserid());
            this.current.setUpdateprogram(this.getClass().getName());
            this.current.setValidrow(true);

            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ItemCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String createItemSchedule() {
        try {
            this.schedule4Create.setItemno(this.current.getItemcd());
            this.schedule4Create.setOwnerid(this.loginController.getLoginUser().getUserid());
            this.schedule4Create.setUserid(this.condition.getUser());
            this.schedule4Create.setNote(this.current.getDetail());
            this.schedule4Create.setTitle(this.current.getCustomerid().getCustomername());
            this.schedule4Create.setOpen(false);
            this.schedule4Create.setTel("__");
            this.schedule4Create.setState("");
            this.schedule4Create.setAddress("");
            this.schedule4Create.setCustomer("");
            this.schedule4Create.setStatein(false);
            this.schedule4Create.setStateout(false);
            this.schedule4Create.setWorkmemo("");
            this.schedule4Create.setAddcode(this.loginController.getLoginUser().getUserid());
            this.schedule4Create.setAddprogram(this.getClass().getName());
            this.schedule4Create.setAdddate(new Date());
            this.schedule4Create.setUpdatecode(this.loginController.getLoginUser().getUserid());
            this.schedule4Create.setUpdateprogram(this.getClass().getName());
            this.schedule4Create.setUpdatedate(new Date());
            this.schedule4Create.setValidrow(true);

            this.current.setUserid(this.condition.getUser());
            this.current.setScheid(this.schedule4Create);
            this.current.setAppoint(true);
            this.current.setCancel(false);
            this.current.setDirectpayment(false);
            this.current.setFinished(false);
            this.current.setOnhold(false);
            this.current.setWorked(false);
            this.current.setAdddate(new Date());
            this.current.setAddcode(this.loginController.getLoginUser().getUserid());
            this.current.setAddprogram(this.getClass().getName());
            this.current.setUpdatedate(new Date());
            this.current.setUpdatecode(this.loginController.getLoginUser().getUserid());
            this.current.setUpdateprogram(this.getClass().getName());
            this.current.setValidrow(true);

            this.scheduleController.create();
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ItemCreated"));
            this.scheduleController.prepareCreate();
            this.schedule4Create = this.scheduleController.getSelected();
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }
    
    public String prepareEdit() {
        current = (Item) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ItemUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Item) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    public String getRetCode() {
        return "\\n";
    }

    public String getBr() {
        return "%0D%0A";
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ItemDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count(this.getCondition());
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}, this.getCondition()).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    public String orderByAdddateAsc() {
        this.condition.setAsc(Boolean.TRUE);
        this.condition.setOrderBy(Item_.adddate);
        this.getPagination().setPage(0);
        recreateModel();
        return "List";
    }

    public String orderByAdddateDesc() {
        this.condition.setAsc(Boolean.FALSE);
        this.condition.setOrderBy(Item_.adddate);
        this.getPagination().setPage(0);
        recreateModel();
        return "List";
    }

    public String orderByVisitdateAsc() {
        this.condition.setAsc(Boolean.TRUE);
        this.condition.setOrderBy(Schedule_.datefrom);
        this.getPagination().setPage(0);
        recreateModel();
        return "List";
    }

    public String orderByVisitdateDesc() {
        this.condition.setAsc(Boolean.FALSE);
        this.condition.setOrderBy(Schedule_.datefrom);
        this.getPagination().setPage(0);
        recreateModel();
        return "List";
    }

    public String orderByUpdatedateAsc() {
        this.condition.setAsc(Boolean.TRUE);
        this.condition.setOrderBy(Item_.updatedate);
        this.getPagination().setPage(0);
        recreateModel();
        return "List";
    }

    public String orderByUpdatedateDesc() {
        this.condition.setAsc(Boolean.FALSE);
        this.condition.setOrderBy(Item_.updatedate);
        this.getPagination().setPage(0);
        recreateModel();
        return "List";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(this.getCondition()), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(this.getCondition()), true);
    }

    public Item getItem(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = Item.class)
    public static class ItemControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ItemController controller = (ItemController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "itemController");
            return controller.getItem(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Item) {
                Item o = (Item) object;
                return getStringKey(o.getItemid());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Item.class.getName());
            }
        }

    }

}
