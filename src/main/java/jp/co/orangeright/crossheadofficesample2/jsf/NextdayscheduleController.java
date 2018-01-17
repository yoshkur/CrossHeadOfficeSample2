package jp.co.orangeright.crossheadofficesample2.jsf;

import jp.co.orangeright.crossheadofficesample2.entity.Nextdayschedule;
import jp.co.orangeright.crossheadofficesample2.jsf.util.JsfUtil;
import jp.co.orangeright.crossheadofficesample2.jsf.util.PaginationHelper;
import jp.co.orangeright.crossheadofficesample2.ejb.NextdayscheduleFacade;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
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
import jp.co.orangeright.crossheadofficesample2.ejb.ItemFacade;
import jp.co.orangeright.crossheadofficesample2.entity.Item;
import jp.co.orangeright.crossheadofficesample2.entity.UserM;
import jp.co.orangeright.crossheadofficesample2.ejb.UserMFacade;
import jp.co.orangeright.crossheadofficesample2.entity.Nextdayschedule_;
import jp.co.orangeright.crossheadofficesample2.jsf.item.ItemSearchCondition;
import jp.co.orangeright.crossheadofficesample2.jsf.nextdayschedule.NextdayscheduleSearchCondition;

@Named("nextdayscheduleController")
@SessionScoped
public class NextdayscheduleController implements Serializable {

    private Nextdayschedule current;
    private DataModel items = null;
    @EJB
    private NextdayscheduleFacade ejbFacade;
    @EJB
    private UserMFacade ejbUser;
    @EJB
    private ItemFacade ejbItem;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    private NextdayscheduleSearchCondition condition;
    @Inject
    private LoginController loginController;
    @Inject
    transient private OrangeRightMailBean mailBean;

    public NextdayscheduleController() {
    }

    public Nextdayschedule getSelected() {
        if (current == null) {
            current = new Nextdayschedule();
            selectedItemIndex = -1;
        }
        return current;
    }

    private NextdayscheduleFacade getFacade() {
        return ejbFacade;
    }

    private NextdayscheduleSearchCondition getCondition() {
        if (this.condition == null) {
            this.condition = new NextdayscheduleSearchCondition();
            this.condition.setAsc(Boolean.FALSE);
            this.condition.setOrderBy(Nextdayschedule_.adddate);
        }
        return condition;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(20) {

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

    public String getUserName(String userid) {
        UserM user = this.ejbUser.find(userid);
        return user.getUsername();
    }

    public String prepareList() {
        this.getCondition().setReturnflg(null);
        this.getPagination().setPage(0);
        recreateModel();
        return "/nextdayschedule/List?faces-redirect=true";
    }

    public String noReplyList() {
        this.getCondition().setReturnflg(false);
        this.getPagination().setPage(0);
        recreateModel();
        return "/nextdayschedule/NoReplyList?faces-redirect=true";
    }

    public String changeReturnflg() {
        this.current = (Nextdayschedule) getItems().getRowData();
        this.current.setReturnflg(Boolean.TRUE);
        this.current.setReturnmessage("本部にて処理。");
        this.current.setUpdatecode(this.loginController.getLoginUser().getUserid());
        this.current.setUpdatedate(new Date());
        this.current.setUpdateprogram(this.getClass().getName());
        this.update();
        return this.noReplyList();
    }

    public String sendNextdayscheduleMessage() {
        ItemSearchCondition conditionTemp = new ItemSearchCondition();
        conditionTemp.setKeyword(null);
        conditionTemp.setCustomer(null);
        conditionTemp.setUser(null);
        conditionTemp.setFinished(Boolean.FALSE);
        conditionTemp.setAccounting(Boolean.FALSE);
        conditionTemp.setItemcd(null);
        conditionTemp.setPayment(null);
        conditionTemp.setAppoint(Boolean.TRUE);
        conditionTemp.setWorked(Boolean.FALSE);
        Calendar cal = GregorianCalendar.getInstance();
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.add(Calendar.DAY_OF_MONTH, 1);
        conditionTemp.setTurnStart(cal.getTime());
        conditionTemp.setTurnEnd(cal.getTime());
        conditionTemp.setCancel(Boolean.FALSE);
        conditionTemp.setOnhold(Boolean.FALSE);
        conditionTemp.setAddDateStart(null);
        conditionTemp.setAddDateEnd(null);
        conditionTemp.setUpdateDateStart(null);
        conditionTemp.setUpdateDateEnd(null);
        List<Item> itemList = this.ejbItem.findAll(conditionTemp);
        Set<UserM> userSet = new HashSet<>();
        for (Item i : itemList) {
            userSet.add(i.getUserid());
        }
        this.mailBean.setSub("案件の確認");
        this.mailBean.setBody("明日、案件の予定が入っています。" + System.getProperty("line.separator")
                + "Crossにログインして案件の予定を確認し、" + System.getProperty("line.separator")
                + "「翌日案件予定確認」から報告してください。" + System.getProperty("line.separator")
                + "" + System.getProperty("line.separator")
                + "");
        String messege = "案件管理にてスケジュールをご確認頂いた後、その旨を返信してください。";
        for (UserM user : userSet) {
            this.mailBean.setTo(user.getMobilemail());
            this.prepareCreate();
            this.current.setSendto(user.getUserid());
            this.current.setSendmessagetitle(this.mailBean.getSub());
            this.current.setSendmessage(messege);
            this.current.setReturnflg(Boolean.FALSE);
            this.current.setReturnmessage("");
            this.current.setAddcode(this.loginController.getLoginUser().getUserid());
            this.current.setAdddate(new Date());
            this.current.setAddprogram(this.getClass().getName());
            this.current.setUpdatecode(this.loginController.getLoginUser().getUserid());
            this.current.setUpdatedate(new Date());
            this.current.setUpdateprogram(this.getClass().getName());
            this.create();
        }
        return "/faces/index?faces-redirect=true";
    }

    public String prepareView() {
        current = (Nextdayschedule) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "/nextdayschedule/View?faces-redirect=true";
    }

    public String prepareCreate() {
        current = new Nextdayschedule();
        selectedItemIndex = -1;
        return "/nextdayschedule/Create?faces-redirect=true";
    }

    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("NextdayscheduleCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (Nextdayschedule) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "/nextdayschedule/Edit?faces-redirect=true";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("NextdayscheduleUpdated"));
            return "/nextdayschedule/View?faces-redirect=true";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Nextdayschedule) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "/nextdayschedule/List?faces-redirect=true";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "/nextdayschedule/View?faces-redirect=true";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "/nextdayschedule/List?faces-redirect=true";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("NextdayscheduleDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
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
        return "/nextdayschedule/List?faces-redirect=true";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "/nextdayschedule/List?faces-redirect=true";
    }

    public String nextNoReply() {
        getPagination().nextPage();
        recreateModel();
        return "/nextdayschedule/NoReplyList?faces-redirect=true";
    }

    public String previousNoReply() {
        getPagination().previousPage();
        recreateModel();
        return "/nextdayschedule/NoReplyList?faces-redirect=true";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    public Nextdayschedule getNextdayschedule(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = Nextdayschedule.class)
    public static class NextdayscheduleControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            NextdayscheduleController controller = (NextdayscheduleController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "nextdayscheduleController");
            return controller.getNextdayschedule(getKey(value));
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
            if (object instanceof Nextdayschedule) {
                Nextdayschedule o = (Nextdayschedule) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Nextdayschedule.class.getName());
            }
        }

    }

}
